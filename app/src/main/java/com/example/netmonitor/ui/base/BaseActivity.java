package com.example.netmonitor.ui.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.netmonitor.ui.application.App;
import com.example.netmonitor.ui.widget.PopNetMonitorWindow;

/**
 * @author ChenYu
 * Author's github https://github.com/PrettyAnt
 * <p>
 * Created on 11:20 AM  2020/5/25
 * PackageName : com.example.netmonitor.ui.base
 * describle :
 */
public abstract class BaseActivity extends AppCompatActivity {

    public PopNetMonitorWindow popNetMonitorWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        popNetMonitorWindow = ((App) getApplication()).getPopNetMonitorWindow();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && netMonitorIsVisible()) {
            popNetMonitorWindow.showNetMonitor(this);
        } else {
            popNetMonitorWindow.hindNetMonitor();
        }
    }

    /**
     * 默认情况下为隐藏悬浮框，控制权交给子类处理
     *
     * @return true 显示悬浮窗  false 隐藏悬浮窗
     */
    protected boolean netMonitorIsVisible() {
        return false;
    }
}
