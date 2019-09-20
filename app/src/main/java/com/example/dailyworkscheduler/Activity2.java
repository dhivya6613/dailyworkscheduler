package com.example.dailyworkscheduler;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {
    DatabaseHelper mydb = new DatabaseHelper(this);
    EditText timefrom,timeto;
    Button startbutton;
    Button notifybutton;



    public static final String TAG = BuildConfig.APPLICATION_ID;
    public String mNotificationChannelId = null;



    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        timefrom = (EditText) findViewById(R.id.timefrom);
        timeto = (EditText) findViewById(R.id.timeto);
        startbutton = (Button) findViewById(R.id.login);
        notifybutton = (Button) findViewById(R.id.notify);


        addData();
        notifybutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    //here i tried to store the query value in a string str
                    public void onClick(View v) {
                        Cursor res=mydb.retrieve();
                        if(res.getCount()==0) {
                            Log.i("res","no data");
                            return;
                        }
                        StringBuffer str=new StringBuffer();
                        str.append(res.getString(1));



                        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_HIGH);

                            // Configure the notification channel.
                            notificationChannel.setDescription("Channel description");
                            notificationChannel.enableLights(true);
                            notificationChannel.setLightColor(Color.RED);
                            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
                            notificationChannel.enableVibration(true);
                            notificationManager.createNotificationChannel(notificationChannel);
                        }


                        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(Activity2.this, "NOTIFICATION_CHANNEL_ID");

                        notificationBuilder.setAutoCancel(true)
                                .setDefaults(Notification.DEFAULT_ALL)
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.drawable.ic_menu_add)
                                .setTicker("Hearty365")
                                .setPriority(NotificationManager.IMPORTANCE_MAX)
                                .setContentTitle("work is scheduled")
                                .setContentText("Hi,dhivya ,do the work: "+str)
                                .setChannelId(NOTIFICATION_CHANNEL_ID)
                                .setOngoing(true)
                                .setContentInfo("Info");

                        notificationManager.notify(/*notification id*/1, notificationBuilder.build());
                        Log.i("notification",     "notification is called" );
                    }
                }
        );


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

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}



