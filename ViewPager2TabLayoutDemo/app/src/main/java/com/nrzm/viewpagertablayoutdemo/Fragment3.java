package com.nrzm.viewpagertablayoutdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class Fragment3 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Fragment 3의 레이아웃 inflate
        View view = inflater.inflate(R.layout.fragment_3, container, false);

        // Fragment 3의 UI 설정 및 로직 구현

        return view;
    }
}
