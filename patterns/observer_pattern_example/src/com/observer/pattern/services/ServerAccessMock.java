package com.observer.pattern.services;

import com.observer.pattern.services.ActionController.State;

import android.os.Handler;
import android.os.Looper;

/**
 * This is a mock class used to simulate the server access. Note that any method
 * executed in this class is simulating an access to a remote server that runs
 * in a thread different that the UI thread.
 * 
 * @author Juan P. Peretti 
 * 
 */
public class ServerAccessMock {


    private CommHandler mHandler;

    public ServerAccessMock() {
        mHandler = new CommHandler(Looper.getMainLooper());
    }

    public void login() {
        new Thread(new Runner(MonitoredAction.ACTION_LOGIN)).start();
    }

    public void fetchUserinfo() {
        new Thread(new Runner(MonitoredAction.ACTION_FETCH_USER_INFO)).start();
    }

    public void updateUserProfile() {
        new Thread(new Runner(MonitoredAction.ACTION_UPDATE_USER_INFO)).start();
    }

    private void notifyActionDone(MonitoredAction action) {
        mHandler.notifyAction(action, State.STATE_DONE);
    }

    private void notifyActionDone(MonitoredAction action, State state) {
        mHandler.notifyAction(action, state);
    }

    private class Runner implements Runnable {


        private MonitoredAction mAction;

        Runner(MonitoredAction action) {
            mAction = action;
        }

        @Override
        public void run() {

            notifyActionDone(mAction, State.STATE_IN_PROGRESS);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {

            }

            notifyActionDone(mAction);
        }

    }

    private class CommHandler extends Handler {


        public CommHandler(Looper looper) {
            super(looper);
        }

        void notifyAction(final MonitoredAction action, final State state) {
            post(new Runnable() {


                @Override
                public void run() {
                    ActionController.ACTION_CONTROLLER.setActionStatus(action, state, null);
                }
            });
        }
    }

}
