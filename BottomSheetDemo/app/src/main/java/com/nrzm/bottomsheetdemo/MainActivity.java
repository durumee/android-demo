package com.nrzm.bottomsheetdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MainActivity extends AppCompatActivity {

    private BottomSheetBehavior<View> bottomSheetBehavior;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShowBottomSheet = findViewById(R.id.btnShowBottomSheet);
        Button btnShowBottomSheetAutoClose = findViewById(R.id.btnShowBottomSheetAutoClose);
        TextView tvBsText = findViewById(R.id.tvBsText);
        View bottomSheet = findViewById(R.id.bottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        handler = new Handler();
        runnable = () -> {
            tvBsText.setText("Bottom Sheet 의 내용 영역입니다.");
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        };

        btnShowBottomSheet.setOnClickListener(v -> bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED));

        btnShowBottomSheetAutoClose.setOnClickListener(v -> {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            tvBsText.setText("Bottom Sheet 의 내용 영역입니다. 5초 뒤 사라집니다.");
            // Toast 흉내를 내려면 아래 코드를 사용
            handler.postDelayed(runnable, 5000);
        });

        Button btnCloseBottomSheet = findViewById(R.id.btnCloseBottomSheet);
        btnCloseBottomSheet.setOnClickListener(v -> bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable); // 액티비티 종료 시 runnable 제거
    }
}