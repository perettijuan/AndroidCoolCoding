package com.wearable.application.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ActionBroadcastReceiver extends BroadcastReceiver {

    static final String ACTION = ActionBroadcastReceiver.class.getName();

    static final String KEY_ACTION = "action_key";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getStringExtra(KEY_ACTION);
        Toast.makeText(context, action, Toast.LENGTH_LONG).show();
    }

}