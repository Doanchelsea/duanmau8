package vn.ed.fpt.doannv.duanmau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // chuyển màn hình tại đây
                Intent intent = new Intent(HelloActivity.this, LoginActivity.class);
                startActivity(intent);
                // kết thúc màn hình chào
                finish();
            }
        },1000);
    }
}
