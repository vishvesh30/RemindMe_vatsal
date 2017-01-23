package com.example.admin.remindme;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class list_category extends AppCompatActivity {

    ArrayAdapter adapter;
    SQLClass db;
    ArrayList<String> arr_list=new ArrayList<>();
    ListView list_cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);

        db=new SQLClass(this);
        Cursor data=db.viewAllcategory();

        if(data.getCount()!=0)
        {
            data.moveToFirst();
            do {
                arr_list.add(data.getString(0));
            }while (data.moveToNext());
        }
        adapter=new ArrayAdapter<String>(this,R.layout.listview_display,arr_list);
        list_cat=(ListView) findViewById(R.id.listview_category);
        list_cat.setAdapter(adapter);
    }
}
