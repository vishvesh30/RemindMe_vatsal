package com.example.admin.remindme;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_category_name extends AppCompatActivity {

    SQLClass db;
    Button add_cat;
    EditText cat_name;
    int chk=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category_name);
        db=new SQLClass(this);
        cat_name=(EditText) findViewById(R.id.edittext_category_name);
        add_cat=(Button) findViewById(R.id.button_add_category);

        add_cat.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor data=db.viewAllcategory();
                        if(data.getCount()>0)
                        {
                            data.moveToFirst();
                            do {
                                if(data.getString(0).toLowerCase().equals(cat_name.getText().toString().toLowerCase()))
                                {
                                    chk=1;
                                    break;
                                }
                            }while (data.moveToNext());
                        }
                        if(chk==0)
                        {
                            boolean isInserted=db.insertcategory(cat_name.getText().toString());
                            if(isInserted==true)
                            {
                                Toast.makeText(add_category_name.this,"Data Added Successfully",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(add_category_name.this,DropDownActivity.class));
                            }
                            else
                            {
                                Toast.makeText(add_category_name.this,"Data is not Added",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(add_category_name.this,"Already Exist",Toast.LENGTH_SHORT).show();
                            chk=0;
                        }
                    }
                }
        );
    }
}
