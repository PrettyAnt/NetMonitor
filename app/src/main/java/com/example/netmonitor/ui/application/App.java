package com.example.netmonitor.ui.application;

import android.app.Application;

import com.example.netmonitor.ui.widget.PopNetMonitorWindow;

/**
 * @author ChenYu
 * Author's github https://github.com/PrettyAnt
 * <p>
 * Created on 11:20 AM  2020/5/25
 * PackageName : com.example.netmonitor.ui.application
 * describle :
 */
public class App extends Application {

    private PopNetMonitorWindow popNetMonitorWindow;

    @Override
    public void onCreate() {
        super.onCreate();
        popNetMonitorWindow = PopNetMonitorWindow.initParams(this);
    }

    public PopNetMonitorWindow getPopNetMonitorWindow() {
        return popNetMonitorWindow;
    }
}
