package com.example.fooddelivery;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

public class AddDrinks extends AppCompatActivity {
    EditText id, name, price;
    Button btnAdd, btnEdit, btnDelete, btnViewAll;
   /* private HashMap<String, Location> locations;
    MyApp mApp;
    ListView listview1;*/
    SQLiteDatabase db;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_drink);

        addListenerOnButton();

        id = (EditText)findViewById(R.id.et_drink_id);
        name = (EditText)findViewById(R.id.et_drink_name);
        price = (EditText)findViewById(R.id.et_price);

        db = openOrCreateDatabase("Drinks_DB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS table_drinks(drink_id int, drink_name VARCHAR, price int);");

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
                db.execSQL("INSERT INTO table_drinks VALUES('"+id.getText()+"','"+name.getText()+"','"+price.getText()+"');");
                Toast toast = Toast.makeText(context,"Success, Record added", Toast.LENGTH_SHORT);
                toast.show();
                //showMessage("Success","Record added" );
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                db.execSQL("UPDATE table_drinks SET food_name = '"+name.getText()+"' WHERE drink_id='"+id.getText()+"'");
                Toast toast = Toast.makeText(context,"Success, Record modified", Toast.LENGTH_SHORT);
                toast.show();
                // showMessage("Success","Record modified" );
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                db.execSQL("DELETE FROM table_drinks WHERE drink_id='"+id.getText()+"'");
                Toast toast = Toast.makeText(context,"Success, Record deleted", Toast.LENGTH_SHORT);
                toast.show();
                // showMessage("Success","Record deleted" );
            }
        });

        btnViewAll.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Cursor c = db.rawQuery("SELECT * FROM table_drinks", null);

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
                //Toast toast = Toast.makeText(context,"Details "+ buffer.toString(), Toast.LENGTH_SHORT);
                //toast.show();
                showMessage("All",buffer.toString());
            }
        });

  /*      Button button1 = (Button)findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodsActivity.class);
                startActivity(intent);
            }
        });*/


    }


    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(message);
        builder.show();
    }

}