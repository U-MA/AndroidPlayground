package com.example.ideanote.activenotificationsshyakyo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A fragment that enables display of notifications.
 */
public class ActiveNotificationFragment extends Fragment {

    private static final String TAG = "ActiveNotificationFrag";

    private NotificationManager mNotificationManager;
    private TextView mNumberOfNotifications;

    // Every notification needs a unique ID otherwise the previous one would be overwitten.
    private int mNotificationId = 0;
    private PendingIntent mDeletePendingIntent;


    public ActiveNotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_active_notification, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateNumberOfNotifications();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNotificationManager = (NotificationManager) getActivity().getSystemService(
                Context.NOTIFICATION_SERVICE);
        mNumberOfNotifications = (TextView) view.findViewById(R.id.number_of_notifications);

        //Supply actions to the button that is displayed on screen.
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.add_notification: {
                        addNotificationAndReadNumber();
                        break;
                    }
                }
            }
        };
        view.findViewById(R.id.add_notification).setOnClickListener(onClickListener);

        // [BEGIN create_pending_intent_for_deletion]
        // Create a PendingIntent to be fired upon deletion of a Notification.
        Intent deleteIntent = new Intent(ActiveNotificationActivity.ACTION_NOTIFICATION_DELETE);
        mDeletePendingIntent = PendingIntent.getBroadcast(getActivity(),
                2323 /* request code */, deleteIntent, 0);
        // [END create_pending_intent_for_deletion]
    }

    /**
     * Add a new {@link Notification} with sample data and send it to the system.
     * Then read the current number of displayed notifications for this application.
     */
    private void addNotificationAndReadNumber() {
        // [BEGIN create_notification]
        // Create a Notification and notify the system
        final Notification.Builder builder = new Notification.Builder(getActivity())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(getString(R.string.sample_notification_content))
                .setAutoCancel(true)
                .setDeleteIntent(mDeletePendingIntent);

        final Notification notification = builder.build();
        mNotificationManager.notify(++mNotificationId, notification);
        // [END create_notification]
        Log.i(TAG, "Add a notification");
        updateNumberOfNotifications();
    }

    /**
     * Request the current number of notifications from the {@link NotificationManager} and
     * display them to the user
     */
    protected void updateNumberOfNotifications() {
        // [BEGIN get_active_notifications]
        // Query the currently displayed notifications.
        final StatusBarNotification[] activeNotifications = mNotificationManager
                .getActiveNotifications();
        // [END get_active_notifications]
        final int numberOfNotifications = activeNotifications.length;
        mNumberOfNotifications.setText(getString(R.string.active_notifications,
                numberOfNotifications));
        Log.i(TAG, getString(R.string.active_notifications, numberOfNotifications));
    }
}
