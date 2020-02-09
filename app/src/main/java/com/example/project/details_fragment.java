package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.Gson;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.WindowManager.LayoutParams;


public class details_fragment extends Fragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.hospitalName)
    TextView hospitalName;

    @BindView(R.id.address)
    TextView hospitalAddress;

    @BindView(R.id.ratingBar)
    RatingBar ratingBar;

    @BindView(R.id.rating)
    TextView hospitalRating;

    @BindView(R.id.dp_time)
    TextView hospitalTime;

    @BindView(R.id.phone_num)
    TextView hospitalPhnonum;

    @BindView(R.id.url)
    TextView hospitalLink;

    @BindView(R.id.dp_location)
    TextView hospitalLocation;

    @BindView(R.id.services_rv)
    RecyclerView services_rv;

    @BindView(R.id.dept_rv)
    RecyclerView dept_rv;

    @BindView(R.id.comments_rv)
    RecyclerView commentss_rv;

    String name, address, homeurl, contact, location, time;

    RVCell listData = new RVCell();
    static ArrayList<String> services = new ArrayList<>();
    static ArrayList<String> departments = new ArrayList<>();


    public details_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details_fragment, container, false);

        ButterKnife.bind(this, view);

        getActivity().getWindow().setFlags(LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        collapsingToolbar.setContentScrimColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        collapsingToolbar.setTitle("Leben");
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedToolbar);
        collapsingToolbar.setTitleEnabled(true);
        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                startActivity(new Intent(getActivity(), HomePage.class));
                return true;
            }
            return false;
        });

        if (getArguments() != null) {
            String list = getArguments().getString("MD_Details");
            Gson gson = new Gson();
            listData = gson.fromJson(list, RVCell.class);
        }
        setItems();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ChipNavigationBar bottomNavigationView = getActivity().findViewById(R.id.navBar);
        bottomNavigationView.setVisibility(View.GONE);

    }

    private void setItems() {

        hospitalName.setText(listData.getName());
        hospitalAddress.setText(listData.getAddress());
        hospitalLink.setText(listData.getHomeurl());
        hospitalLocation.setText(listData.getLocation());
        hospitalTime.setText(listData.getTime());
        hospitalPhnonum.setText(listData.getPhno());

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
