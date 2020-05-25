package com.example.netmonitor.ui.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.netmonitor.R;


/**
 * Created by chenyu.
 * Created on 下午1:58 2017/12/13.
 * Author'github https://github.com/PrettyAnt
 */

public class PopNetMonitorWindow extends PopupWindow implements View.OnClickListener, View.OnTouchListener {
    private static PopNetMonitorWindow popNetMonitorWindow;
    private      static   Context            mContext;
    private static float               currentX = 0;//静态的，记录当前位置，跳转到下个页面时还在此位置
    private static float               currentY = 0;
    private        RelativeLayout      rl_left_close;
    private        RelativeLayout      rl_right_close;
    private        ImageView           iv_menu;
    private        WindowManager       windowManager;
    private        View                popInflate;
    private Activity activity;

    private static PopNetMonitorWindow getInstance() {
        if (popNetMonitorWindow == null) {
            synchronized (PopNetMonitorWindow.class) {
                if (popNetMonitorWindow == null) {
                    popNetMonitorWindow = new PopNetMonitorWindow();
                }
            }
        }

        return popNetMonitorWindow;
    }

    private PopNetMonitorWindow() {
    }

    public  static PopNetMonitorWindow initParams(Context context) {
        getInstance();
        mContext = context;
        currentX = context.getResources().getDisplayMetrics().widthPixels;
        currentY = context.getResources().getDisplayMetrics().heightPixels / 2;
        return popNetMonitorWindow;
    }

    /**
     * 显示悬浮框
     *
     */
    public void showNetMonitor(Activity activity) {
        this.activity = activity;
        init(activity);
        popNetMonitorWindow.showAtLocation(activity.getWindow().getDecorView(),
                Gravity.NO_GRAVITY,
                (int) currentX,
                (int) currentY);

    }

    public  void hindNetMonitor() {
        if (popNetMonitorWindow == null) {
            return;
        }
        if (popNetMonitorWindow.isShowing()) {
            popNetMonitorWindow.dismiss();
        }
    }

    /**
     * 初始化
     *
     * @param context
     */
    @SuppressLint("ServiceCast")
    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        popInflate = inflater.inflate(R.layout.pop, null);
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        this.setContentView(popInflate);
        if (getScreenWidth() < 800) {
            this.setWidth(108);
            this.setHeight(132);
        } else {
            this.setWidth(180);
            this.setHeight(220);
        }
        this.setFocusable(false);
        initView(popInflate);
        ColorDrawable dw = new ColorDrawable(00000000);
        this.setBackgroundDrawable(dw);
    }

    private void initView(View popInflate) {
        rl_left_close = popInflate.findViewById(R.id.rl_left_close);
        rl_right_close = popInflate.findViewById(R.id.rl_right_close);
        iv_menu = popInflate.findViewById(R.id.iv_menu);
        if (currentX < getScreenWidth() / 2) {
            currentX = 0;//悬浮框靠近右侧
            initCancleView(false, true);
        } else {
            currentX = getScreenWidth();//悬浮框靠近左侧
            initCancleView(true, false);
        }
        rl_left_close.setOnClickListener(this);
        rl_right_close.setOnClickListener(this);
        iv_menu.setOnClickListener(this);
        iv_menu.setOnTouchListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_left_close:
                hindNetMonitor();
                break;
            case R.id.rl_right_close:
                hindNetMonitor();
                break;
            case R.id.iv_menu:
                Toast.makeText(mContext, "喜欢就点下关注哦，谢谢啦~", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private float startX = 0;
    private float startY = 0;
    private long endTime;
    private long beginTime = 0;

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getRawX();
                startY = event.getRawY();
                if (beginTime == 0) {
                    beginTime = System.currentTimeMillis();
                } else {
                    endTime = System.currentTimeMillis();

                    if (endTime - beginTime < 1000) {//当两次按下的时间间隔小于300ms，就将此事件消费掉，不再向下传递
//                              Toast.makeText(getApplicationContext(), "时长大于一秒", Toast.LENGTH_SHORT).show();
                        beginTime = endTime;
                        return true;
                    }
                    beginTime = endTime;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //计算偏移量，刷新视图
                currentX = event.getRawX();
                currentY = event.getRawY();
                initCancleView(false, false);
                if (currentY <= 0) {
                    currentY = popInflate.getHeight();
                }
                if (currentY >= getScreenHeight()) {
                    currentY = getScreenHeight() - popInflate.getHeight();
                }
                //更新位置
                update((int) currentX - popInflate.getWidth() / 2, (int) currentY - popInflate.getHeight() / 2, -1, -1, false);
                break;
            case MotionEvent.ACTION_UP:
                if (currentX < getScreenWidth() / 2) {
                    currentX = 0;//悬浮框靠近右侧
                    initCancleView(false, true);
                } else {
                    currentX = getScreenWidth();//悬浮框靠近左侧
                    initCancleView(true, false);
                }

                update((int) currentX - popInflate.getWidth(), (int) currentY, -1, -1, false);
                //如果初始落点与松手落点的坐标差值超过6个像素，则拦截该点击事件
                //否则继续传递，将事件交给OnClickListener函数处理
                if (Math.abs(event.getRawX() - startX) > 6 && Math.abs(event.getRawY() - startY) > 6) {
                    return true;
                }
                break;
        }
        return false;
    }

    /**
     * 当悬浮框滑到界面边缘时，设置“x”是否显示
     *
     * @param left  左边的“x”
     * @param right 右边的“x”
     */
    private void initCancleView(boolean left, boolean right) {
        if (left) {
            rl_left_close.setVisibility(View.VISIBLE);
        } else {
            rl_left_close.setVisibility(View.GONE);
        }
        if (right) {
            rl_right_close.setVisibility(View.VISIBLE);
        } else {
            rl_right_close.setVisibility(View.GONE);
        }
    }

    //获取屏幕宽度
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private int getScreenWidth() {
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        return point.x;
    }

    //获取屏幕高度
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private int getScreenHeight() {
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        return point.y;
    }
}
