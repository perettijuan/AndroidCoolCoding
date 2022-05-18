package com.jpp.mvikmm.android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.badoo.reaktive.disposable.CompositeDisposable
import com.badoo.reaktive.disposable.Disposable
import com.badoo.reaktive.observable.ObservableObserver
import com.jpp.mvikmm.presentation.Presenter
import com.jpp.mvikmm.presentation.UiState
import com.jpp.mvikmm.presentation.UserIntent

class MainFragment : Fragment() {

    private lateinit var presenter: Presenter
    private lateinit var userIntent: UserIntent

    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    private lateinit var button: Button

    private val disposables = CompositeDisposable()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = ExampleDi.presenter
        userIntent = ExampleDi.userIntent
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.main_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // init views
        progressBar = view.findViewById(R.id.loading)
        textView = view.findViewById(R.id.content_text)
        button = view.findViewById(R.id.content_button)
        button.setOnClickListener {
            userIntent.onButtonPressed()
        }

        // bind states
        presenter.uiState.subscribe(object : ObservableObserver<UiState> {
            override fun onComplete() {
                TODO("Not yet implemented")
            }

            override fun onError(error: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onSubscribe(disposable: Disposable) {
                disposables.add(disposable)
            }

            override fun onNext(value: UiState) {
                renderUiState(value)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        presenter.onReady()
    }

    override fun onPause() {
        presenter.onUnready()
        super.onPause()
    }

    private fun renderUiState(state: UiState) {
        progressBar.setVisible(state.loadingVisible)
        textView.text = state.content.text
        textView.setVisible(state.content.isVisible)
        button.setVisible(state.content.isVisible)
    }

    private fun View.setVisible(asVisible: Boolean) {
        visibility = if (asVisible) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}