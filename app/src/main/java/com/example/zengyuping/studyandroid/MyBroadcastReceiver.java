package com.example.zengyuping.studyandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by zengyuping on 2016/3/22.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Received from MyBroadcastReceiver",Toast.LENGTH_LONG).show();
    }
}
