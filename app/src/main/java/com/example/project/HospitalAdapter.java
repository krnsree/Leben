package com.example.project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {


    private final Context context;
    ArrayList<RVCell> hospitalLists;
    ProgressBar progressBar;

    public HospitalAdapter(ArrayList<RVCell> hospitalLists, Context context)
    {
        this.context=context;
        this.hospitalLists=hospitalLists;
        this.progressBar=progressBar;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.rv_cell,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.title.setText(hospitalLists.get(position).getName());
        holder.location.setText(hospitalLists.get(position).getLocation());
        holder.time.setText(hospitalLists.get(position).getTime());
        holder.phone.setText(hospitalLists.get(position).getPhno());
        Log.e("tag", "onBindViewHolder: "+hospitalLists.get(position).getName() );
    }

    @Override
    public int getItemCount() {
        return hospitalLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title,location,time,phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            location=itemView.findViewById(R.id.location);
            time=itemView.findViewById(R.id.time);
            phone=itemView.findViewById(R.id.phno);
        }
    }

}
