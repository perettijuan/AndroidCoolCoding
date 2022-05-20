package com.jpp.mvikmm.android

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
import com.jpp.mvikmm.presentation.ViewState
import com.jpp.mvikmm.presentation.UserIntent

class MainFragment : Fragment() {

    lateinit var presenter: Presenter
    lateinit var userIntent: UserIntent

    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    private lateinit var button: Button

    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.main_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Inject
        ExampleDi.inject(this)

        // init views
        progressBar = view.findViewById(R.id.loading)
        textView = view.findViewById(R.id.content_text)
        button = view.findViewById(R.id.content_button)
        button.setOnClickListener {
            userIntent.onButtonPressed()
        }

        // bind states
        presenter.viewState.subscribe(object : ObservableObserver<ViewState> {
            override fun onComplete() {
                TODO("Not yet implemented")
            }

            override fun onError(error: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onSubscribe(disposable: Disposable) {
                disposables.add(disposable)
            }

            override fun onNext(value: ViewState) {
                renderUiState(value)
            }
        })
    }

    private fun renderUiState(state: ViewState) {
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