package com.example.project;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePage extends AppCompatActivity {

    /*@BindView(R.id.navBar)
    ChipNavigationBar navBar;
*/

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
    Fragment accountFragment = new AccountFragment();
    Fragment activeFragment = homeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);

        home.setVisibility(View.VISIBLE);

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

        accountCard.setOnClickListener(v -> getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, accountFragment)
                .addToBackStack("AccountPage")
                .commitAllowingStateLoss());


    }

    @Override
    protected void onStop() {
        super.onStop();
        home.setVisibility(View.GONE);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        return true;
    }

}
