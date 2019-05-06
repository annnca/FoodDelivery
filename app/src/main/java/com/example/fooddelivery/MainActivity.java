package com.example.fooddelivery;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.function.ToDoubleBiFunction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        IntentFilter filter = new IntentFilter();

        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction("mypackage.CONNECTIVITY_CHANGE");

        NetworkMonitor networkMonitor = new NetworkMonitor();
        networkMonitor.registerConnectivityNetworkMonitor(MainActivity.this);
        addListenerOnButton();
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
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }else if (id == R.id.item2) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void addListenerOnButton() {
        final Context context = this;

        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");

        Button button1 = (Button)findViewById(R.id.button1);
        button1.setTypeface(custom_font1);
        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodsActivity.class);
                startActivity(intent);
            }
        });

        Button button3 = (Button)findViewById(R.id.button3);
        button3.setTypeface(custom_font1);
        button3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TotalsActivity.class);
                startActivity(intent);
            }
        });
        Button button5 = (Button)findViewById(R.id.button5);
        button5.setTypeface(custom_font1);
        button5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapMainActivity.class);
                startActivity(intent);
            }
        });
        Button button6 = (Button)findViewById(R.id.button6);
        button6.setTypeface(custom_font1);
        button6.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImageActivity.class);
                startActivity(intent);
            }
        });

    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
        else return false;
        } else
        return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need a Mobile Data or WIFI connection to proceed.");
        builder.setIcon(android.R.drawable.ic_delete);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        return builder;
    }
}
