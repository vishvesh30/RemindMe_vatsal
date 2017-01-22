package com.example.admin.remindme;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;

public class recycler_view_activity extends AppCompatActivity {
    FloatingActionButton mFAB;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    ArrayList<DataBaseModel> arrayList=new ArrayList<>();
    SQLClass DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_activity);

        mRecyclerView = (RecyclerView) findViewById(R.id.applicantList_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new recycler_view_divider(this, LinearLayoutManager.VERTICAL));

        DB=new SQLClass(this);
        Cursor cursor=DB.viewAllData();
        if(cursor.getCount()!=0)
        {
            cursor.moveToFirst();
            do {
                DataBaseModel model=new DataBaseModel(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)
                        ,cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9)
                        ,cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13),cursor.getString(14));
                arrayList.add(model);
            }while (cursor.moveToNext());
        }
        mAdapter=new recycler_view_adapter(arrayList);
        mRecyclerView.setAdapter(mAdapter);

    }
}
