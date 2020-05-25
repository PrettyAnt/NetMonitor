package com.example.netmonitor.ui.acitiviy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_show;
    private Button btn_hint;
    private Button btn_skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_show = findViewById(R.id.btn_show);
        btn_hint = findViewById(R.id.btn_hint);
        btn_skip = findViewById(R.id.btn_skip);
        btn_hint.setOnClickListener(this);
        btn_show.setOnClickListener(this);
        btn_skip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                popNetMonitorWindow.showNetMonitor(this);
                break;
            case R.id.btn_hint:
                popNetMonitorWindow.hindNetMonitor();
                break;
            case R.id.btn_skip:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected boolean netMonitorIsVisible() {
        return false;
    }
}
