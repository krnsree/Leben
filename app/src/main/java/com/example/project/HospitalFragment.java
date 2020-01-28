package com.example.project;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HospitalFragment extends Fragment {

    @BindView(R.id.hospitalList)
    RecyclerView hospitalList;

    @BindView(R.id.rvProgressBar)
    ProgressBar progressBar;

    CollectionReference medcenters;
    FirebaseFirestore ref;

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
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.e(TAG, "onViewCreated: "+hospitalList);

        progressBar.setVisibility(View.VISIBLE);

        rvSetUp();
        dbSetup();
    }

    private void dbSetup() {
        ref=FirebaseFirestore.getInstance();
        medcenters=ref.collection("MedicalCenters");
        Query query=medcenters.orderBy("name");
        FirestoreRecyclerOptions<RVCell> options=new FirestoreRecyclerOptions.Builder<RVCell>()
                .setQuery(query,RVCell.class)
                .build();
        hospitalAdapter=new HospitalAdapter(options,getContext());
        hospitalList.setAdapter(hospitalAdapter);
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void rvSetUp() {

        hospitalList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        hospitalList.setLayoutManager(layoutManager);
    }

    @Override
    public void onStart() {
        super.onStart();
        hospitalAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        hospitalAdapter.stopListening();
    }
}
