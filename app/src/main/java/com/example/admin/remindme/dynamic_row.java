package com.example.admin.remindme;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.app.ActionBar.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by abc on 1/22/2017.
 */
public class dynamic_row extends AppCompatActivity{

    ArrayList<String> arr=new ArrayList<>();
    Button add_field;
    Button add_more_field,submit;
    EditText name_of_field;
    SQLClass db;
    static int id;
    String tmp;
    TextView name;
    EditText val;
    int x=0;

    public static Intent newIntent(Context context,int tmp)
    {
        Intent i=new Intent(context,dynamic_row.class);
        id=tmp;
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_row_fields);
        db=new SQLClass(this);
        final LinearLayout lm=(LinearLayout)findViewById(R.id.ll);
        add_field=(Button)findViewById(R.id.btn);
        name_of_field=(EditText)findViewById(R.id.field_name);
        name_of_field.setEnabled(false);
        add_field.setEnabled(false);
        add_more_field=(Button)findViewById(R.id.btn1);
        add_more_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(x!=0)
                {
                    arr.add(name.getText().toString()+"____"+tmp);
                }
                x=1;
                add_field.setEnabled(true);
                name_of_field.setEnabled(true);
                Toast.makeText(getApplicationContext(),"Enter the name of field",Toast.LENGTH_SHORT);
            }
        });
        add_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_field.setEnabled(false);
                name_of_field.setEnabled(false);
                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
                // Create LinearLayout
                LinearLayout ll = new LinearLayout(getApplicationContext());
                ll.setOrientation(LinearLayout.HORIZONTAL);

                // Create TextView
               name = new TextView(getApplicationContext());
                name.setText(name_of_field.getText());
                name.setTextColor(Color.parseColor("#000000"));
                ll.addView(name);
                // Create EditText
                val= new EditText(getApplicationContext());
                val.setWidth(500);
                val.setTextColor(Color.parseColor("#000000"));
                ll.addView(val);

                val.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.length() > 0)
                {
                    tmp=s.toString();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });
                //Add button to LinearLayout defined in XML
                lm.addView(ll);



            }
        });
        submit=(Button) findViewById(R.id.btn2);
        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        arr.add(name.getText().toString()+"____"+tmp);
                        boolean isInserted=db.insertextra(arr,id);
                        if(isInserted==true)
                        {
                            Toast.makeText(dynamic_row.this,"Data Added Successfully",Toast.LENGTH_SHORT).show();
                            Intent i=extra_display_activity.newIntent(dynamic_row.this,id);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(dynamic_row.this,"Data is not Added",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}
