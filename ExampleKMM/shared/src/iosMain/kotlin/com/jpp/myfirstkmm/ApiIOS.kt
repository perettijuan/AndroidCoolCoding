package com.jpp.myfirstkmm

import com.badoo.reaktive.coroutinesinterop.singleFromCoroutine
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.SingleWrapper
import com.badoo.reaktive.single.observeOn
import com.badoo.reaktive.single.subscribeOn
import com.badoo.reaktive.single.wrap
import com.jpp.myfirstkmm.api.Api

class ApiIOS(private val api: Api) {
//    fun flowMe(count: Int, succeed: Boolean): SingleWrapper<Api.Result> {
//        return singleFromCoroutine { api.executeApi(true, 1) }
//            .subscribeOn(ioScheduler) // Switching to a background thread is necessary
//            .observeOn(mainScheduler)
//            .wrap()
//    }
}