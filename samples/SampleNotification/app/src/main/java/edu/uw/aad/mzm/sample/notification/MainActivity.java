package edu.uw.aad.mzm.sample.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Margaret on 5/18/2015
 *
 * This sample demos a few basic concepts of Android Notifications:
 * - create a Notification
 * - update a Notification
 * - dismiss one Notification or all Notifications
 * - synthesize backstack for navigation
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {


    // Unique (within app) ID for email Notification
    public static final int EMAIL_NOTIFICATION_ID = 1;

    // Notification ID for recipe
    public static final int RECIPE_NOTIFICATION_ID = 2;

    // Use NotificationCompat for backwards compatibility
    private NotificationManagerCompat mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] buttonIds = {R.id.buttonCreateBasic,
                            R.id.buttonUpdateBasic,
                            R.id.buttonCancelBasic,
                            R.id.buttonBackStack};

        // SetOnClickListener for each button
        for (int id: buttonIds) {
            findViewById(id).setOnClickListener(this);
        }

        mNotificationManager = NotificationManagerCompat.from(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonCreateBasic:
                showBasicNotification();
                break;
            case R.id.buttonUpdateBasic:
                updateBasicNotification();
                break;
            case R.id.buttonCancelBasic:
                cancelBasicNotification();
                break;
            case R.id.buttonBackStack:
                createNotificationWithBackstack();
                break;
        }

    }


    private void showBasicNotification() {


        // Create an Intent
        Intent intent = new Intent(this, ChildActivity.class);

        // Create a PendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_mail);
        builder.setContentTitle("You have a new email");
        builder.setContentText("Hi Margaret, just want to follow up with you...");
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        // Create the notification
        Notification basicNotification = builder.build();

        mNotificationManager.notify(EMAIL_NOTIFICATION_ID, basicNotification);
    }

    /**
     * Update an existing Notification
     */
    private void updateBasicNotification() {

        // Create an Intent
        Intent intent = new Intent(this, ChildActivity.class);

        // Create a PendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        // Build the Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_mail);
        builder.setContentTitle("Updated: You have a new email");
        builder.setContentText("Updated: Hi Margaret, just want to follow up with you...");
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        // Create the Notification
        Notification basicNotification = builder.build();

        // Note we use the same EMAIL_NOTIFICATION_ID to identify an existing Notification
        mNotificationManager.notify(EMAIL_NOTIFICATION_ID, basicNotification);

    }

    /**
     * Cancels notification(s)
     */
    private void cancelBasicNotification() {

        // Cancels one notification
//        mNotificationManager.cancel(EMAIL_NOTIFICATION_ID);

        // Cancels all my notifications
        mNotificationManager.cancelAll();
    }


    /**
     * Creates a notification that demos how TaskStackBuilder works
     */
    private void createNotificationWithBackstack() {

        // Create a intent
        Intent intent = new Intent(this, ChildActivity.class);

        // Create a task stack builder
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Adds the parent activity to the back stack
        stackBuilder.addParentStack(ChildActivity.class);

        // Adds the activity (to be started) to the top of the stack
        stackBuilder.addNextIntent(intent);

        // Create the PendingIntent
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // Build the Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_restaurant);
        builder.setContentTitle("Check out this new Chinese restaurant");
        builder.setContentText("Hi Margaret, do you like spicy food? Then check out this new Chinese restaurant!");
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        // Create the Notification
        Notification basicNotification = builder.build();

        mNotificationManager.notify(RECIPE_NOTIFICATION_ID, basicNotification);
    }

}
