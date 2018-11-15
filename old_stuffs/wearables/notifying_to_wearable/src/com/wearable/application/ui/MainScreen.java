package com.wearable.application.ui;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.wearable.application.R;

/**
 * A home screen that contains a couple of buttons to send notifications. <br>
 * Note that the notifications will be open in both: the phone and the wearable.
 * 
 * @author Juan P. Peretti
 * 
 */
public class MainScreen extends FragmentActivity implements OnClickListener {


    private static final int NOTIFICATION_ID = 1123133;

    private Button btnLaunchSimpleNotification;
    private Button btnLaunchNotificationWithActionButton;
    private Button btnLaunchNotificationOnlyWearableAction;
    private Button btnLaunchNotificationWithBigView;
    private Button btnLaunchNotificationWithHintHide;
    private ActionBroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        btnLaunchSimpleNotification = (Button) findViewById(R.id.btn_launch_simple_notification);
        btnLaunchSimpleNotification.setOnClickListener(this);
        btnLaunchNotificationWithActionButton = (Button) findViewById(R.id.btn_launch_notification_with_action_buttons);
        btnLaunchNotificationWithActionButton.setOnClickListener(this);
        btnLaunchNotificationOnlyWearableAction = (Button) findViewById(R.id.btn_launch_notification_with_only_wearable_action);
        btnLaunchNotificationOnlyWearableAction.setOnClickListener(this);
        btnLaunchNotificationWithBigView = (Button) findViewById(R.id.btn_launch_notification_with_big_view);
        btnLaunchNotificationWithBigView.setOnClickListener(this);
        btnLaunchNotificationWithHintHide = (Button) findViewById(R.id.btn_launch_notification_with_hint_hide);
        btnLaunchNotificationWithHintHide.setOnClickListener(this);

        mReceiver = new ActionBroadcastReceiver();

    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter iFilter = new IntentFilter(ActionBroadcastReceiver.ACTION);
        registerReceiver(mReceiver, iFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.btn_launch_simple_notification:
            createSimpleNotification();
            break;
        case R.id.btn_launch_notification_with_action_buttons:
            createNotificationWithActionButton();
            break;
        case R.id.btn_launch_notification_with_only_wearable_action:
            createNotificatioOnlyWearableAction();
            break;
        case R.id.btn_launch_notification_with_big_view:
            createNotificatioWithBigView();
            break;
        case R.id.btn_launch_notification_with_hint_hide:
            createNotificationHintIdenIcon();
            break;
        }

    }

    /**
     * Creates a simple notification, that will be shown in both: wearable and
     * handheld. When the user taps in the notification, another activity will
     * be open.
     */
    private void createSimpleNotification() {
        Intent intent = new Intent(getApplicationContext(), NotifiableScreen.class);
        // Put any extras you may need here
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Title - Simple")
                .setContentText("Content")
                .setContentIntent(pi);

        NotificationManagerCompat notifManager = NotificationManagerCompat.from(getApplicationContext());
        notifManager.notify(NOTIFICATION_ID, builder.build());
    }

    /**
     * Creates a notification with an extra action item, that will be placed in
     * both, the wearable and the handheld. When the user taps in this extra
     * item, a broadcast action will be triggered.
     */
    private void createNotificationWithActionButton() {
        Intent intent = new Intent(getApplicationContext(), NotifiableScreen.class);
        Intent broadcastIntent = new Intent(ActionBroadcastReceiver.ACTION);
        broadcastIntent.putExtra(ActionBroadcastReceiver.KEY_ACTION, "Yeah, it is cool!");
        // Put any extras you may need here
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
        PendingIntent broadcastAction = PendingIntent.getBroadcast(getApplicationContext(), 0, broadcastIntent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Title - Simple")
                .setContentText("Content")
                .setContentIntent(pi)
                .addAction(R.drawable.ic_launcher, "Is it cool?", broadcastAction);

        NotificationManagerCompat notifManager = NotificationManagerCompat.from(getApplicationContext());
        notifManager.notify(NOTIFICATION_ID, builder.build());
    }

    /**
     * Creates a notification with an extra action item, that will be placed
     * only in the wearable . When the user taps in this extra item, a broadcast
     * action will be triggered.
     */
    private void createNotificatioOnlyWearableAction() {
        // Create an intent for the reply action
        Intent broadcastIntent = new Intent(ActionBroadcastReceiver.ACTION);
        broadcastIntent.putExtra(ActionBroadcastReceiver.KEY_ACTION, "Yeah, it is damn cool!");
        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), 0, broadcastIntent, 0);

        // Create the action
        NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_launcher, "Action only wearable", pi).build();

        // Build the notification and add the action via WearableExtender
        Notification notif = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Title")
                .setContentText("Content")
                .extend(new NotificationCompat.WearableExtender().addAction(action))
                .build();

        NotificationManagerCompat notifManager = NotificationManagerCompat.from(getApplicationContext());
        notifManager.notify(NOTIFICATION_ID, notif);

    }

    /**
     * Creates a notification using the big view style.
     */
    private void createNotificatioWithBigView() {

        // Create an intent for the reply action
        Intent broadcastIntent = new Intent(ActionBroadcastReceiver.ACTION);
        broadcastIntent.putExtra(ActionBroadcastReceiver.KEY_ACTION, "Yeah, it is damn cool!");
        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), 0, broadcastIntent, 0);

        // Specify the big view content to display
        BigTextStyle bigStyle = new BigTextStyle();
        bigStyle.bigText("This text could probably be a small hint to something that the user will have to do");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.btn_media_player_pressed))
                .setContentTitle("Content title")
                .setContentIntent(pi)
                .setStyle(bigStyle);

        NotificationManagerCompat notifManager = NotificationManagerCompat.from(getApplicationContext());
        notifManager.notify(NOTIFICATION_ID, builder.build());
    }

    /**
     * Creates a notification with an extra action item, that will be placed
     * only in the wearable . When the user taps in this extra item, a broadcast
     * action will be triggered.
     */
    private void createNotificationHintIdenIcon() {
        // Create an intent for the reply action
        Intent broadcastIntent = new Intent(ActionBroadcastReceiver.ACTION);
        broadcastIntent.putExtra(ActionBroadcastReceiver.KEY_ACTION, "Yeah, it is damn cool!");

        NotificationCompat.WearableExtender extender = new NotificationCompat.WearableExtender();
        extender.setHintHideIcon(true);

        // Build the notification and add the action via WearableExtender
        Notification notif = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Title")
                .setContentText("Content")
                .extend(extender)
                .build();

        NotificationManagerCompat notifManager = NotificationManagerCompat.from(getApplicationContext());
        notifManager.notify(NOTIFICATION_ID, notif);

    }
}
