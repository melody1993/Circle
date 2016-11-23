package com.example.wang55.circleprogressview.UI;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wang55.circleprogressview.R;
import com.example.wang55.circleprogressview.view.CircleProgressView;

public class MainActivity extends AppCompatActivity {

    private CircleProgressView pView;
    Handler handler=new Handler();
    int curProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pView = (CircleProgressView) findViewById(R.id.pv);

    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(updatePr,1000);
    }
    Runnable updatePr=new Runnable() {
        @Override
        public void run() {

            if(curProgress!=100){
                curProgress+=1;
                pView.setCurProgress(curProgress);
                handler.postDelayed(updatePr,200);
            }
        }
    };
}
