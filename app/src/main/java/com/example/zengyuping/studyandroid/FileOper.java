package com.example.zengyuping.studyandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileOper extends AppCompatActivity {
    //region 数据持久化－文件存储
//    private FileOutputStream fs=null;
//    private BufferedWriter out=null;
//    private FileInputStream fs_in=null;
//    private BufferedReader input=null;
//
//    private static String file_name="file_test1";
//    private static String file_data="保存一些数据到文件中！";
//    private EditText account;
    //endregion

    //region 数据持久化-SharedPreferences
    private Button login;
    private EditText account;
    private  EditText password;
    private RadioGroup sex_radio;

    private String accountData=null;
    private String passwordData=null;
    private String sexData=null;
    private String file_name="loginInfo";
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_oper);

//        Button btnSaveFile=(Button)findViewById(R.id.btnSaveFile);
//        btnSaveFile.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                saveToFile(file_name,file_data);
//            }
//        });

//        account=(EditText)findViewById(R.id.account);
//        String account_read=readFromFile(file_name);
//        if(!account_read.isEmpty())
//        {
//            account.setText(account_read);
//            account.setSelection(account_read.length());
//        }
        account=(EditText)findViewById(R.id.account);
        password=(EditText)findViewById(R.id.password);
        sex_radio=(RadioGroup)findViewById(R.id.sex);
        sex_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radionButtonId=group.getCheckedRadioButtonId();
                switch (radionButtonId)
                {
                    case R.id.sex_man:
                        sexData="男";
                        break;
                    case R.id.sex_woman:
                        sexData="女";
                        break;
                    default:
                        sexData="null";
                        break;
                }
            }
        });

        readFromSharedP(file_name);
        account.setText(accountData);
        password.setText(passwordData);
        if(sexData.equals("男"))
            sex_radio.check(R.id.sex_man);
        else if(sexData.equals("女"))
            sex_radio.check(R.id.sex_woman);

        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                accountData =account.getText().toString();
                passwordData=password.getText().toString();
                saveToSharedP(file_name,accountData,passwordData,sexData);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        String account_write = account.getText().toString();
//        saveToFile(file_name,account_write);
    }

//    private void saveToFile(String name, String data)
//    {
//        try{
//            fs= openFileOutput(name, Context.MODE_PRIVATE);
//            out=new BufferedWriter(new OutputStreamWriter(fs));
//            out.write(data);
//        }catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//        finally {
//            try{
//                if(out!=null)
//                {
//                    out.close();
//                }
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private  String readFromFile(String name)
//    {
//        StringBuffer stringBuffer=new StringBuffer();
//        try {
//            fs_in=openFileInput(name);
//            input=new BufferedReader(new InputStreamReader(fs_in));
//            String line="";
//            while((line=input.readLine())!=null)
//            {
//                stringBuffer.append(line);
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        finally {
//            if(input !=null)
//            {
//                try {
//                    input.close();
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return stringBuffer.toString();
//    }

    private void saveToSharedP(String name,String account,String password,String sex)
    {
        SharedPreferences.Editor editor= getSharedPreferences(name,MODE_PRIVATE).edit();
        editor.putString("Account",account);
        editor.putString("Password",password);
        editor.putString("Sex",sex);
        editor.commit();
    }

    private  void readFromSharedP(String name)
    {
        SharedPreferences sharedP=getSharedPreferences(name,MODE_PRIVATE);
        accountData=sharedP.getString("Account","");
        passwordData=sharedP.getString("Password","");
        sexData=sharedP.getString("Sex","");
    }
}
