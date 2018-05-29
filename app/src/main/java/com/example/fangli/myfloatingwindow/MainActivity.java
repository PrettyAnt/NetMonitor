package com.example.fangli.myfloatingwindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.fangli.myfloatingwindow.view.MyPopupWindow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_show;
    private Button btn_hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MyPopupWindow.getInstance(this).showFloatWindow(this);
        initView();
    }

    private void initView() {
        btn_show = (Button) findViewById(R.id.btn_show);
        btn_hint = (Button) findViewById(R.id.btn_hint);
        btn_hint.setOnClickListener(this);
        btn_show.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                MyPopupWindow.getInstance().showMyPop(this);
                break;
            case R.id.btn_hint:
                MyPopupWindow.hindMyPop();
                break;
        }
    }
}
