package com.jpp.mvikmm.presentation

import com.badoo.reaktive.disposable.CompositeDisposable
import com.badoo.reaktive.disposable.Disposable
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.ObservableObserver
import com.badoo.reaktive.subject.publish.PublishSubject
import com.jpp.mvikmm.model.MessageRepository

/**
 * Presenter implementation. It receives an instance of [MessageRepository] as input
 * representation of the model state.
 */
class PresenterImpl(private val repository: MessageRepository) : Presenter {

    private val disposables = CompositeDisposable()

    /*
     * This backing field is optional. Sometimes, the Presenter
     * must build the new state of the build based on a combination
     * of the model state and the existing view state.
     */
    private var currentState: UiState = UiState()
        set(value) {
            field = value
            _uiState.onNext(value)
        }
    private val _uiState = PublishSubject<UiState>()
    override val uiState: Observable<UiState> = _uiState

    init {
        repository.messagesState.subscribe(
            object : ObservableObserver<MessageRepository.State> {
                override fun onComplete() {
                    // Do nothing
                }

                override fun onError(error: Throwable) {
                    val newContent = currentState.content.copy(
                        isVisible = true,
                        text = "Something went wrong"
                    )
                    val newState = currentState.copy(
                        loadingVisible = false,
                        content = newContent
                    )
                    currentState = newState
                }

                override fun onSubscribe(disposable: Disposable) {
                    disposables.add(disposable)
                }

                override fun onNext(value: MessageRepository.State) {
                    currentState = transformModelIntoUiState(value)
                }
            }
        )
    }

    override fun onReady() {
        // This is breaking unidirectional data flow
        repository.produceMessage()
    }

    override fun onUnready() {
        disposables.dispose()
    }

    private fun transformModelIntoUiState(modelState: MessageRepository.State): UiState {
        return when (modelState) {
            is MessageRepository.State.Loading -> UiState(
                loadingVisible = true,
                content = UiState.Content(isVisible = false)
            )
            is MessageRepository.State.Loaded -> UiState(
                loadingVisible = false,
                content = UiState.Content(
                    isVisible = true,
                    text = modelState.message.text
                )
            )
            is MessageRepository.State.FailedToLoad -> UiState(
                loadingVisible = false,
                content = UiState.Content(
                    isVisible = true,
                    text = "Something went wrong, we failed to load the message"
                )
            )
        }
    }
}