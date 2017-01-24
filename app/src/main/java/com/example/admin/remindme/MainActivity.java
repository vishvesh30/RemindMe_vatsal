package com.example.admin.remindme;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText name,mobile,email,address,date_birth;
    Button next;
    static String type;
    RadioGroup rdgp;
    RadioButton rdbutton;
    ImageView birth_date;
    int year_x;
    int month_x;
    int day_x;
    int Dialog_id=0;

    public static Intent newIntent(Context context,String item)
    {
        type=item;
        Intent i=new Intent(context,MainActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Calendar cal=Calendar.getInstance();
        year_x=cal.get(Calendar.YEAR);
        month_x=cal.get(Calendar.MONTH);
        day_x=cal.get(Calendar.DAY_OF_MONTH);
        AddData();
    }
    public void AddData()
    {
        birth_date=(ImageView) findViewById(R.id.imageview_form_birth_date);
        rdgp=(RadioGroup) findViewById(R.id.radiogroup_form);
        name=(EditText) findViewById(R.id.edittext_form_name);
        mobile=(EditText) findViewById(R.id.edittext_form_mobile_no);
        email=(EditText) findViewById(R.id.edittext_form_email);
        address=(EditText) findViewById(R.id.edittext_form_address);
        date_birth=(EditText) findViewById(R.id.edittext_form_date_birth);
        next=(Button) findViewById(R.id.button_form_submit);

        birth_date.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog(Dialog_id);
                    }
                }
        );

        next.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int id=rdgp.getCheckedRadioButtonId();
                        rdbutton=(RadioButton) findViewById(id);
                        Intent i = Main2Activity.newIntent(MainActivity.this,name.getText().toString(),mobile.getText().toString()
                        ,email.getText().toString(),address.getText().toString(),date_birth.getText().toString(),rdbutton.getText().toString(),type);
                        startActivity(i);
                    }
                }
        );
    }
    @Override
    public Dialog onCreateDialog(int id)
    {
        if(id==Dialog_id)
        {
            return new DatePickerDialog(MainActivity.this,kDateListener,year_x,month_x,day_x);
        }
        return null;
    }
    protected DatePickerDialog.OnDateSetListener kDateListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            month_x=month+1;
            year_x=year;
            day_x=dayOfMonth;
            date_birth.setText(day_x+"/"+month_x+"/"+year_x);
        }
    };
}
