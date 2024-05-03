package com.nrzm.viewpagertablayoutdemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        // ViewPager2 어댑터 설정
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        adapter.addFragment(new Fragment1(), "Tab 1");
        adapter.addFragment(new Fragment2(), "Tab 2");
        adapter.addFragment(new Fragment3(), "Tab 3");
        viewPager.setAdapter(adapter);

        // TabLayout과 ViewPager2 연결
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(adapter.getPageTitle(position))
        ).attach();
    }

    // ViewPager2 어댑터 클래스
    private class ViewPagerAdapter extends FragmentStateAdapter {
        private List<Fragment> fragmentList = new ArrayList<>();
        private List<String> fragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(MainActivity activity) {
            super(activity);
        }

        @Override
        public Fragment createFragment(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return fragmentList.size();
        }

        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }
    }
}