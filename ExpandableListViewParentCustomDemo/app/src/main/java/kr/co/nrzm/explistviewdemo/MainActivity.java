package kr.co.nrzm.explistviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private MyExpandableListAdapter adapter;
    private List<String> parentList;
    private Map<String, List<String>> childMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableListView = findViewById(R.id.expandableListView);
        expandableListView.setGroupIndicator(null); // 그룹 인디케이터(펼침/닫힘 아이콘) 제거

        prepareData();

        adapter = new MyExpandableListAdapter(this, parentList, childMap);
        expandableListView.setAdapter(adapter);
    }

    private void prepareData() {
        parentList = new ArrayList<>();
        childMap = new HashMap<>();

        parentList.add("Parent 1");
        parentList.add("Parent 2");
        parentList.add("Parent 3");

        List<String> child1 = new ArrayList<>();
        child1.add("Child 1");
        child1.add("Child 2");

        List<String> child2 = new ArrayList<>();
        child2.add("Child 3");
        child2.add("Child 4");
        child2.add("Child 5");

        List<String> child3 = new ArrayList<>();
        child3.add("Child 6");

        childMap.put(parentList.get(0), child1);
        childMap.put(parentList.get(1), child2);
        childMap.put(parentList.get(2), child3);
    }

    private class MyExpandableListAdapter extends BaseExpandableListAdapter {

        private Context context;
        private List<String> parentList;
        private Map<String, List<String>> childMap;

        public MyExpandableListAdapter(Context context, List<String> parentList, Map<String, List<String>> childMap) {
            this.context = context;
            this.parentList = parentList;
            this.childMap = childMap;
        }

        @Override
        public int getGroupCount() {
            return parentList.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return childMap.get(parentList.get(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return parentList.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childMap.get(parentList.get(groupPosition)).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.list_item_parent, null);
            }

            CheckBox parentCb = convertView.findViewById(R.id.parentCb);
            parentCb.setText(parentList.get(groupPosition));
            parentCb.setChecked(isExpanded);

            convertView.setOnClickListener(v -> {
                if (isExpanded) {
                    expandableListView.collapseGroup(groupPosition);
                } else {
                    expandableListView.expandGroup(groupPosition);
                }
            });

            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_item_child, null);
            }

            CheckBox checkBox = convertView.findViewById(R.id.checkBox);
            checkBox.setText(childMap.get(parentList.get(groupPosition)).get(childPosition));
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // 체크박스 상태 변경 처리
                    if (isChecked) {
                        // 체크박스가 선택되었을 때의 동작
                        Log.i("체크박스", "체크");
                    } else {
                        // 체크박스가 선택 해제되었을 때의 동작
                        Log.i("체크박스", "언체크");
                    }
                }
            });
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}