package com.example.admin.remindme;

/**
 * Created by admin on 24-01-2017.
 */

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by vishvraj on 20-01-2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {
    static DataBaseModel ddd;
    ArrayList<DataBaseModel> arrayList=new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name,no,days_left1;
        public ProgressBar progressBar;

        public MyViewHolder(View view) {
            super(view);
/*            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
            totaldays = (TextView) view.findViewById(R.id.total_days);*/
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar2);
            name=(TextView)view.findViewById(R.id.textView_name);
            no=(TextView)view.findViewById(R.id.textView_no);
            days_left1=(TextView)view.findViewById(R.id.days_left);
        }
    }


    public MoviesAdapter(ArrayList<DataBaseModel> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movies_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        setAnimation(holder.itemView, position);
        ddd= arrayList.get(position);

        //for current date
    /*    Calendar calendar = Calendar.getInstance();
        int curr_year = calendar.get(Calendar.YEAR);
        int curr_month = calendar.get(Calendar.MONTH);
        int curr_date = calendar.get(Calendar.DATE);
        curr_month += 1;*/

        //for total days available
        int total_days = 0;

        //date from sqlite database
        //int db_year = Integer.parseInt(arrayList.getYear());
        //int db_month = Integer.parseInt(arrayList.getMonth());
        //int db_date = Integer.parseInt(arrayList.getDate());

        //for jola's time api dont forget to add gradle dependancy
        // String date_from_db = String.valueOf(db_date) + "/" + String.valueOf(db_month) + "/" + String.valueOf(db_year) + " 09:29:58";
        //String date_from_curr = String.valueOf(curr_date) + "/" + String.valueOf(curr_month) + "/" + String.valueOf(curr_year) + " 09:29:58";

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Date d1 = null;
        Date d2 = null;

        DataBaseModel dbm=arrayList.get(position);
        ddd=dbm;

        try {
            d1 = format.parse(ddd.getEnd_Date());
            d2 = format.parse(DateFormat.getDateTimeInstance().format(new Date()));
            DateTime dt1 = new DateTime(d1);
            DateTime dt2 = new DateTime(d2);
            total_days = Days.daysBetween(dt1, dt2).getDays();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //displaying data
        /*String no = "Mobile No:-\t" + movie.getTitle();
        String genre = "Genre:-\t" + movie.getGenre();
        String Date = "Date:-\t" + date_from_db;
        String days = "Total Days:-\t" + String.valueOf(total_days);
        holder.title.setText(title);
        holder.genre.setText(genre);
        holder.year.setText(Date);
        holder.totaldays.setText(days);*/

        holder.no.setText(ddd.getMobile_No());
        holder.name.setText(ddd.getName());
       holder.days_left1.setText(String.valueOf(total_days));

        int progress=365-total_days;
        if(progress<=0){
            progress=365;
        }
        if(progress>350) {
            holder.progressBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        }else if(progress>300 && progress<=350) {
            holder.progressBar.getProgressDrawable().setColorFilter(Color.MAGENTA, PorterDuff.Mode.SRC_IN);
        }else if(progress>200 && progress<=300) {
            holder.progressBar.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
        }else if(progress>100 && progress<=200) {
            holder.progressBar.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
        }else{
            holder.progressBar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
        }
        holder.progressBar.setProgress(progress);
    }
    private int lastPosition = -1;
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
