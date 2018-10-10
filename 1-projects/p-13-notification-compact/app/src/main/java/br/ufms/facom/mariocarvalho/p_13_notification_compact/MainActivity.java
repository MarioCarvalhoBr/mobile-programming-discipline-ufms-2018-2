package br.ufms.facom.mariocarvalho.p_13_notification_compact;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNotification = findViewById(R.id.btnNotification);
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNotificationChannel();
            }
        });
    }
    // Method for creating notification
    private void createNotificationChannel (){
        // My Res
        Resources res = getResources();
        // Building intent
        Intent intent = new Intent(MainActivity.this, AlertNotificationActivity.class);
        PendingIntent mPendingIntent = PendingIntent.getActivity(
                MainActivity.this, 0,intent,0);

        String CHANNEL_ID = "br.ufms.facom.mariocarvalho.p_13_notification_compact";// The internal id of the channel.
        String CHANNEL_NAME = "CHANNEL_NAME_APP_NOTIFICATION";// The public name of the channel.
        // Adicionando support
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            /* Create or update. */
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);

            final NotificationManager notificationManager = (NotificationManager) MainActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);

        }
        // My notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                MainActivity.this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stat_my_notification)
                .setContentTitle(res.getString(R.string.txt_notification_title))
                .setContentText(res.getString(R.string.txt_notification_content))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(mPendingIntent)
                .setAutoCancel(true);

        //My style
        NotificationCompat.InboxStyle mInboxStyle = new NotificationCompat.InboxStyle();

        mBuilder.setStyle(mInboxStyle);
        // Sending service
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(0, mBuilder.build());

    }
}
