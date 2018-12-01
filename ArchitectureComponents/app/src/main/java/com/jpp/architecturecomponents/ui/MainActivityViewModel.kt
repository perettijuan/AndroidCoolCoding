package com.jpp.architecturecomponents.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jpp.architecturecomponents.domain.GetItemsUseCase
import kotlinx.coroutines.*

import kotlinx.coroutines.android.Main
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * ViewModel for the MainActivity.
 * This ViewModel reflects at all time the state in which the MainActivity UI is, by exposing the
 * [MainActivityViewState] that represents such state.
 *
 * Exposes only two methods that can be used by the consumer, following the MVI (Model-View-Intent) architecture:
 *  - [bindViewState] exposes a LiveData object that holds the current [MainActivityState]. When a
 *    new state needs to be pushed, the LiveData object is updated with the new data.
 *  - [onIntent] is called when the MainActivity detects that the user has acted upon the UI and
 *    the model needs to be updated based on that action. The ViewModel takes the input action
 *    and executes the corresponding use case in a background thread (see the section about coroutines).
 *    When the use case is done a new state is emitted (it doesn't update the current state in order
 *    to achieve immutability) using the exposed LiveData object.
 *
 * Coroutines + multithreading
 * This ViewModel is a CoroutineScope (check https://medium.com/@elizarov/structured-concurrency-722d765aa952 for
 * more information), meaning that all the code that is executed within a coroutine, will use the context
 * provided by this scope.
 * The scope in which the code falls back is the UI scope, meaning that all the
 * work that needs to be executed in the background needs to explicitly use a different Dispatcher.
 * When the ViewModel is cleared, the context is killed - by cancelling the Job that gives live
 * to the context - what ends up in cancelling any work that is being executed in the background
 * coroutine.
 */
class MainActivityViewModel @Inject constructor(private val useCase: GetItemsUseCase): ViewModel(), CoroutineScope {

    /*
     * This Job represents the work that it is being done in the
     * background. It will be cancelled as soon as the ViewModel is
     * destroyed, meaning that any work being executed in the
     * context of the coroutine - the background - will be
     * automatically cancelled.
     */
    private val currentJob = Job()

    /*
     * This is the scope in which the coroutines are executed. This indicates that, as the scope
     * is alive, any subclass of this can execute coroutines using the scope. When the scope
     * is killed, all possible work being executed is automatically cancelled.
     *
     * It is based in the ViewModel's currentJob and in the UI context:
     *  - the ViewModel's currentJob means that when the job is cancelled, the scope is killed.
     *  - the UI context indicates that any code that it is not specifically wrapped into another
     *    context, will execute in the UI thread, forcing us to be explicit when we're writing
     *    code that might take too long to finish.
     *
     * What is the plus (+) operator? -> https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/plus.html
     */
     override val coroutineContext: CoroutineContext
        get() = currentJob + Dispatchers.Main

    /*
     * The mapper that maps domain objects into UI objects.
     */
    private val mapper = MainUiMapper()


    /*
     * This LiveData object represents the current state of the MainActivity at any given time.
     */
    private val viewState by lazy { MutableLiveData<MainActivityViewState>().apply { value = MainActivityViewState.Loading } }

    /*
     * Reduces the state to ensure that we don't show duplicated states.
     */
    private val mainStateReducer = MainActivityViewStateReducer()

    /**
     * Binds the current view state that is being shown by the MainActivity.
     */
    fun bindViewState(): LiveData<MainActivityViewState> = viewState


    /**
     * Executes the [MainActivityIntent] requested. The result is posted
     * asynchronously to the LiveData provided by [bindViewState].
     */
    fun onIntent(mainIntent: MainActivityIntent) {
        launch {
            when (mainIntent) {
                MainActivityIntent.LoadMoreItems -> viewState.value = withContext(Dispatchers.Default) { loadMoreItems() }
            }
        }
    }

    /**
     * Execute the use case and return the result of the execution.
     */
    private fun loadMoreItems(): MainActivityViewState {
        val bottom = if (viewState.value is MainActivityViewState.Items) (viewState.value as MainActivityViewState.Items).items.size else 0
        val top = bottom + 100
        return useCase(bottom, top)
                .map { mapper(it) }
                .let { mainStateReducer(viewState.value, MainActivityViewState.Items(it)) }
    }

    /*
     * This method is called when the owner of the ViewModel is destroyed in order
     * to clean up resources.
     *
     * Cancelling a coroutine in Kotlin automatically kills all the work that is being
     * done by that coroutine. Therefore, cancelling [currentJob] ensures that the work
     * in the coroutine is terminated.
     * Even more, cancelling [currentJob] will kill the scope of the ViewModel, meaning
     * that all the work being executed in any coroutine launched from the ViewModel
     * will be cancelled.
     */
    override fun onCleared() {
        currentJob.cancel()
        Log.d("ViewModel", "Scope is active $isActive")
        super.onCleared()
    }
}