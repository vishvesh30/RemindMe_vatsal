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

import org.joda.time.DateTimeUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


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

        long total_days = 0;
        DataBaseModel dbm=arrayList.get(position);
        ddd=dbm;

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        String currentDateTimeString;
        currentDateTimeString=mDay + "/" + (mMonth + 1) + "/" + mYear;
        String endDateTimeString=dbm.getEnd_Date();
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("dd/M/yyyy");

        try {

            Date date2 = simpleDateFormat.parse(endDateTimeString);
            Date date1 = simpleDateFormat.parse(currentDateTimeString);

            total_days=printDifference(date1, date2);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.no.setText(ddd.getMobile_No());
        holder.name.setText(ddd.getName());
       holder.days_left1.setText(String.valueOf(total_days));

        int progress=365-(int)total_days;
        if(progress<=0){
            progress=0;
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
    public long printDifference(Date startDate, Date endDate){

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays,
                elapsedHours, elapsedMinutes, elapsedSeconds);
        return elapsedDays;

    }
}
