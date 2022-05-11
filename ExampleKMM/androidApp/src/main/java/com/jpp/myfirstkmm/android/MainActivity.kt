package com.jpp.myfirstkmm.android

import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.badoo.reaktive.disposable.CompositeDisposable
import com.badoo.reaktive.disposable.Disposable
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.SingleObserver
import com.badoo.reaktive.single.observeOn
import com.badoo.reaktive.single.subscribeOn
import com.jpp.myfirstkmm.Greeting
import com.jpp.myfirstkmm.api.Api
import com.jpp.myfirstkmm.api.ApiImpl
import com.jpp.myfirstkmm.api.Logger

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    private var disposables = CompositeDisposable()

    private val logger = object : Logger {
        override fun logThread(message: String) {
            Log.d(
                "JPPLOG",
                "Message is $message in current thread = ${Looper.myLooper() == Looper.getMainLooper()}"
            )
        }
    }
    private val api = ApiImpl(logger)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()

        logger.logThread("flowMe")
        api.flowMe(3, true)
            .subscribe(object : SingleObserver<Api.Result> {
                override fun onError(error: Throwable) {
                    tv.text = "Something failed"
                }

                override fun onSubscribe(disposable: Disposable) {
                    disposables.add(disposable)
                }

                override fun onSuccess(value: Api.Result) {
                    logger.logThread("onSuccess")
                    tv.text = value.message
                }

            })
    }
}
