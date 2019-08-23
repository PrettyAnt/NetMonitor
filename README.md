# MyFloatingWindow 

   ## 特点&关键方法：
    
    1.跟传统悬浮窗相比，权限比较低(传统悬浮窗是通过service+windowmanager控制)此悬浮窗继承自popupwindow,依附于当前的activity
    2.通过popupWindow.show()和popupWindow.dismiss()方法对悬浮窗进行显示和隐藏;
    3.通过popupWindow.showAtLocation(View parent, int gravity, int x, int y)初始化显示悬浮窗的位置，通过触摸事件的监听+popupwindow.update(int x, int y, 
    int width, int height, boolean force)来更新悬浮窗的当前的位置

   ## 注意：
    
    1.为防止悬浮窗被重复调用，最好使用单例模式。
    2.悬浮窗的本质是popupwindow,注意popupwindow的创建时机（页面初始化完毕）。

   ## 使用方式：
    
    如从activity A切换到activity B,应该现在activity A的onPause()或onStop()方法
    中隐藏悬浮窗，然后启动activity B后在activity B中显示悬浮窗。
