package com.observer.pattern.services;

/**
 * Listener interface for monitoring action status changes.<br>
 * All clients interested in the state changes of {@link MonitoredAction} need
 * to implement.
 * 
 * @author Juan P. Peretti 
 * 
 * @param <T>
 */
public interface IActionStatusListener<T> {


    /**
     * Method called when a {@link State} on a {@link MonitoredAction} is
     * changed.
     * 
     * @param action
     *            - the {@link MonitoredAction} that has change of state.
     * @param actionStatus
     *            - the current {@link State} for the action. It can be
     *            interpreted as the new state of the action.
     * @param extraData
     *            - some extra data related to the update.
     */
    void actionStatusChange(MonitoredAction action,
            ActionController.State actionStatus,
            T extraData);
}
