package com.mvp.pattern.ui.view.impl;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mvp.pattern.R;
import com.mvp.pattern.ui.presenter.IMainScreenPresenter;
import com.mvp.pattern.ui.presenter.impl.MainScreenPresenter;
import com.mvp.pattern.ui.view.IMainScreenView;

/**
 * Activity that shows the Main Screen in the application. <br>
 * It implements {@link IMainScreenView} in order to respect the signature given
 * by this interface.
 * 
 * @author Juan P. Peretti 
 * 
 */

public class MainScreen extends Activity implements IMainScreenView, OnClickListener {


    private Button btnA;
    private Button btnB;
    private TextView txtResult;

    // The presenter interface used by this view.
    private IMainScreenPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        btnA = (Button) findViewById(R.id.btn_a);
        btnB = (Button) findViewById(R.id.btn_b);
        txtResult = (TextView) findViewById(R.id.txt_result);
        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);

        mPresenter = new MainScreenPresenter(this);
        mPresenter.onCreate();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onStop();
        }
    }

    @Override
    public void presentButtonAResponse() {
        txtResult.setText("Button A pressed");
    }

    @Override
    public void presentButtonBResponse() {
        txtResult.setText("Button B pressed");
    }

    @Override
    public void presentResultWithMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.btn_a:
            if (mPresenter != null) {
                mPresenter.onButtonAPressed();
            }
            break;
        case R.id.btn_b:
            if (mPresenter != null) {
                mPresenter.onButtonBPressed();
            }
            break;

        }
    }

}
