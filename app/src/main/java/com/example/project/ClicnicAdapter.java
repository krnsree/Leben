package com.example.project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClicnicAdapter extends RecyclerView.Adapter<ClicnicAdapter.ViewHolder> {


    private final Context context;
    ArrayList<RVCell> clinicLists;

    public ClicnicAdapter(ArrayList<RVCell> clinicLists, Context context)
    {
        this.context=context;
        this.clinicLists=clinicLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.rv_cell,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(clinicLists.get(position).getName());
        holder.location.setText(clinicLists.get(position).getLocation());
        holder.time.setText(clinicLists.get(position).getTime());
        holder.phone.setText(clinicLists.get(position).getPhno());
        Log.e("tag", "onBindViewHolder: "+clinicLists.get(position).getName() );

    }



    @Override
    public int getItemCount() {
        return clinicLists.size();
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
