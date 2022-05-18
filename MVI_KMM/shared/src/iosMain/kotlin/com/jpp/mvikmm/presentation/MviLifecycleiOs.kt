package com.jpp.mvikmm.presentation

import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.subject.publish.PublishSubject

class MviLifecycleiOs : MviLifecycle {

    private val _events = PublishSubject<MviLifecycle.Event>()
    override val events: Observable<MviLifecycle.Event> = _events

    fun onCreated() {
        _events.onNext(MviLifecycle.Event.ON_VIEW_CREATED)
    }

    fun onAppear() {
        _events.onNext(MviLifecycle.Event.ON_VIEW_READY)
    }

    fun onDisappear() {
        _events.onNext(MviLifecycle.Event.ON_VIEW_UNREADY)
    }

    fun onDestroyed() {
        _events.onNext(MviLifecycle.Event.ON_VIEW_DESTROYED)
    }
}