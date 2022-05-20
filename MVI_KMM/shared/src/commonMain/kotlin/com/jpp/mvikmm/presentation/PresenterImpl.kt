package com.jpp.mvikmm.presentation

import com.badoo.reaktive.disposable.CompositeDisposable
import com.badoo.reaktive.disposable.Disposable
import com.badoo.reaktive.observable.ObservableObserver
import com.badoo.reaktive.observable.ObservableWrapper
import com.badoo.reaktive.observable.wrap
import com.badoo.reaktive.subject.publish.PublishSubject
import com.jpp.mvikmm.model.MessageRepository

/**
 * Presenter implementation. It receives an instance of [MessageRepository] as input
 * representation of the model state.
 */
class PresenterImpl(
    private val repository: MessageRepository,
    lifecycle: MviLifecycle
) : Presenter {

    private val disposables = CompositeDisposable()

    /*
     * This backing field is optional. Sometimes, the Presenter
     * must build the new state of the build based on a combination
     * of the model state and the existing view state.
     */
    private var mCurrentState: ViewState = ViewState()
        set(value) {
            field = value
            _uiState.onNext(value)
        }
    private val _uiState = PublishSubject<ViewState>()
    override val viewState: ObservableWrapper<ViewState> = _uiState.wrap()

    init {
        lifecycle.events.subscribe(
            object : ObservableObserver<MviLifecycle.Event> {
                override fun onComplete() {
                    // Do Nothing
                }

                override fun onError(error: Throwable) {
                    // Not implemented for simplicity
                }

                override fun onSubscribe(disposable: Disposable) {
                    disposables.add(disposable)
                }

                override fun onNext(value: MviLifecycle.Event) {
                    when (value) {
                        MviLifecycle.Event.ON_VIEW_CREATED -> pushInitialState()
                        MviLifecycle.Event.ON_VIEW_UNREADY -> onUnready()
                        else -> Unit // Ignore all other events.
                    }
                }
            }
        )
    }

    private fun onUnready() {
        disposables.dispose()
    }

    private fun pushInitialState() {
        repository.messagesState.subscribe(
            object : ObservableObserver<MessageRepository.State> {
                override fun onComplete() {
                    // Do nothing
                }

                override fun onError(error: Throwable) {
                    val newContent = mCurrentState.content.copy(
                        isVisible = true,
                        text = "Something went wrong"
                    )
                    val newState = mCurrentState.copy(
                        loadingVisible = false,
                        content = newContent
                    )
                    mCurrentState = newState
                }

                override fun onSubscribe(disposable: Disposable) {
                    disposables.add(disposable)
                }

                override fun onNext(value: MessageRepository.State) {
                    mCurrentState = transformModelIntoUiState(value)
                }
            }
        )


        /*
         * Initial state is loading by default, even
         * when the Presenter hasn't initiated the
         * actions.
         */
        mCurrentState = ViewState(
            loadingVisible = true,
            content = ViewState.Content(isVisible = false)
        )
    }

    private fun transformModelIntoUiState(modelState: MessageRepository.State): ViewState {
        return when (modelState) {
            is MessageRepository.State.Loading -> ViewState(
                loadingVisible = true,
                content = ViewState.Content(isVisible = false)
            )
            is MessageRepository.State.Loaded -> ViewState(
                loadingVisible = false,
                content = ViewState.Content(
                    isVisible = true,
                    text = modelState.message.text
                )
            )
            is MessageRepository.State.FailedToLoad -> ViewState(
                loadingVisible = false,
                content = ViewState.Content(
                    isVisible = true,
                    text = "Something went wrong, we failed to load the message"
                )
            )
        }
    }
}