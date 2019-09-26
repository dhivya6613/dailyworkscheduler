package com.example.dailyworkscheduler;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "TIME_ACTIVITY";
    //database name-scheduler database
    public static final String DATABASE_NAME="scheduler.db";
    public static final String TABLE_USERS = "users";

    //TABLE USERS COLUMNS
    //ID COLUMN @primaryKey
    public static final String KEY_ID = "id";

    //COLUMN user name
    public static final String KEY_USER_NAME = "username";

    //COLUMN email
    public static final String KEY_EMAIL = "email";

    //COLUMN password
    public static final String KEY_PASSWORD = "password";

    //SQL for creating users table
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PASSWORD + " TEXT"
            + " ) ";

    //name of two tables used in database

    public static final String TABLE_NAME="to_schedule";
    public static final String TABLE_NAME2="free_hours";

    //columns in to_schedule table

    public static final String col_1="t_workid ";
    public static final String col_2="workname";
    public static final String col_3="priority";
    public static final String col_4="req_time";


    //columns in free_hours table

    public static final String col_6="free_time_id";
    public static final String col_7="time_from";
    public static final String col_8="time_to";

    //database is created here

    public DatabaseHelper(Activity2 context ) {
        //database is created by calling this constructor
        super(context,DATABASE_NAME , null, 1);

    }

    public DatabaseHelper(MainActivity context ) {
        //database is created by calling this constructor
        super(context,DATABASE_NAME , null, 1);

    }
    public DatabaseHelper(MyService context ) {
        //database is created by calling this constructor
        super(context,DATABASE_NAME , null, 1);

    }

    //tables are created here

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+ TABLE_NAME + "(t_workid integer primary key autoincrement ,workname text ,priority integer ,req_time time)");
        Log.i(TAG, "to_schedule table is created");
        db.execSQL("create table "+ TABLE_NAME2+ "(free_time_id integer primary key autoincrement ,time_from time ,time_to time)");
        Log.i(TAG, "free hours table is created");
        db.execSQL(SQL_TABLE_USERS );
        Log.i(TAG,"user table is created");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        onCreate(db);
    }

   public boolean create_to_schedule(String w_name ,String prior,String req_time){


        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col_2,w_name);
        contentValues.put(col_3,prior);
        contentValues.put(col_4,req_time);


        long result=db.insert(TABLE_NAME,null,contentValues);


       Log.i("dhivya",     "result value is : " + result);

        if(result==-1)
            return false;
        else
            return true;

    }
    public boolean create_time_hours(String timefrom,String timeto){


        SQLiteDatabase db=this.getWritableDatabase();
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

    public Cursor retrievework(){
        //query for selecting work with maximum priority
        SQLiteDatabase mydb=this.getReadableDatabase();
        Cursor cursor =mydb.rawQuery("select max(workname) from to_schedule order by priority desc",null);
        if(cursor.getCount()!=0) Log.i("query","has data");
        return cursor;
    }
    public Cursor retrievetimefrom(){
        //query for selecting work with maximum priority
        SQLiteDatabase mydb=this.getReadableDatabase();
        Cursor cursor =mydb.rawQuery("select time_from  from free_hours where time_from>CURRENT_TIME ",null);
        if(cursor.getCount()!=0) Log.i("query","has data");
        return cursor;
    }



    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
