package com.example.zengyuping.studyandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnMsgListView;

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
    }
}
