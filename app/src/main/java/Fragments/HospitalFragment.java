package Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

import Adapter.MCAdapter;
import Models.RVCell;
import butterknife.BindView;
import butterknife.ButterKnife;


public class HospitalFragment extends Fragment {

    @BindView(R.id.hospitalList)
    RecyclerView hospitalList;

    @BindView(R.id.hrshimmer)
    ShimmerFrameLayout hrshimmer;

    /*@BindView(R.id.event_action_bar)
    Toolbar toolbar;*/

    CollectionReference medcenters;
    FirebaseFirestore ref;

    static ArrayList<RVCell> hospitalLists = new ArrayList<>();

    MCAdapter hospitalAdapter;

    private String TAG = "TAG";

    static boolean isDataAvailable;

    public HospitalFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hospital, container, false);
        ButterKnife.bind(this, view);
        getActivity().findViewById(R.id.home).setVisibility(View.GONE);


        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        isDataAvailable=false;
        /*AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }*/

        hospitalList.setHasFixedSize(true);
        hospitalAdapter = new MCAdapter(hospitalLists, getContext(), getActivity().getSupportFragmentManager(),getActivity());
        hospitalList.setAdapter(hospitalAdapter);
        getItems();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void getItems() {

        hrshimmer.startShimmerAnimation();
        if (hospitalLists != null && hospitalLists.size() > 0) {
            Log.e(TAG, "onItems: 1");
            // hospitalAdapter.notifyDataSetChanged();
            isDataAvailable = true;
            hrshimmer.stopShimmerAnimation();
            hrshimmer.setVisibility(View.GONE);
            hospitalList.setAdapter(hospitalAdapter);
            return;
        }

        Log.e(TAG, "onItems: 2");

        ref = FirebaseFirestore.getInstance();

        ref.collection("MedicalCenters")
                .whereEqualTo("type", "hospital")
                .get()
                .addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {

                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            if (documentSnapshot.exists()) {
                                RVCell rvcell = new RVCell();
                                rvcell.setName(String.valueOf(documentSnapshot.getData().get("name")));
                                rvcell.setAddress(String.valueOf(documentSnapshot.getData().get("address")));
                                rvcell.setLocation(String.valueOf(documentSnapshot.getData().get("location")));
                                rvcell.setTime(String.valueOf(documentSnapshot.getData().get("time")));
                                rvcell.setPhno(String.valueOf(documentSnapshot.getData().get("phno")));
                                rvcell.setHomeurl(String.valueOf(documentSnapshot.getData().get("homeurl")));
                                if (documentSnapshot.getData().get("services") != null)
                                    rvcell.setServices((ArrayList<String>) documentSnapshot.getData().get("services"));
                                if (documentSnapshot.getData().get("dept") != null)
                                    rvcell.setDept((ArrayList<String>) documentSnapshot.getData().get("dept"));
                                hospitalLists.add(rvcell);
                                rvcell.setLatitude(String.valueOf(documentSnapshot.getData().get("lat")));
                                rvcell.setLongitude(String.valueOf(documentSnapshot.getData().get("lon")));
                            }

                        }
                        hospitalAdapter.notifyDataSetChanged();
                        hrshimmer.stopShimmerAnimation();
                        hrshimmer.setVisibility(View.GONE);

                    } else
                        Toast.makeText(getContext(), "No hospitals available", Toast.LENGTH_SHORT).show();
                });
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();

        getActivity().findViewById(R.id.home).setVisibility(View.GONE);

        if (isDataAvailable) {
            hrshimmer.stopShimmerAnimation();
            hrshimmer.setVisibility(View.GONE);
        } else {
            hrshimmer.setVisibility(View.VISIBLE);
            hrshimmer.startShimmerAnimation();
        }

        Log.e(TAG, "onStart: 1");

    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().findViewById(R.id.home).setVisibility(View.GONE);

        if (isDataAvailable) {
            hrshimmer.stopShimmerAnimation();
            hrshimmer.setVisibility(View.GONE);
        } else {
            hrshimmer.setVisibility(View.VISIBLE);
            hrshimmer.startShimmerAnimation();
        }
        getItems();

    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().findViewById(R.id.home).setVisibility(View.VISIBLE);
    }


}
