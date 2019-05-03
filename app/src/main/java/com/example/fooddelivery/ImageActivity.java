package com.example.fooddelivery;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.INotificationSideChannel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView imageToUpload;
    Button bUploadImage;
    EditText uploadImageName;

    private static final int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imageToUpload = (ImageView)findViewById(R.id.imageToUpload);
        bUploadImage = (Button)findViewById(R.id.bUploadImage);
        uploadImageName = (EditText)findViewById(R.id.etUploadName);

        imageToUpload.setOnClickListener(this);
        bUploadImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.imageToUpload:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
            case R.id.bUploadImage:
                break;
        }
    }

    @Override
    protected  void onActivityResult(int requestedCode, int resultCode, Intent data){
        super.onActivityResult(requestedCode, resultCode, data);
        if(requestedCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            {
                Uri selectedImage = data.getData();
                imageToUpload.setImageURI(selectedImage);
            }
        }
    }
}
