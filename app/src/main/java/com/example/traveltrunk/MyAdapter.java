package com.example.traveltrunk;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList loc_id, des_id, tim_id, date_id, airlin_id;

    public MyAdapter(Context context, ArrayList loc_id, ArrayList des_id, ArrayList tim_id,ArrayList date_id, ArrayList airlin_id) {
        this.context = context;
        this.loc_id = loc_id;
        this.des_id = des_id;
        this.tim_id = tim_id;
        this.date_id = date_id;
        this.airlin_id = airlin_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.flightentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.loc_id.setText(String.valueOf(loc_id.get(position)));
        holder.des_id.setText(String.valueOf(des_id.get(position)));
        holder.tim_id.setText(String.valueOf(tim_id.get(position)));
        holder.date_id.setText(String.valueOf(date_id.get(position)));
        holder.airlin_id.setText(String.valueOf(airlin_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return loc_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView loc_id, des_id, tim_id, date_id, airlin_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            loc_id = itemView.findViewById(R.id.textloc);
            des_id = itemView.findViewById(R.id.textdes);
            tim_id = itemView.findViewById(R.id.texttim);
            date_id = itemView.findViewById(R.id.textdate);
            airlin_id = itemView.findViewById(R.id.textairlin);
        }
    }
}