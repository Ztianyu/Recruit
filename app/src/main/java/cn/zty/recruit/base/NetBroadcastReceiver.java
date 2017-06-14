package cn.zty.recruit.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import cn.zty.recruit.utils.NetUtils;

public class NetBroadcastReceiver extends BroadcastReceiver {

    public NetEvent event = RecruitApplication.getInstance().getEvent();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netWorkState = NetUtils.getNetWorkState(context);
            // 接口回调传过去状态的类型
            event.onNetChange(netWorkState);
        }
    }

    public interface NetEvent {
        void onNetChange(int netMobile);
    }
}
