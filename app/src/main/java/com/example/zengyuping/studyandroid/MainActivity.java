package com.example.zengyuping.studyandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


//如果在调试的时候发现模拟器中的程序一直是旧的，可以先把模拟器中的APP删除，这个时候重新运行会自动安装最新的。
public class MainActivity extends AppCompatActivity {

    private Button btnMsgListView;

    private IntentFilter intentFilter;
    private LocalBroadcastReceiver localBroadcastReceiver;
    private LocalBroadcastManager localBroadcastManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMsgListView=(Button)findViewById(R.id.btnMsgListView);
        btnMsgListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_MsgListView =new Intent(MainActivity.this,MsgListView.class);
                startActivity(intent_MsgListView);
            }
        });

        Button btnSendBroadcast=(Button)findViewById(R.id.btnSendBroadcast);
        btnSendBroadcast.setOnClickListener(new MyClickListener());

        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.localbroadcast.LOCAL_BROADCAST");
        localBroadcastReceiver= new LocalBroadcastReceiver();
        localBroadcastManager = localBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(localBroadcastReceiver,intentFilter);

        Button btnSendLocalBroadcast=(Button)findViewById(R.id.btnSendLocalBroadcast);
        btnSendLocalBroadcast.setOnClickListener(new MyClickListener());

        Button btnOpenFileOper=(Button)findViewById(R.id.btnOpenFileOper);
        btnOpenFileOper.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,FileOper.class);
                startActivity(intent);
            }
        });

        Button btnOperSQLiteOper = (Button)findViewById(R.id.btnOpenSQLiteOper);
        btnOperSQLiteOper.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SQLiteOper.class);
                startActivity(intent);
            }
        });

        Button btnContacts = (Button)findViewById(R.id.btnContacts);
        btnContacts.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ContactsOfContentProvider.class);
                startActivity(intent);
            }
        });

        Button btnNotification = (Button)findViewById(R.id.btnNotification);
        btnNotification.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Notification.class);
                startActivity(intent);
            }
        });

        Button btnCamera = (Button)findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CameraTest.class);
                startActivity(intent);
            }
        });
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localBroadcastReceiver);
    }

    class  MyClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId())
            {
                case R.id.btnSendBroadcast:
                    intent=new Intent("com.example.broadcasttest.MY_BROADCAST");
                    sendBroadcast(intent);
                    break;
                case R.id.btnSendLocalBroadcast:
                    intent=new Intent("com.example.localbroadcast.LOCAL_BROADCAST");
                    localBroadcastManager.sendBroadcast(intent);
                    break;
            }
        }
    }

    class LocalBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"LocalBroadcastReceiver",Toast.LENGTH_LONG).show();
        }
    }
}
