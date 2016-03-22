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
