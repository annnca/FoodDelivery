package com.example.fooddelivery;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

public class AddFoods extends AppCompatActivity {
    EditText id, name, price;
    Button btnAdd, btnEdit, btnDelete, btnViewAll;
    private HashMap<String, Location> locations;
   // MyApp mApp;
    ListView listview1;
    SQLiteDatabase db;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_food);
        addListenerOnButton();

        id = (EditText)findViewById(R.id.et_food_id);
        name = (EditText)findViewById(R.id.et_food_name);
        price = (EditText)findViewById(R.id.et_price);

        db = openOrCreateDatabase("Foods_DB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS table_foods(food_id int, food_name VARCHAR, price int);");
        locations = loadLocationsData();
        //initializeUI();
    }

    private void initializeUI(){
        String[] cities = getCityNames();
        //sdk provided layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cities);
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

            locations.put("- "+c.getString(1).toString()+"[$"+c.getString(2).toString()+"]", new Location(c.getInt(0), c.getString(1).toString(), c.getInt(2)));

        }

        return locations;
    }
    private void addListenerOnButton() {
        final Context context = this;

        btnAdd = (Button)findViewById(R.id.btn_add);
        btnDelete = (Button)findViewById(R.id.btn_delete);
        btnEdit = (Button)findViewById(R.id.btn_edit) ;
        btnViewAll = (Button)findViewById(R.id.btn_viewAll);

        btnAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                db.execSQL("INSERT INTO table_foods VALUES('"+id.getText()+"','"+name.getText()+"','"+price.getText()+"');");
                Toast toast = Toast.makeText(context,"Success, Record added", Toast.LENGTH_SHORT);
                toast.show();
                showMessage("Success","Record added" );
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                db.execSQL("UPDATE table_foods SET food_name = '"+name.getText()+"' WHERE food_id='"+id.getText()+"'");
               Toast toast = Toast.makeText(context,"Success, Record modified", Toast.LENGTH_SHORT);
               toast.show();
                showMessage("Success","Record modified" );
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                db.execSQL("DELETE FROM table_foods WHERE food_id='"+id.getText()+"'");
                Toast toast = Toast.makeText(context,"Success, Record deleted", Toast.LENGTH_SHORT);
                toast.show();
                 showMessage("Success","Record deleted" );
            }
        });

        btnViewAll.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Cursor c = db.rawQuery("SELECT * FROM table_foods", null);

                if(c.getCount() == 0)
                {
                    Toast toast = Toast.makeText(context,"Error, No record found", Toast.LENGTH_SHORT);
                    toast.show();
                }
                StringBuffer buffer = new StringBuffer();

                while(c.moveToNext())
                {
                    buffer.append("Id: "+c.getString(0)+"\n");
                    buffer.append("Name: "+c.getString(1)+"\n");
                    buffer.append("Price: "+c.getString(2)+"\n\n");
                }
                Toast toast = Toast.makeText(context,"Details "+ buffer.toString(), Toast.LENGTH_SHORT);
                toast.show();
                showMessage("All",buffer.toString());
            }
        });


    }


    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(message);
        builder.show();
    }

}
