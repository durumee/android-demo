package com.nrzm.bottomnavigationviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

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

        // ViewPager2에 책장 넘기는 효과 적용
//        viewPager.setPageTransformer(new BookFlipPageTransformer());
        // ViewPager2에 페이드 인/아웃 효과 적용
//        viewPager.setPageTransformer(new FadePageTransformer());
        // ViewPager2에 이동 방향의 모서리 하단에서 나타나는 효과 적용
//        viewPager.setPageTransformer(new DiagonalDownPageTransformer());
        // ViewPager2에 이동 방향에 따라 모서리 하단/상단에서 나타나는 효과 적용
        viewPager.setPageTransformer(new DiagonalUpDownPageTransformer());

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

    // 책장 넘기는 효과를 위한 PageTransformer
    private class BookFlipPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(View page, float position) {
            int pageWidth = page.getWidth();
            int pageHeight = page.getHeight();

            if (position < -1) {
                page.setAlpha(0f);
            } else if (position <= 1) {
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float verticalMargin = pageHeight * (1 - scaleFactor) / 2;
                float horizontalMargin = pageWidth * (1 - scaleFactor) / 2;

                if (position < 0) {
                    page.setTranslationX(horizontalMargin - verticalMargin / 2);
                } else {
                    page.setTranslationX(-horizontalMargin + verticalMargin / 2);
                }

                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);

                page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            } else {
                page.setAlpha(0f);
            }
        }
    }

    // 페이드 인/아웃 애니메이션을 위한 PageTransformer
    private class FadePageTransformer implements ViewPager2.PageTransformer {
        @Override
        public void transformPage(View page, float position) {
            if (position < -1 || position > 1) {
                page.setAlpha(0);
            } else if (position <= 0 || position <= 1) {
                page.setAlpha(1 - Math.abs(position));
            } else {
                page.setAlpha(position);
            }
        }
    }

    // 커스텀 애니메이션 효과를 위한 PageTransformer
    // 좌측 이동 시 우상단에서 내려옴, 우측 이동 시 좌상단에서 내려옴
    private class DiagonalDownPageTransformer implements ViewPager2.PageTransformer {
        @Override
        public void transformPage(View page, float position) {
            float pageWidth = page.getWidth();
            float pageHeight = page.getHeight();

            if (position < -1) {
                page.setAlpha(0f);
            } else if (position <= 0) {
                // 좌측으로 이동
                page.setAlpha(1 + position);
                page.setPivotX(pageWidth);
                page.setPivotY(pageHeight);
                page.setTranslationX(pageWidth * -position);
                page.setTranslationY(pageHeight * position);
                page.setScaleX(1 + position);
                page.setScaleY(1 + position);
            } else if (position <= 1) {
                // 우측으로 이동
                page.setAlpha(1 - position);
                page.setPivotX(0f);
                page.setPivotY(pageHeight);
                page.setTranslationX(pageWidth * -position);
                page.setTranslationY(pageHeight * -position);
                page.setScaleX(1 - position);
                page.setScaleY(1 - position);
            } else {
                page.setAlpha(0f);
            }
        }
    }

    //좌측 이동 시 우측 하단에서 올라오고, 우측 이동 시 좌측 상단에서 내려오는 효과
    private class DiagonalUpDownPageTransformer implements ViewPager2.PageTransformer {
        @Override
        public void transformPage(View page, float position) {
            float pageWidth = page.getWidth();
            float pageHeight = page.getHeight();

            if (position < -1) {
                page.setAlpha(0f);
            } else if (position <= 0) {
                // 좌측으로 이동
                page.setAlpha(1 + position);
                page.setPivotX(pageWidth);
                page.setPivotY(0f);
                page.setTranslationX(pageWidth * -position);
                page.setTranslationY(pageHeight * -position);
                page.setScaleX(1 + position);
                page.setScaleY(1 + position);
            } else if (position <= 1) {
                // 우측으로 이동
                page.setAlpha(1 - position);
                page.setPivotX(0f);
                page.setPivotY(pageHeight);
                page.setTranslationX(pageWidth * -position);
                page.setTranslationY(pageHeight * -position);
                page.setScaleX(1 - position);
                page.setScaleY(1 - position);
            } else {
                page.setAlpha(0f);
            }
        }
    }
}