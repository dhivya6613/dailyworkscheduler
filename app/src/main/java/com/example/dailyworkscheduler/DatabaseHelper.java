package com.example.dailyworkscheduler;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "TIME_ACTIVITY";
    //database name-scheduler database
    public static final String DATABASE_NAME="scheduler.db";

    //name of two tables used in database

    public static final String TABLE_NAME="to_schedule";
    public static final String TABLE_NAME2="free_hours";

    //columns in to_schedule table

    public static final String col_1="t_workid ";
    public static final String col_2="workname";
    public static final String col_3="priority";
    public static final String col_4="req_time";
    public static final String col_5="appr_time";

    //columns in free_hours table

    public static final String col_6="free_time_id";
    public static final String col_7="time_from";
    public static final String col_8="time_to";

    //database is created here

    public DatabaseHelper(Context context ) {
        //database is created by calling this constructor
        super(context,DATABASE_NAME , null, 1);

    }
    SQLiteDatabase db=this.getWritableDatabase();
    //tables are created here

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+ TABLE_NAME + "(t_workid integer primary key autoincrement ,workname text ,priority integer ,req_time time ,appr_time text)");
        Log.i(TAG, "to_schedule table is created");
        db.execSQL("create table "+ TABLE_NAME2+ "(free_time_id integer primary key autoincrement ,time_from time ,time_to time)");
        Log.i(TAG, "free hours table is created");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        onCreate(db);
    }

   public boolean create_to_schedule(String w_name ,String prior,String req_time ,String a_time){



        ContentValues contentValues=new ContentValues();
        contentValues.put(col_2,w_name);
        contentValues.put(col_3,prior);
        contentValues.put(col_4,req_time);
        contentValues.put(col_5,a_time);

        long result=db.insert(TABLE_NAME,null,contentValues);

       Log.i("dhivya",     "result value is : " + result);

        if(result==-1)
            return false;
        else
            return true;

    }
    public boolean create_time_hours(String timefrom,String timeto){



        ContentValues timeValues=new ContentValues();
        timeValues.put(col_7,timefrom);
        timeValues.put(col_8,timeto);

        long result1=db.insert(TABLE_NAME2,null,timeValues);
        Log.i("suba",     "time inserted value is : " + result1);

        if(result1==-1){
            Log.i(TAG, "values not added in free hours table");
            return false;
        }

        else{
            Log.i(TAG, "values added in free hours table");
            return true;
    }}
    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
