package com.example.netmonitor.ui.acitiviy;

import android.os.Bundle;
import android.view.View;

import com.example.netmonitor.R;
import com.example.netmonitor.ui.base.BaseActivity;

/**
 * @author ChenYu
 * Author's github https://github.com/PrettyAnt
 * <p>
 * Created on 11:20 AM  2020/5/25
 * PackageName : com.example.netmonitor.ui.acitiviy
 * describle :
 */
public class SecondActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    protected boolean netMonitorIsVisible() {
        return true;
    }
}
