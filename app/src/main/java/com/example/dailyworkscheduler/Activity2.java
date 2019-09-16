package com.example.dailyworkscheduler;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText timefrom,timeto;
    Button startbutton;
    Button notifybutton;

    public static final String TAG = BuildConfig.APPLICATION_ID;
    public String mNotificationChannelId = null;


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mydb = new DatabaseHelper(this);
        timefrom = (EditText) findViewById(R.id.timefrom);
        timeto = (EditText) findViewById(R.id.timeto);
        startbutton = (Button) findViewById(R.id.login);
        notifybutton = (Button) findViewById(R.id.notify);

        addData();
        notifybutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addNotification();
                        Log.i("notification",     "notification is called" );
                    }
                }
        );


    }

    private void addNotification() {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int notifyId = 1;

        Notification notification = new Notification.Builder(Activity2.this)
                .setContentTitle("Some Message")
                .setContentText("You've received new messages!")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .build();


        notificationManager.notify(notifyId, notification);
    }
    public NotificationChannel createNotificationChannel() {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String channelId = "some_channel_id";
        CharSequence channelName = "Some Channel";
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.enableVibration(true);
        notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        notificationManager.createNotificationChannel(notificationChannel);
        return notificationChannel;
    }
    public String getNotificationChannelId() {
        if(mNotificationChannelId == null) {
            NotificationChannel notificationChannel = createNotificationChannel();
            mNotificationChannelId = notificationChannel.getId();
        }
        return mNotificationChannelId;
    }
    public void addData(){
        startbutton.setOnClickListener(

                new View.OnClickListener(){
                    public void onClick(View v){

                        boolean isInserted=mydb.create_time_hours(timefrom.getText().toString(),
                                timeto.getText().toString());
                        if(isInserted==true)
                            Toast.makeText(Activity2.this,"data inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Activity2.this,"data not inserted",Toast.LENGTH_LONG).show();



                        //dataInserted();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}



