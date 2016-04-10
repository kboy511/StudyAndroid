package com.example.zengyuping.studyandroid;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CameraTest extends AppCompatActivity {

    public static final int TAKE_PHONE = 1;
    public static final int CROP_PICTURE = 2;
    public static final int CHOOSE_PICTURE = 3;

    private Button btnTakePhoto;
    private Button btnChoosePhoto;
    private ImageView ivPicture;
    private Uri imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_test);

        btnTakePhoto = (Button) findViewById(R.id.btnTakePhoto);
        btnTakePhoto.setOnClickListener(new myOnClickListener());

        btnChoosePhoto = (Button) findViewById(R.id.btnChoosePhoto);
        btnChoosePhoto.setOnClickListener(new myOnClickListener());

        ivPicture = (ImageView) findViewById(R.id.ivPicture);
        // ivPicture.setOnClickListener(new myOnClickListener());
    }

    public class myOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnTakePhoto:
                    setTakePhoto();
                    break;
                case R.id.btnChoosePhoto:
                    setChoosePhoto();
                    break;
                default:
                    break;
            }

        }
    }

    public void setTakePhoto() {
        File outPutImage = new File(Environment.getExternalStorageDirectory(), "test.jpg");
        try {
            if (outPutImage.exists()) {
                outPutImage.delete();
            }
            outPutImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageUrl = Uri.fromFile(outPutImage);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUrl);
        startActivityForResult(intent, TAKE_PHONE);
    }

    public void setChoosePhoto() {
        File outPutImage = new File(Environment.getExternalStorageDirectory(), "test1.jpg");
        try {
            if (outPutImage.exists())
                outPutImage.delete();
            outPutImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageUrl = Uri.fromFile(outPutImage);
        //Intent intent = new Intent("android.intent.action.GET_CONTENT");
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra("crop", "true");//出剪辑的小方框
        intent.putExtra("aspectx",1);//出现放大和缩小
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUrl);
        Intent wrapperIntent = Intent.createChooser(intent,"选择图片");
        startActivityForResult(wrapperIntent, CROP_PICTURE);

//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, CHOOSE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case TAKE_PHONE:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUrl, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUrl);
                    startActivityForResult(intent, CROP_PICTURE);
                }
                break;
            case CROP_PICTURE:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUrl));
                        ivPicture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PICTURE:
                if (resultCode == RESULT_OK) {
                    try {
                        Uri uri = data.getData();
                        ContentResolver cr = this.getContentResolver();
                        Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                        ivPicture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

}
