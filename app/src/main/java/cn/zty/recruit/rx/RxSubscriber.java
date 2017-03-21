package cn.zty.recruit.rx;


import android.util.Log;

import java.io.IOException;

import rx.Subscriber;

/**
 * Created by zty on 2017/2/22.
 */
public abstract class RxSubscriber<T> extends Subscriber<T> {

    public RxSubscriber() {
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        //统一处理请求异常的情况
        if (e instanceof IOException) {
//            ToastUtil.show("网络链接异常...");
        } else {
            Log.e("RxSubscriberOnError", e.getMessage());
        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    protected abstract void _onNext(T t);

}
