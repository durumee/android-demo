package kr.co.nrzm.mpandroidchartmonthlydemo;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LineChart chart = findViewById(R.id.chart);

        // 30일간의 날짜 생성
        List<String> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd", Locale.getDefault());
        for (int i = 0; i < 30; i++) {
            dates.add(sdf.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        // 첫 번째 데이터셋 (몸무게 증가)
        List<Entry> entries1 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            entries1.add(new Entry(i, (float) (Math.random() * 30 + 70)));
        }
        LineDataSet dataSet1 = new LineDataSet(entries1, "체중관리 사례 #1 몸무게 증가");
        dataSet1.setColor(Color.RED);

        // 두 번째 데이터셋 (몸무게 감소)
        List<Entry> entries2 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            entries2.add(new Entry(i, (float) (Math.random() * -40 + 70)));
        }
        LineDataSet dataSet2 = new LineDataSet(entries2, "체중관리 사례 #2 몸무게 감소");
        dataSet2.setColor(Color.BLUE);

        // 데이터셋을 리스트에 추가
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet1);
        dataSets.add(dataSet2);

        // 데이터셋 리스트를 LineData에 전달
        LineData lineData = new LineData(dataSets);

        // 차트에 LineData 설정
        chart.setData(lineData);

        // X축 설정
        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return dates.get((int) value);
            }
        });
        xAxis.setLabelCount(8, true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        // Y축 설정
        chart.getAxisLeft().setAxisMinimum(0);
        chart.getAxisLeft().setAxisMaximum(120f);
        chart.getAxisRight().setAxisMinimum(0);
        chart.getAxisRight().setAxisMaximum(120f);

        // Description 설정
        Description description = new Description();
        description.setText("30일간의 몸무게 변화량");
        description.setTextSize(12f);
        description.setTextColor(Color.DKGRAY);
        chart.setDescription(description);

        chart.invalidate();
    }
}