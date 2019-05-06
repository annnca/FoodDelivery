package com.example.fooddelivery;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.StringTokenizer;

public class TotalsActivity extends AppCompatActivity {
    MyApp mApp;
    EditText et_summary;
    TextView tv_total;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.totals);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/LatoLight.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");

        mApp = ((MyApp)getApplicationContext());

        TextView tv3 = (TextView)findViewById(R.id.textView3);
        tv3.setTypeface(custom_font);

        et_summary = (EditText)findViewById(R.id.et_summary);
        et_summary.setTypeface(custom_font);

        tv_total = (TextView)findViewById(R.id.tv_total);
        tv_total.setTypeface(custom_font);

        Toast.makeText(getApplicationContext(), "You have chosen the "+""+mApp.getmGlobalVariable(), Toast.LENGTH_LONG).show();
        mApp.setGlobalVarValue(mApp.getmGlobalVariable().replace("\\n", System.getProperty("line.separator")));
        et_summary.setText(mApp.getmGlobalVariable()+"\n");
        String str = mApp.getmGlobalVariable();
        StringTokenizer st = new StringTokenizer(str,"$");
        String test = "";
        float total = 0;
        int count  = 0;
        while(st.hasMoreTokens()){
          //  Toast.makeText(getApplicationContext(), st.nextElement().toString().substring(0,1), Toast.LENGTH_LONG).show();

            test = st.nextElement().toString().substring(0,1);
            if (count >0)
                total+=Float.parseFloat(test);
            count++;
        }
       // et_summary.setText(mApp.getmGlobalVariable());
        tv_total.setText("Total: "+total+"");
        mApp.SetGlobalClear();
        //addListenerOnButton();
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
            Intent intent = new Intent(TotalsActivity.this, MainActivity.class);
            startActivity(intent);
        }else if (id == R.id.item2) {
            Intent intent = new Intent(TotalsActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
