package cn.zty.recruit.presenter;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.baselib.utils.VersionUtils;
import cn.zty.recruit.bean.VersionModel;
import cn.zty.recruit.listener.ToastSureListener;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.RequestAppService;
import cn.zty.recruit.utils.DialogUtils;
import cn.zty.recruit.utils.DownloadUtils;
import cn.zty.recruit.utils.ToastUtils;
import cn.zty.recruit.view.VersionView;

/**
 * Created by zty on 2017/4/6.
 */

public class VersionPresenter extends IBasePresenter<VersionView> {

    private static String appName = "app_android";

    RequestAppService service;

    public VersionPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(RequestAppService.class);
    }

    public void getVersion() {

        mSubscription = RxManager.getInstance().doSubscribe1(service.requestApp(appName), new RxSubscriber<VersionModel>() {
            @Override
            protected void _onNext(VersionModel versionModel) {
                mView.onSuccess(versionModel);
            }
        }, false);
    }

    public void showDialog(FragmentManager manager, final Context context, final VersionModel versionModel, boolean isShowNone) {

        int versionCode = VersionUtils.getVersionCode(context);

        if (versionModel.getAppVersion() > versionCode) {
            DialogUtils.showToast(manager, "有新版本更新了，是否更新?", new ToastSureListener() {
                @Override
                public void onSure() {
                    DownloadUtils.downApk(context, versionModel.getAppAddress(), "recruit" + versionModel.getAppVersion());
                }
            });
        } else {
            if (isShowNone)
                ToastUtils.show("已经是最新版本了");
        }
    }

}
