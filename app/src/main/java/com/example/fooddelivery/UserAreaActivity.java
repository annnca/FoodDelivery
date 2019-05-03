package com.example.fooddelivery;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.net.ConnectException;

public class UserAreaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);


        Intent intent = getIntent();
            String name = intent.getStringExtra("name");
            String username = intent.getStringExtra("username");
            int age = intent.getIntExtra("age", -1);

            TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
            EditText etUsername = (EditText) findViewById(R.id.etUsername);
            EditText etAge = (EditText) findViewById(R.id.etAge);

            // Display user details
            String message = name + " welcome!";
            tvWelcomeMsg.setText(message);
            etUsername.setText(username);
            etAge.setText(age + "");
            addListenerOnButton();
    }

    private void addListenerOnButton() {
        final Context context = this;
        Button btnNext = (Button)findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
    }



}
