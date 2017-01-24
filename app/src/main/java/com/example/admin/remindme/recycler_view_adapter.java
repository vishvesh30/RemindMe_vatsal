package com.example.admin.remindme;

/**
 * Created by abc on 1/19/2017.
 */

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by abc on 1/11/2017.
 */
public class recycler_view_adapter extends RecyclerView.Adapter<recycler_view_adapter.RecyclerViewHolder> {
    ArrayList<DataBaseModel> arrayList=new ArrayList<>();
    ImageButton mShareButton;
    static DataBaseModel ddd;

    public recycler_view_adapter(ArrayList<DataBaseModel> arrayList) {
        this.arrayList=arrayList;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,parent,false);
        RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(view);
        return recyclerViewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        DataBaseModel dbm=arrayList.get(position);
        ddd=dbm;
        holder.name.setText(dbm.getName());
        holder.no.setText(dbm.getMobile_No());
        setAnimation(holder.itemView, position);
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

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView no,name;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            no=(TextView)itemView.findViewById(R.id.textView_no);
            name=(TextView)itemView.findViewById(R.id.textView_name);
            itemView.setOnClickListener(this);
            mShareButton=(ImageButton)itemView.findViewById(R.id.imageButton_share);
            mShareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("application/pdf");
                    sharingIntent.putExtra(Intent.EXTRA_STREAM,"");
                    v.getContext().startActivity(Intent.createChooser(sharingIntent, "Share via"));


                }
            });
        }
        @Override
        public void onClick(View v) {
            int pos=getAdapterPosition();
            ddd =arrayList.get(pos);
            Intent i=recycler_view_details_activity.newIntent(v.getContext(),ddd);
            v.getContext().startActivity(i);

        }

    }
}