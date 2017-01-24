package com.example.admin.remindme;

/**
 * Created by admin on 24-01-2017.
 */

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivityVishvesh extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;
    SQLClass DB;
    ArrayList<DataBaseModel> arrayList = new ArrayList<>();
    static DataBaseModel ddd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vishvesh);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new MoviesAdapter(arrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addOnItemTouchListener(new TouchListener(getApplicationContext(), recyclerView, new TouchListener.ClickListener() {


            @Override
            public void onClick(View view, int position) {
                ddd = arrayList.get(position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        DB = new SQLClass(this);
        Cursor cursor = DB.viewAllData();
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                DataBaseModel model = new DataBaseModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)
                        , cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9)
                        , cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14));
                arrayList.add(model);
            } while (cursor.moveToNext());
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        // prepareMovieData();
    }
}