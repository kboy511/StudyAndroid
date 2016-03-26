package com.example.zengyuping.studyandroid;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by zengyuping on 2016/3/26.
 */
public class MyDBHelper extends SQLiteOpenHelper {
    public static final String CREATE_PERSON="create table person("
                                                +"id integer primary key autoincrement,"
                                                +"name text,"
                                                +"age integer,"
                                                +"height real,"
                                                +"sex text)";

    public static final String CREATE_TEACHER="create table teacher("
                                        +"id integer primary key autoincrement,"
                                        +"class text)";
    private Context mContext;

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version)
    {
        super(context,name,factory,version);
        mContext=context;
    }

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PERSON);
        db.execSQL(CREATE_TEACHER);
        Toast.makeText(mContext,"Create table person and Teacher success",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists person");
        db.execSQL("drop table if exists teacher");
        onCreate(db);
    }
}
