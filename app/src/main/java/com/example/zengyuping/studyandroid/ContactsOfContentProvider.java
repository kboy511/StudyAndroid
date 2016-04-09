package com.example.zengyuping.studyandroid;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ContactsOfContentProvider extends AppCompatActivity {

    private ListView contactViews;

    ArrayAdapter<String> adapter;
    List<String> contactList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_of_content_provider);

        contactViews = (ListView)findViewById(R.id.contacts);
        adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contactList);
        contactViews.setAdapter(adapter);
        readContacts();
    }

    public void readContacts()
    {
        Cursor cursor=null;
        try{
            cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            while(cursor.moveToNext())
            {
                String contactName=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String contactPhoneNum=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactList.add(contactName+'\n'+contactPhoneNum);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if(cursor !=null)
            {
                cursor.close();
            }
        }
    }
}
