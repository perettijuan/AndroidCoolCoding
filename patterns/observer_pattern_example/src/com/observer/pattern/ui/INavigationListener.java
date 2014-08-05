package com.observer.pattern.ui;

public interface INavigationListener {


    /**
     * Called whenever that a new Section needs to be shown.
     * 
     * @param section
     *            - the new Section to be shown.
     */
    public void showSection(Section section);

}