package com.observer.pattern.services;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

/**
 * A controller that will monitor {@link MonitoredAction}s and will notify the
 * changes of the {@link State} of the actions to all registered
 * {@link IActionStatusListener}s
 * 
 * 
 * @author Juan P. Peretti 
 * 
 */
public class ActionController {


    public static ActionController ACTION_CONTROLLER = new ActionController();

    /**
     * All the possible states that an action can assume
     */
    public enum State {
        STATE_REMOVE,
        STATE_NOT_FOUND,
        STATE_DONE,
        STATE_IN_PROGRESS,
        STATE_ERROR
    }

    // Container for all the actions.
    private HashMap<MonitoredAction, State> mMonitoredActions = new HashMap<MonitoredAction, ActionController.State>();

    private HashMap<MonitoredAction, Vector<IActionStatusListener<?>>> mListeners = new HashMap<MonitoredAction, Vector<IActionStatusListener<?>>>();

    /**
     * Default constructor to avoid external instantiation.
     */
    ActionController() {

    }

    /**
     * Changes the status of a given {@link MonitoredAction} and notifies to all
     * the registered listeners about this.
     * 
     * @param action
     *            - the {@link MonitoredAction} to notify about
     * @param newStatus
     *            - the new status to notify.
     * @param extraData
     *            - some extra data that needs to be notified.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    <T> void setActionStatus(MonitoredAction action, State newStatus, T extraData) {
        if (State.STATE_REMOVE.equals(newStatus)) {
            if (mMonitoredActions.containsKey(action)) {
                mMonitoredActions.remove(action);
            }
        } else {
            mMonitoredActions.put(action, newStatus);
            Vector<IActionStatusListener<?>> listeners = mListeners.get(action);
            if (listeners != null) {
                Enumeration<IActionStatusListener<?>> allListeners = listeners.elements();
                while (allListeners.hasMoreElements()) {
                    IActionStatusListener listener = allListeners.nextElement();
                    listener.actionStatusChange(action, newStatus, extraData);
                }
            }

            if (State.STATE_DONE.equals(newStatus)) {
                if (mMonitoredActions.containsKey(action)) {
                    mMonitoredActions.remove(action);
                }
            }
        }
    }

    /**
     * Registers a {@link IActionStatusListener} to listen for an specific
     * {@link MonitoredAction}
     * 
     * @param listener
     *            - the listener to register
     * @param action
     *            - the action to register to.
     */
    public void registerListener(IActionStatusListener<?> listener, MonitoredAction action) {
        if (!mListeners.containsKey(action)) {
            mListeners.put(action, new Vector<IActionStatusListener<?>>());
        }
        Vector<IActionStatusListener<?>> listeners = mListeners.get(action);
        listeners.add(listener);
    }

    /**
     * Registers a {@link IActionStatusListener} to listen for an specific list
     * of {@link MonitoredAction}
     * 
     * @param listener
     *            - the listener to register
     * @param actions
     *            - the list of actions to monitor.
     */
    public void registerListener(IActionStatusListener<?> listener, MonitoredAction... actions) {
        for (MonitoredAction action : actions) {
            registerListener(listener, action);
        }
    }

    /**
     * Adds a new listener for monitoring actions.
     * 
     * @param newListener
     *            - the {@link IActionStatusListener} to register.
     */
    public void registerListener(IActionStatusListener<?> newListener) {
        for (MonitoredAction key : mListeners.keySet()) {
            registerListener(newListener, key);
        }
    }

    /**
     * Unregisters a given {@link IActionStatusListener} for listening for an
     * specific {@link MonitoredAction}
     * 
     * @param listener
     *            - the listener to be unregistered
     * @param action
     *            - the action to unregister from
     * @return - true if it is removed, false any other case.
     */
    public boolean unregisterListener(IActionStatusListener<?> listener, MonitoredAction action) {
        boolean unregistered = false;
        if (mListeners.containsKey(action)) {
            Vector<IActionStatusListener<?>> listeners = mListeners.get(action);
            if (listeners.contains(listener)) {
                unregistered = listeners.remove(listener);
            }
        }
        return unregistered;
    }

    /**
     * Unregisters a given {@link IActionStatusListener} from listening for an
     * specific {@link MonitoredAction} list.
     * 
     * @param listener
     *            - the listener to be unregistered
     * @param actions
     *            - the list of actions to unregister from
     */
    public void unregisterListener(IActionStatusListener<?> listener, MonitoredAction... actions) {
        for (MonitoredAction action : actions) {
            unregisterListener(listener, action);
        }
    }

    /**
     * Unregisters a {@link IActionStatusListener} from all the listeners. The
     * {@link IActionStatusListener} will be no longer notified when a
     * {@link MonitoredAction} changes it status.
     * 
     * @param listener
     *            - the {@link IActionStatusListener} to unregister.
     */
    public void unregisterListener(IActionStatusListener<?> listener) {
        for (MonitoredAction key : mListeners.keySet()) {
            unregisterListener(listener, key);
        }
    }

    /**
     * Returns the state of a given {@link MonitoredAction} (if exists).
     * 
     * @param action
     *            - the {@link MonitoredAction} to look for status
     * @return - the status found (null if no one is found).
     */
    public State getStateForAction(MonitoredAction action) {
        State state = null;
        if (mMonitoredActions.containsKey(action)) {
            state = mMonitoredActions.get(action);
        }
        return state;
    }

    /**
     * Determinate if a {@link MonitoredAction} is being monitored.
     * 
     * @param action
     *            - the {@link MonitoredAction} to look for.
     * @return - true if it is monitored. False any other case.
     */
    public boolean isActionBeingMonitored(MonitoredAction action) {
        return mMonitoredActions.containsKey(action);
    }

    /**
     * Resets all the data related to the {@link ActionController}
     */
    public void reset() {
        mMonitoredActions.clear();
        mListeners.clear();
    }
}