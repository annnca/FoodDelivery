package com.example.fooddelivery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.NameValuePair;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ImageActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView imageToUpload;
    Button bUploadImage;
    EditText uploadImageName;

    private static final int RESULT_LOAD_IMAGE = 1;
    private static final String SERVER_ADDRESS = "https://mcproj1234.000webhostapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/LatoLight.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");

        imageToUpload = (ImageView)findViewById(R.id.imageToUpload);
        bUploadImage = (Button)findViewById(R.id.bUploadImage);
        uploadImageName = (EditText)findViewById(R.id.etUploadName);
        EditText et2 = (EditText)findViewById(R.id.et2);

        imageToUpload.setOnClickListener(this);
        bUploadImage.setOnClickListener(this);

        bUploadImage.setTypeface(custom_font1);
        uploadImageName.setTypeface(custom_font);
        et2.setTypeface(custom_font);
        et2.setText("Enter now our contest! Take a picture of you and the meals purchased from us and get the chance to win free orders daily !");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.imageToUpload:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;

            case R.id.bUploadImage:
                Toast.makeText(this, "Image uploaded. Thank you!", Toast.LENGTH_SHORT).show();
                Bitmap image = ((BitmapDrawable)imageToUpload.getDrawable()).getBitmap();
                new UploadImage(image, uploadImageName.getText().toString());
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

    private class UploadImage extends AsyncTask<Void, Void, Void>{
        Bitmap image;
        String name;

        public UploadImage(Bitmap image, String name){
            this.image = image;
            this.name = name;
        }

        @Override
        protected Void doInBackground(Void... params){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
//https://www.youtube.com/watch?v=TMnQJKtmOd4
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("image", encodedImage));
            dataToSend.add(new BasicNameValuePair("name", name));

            HttpParams httpRequestParams = getHttpRequestParams();

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS+"SavePicture.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(), "Image Uploaded!", Toast.LENGTH_SHORT).show();
        }

        private HttpParams getHttpRequestParams(){
            //attributes of the request sent to the server
            HttpParams httpRequestParams = new BasicHttpParams();
            //timeout
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, 1000 * 30);
            HttpConnectionParams.setSoTimeout(httpRequestParams, 1000*30);

            return httpRequestParams;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item1) {
            Intent intent = new Intent(ImageActivity.this, MainActivity.class);
            startActivity(intent);
        }else if (id == R.id.item2) {
            Intent intent = new Intent(ImageActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
