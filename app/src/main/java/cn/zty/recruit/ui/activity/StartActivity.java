package cn.zty.recruit.ui.activity;

import android.content.Intent;
import android.os.Handler;

import java.util.List;

import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.BaseData;
import cn.zty.recruit.base.Constants;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.presenter.DictPresenter;
import cn.zty.recruit.view.DictListView;

/**
 * 启动页
 * Created by zty on 2017/3/24.
 */

public class StartActivity extends BaseActivity implements DictListView {

    private DictPresenter dictPresenter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {
        dictPresenter = new DictPresenter();
        dictPresenter.attach(this);
        presenters.add(dictPresenter);
    }

    @Override
    protected void initData() {

        startActivity(new Intent(StartActivity.this, MainActivity.class));
        finish();
//        dictPresenter.getDictList(Constants.DICT_TYPE3);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                    handler.sendEmptyMessage(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();
    }

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                startActivity(new Intent(StartActivity.this, MainActivity.class));
                finish();
            }
        }
    };

    @Override
    public void onDictSuccess(String type, List<TipModel> models) {
        if (type.equals(Constants.DICT_TYPE3)) {
            BaseData.educations.addAll(models);
            handler.sendEmptyMessage(1);
        }
    }
}
