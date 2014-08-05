package com.mvp.pattern.ui.view;

/**
 * Interface that defines the IView signature for the main screen in the
 * application.
 * 
 * @author Juan P. Peretti 
 * 
 */
public interface IMainScreenView {


    /**
     * Called when the response to button A needs to be presented.
     */
    public void presentButtonAResponse();

    /**
     * Called when the response to button B needs to be presented.
     */
    public void presentButtonBResponse();

    /**
     * Called when a result needs to be shown with the given message.
     */
    public void presentResultWithMessage(String message);

}
