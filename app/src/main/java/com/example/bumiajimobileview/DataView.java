package com.example.bumiajimobileview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bumiajimobileview.databinding.ActivityDataViewBinding;
import com.example.bumiajimobileview.databinding.ActivityMainBinding;

public class DataView extends AppCompatActivity {
        ActivityDataViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityDataViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new Fragment_latest());

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.latest) {
                replaceFragment(new Fragment_latest());
            } else if (itemId == R.id.table) {
                replaceFragment(new Fragment_table());
            } else if (itemId == R.id.chart) {
                replaceFragment(new Fragment_chart());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment);
            fragmentTransaction.commit();
        }

}
