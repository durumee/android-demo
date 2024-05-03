package co.kr.nrzm.drawlayoutdemo;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawer_layout = findViewById(R.id.drawer_layout);
        Button btn_main = findViewById(R.id.btn_main);
        Button btn_close = findViewById(R.id.btn_close);

        // drawer 잠금, 명령에 의해서만 열림
//        mBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        // swipe 동작을 통해서도 drawer 열림
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

        // 열기 버튼
        btn_main.setOnClickListener(v -> {
            // 기본값인 왼쪽으로 열릴때는 아래 코드로 드로어를 호출한다
            // 이때 Drawer 로 사용되는 컴포넌트의 layout_gravity 를 start 로 해줘야 함
//                mBinding.drawerLayout.open();

            // 오른쪽으로 열릴때는 아래 코드로
            // 이때 Drawer 로 사용되는 컴포넌트의 layout_gravity 를 end 로 해줘야 함
            drawer_layout.openDrawer(GravityCompat.END);
        });

//        // 닫기 버튼
        btn_close.setOnClickListener(v -> {
            // 닫기는 열기와 close, closeDrawer 메소드만 다름
//                mBinding.drawerLayout.close();
            drawer_layout.closeDrawer(GravityCompat.END);
        });
    }
}

