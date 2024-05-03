package com.nrzm.bottomnavigationviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // ViewPager2와 BottomNavigationView 연결
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_search) {
                viewPager.setCurrentItem(0);
                return true;
            } else if (item.getItemId() == R.id.menu_home) {
                viewPager.setCurrentItem(1);
                return true;
            } else if (item.getItemId() == R.id.menu_profile) {
                viewPager.setCurrentItem(2);
                return true;
            }

            return false;
        });

        // 초기 화면 설정
        bottomNavigationView.setSelectedItemId(R.id.menu_home);
    }

    private static class ViewPagerAdapter extends FragmentStateAdapter {
        private final Context context;

        public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
            this.context = fragmentActivity;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new SearchFragment();
                case 1:
                    return new HomeFragment();
                case 2:
                    return new ProfileFragment();
                default:
                    throw new IllegalArgumentException("Invalid position");
            }
        }

        @Override
        public int getItemCount() {
            // menu.xml에서 메뉴 아이템의 개수를 가져옴
            MenuInflater inflater = new MenuInflater(context);
            Menu menu = new PopupMenu(context, null).getMenu();
            inflater.inflate(R.menu.bottom_navigation_menu, menu);
            return menu.size();
        }
    }
}