package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HospitalFragment extends Fragment {

    @BindView(R.id.hospitalList)
    RecyclerView hospitalList;

    @BindView(R.id.rvProgressBar)
    ProgressBar progressBar;

    CollectionReference medcenters;
    FirebaseFirestore ref;

    ArrayList<RVCell> hospitalLists= new ArrayList<>();

    HospitalAdapter hospitalAdapter;

    private String TAG="TAG";

    public HospitalFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_hospital, container, false);
        ButterKnife.bind(this,view);
        progressBar.setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hospitalList.setHasFixedSize(true);
        hospitalAdapter=new HospitalAdapter(hospitalLists,getContext());
        hospitalList.setAdapter(hospitalAdapter);
        getItems();

    }

    private void getItems() {

        ref=FirebaseFirestore.getInstance();

        ref.collection("MedicalCenters")
                .get()
                .addOnCompleteListener(task -> {

                    progressBar.setVisibility(View.INVISIBLE);
                    if(task.isSuccessful())
                    {

                        for(QueryDocumentSnapshot documentSnapshot : task.getResult())
                        {
                            if (documentSnapshot.exists()) {
                                RVCell rvcell = new RVCell();
                                rvcell.setName(String.valueOf(documentSnapshot.getData().get("name")));
                                rvcell.setAddress(String.valueOf(documentSnapshot.getData().get("address")));
                                rvcell.setLocation(String.valueOf(documentSnapshot.getData().get("location")));
                                rvcell.setTime(String.valueOf(documentSnapshot.getData().get("time")));
                                rvcell.setPhno(String.valueOf(documentSnapshot.getData().get("phno")));
                                hospitalLists.add(rvcell);
                            }

                        }
                        hospitalAdapter.notifyDataSetChanged();

                    }
                    else
                        Toast.makeText(getContext(), "No hospital;s available", Toast.LENGTH_SHORT).show();
                });
    }

}
