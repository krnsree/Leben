package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import Fragments.AccountFragment;
import Fragments.ClinicFragment;
import Fragments.HospitalFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePage extends AppCompatActivity {

    @BindView(R.id.hospitalCard)
    CardView HospitalCard;

    @BindView(R.id.clinicCard)
    CardView ClinicCard;

    @BindView(R.id.accountCard)
    CardView accountCard;

    @BindView(R.id.home)
    LinearLayout home;


    FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment homeFragment = new HospitalFragment();
    Fragment clinincFragment = new ClinicFragment();
    AccountFragment acc=new AccountFragment();
    Fragment activeFragment = homeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);

        home.setVisibility(View.VISIBLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        HospitalCard.setOnClickListener(v -> getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, homeFragment)
                .addToBackStack("HospitalList")
                .commitAllowingStateLoss());

        ClinicCard.setOnClickListener(v -> getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, clinincFragment)
                .addToBackStack("ClinicList")
                .commitAllowingStateLoss());

        accountCard.setOnClickListener(v ->
                acc.show(getSupportFragmentManager(),acc.getTag())
                );

    }


    @Override
    protected void onStart() {
        super.onStart();
        home.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        home.setVisibility(View.GONE);
    }

}
