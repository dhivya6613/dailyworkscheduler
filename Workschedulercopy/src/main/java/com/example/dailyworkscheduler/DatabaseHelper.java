package com.example.dailyworkscheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="scheduler.db";
    public static final String TABLE_NAME="to_schedule";
    public static final String col_1="t_workid ";
    public static final String col_2="workname";
    public static final String col_3="priority";
    public static final String col_4="req_time";
    public static final String col_5="appr_time";

    public DatabaseHelper(Context context ) {
        //database is created by calling this constructor
        super(context,DATABASE_NAME , null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME + "(t_workid integer primary key autoincrement ,workname text ,priority integer ,req_time time ,appr_time text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

   public boolean insertData(String w_name ,String prior,String req_time ,String a_time){
        SQLiteDatabase db=this.getWritableDatabase();
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
}
