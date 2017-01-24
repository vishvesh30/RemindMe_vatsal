package com.example.admin.remindme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by admin on 20-01-2017.
 */

public class recycler_view_details_activity extends AppCompatActivity {

    static DataBaseModel dx;
    TextView name,mobile,email,address,gender,birth_date;
    Button extra_button;

    public static Intent newIntent(Context context, DataBaseModel dbb)
    {
        dx=dbb;
        Intent intent=new Intent(context,recycler_view_details_activity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_details);

        name=(TextView) findViewById(R.id.recycler_details_name);
        mobile=(TextView) findViewById(R.id.recycler_details_mobile_no);
        email=(TextView) findViewById(R.id.recycler_details_email);
        address=(TextView) findViewById(R.id.recycler_details_address);
        gender=(TextView) findViewById(R.id.recycler_details_gender);
        birth_date=(TextView) findViewById(R.id.recycler_details_birth_date);

        name.setText(dx.getName());
        mobile.setText(dx.getMobile_No());
        email.setText(dx.getEmail_Id());
        address.setText(dx.getAddress());
        gender.setText(dx.getGender());
        birth_date.setText(dx.getBirth_Date());

        extra_button=(Button) findViewById(R.id.details_button_extra);
        extra_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i=dynamic_row.newIntent(recycler_view_details_activity.this,dx.getId());
                        startActivity(i);
                    }
                }
        );
    }
}
