package com.jpp.mvikmm.presentation

import com.badoo.reaktive.disposable.CompositeDisposable
import com.badoo.reaktive.disposable.Disposable
import com.badoo.reaktive.observable.ObservableObserver
import com.jpp.mvikmm.model.MessageRepository

class UserIntentImpl(
    private val repository: MessageRepository,
    lifecycle: MviLifecycle
) : UserIntent {

    private val disposables = CompositeDisposable()

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
                        MviLifecycle.Event.ON_VIEW_READY -> initiateFlow()
                        MviLifecycle.Event.ON_VIEW_UNREADY -> onUnready()
                        else -> Unit // Ignore all other events.
                    }
                }
            }
        )
    }

    override fun onButtonPressed() {
        repository.produceMessage()
    }

    private fun initiateFlow() {
        repository.produceMessage()
    }

    private fun onUnready() {
        disposables.dispose()
    }
}