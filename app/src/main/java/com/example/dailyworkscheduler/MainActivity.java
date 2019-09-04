package com.example.dailyworkscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText editname,edittime,editpriority,editappr;
    Button adddata;

    View myview;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        myview=inflater.inflate(R.layout.activity_main,container,false);
        return myview;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        mydb = new DatabaseHelper(this);

        editname=(EditText)findViewById(R.id.w_name);
        editpriority=(EditText)findViewById(R.id.prior);
        edittime=(EditText) findViewById(R.id.req_time);
        editappr=(EditText)findViewById(R.id.a_time);
        adddata=(Button)findViewById(R.id.add_data);

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        addData();
    }

   public void addData(){
        adddata.setOnClickListener(
            new View.OnClickListener(){
                public void onClick(View v){
                    boolean isInserted=mydb.insertData(editname.getText().toString(),
                            editpriority.getText().toString(),
                            edittime.getText().toString(),
                            editappr.getText().toString());
                    if(isInserted==true)
                        Toast.makeText(MainActivity.this,"data inserted",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(MainActivity.this,"data not inserted",Toast.LENGTH_LONG).show();
                    dataInserted();
                }
            }
        );
    }
    private void dataInserted(){
        Intent launchactivity=new Intent(this,Activity2.class);
        startActivity(launchactivity);
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
