package kr.co.nrzm.mpandroidchartdemo;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LineChart chart = findViewById(R.id.chart);

        // 첫 번째 데이터셋
        List<Entry> entries1 = new ArrayList<>();
        entries1.add(new Entry(0f, 10f));
        entries1.add(new Entry(1f, 20f));
        entries1.add(new Entry(2f, 15f));
        LineDataSet dataSet1 = new LineDataSet(entries1, "레이블 1");
        dataSet1.setColor(Color.RED);  // 빨간색

        // 두 번째 데이터셋
        List<Entry> entries2 = new ArrayList<>();
        entries2.add(new Entry(0f, 5f));
        entries2.add(new Entry(1f, 15f));
        entries2.add(new Entry(2f, 10f));
        LineDataSet dataSet2 = new LineDataSet(entries2, "레이블 2");
        dataSet2.setColor(Color.GREEN);  // 초록색

        // 세 번째 데이터셋
        List<Entry> entries3 = new ArrayList<>();
        entries3.add(new Entry(0f, 8f));
        entries3.add(new Entry(1f, 12f));
        entries3.add(new Entry(2f, 20f));
        LineDataSet dataSet3 = new LineDataSet(entries3, "레이블 3");
        dataSet3.setColor(Color.BLUE);  // 파란색

        // 데이터셋을 리스트에 추가
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet1);
        dataSets.add(dataSet2);
        dataSets.add(dataSet3);

        // 데이터셋 리스트를 LineData에 전달
        LineData lineData = new LineData(dataSets);

        // 차트에 LineData 설정
        chart.setData(lineData);
        chart.invalidate();
    }
}