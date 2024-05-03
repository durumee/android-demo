package kr.co.nrzm.cursoradapterdemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private CustomSQLHelper customSQLHelper;
    private SQLiteDatabase sqlDB;
    private Cursor cursor;
    private MyCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnInit = findViewById(R.id.btnInit);
        Button btnInsert = findViewById(R.id.btnInsert);
        Button btnSelect = findViewById(R.id.btnSelect);
        EditText edtName = findViewById(R.id.edtName);
        EditText edtNumber = findViewById(R.id.edtNumber);
        ListView listView = findViewById(R.id.listView);

        customSQLHelper = new CustomSQLHelper(this);

        adapter = new MyCursorAdapter(this, cursor, 0);
        listView.setAdapter(adapter);
        View headerView = getLayoutInflater().inflate(R.layout.list_header, null);
        listView.addHeaderView(headerView);

        refreshData();

        btnInit.setOnClickListener(v -> {
            sqlDB = customSQLHelper.getWritableDatabase();
            customSQLHelper.onUpgrade(sqlDB, 1, 2); // 뒤의 버전은 버전 구분 정보가 필요할때 활용
            sqlDB.close();

            refreshData();
        });

        btnInsert.setOnClickListener(v -> {
            sqlDB = customSQLHelper.getWritableDatabase();
            sqlDB.execSQL("INSERT INTO groupTBL (gName, gNumber) VALUES ( '" + edtName.getText().toString() + "', " + edtNumber.getText().toString() + ");");
            sqlDB.close();

            edtName.setText("");
            edtNumber.setText("");
            refreshData();
        });

        btnSelect.setOnClickListener(v -> {
            refreshData();
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            Log.i("보자보자", "position : " + String.valueOf(position));
            Log.i("보자보자", "id : " + String.valueOf(id));
            sqlDB = customSQLHelper.getWritableDatabase();
            sqlDB.execSQL("DELETE FROM groupTBL WHERE _id = " + id + ";");
            sqlDB.close();

            refreshData();
            return false;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cursor != null) cursor.close();
        if (sqlDB != null) sqlDB.close();
    }

    private void refreshData() {
        sqlDB = customSQLHelper.getWritableDatabase();
        cursor = sqlDB.rawQuery("SELECT * FROM groupTBL", null);
        adapter.changeCursor(cursor);
        sqlDB.close();
    }

    public static class CustomSQLHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "groupDB";
        private static final int DATABASE_VERSION = 1;

        public CustomSQLHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("DROP TABLE IF EXISTS shop_list");
            db.execSQL("CREATE TABLE groupTBL ( _id INTEGER PRIMARY KEY AUTOINCREMENT, gName TEXT, gNumber INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
    }

    public class MyCursorAdapter extends CursorAdapter {
        public MyCursorAdapter(Context context, Cursor cursor, int flags) {
            super(context, cursor, flags);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            // 새로운 아이템 뷰를 생성하는 메서드
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.list_item, parent, false);
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            // 아이템 뷰에 데이터를 바인딩하는 메서드
            TextView tvSeq = view.findViewById(R.id.tvSeq);
            TextView gName = view.findViewById(R.id.gName);
            TextView gNumber = view.findViewById(R.id.gNumber);

            int seqId = 0;
            String strName = null;
            int iNum = 0;
            seqId = cursor.getInt(0);
            strName = cursor.getString(1);
            iNum = cursor.getInt(2);
//            try {
//                seqId = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
//                strName = cursor.getString(cursor.getColumnIndexOrThrow("gName"));
//                iNum = cursor.getInt(cursor.getColumnIndexOrThrow("gNumber"));
//            } catch (IllegalArgumentException e) {
//            }

            tvSeq.setText(String.valueOf(seqId));
            gName.setText(strName);
            gNumber.setText(String.valueOf(iNum));
        }
    }
}