package com.example.admin.remindme;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {

    static String Name,Mobile,Email,Address,Date_Birth,Type,Gender;
    Spinner install_duration,snooze,day_remind;
    ArrayAdapter dataAdapter1,dataAdapter2,dataAdapter3;
    ArrayList<String> installment=new ArrayList<>();
    ArrayList<String> snooze_interval=new ArrayList<>();
    ArrayList<String> remind_day=new ArrayList<>();
    ImageView timepicker,startpicker,endpicker;
    EditText time,start_date,end_date,desc;
    int mHour,mMinute,mYear,mMonth,mDay;
    Button submit;
    SQLClass sql;

    public static Intent newIntent(Context context, String name,String mobile,String email,String address,String date_birth,String gender,String type)
    {
        Gender=gender;
        Type=type;
        Name=name;
        Mobile=mobile;
        Email=email;
        Address=address;
        Date_Birth=date_birth;
        Intent i=new Intent(context,Main2Activity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        installment.add("1 Month");
        installment.add("2 Month");
        installment.add("3 Month");
        installment.add("4 Month");
        installment.add("5 Month");
        installment.add("6 Month");
        installment.add("7 Month");
        installment.add("8 Month");
        installment.add("9 Month");
        installment.add("10 Month");
        installment.add("11 Month");
        installment.add("12 Month");

        snooze_interval.add("1 Hour");
        snooze_interval.add("2 Hour");
        snooze_interval.add("4 Hour");
        snooze_interval.add("6 Hour");
        snooze_interval.add("8 Hour");
        snooze_interval.add("12 Hour");
        snooze_interval.add("24 Hour");

        remind_day.add("7 Days");
        remind_day.add("15 Days");
        remind_day.add("30 Days");

        dataAdapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, installment);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dataAdapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, snooze_interval);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dataAdapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, remind_day);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sql=new SQLClass(this);
        AddData();
    }
    public void AddData()
    {
        desc=(EditText) findViewById(R.id.edttext_form_description);
        start_date=(EditText) findViewById(R.id.edittext_form_start_date);
        end_date=(EditText) findViewById(R.id.edittext_form_end_date);
        time=(EditText) findViewById(R.id.edittext_form_remind_time);
        install_duration=(Spinner) findViewById(R.id.spinner_form_install_month);
        snooze=(Spinner) findViewById(R.id.spinner_form_snooze_time);
        day_remind=(Spinner) findViewById(R.id.spinner_form_remind_day);
        install_duration.setAdapter(dataAdapter1);
        snooze.setAdapter(dataAdapter2);
        day_remind.setAdapter(dataAdapter3);
        timepicker=(ImageView) findViewById(R.id.imageview_form_remind_time);
        startpicker=(ImageView) findViewById(R.id.imageview_form_start_date);
        endpicker=(ImageView) findViewById(R.id.imageview_form_end_date);
        submit=(Button) findViewById(R.id.button_submit);
        timepicker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Calendar c = Calendar.getInstance();
                        mHour = c.get(Calendar.HOUR_OF_DAY);
                        mMinute = c.get(Calendar.MINUTE);

                        TimePickerDialog timePickerDialog = new TimePickerDialog(Main2Activity.this,
                                new TimePickerDialog.OnTimeSetListener() {

                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay,
                                                          int minute) {

                                        time.setText(hourOfDay + ":" + minute);
                                    }
                                }, mHour, mMinute, false);
                        timePickerDialog.show();
                    }
                }
        );
        startpicker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(Main2Activity.this,
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {

                                        start_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();
                    }
                }
        );
        endpicker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(Main2Activity.this,
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {

                                        end_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();
                    }
                    }

        );
        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        boolean isInserted=sql.insertdata(Address,Date_Birth,Email,end_date.getText().toString()
                                ,Gender,Mobile,Name,start_date.getText().toString()
                                ,time.getText().toString(),snooze.getSelectedItem().toString(),day_remind.getSelectedItem().toString()
                                ,install_duration.getSelectedItem().toString(),desc.getText().toString(),Type);

                        if(isInserted==true)
                        {
                            Toast.makeText(Main2Activity.this,"Data Successfully Added",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Main2Activity.this,recycler_view_activity.class));
                        }
                        else
                        {
                            Toast.makeText(Main2Activity.this,"Data is not Added",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

}
