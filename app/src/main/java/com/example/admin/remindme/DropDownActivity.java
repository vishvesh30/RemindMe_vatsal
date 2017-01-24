package com.example.admin.remindme;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import java.util.ArrayList;

public class DropDownActivity extends AppCompatActivity {

    SQLClass db;
    ArrayAdapter dataAdapter;
    ArrayList<String> items=new ArrayList<>();
    Spinner types;
    Button submit,category_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_down);

        db=new SQLClass(this);
        Cursor data=db.viewAllcategory();
        if(data.getCount()>0)
        {
            data.moveToFirst();
            do {
                items.add(data.getString(0));
            }while (data.moveToNext());
        }
        types= (Spinner) findViewById(R.id.spinner_types);
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, items);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        types.setAdapter(dataAdapter);
        submit=(Button) findViewById(R.id.button_dropdown);
        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i=MainActivity.newIntent(DropDownActivity.this,types.getSelectedItem().toString());
                        startActivity(i);
                    }
                }
        );

        category_submit=(Button) findViewById(R.id.button_new_category);
        category_submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(DropDownActivity.this,add_category_name.class));
                    }
                }
        );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_upcoming, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.upcoming:
                startActivity(new Intent(DropDownActivity.this,MainActivityVishvesh.class));
                return true;
        }
        return true;
    }

}
