package com.example.fooddelivery;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
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

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/LatoLight.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        String email = intent.getStringExtra("email");

        TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        tvWelcomeMsg.setTypeface(custom_font);
        EditText etUsername = (EditText) findViewById(R.id.etUsername);
        etUsername.setTypeface(custom_font);
        EditText etEmail = (EditText) findViewById(R.id.etEmail);
        etUsername.setTypeface(custom_font);
        TextView t1 = (TextView) findViewById(R.id.textView);
        t1.setTypeface(custom_font1);
        TextView t2 = (TextView) findViewById(R.id.textView2);
        t2.setTypeface(custom_font1);
        Button btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setTypeface(custom_font1);

        // Display user details
        String message = "Welcome, "+name + "!";
        tvWelcomeMsg.setText(message);
        etUsername.setText(username);
        etEmail.setText(email + "");
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
