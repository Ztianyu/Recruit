package cn.zty.baselib.presenter;

import rx.Subscription;

/**
 * Created by zty on 2017/3/3.
 */

public class IBasePresenter<V> {

    public V mView;
    protected Subscription mSubscription;

    public void attach(V view) {
        mView = view;
    }

    public void detach() {
        mView = null;
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }
}
