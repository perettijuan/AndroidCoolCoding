package com.mvp.pattern.ui.presenter;

/**
 * Presenter interface used to define the signature of the Presenter used in the
 * main screen of the application.
 * 
 * @author Juan P. Peretti
 * 
 */
public interface IMainScreenPresenter {


    /**
     * Intercepts the onCreate callback in the Fragment/Activity lifecycle.
     */
    public void onCreate();

    /**
     * Intercepts the onResume callback in the Fragment/Activity lifecycle.
     */
    public void onResume();

    /**
     * Intercepts the onPause callback in the Fragment/Activity lifecycle.
     */
    public void onPause();

    /**
     * Intercepts the onStop callback in the Fragment/Activity lifecycle.
     */
    public void onStop();

    /**
     * Called when the button A is pressed.
     */
    public void onButtonAPressed();

    /**
     * Called when the button B is pressed.
     */
    public void onButtonBPressed();

}
