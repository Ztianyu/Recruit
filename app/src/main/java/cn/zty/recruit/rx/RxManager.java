package cn.zty.recruit.rx;

import android.util.Log;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.recruit.utils.ToastUtils;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zty on 2017/2/22.
 */
public class RxManager {
    private RxManager() {
    }

    public static RxManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RxManager INSTANCE = new RxManager();
    }

    public <T> Subscription doSubscribe(Observable<T> observable, Subscriber<T> subscriber) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public <T> Subscription doSubscribe1(Observable<ResultBean<T>> observable, Subscriber<T> subscriber, final boolean isShowError) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<ResultBean<T>, Boolean>() {
                    @Override
                    public Boolean call(ResultBean<T> tResultBean) {
                        if (tResultBean.getHead().getRet() == 0) {
                            return true;
                        } else {
                            Log.e("error", tResultBean.getHead().getMsg());
                            if (isShowError)
                                ToastUtils.show(tResultBean.getHead().getMsg());
                            return false;
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .map(new Func1<ResultBean<T>, T>() {
                    @Override
                    public T call(ResultBean<T> resultBean) {
                        return resultBean.getResult();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
