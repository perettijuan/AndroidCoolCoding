package com.jpp.myfirstkmm.android

import android.os.Bundle
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

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    private var disposables = CompositeDisposable()

    private val api = ApiImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()

        api.flowMe(3, true)
            .subscribeOn(ioScheduler) // Switching to a background thread is necessary
            .observeOn(mainScheduler)
            .subscribe(object : SingleObserver<Api.Result> {
                override fun onError(error: Throwable) {
                    tv.text = "Something failed"
                }

                override fun onSubscribe(disposable: Disposable) {
                    disposables.add(disposable)
                }

                override fun onSuccess(value: Api.Result) {
                    tv.text = value.message
                }

            })
    }
}
