package com.example.project;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;

import java.util.ArrayList;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {


    private final Context context;
    ArrayList<RVCell> hospitalLists;
    FragmentManager fm;

    public HospitalAdapter(ArrayList<RVCell> hospitalLists, Context context, FragmentManager activity)
    {
        Log.e("Tag", "HospitalAdapter: here" );
        this.context=context;
        this.hospitalLists=hospitalLists;
        this.fm=activity;
        Log.e("Tag", "HospitalAdapter: "+hospitalLists.size() );
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.rv_cell,parent,false);
        return new HospitalAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.title.setText(hospitalLists.get(position).getName());
        holder.location.setText(hospitalLists.get(position).getLocation());
        holder.time.setText(hospitalLists.get(position).getTime());
        holder.phone.setText(hospitalLists.get(position).getPhno());
       // Log.e("tag", "onBindViewHolder: "+hospitalLists.get(position).getName() );

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String list = gson.toJson(hospitalLists.get(position));
                Bundle bundle=new Bundle();
                bundle.putString("MD_Details",list);
                details_fragment df=new details_fragment();
                df.setArguments(bundle);
              fm.beginTransaction().replace(R.id.frameLayout
                      , df ).addToBackStack(null).commitAllowingStateLoss();
            }
        });

    }

    @Override
    public int getItemCount() {
        return hospitalLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title,location,time,phone;
        CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            location=itemView.findViewById(R.id.location);
            time=itemView.findViewById(R.id.time);
            phone=itemView.findViewById(R.id.phno);
            card=itemView.findViewById(R.id.card);
        }
    }

}
