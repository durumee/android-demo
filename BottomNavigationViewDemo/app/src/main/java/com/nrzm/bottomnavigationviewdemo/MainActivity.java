package com.nrzm.bottomnavigationviewdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_search) {
                replaceFragment(new SearchFragment());
                return true;
            } else if (item.getItemId() == R.id.menu_home) {
                replaceFragment(new HomeFragment());
                return true;
            } else if (item.getItemId() == R.id.menu_profile) {
                replaceFragment(new ProfileFragment());
                return true;
            }

            return false;
        });

        // 초기 화면 설정
        replaceFragment(new HomeFragment());
        bottomNavigationView.setSelectedItemId(R.id.menu_home);
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}