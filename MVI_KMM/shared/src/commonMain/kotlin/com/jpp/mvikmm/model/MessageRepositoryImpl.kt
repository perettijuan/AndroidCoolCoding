package com.jpp.mvikmm.model

import com.badoo.reaktive.disposable.Disposable
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.*
import com.badoo.reaktive.subject.publish.PublishSubject
import com.jpp.mvikmm.datasource.MessageDataSource

class MessageRepositoryImpl(private val dataSource: MessageDataSource) : MessageRepository {

    private val _state = PublishSubject<MessageRepository.State>()
    override val messagesState: Observable<MessageRepository.State> = _state

    override fun produceMessage() {
        _state.onNext(MessageRepository.State.Loading)
        singleFromFunction {
            dataSource.nextMessage()
        }
            .delay(1000, mainScheduler)
            .threadLocal()
            // When this is commented out, iOS crashes
//            .subscribeOn(ioScheduler)
//            .observeOn(mainScheduler)
            .subscribe(object : SingleObserver<String> {
                override fun onError(error: Throwable) {
                    _state.onNext(MessageRepository.State.FailedToLoad("Chain failed"))
                }

                override fun onSubscribe(disposable: Disposable) {
                    // Do nothing
                }

                override fun onSuccess(value: String) {
                    _state.onNext(MessageRepository.State.Loaded(Message(text = value)))
                }
            })
    }
}