package com.jpp.mvikmm.presentation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.subject.publish.PublishSubject

class AndroidMviLifecycle(lifecycle: Lifecycle) : MviLifecycle, LifecycleEventObserver {

    init {
        lifecycle.addObserver(this)
    }

    private val _events = PublishSubject<MviLifecycle.Event>()
    override val events: Observable<MviLifecycle.Event> = _events

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        val lifecycleEvent = when (event) {
            Lifecycle.Event.ON_CREATE -> MviLifecycle.Event.ON_VIEW_CREATED
            Lifecycle.Event.ON_START -> MviLifecycle.Event.ON_VIEW_READY
            Lifecycle.Event.ON_STOP -> MviLifecycle.Event.ON_VIEW_UNREADY
            Lifecycle.Event.ON_DESTROY -> MviLifecycle.Event.ON_VIEW_DESTROYED
            else -> null
        }

        if (lifecycleEvent != null) {
            _events.onNext(lifecycleEvent)
        }
    }
}