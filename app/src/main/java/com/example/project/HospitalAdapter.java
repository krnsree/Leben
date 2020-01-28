package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class HospitalAdapter extends FirestoreRecyclerAdapter<RVCell,HospitalAdapter.ViewHolder> {

    Context context;

    public HospitalAdapter(@NonNull FirestoreRecyclerOptions<RVCell> options, Context context){
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull RVCell model)
    {
        holder.location.setText(model.getLocation());
        holder.title.setText(model.getName());
        holder.phone.setText(model.getPhno());
        holder.time.setText(model.getTime());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rv_cell, parent, false);
        return new ViewHolder(v);
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
