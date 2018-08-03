package com.mrchibunna.madDiscovery;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME="DemoDataBase";

    public static final String KEY_ID="id";

    public static final String TABLE_NAME="demoTable";

    public static final String KEY_Event_name="name";

    public static final String KEY_Location="location";

    public static final String KEY_Data_of_event="date";

    public static final String KEY_Time_of_event="time";

    public static final String KEY_Name_of_organizer="organizer";

    public static final String KEY_report="report";

    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY, "+KEY_Event_name+" VARCHAR, "+KEY_Location+" VARCHAR, "+KEY_Data_of_event+" VARCHAR,"+KEY_Time_of_event+" VARCHAR,"+KEY_Name_of_organizer+" VARCHAR,"+KEY_report+" VARCHAR)";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

}