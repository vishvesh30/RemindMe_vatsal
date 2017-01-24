package com.example.admin.remindme;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class extra_display_activity extends AppCompatActivity {

    static int id;
    SQLClass db;
    TextView one,two,three,four,five,six,seven,eight,nine,ten;

    public static Intent newIntent(Context context,int tmp)
    {
        id=tmp;
        Intent i=new Intent(context,extra_display_activity.class);
        return i;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_display_activity);

        db=new SQLClass(this);
        one=(TextView) findViewById(R.id.extra_textview_one);
        two=(TextView) findViewById(R.id.extra_textview_second);
        three=(TextView) findViewById(R.id.extra_textview_third);
        four=(TextView) findViewById(R.id.extra_textview_fourth);
        five=(TextView) findViewById(R.id.extra_textview_fifth);
        six=(TextView) findViewById(R.id.extra_textview_sixth);
        seven=(TextView) findViewById(R.id.extra_textview_seventh);
        eight=(TextView) findViewById(R.id.extra_textview_eight);
        nine=(TextView) findViewById(R.id.extra_textview_nine);
        ten=(TextView) findViewById(R.id.extra_textview_ten);

        Cursor data=db.viewAllExtra();
        if(data.getCount()!=0)
        {
            data.moveToFirst();
            do {
                if(id==data.getInt(0))
                {
                    one.setText(data.getString(1));
                    if(!data.getString(2).equals("null"))
                        two.setText(data.getString(2));
                    if(!data.getString(3).equals("null"))
                        three.setText(data.getString(3));
                    if(!data.getString(4).equals("null"))
                        four.setText(data.getString(4));
                    if(!data.getString(5).equals("null"))
                        five.setText(data.getString(5));
                    if(!data.getString(6).equals("null"))
                        six.setText(data.getString(6));
                    if(!data.getString(7).equals("null"))
                        seven.setText(data.getString(7));
                    if(!data.getString(8).equals("null"))
                        eight.setText(data.getString(8));
                    if(!data.getString(9).equals("null"))
                        nine.setText(data.getString(9));
                    if(!data.getString(10).equals("null"))
                        ten.setText(data.getString(10));
                }
            }while (data.moveToNext());
        }
    }
}
