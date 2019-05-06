package com.example.fooddelivery;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class FoodsActivity extends AppCompatActivity {

    private HashMap<String, Location> locations;
    MyApp mApp;
    ListView listview1;
    SQLiteDatabase db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/LatoLight.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");

        db = openOrCreateDatabase("Foods_DB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS table_foods(food_id int, food_name VARCHAR, price int);");
        setContentView(R.layout.foods);
        locations = loadLocationsData();

        Button button = (Button)findViewById(R.id.button);
        button.setTypeface(custom_font1);

        ListView myList = (ListView)findViewById(R.id.ListView);
        TextView tv = (TextView) findViewById(R.id.textView6);
        tv.setTypeface(custom_font);

        myList.setBackgroundColor(Color.rgb(255, 242, 230));

        int count = myList.getCount();
        tv.setText("Pick something to order from the list below :");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        addListenerOnButton();
        initializeUI();
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
            Intent intent = new Intent(FoodsActivity.this, MainActivity.class);
            startActivity(intent);
        }else if (id == R.id.item2) {
            Intent intent = new Intent(FoodsActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeUI(){
        String[] cities = getCityNames();
        //sdk provided layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, cities);
        listview1.setAdapter(adapter);
    }
    private String[] getCityNames(){
        String[] cities = new String[locations.size()];
        cities = locations.keySet().toArray(cities);
        return cities;
    }
    private HashMap <String, Location>loadLocationsData()
    {
        HashMap<String,Location> locations = new HashMap<String, Location>();
        Cursor c = db.rawQuery("SELECT * FROM table_foods order by food_id asc", null);
        StringBuffer buffer = new StringBuffer();

        while(c.moveToNext()){
            /*buffer.append("Rollno"+c.getString(0)+"\n");
            buffer.append("Name"+c.getString(1)+"\n");
            buffer.append("Marks"+c.getString(2)+"\n\n");*/
            locations.put("\n"+c.getString(1).toString()+"[ $"+c.getString(2).toString()+" ]", new Location(c.getInt(0), c.getString(1).toString(), c.getInt(2)));

        }
       /* locations.put("Takeo", new Location(-27.29, 153.08));
        locations.put("Komport", new Location(-22.22, 33.33));*/
        return locations;
    }

    private void addListenerOnButton() {
        final Context context = this;
        Button button1 = (Button)findViewById(R.id.button);

        listview1 = (ListView)findViewById(R.id.ListView);
        listview1.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                        Object o = listview1.getItemAtPosition(position);
                        String pen = o.toString();
                        mApp = ((MyApp)getApplicationContext());
                        mApp.setGlobalVarValue(pen);
                        Toast.makeText(getApplicationContext(), "You have chosen "+""+pen, Toast.LENGTH_LONG).show();
                    }
                }
        );
        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddFoods.class);
                startActivity(intent);
            }
        });

    }

}