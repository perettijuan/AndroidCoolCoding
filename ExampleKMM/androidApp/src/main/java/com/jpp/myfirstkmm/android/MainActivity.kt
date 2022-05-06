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
            kotlin.runCatching {
                api.executeApi()
            }.onSuccess { result ->
                tv.text = result
            }.onFailure {
                tv.text = "Something failed"
            }
        }

    }
}
