package com.mvp.pattern.ui.presenter.impl;

import com.mvp.pattern.ui.presenter.IMainScreenPresenter;
import com.mvp.pattern.ui.view.IMainScreenView;

/**
 * Presenter used by the Main Screen
 * 
 * @author Juan P. Peretti 
 * 
 */
public class MainScreenPresenter implements IMainScreenPresenter {


    private IMainScreenView mView;

    public MainScreenPresenter(IMainScreenView view) {
        mView = view;
    }

    @Override
    public void onCreate() {
        // In blank on purpose
    }

    @Override
    public void onResume() {
        if (mView != null) {
            mView.presentResultWithMessage("On Resume!!!");
        }
    }

    @Override
    public void onPause() {
        // In blank on purpose
    }

    @Override
    public void onStop() {
        // In blank on purpose
    }

    @Override
    public void onButtonAPressed() {
        if (mView != null) {
            mView.presentButtonAResponse();
        }
    }

    @Override
    public void onButtonBPressed() {
        if (mView != null) {
            mView.presentButtonBResponse();
        }
    }

}
