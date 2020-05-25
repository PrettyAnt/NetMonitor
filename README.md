# NetMonitor 

   ## 特点&关键方法：
    
  * 跟传统悬浮窗相比，**不需要申请任何系统相关权限**(传统悬浮窗是通过service+windowmanager控制)此悬浮窗继承自popupwindow,依附于当前的activity
  * PopNetMonitorWindow.getInstance().showMyPop(this) 用来控制悬浮窗的显示
  * PPopNetMonitorWindow.hindNetMonitor() 用来控制悬浮窗的隐藏
  * 通过popupWindow.showAtLocation(View parent, int gravity, int x, int y)初始化显示悬浮窗的位置，通过触摸事件的监听+popupwindow.update(int x, int y, 
    int width, int height, boolean force)来更新悬浮窗的当前的位置

   ## 注意：
    
   * 为防止悬浮窗被重复调用，最好使用单例模式。
   * 悬浮窗的本质是popupwindow,注意popupwindow的创建时机（页面初始化完毕）。

   ## 创建时机：
    
  * 如从activity A切换到activity B,应该现在activity A的onPause()或onStop()方法
    中隐藏悬浮窗，然后启动activity B后在activity B中显示悬浮窗。
