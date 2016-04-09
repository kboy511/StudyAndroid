package com.example.zengyuping.studyandroid;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class Notification extends AppCompatActivity {

    private  static  final String TAG="Notification";
    private Button btnSendNotification;
    private int mId=1;
    private int numMessage=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        btnSendNotification=(Button)findViewById(R.id.btnSendNotification);
        btnSendNotification.setOnClickListener(new myOnClickListener());
    }

    private class myOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.btnSendNotification:
                    setBtnSendNotification();
                    break;
            }
        }
    }

    public void setBtnSendNotification()
    {
//        NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("我的通知")
//                .setContentText("Hello Notification");

        final android.support.v4.app.NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("我的通知")
//                .setContentText("Hello Notification")
                .setContentTitle("音乐下载")
                .setContentText("burning.mp3")
                .setNumber(++numMessage);
        Intent intent=new Intent(this,ShowNotification.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        notification.setContentIntent(pendingIntent);
//        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//        manager.notify(mId,notification.build());
        final NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int cnt=0;cnt<=100;cnt++)
                        {
                            notification.setProgress(100,cnt,false);
                            manager.notify(mId,notification.build());
                            try{
                                Thread.sleep(100);
                            }
                            catch (InterruptedException e)
                            {
                                Log.d(TAG,"Sleep failure");
                            }
                        }
                        notification.setContentText("下载完成");
                        notification.setProgress(0,0,false);
                        Uri soundUri = Uri.fromFile(new File("/system/media/audio/animationsounds/bootSound.ogg"));
                        notification.setSound(soundUri);
                        long[] vibrates={0,1000,1000,1000};//手机震动
                        notification.setVibrate(vibrates);
                        notification.setLights(Color.GREEN,1000,1000);//控制LED灯
                        manager.notify(mId,notification.build());
                    }
                }
        ).start();
    }
}
