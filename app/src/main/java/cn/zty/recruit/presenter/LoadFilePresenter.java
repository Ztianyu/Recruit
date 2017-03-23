package cn.zty.recruit.presenter;


import java.io.File;

import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.bean.LoadFileModel;
import cn.zty.recruit.manager.LoadFileManager;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.view.LoadFileView;

/**
 * Created by zty on 2017/2/24.
 */

public class LoadFilePresenter extends IBasePresenter<LoadFileView> {
    LoadFileManager manager;

    public LoadFilePresenter() {
        manager = new LoadFileManager();
    }

    public void load(File file, String path) {
        mSubscription = RxManager.getInstance().doSubscribe1(manager.load(file, path), new RxSubscriber<LoadFileModel>() {
            @Override
            protected void _onNext(LoadFileModel loadFileModel) {
                mView.onLoadFileSuccess(loadFileModel);
            }
        }, false);
    }
}
