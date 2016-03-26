package com.example.zengyuping.studyandroid;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SQLiteOper extends AppCompatActivity {

    private Button createDb;
    private Button addData;
    private Button updateData;
    private Button delData;
    private Button queryData;

    private MyDBHelper myDBHelper;

    private static final String dbName = "PersonStore.db";
    private static final int version = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_oper);

        myDBHelper = new MyDBHelper(this, dbName, null, version);

        createDb = (Button) findViewById(R.id.btnCreateDB);
        createDb.setOnClickListener(new MyOnClickListener());

        addData = (Button) findViewById(R.id.btnAddData);
        addData.setOnClickListener(new MyOnClickListener());

        updateData = (Button) findViewById(R.id.btnUpdateData);
        updateData.setOnClickListener(new MyOnClickListener());

        delData = (Button) findViewById(R.id.btnDelData);
        delData.setOnClickListener(new MyOnClickListener());

        queryData=(Button)findViewById(R.id.btnQueryData);
        queryData.setOnClickListener(new MyOnClickListener());

    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnCreateDB:
                    myDBHelper.getWritableDatabase();
                    break;
                case R.id.btnAddData:
                    addDataToPerson("xiao ming",20,172.5,"man");
                    addDataToPerson("xiao hong",22,160.3,"woman");
                    addDataToPerson("xiao li",25,180.5,"man");
                    break;
                case R.id.btnUpdateData:
                    updateHeight("xiao ming",175.0);
                    break;
                case R.id.btnDelData:
                    deleteByName("xiao li");
                    break;
                case R.id.btnQueryData:
                    queryByAge(21);
                    break;
                default:
                    break;
            }
        }
    }

    public void queryByAge(int ageQuery)
    {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from person where age > ?",new String[]{String.valueOf(ageQuery)});
        while (cursor.moveToNext())
        {
            int id=cursor.getInt(cursor.getColumnIndex("id"));
            String name=cursor.getString(cursor.getColumnIndex("name"));
            int age=cursor.getInt(cursor.getColumnIndex("age"));
            double height = cursor.getDouble(cursor.getColumnIndex("height"));
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
            Log.d("TAB","id:"+id+"|name:"+name+"|age:"+age+"|height:"+height+"|sex:"+sex);
        }
    }

    public void deleteByName(String name)
    {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        db.delete("person","name=?",new String[]{name});
    }

    public void updateHeight(String name,double height)
    {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("height",height);
        db.update("person",values,"name=?",new String[]{name});
    }

    public void addDataToPerson(String name,int age,double height,String sex)
    {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("age",age);
        values.put("height",height);
        values.put("sex",sex);
        db.insert("person",null,values);
    }
}
