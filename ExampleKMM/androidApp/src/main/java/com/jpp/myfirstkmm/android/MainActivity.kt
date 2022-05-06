package com.jpp.myfirstkmm.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jpp.myfirstkmm.Greeting
import com.jpp.myfirstkmm.api.Api
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    private val mainScope = MainScope()
    private val api = Api()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()

        mainScope.launch {
            api.state.collect { value ->
                tv.text = value.message
            }
        }
        mainScope.launch {
            try {
                api.flowMe(count = 3, succeed = false)
            } catch (e: Throwable) {
                // TODO this should happen in a Presenter
                tv.text = "OOps"
            }
        }
    }
}
