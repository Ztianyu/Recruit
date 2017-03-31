package cn.zty.recruit.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.utils.ResourceUtil;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.BaseData;
import cn.zty.recruit.base.Constants;
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.bean.UserModel;
import cn.zty.recruit.presenter.DictPresenter;
import cn.zty.recruit.presenter.GetUserPresenter;
import cn.zty.recruit.view.DictListView;
import cn.zty.recruit.view.UserView;

/**
 * 启动页
 * Created by zty on 2017/3/24.
 */

public class StartActivity extends BaseActivity implements
        View.OnFocusChangeListener,
        DictListView,
        UserView {

    @BindView(R.id.editStartCode)
    EditText editStartCode;
    @BindView(R.id.textStartCodeTip)
    TextView textStartCodeTip;
    @BindView(R.id.btnSureCode)
    TextView btnSureCode;

    private DictPresenter dictPresenter;

    GetUserPresenter presenter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {
        dictPresenter = new DictPresenter();
        dictPresenter.attach(this);
        presenters.add(dictPresenter);

        presenter = new GetUserPresenter();
        presenter.attach(this);
        presenters.add(presenter);

        setTitleBar();

        editStartCode.setOnFocusChangeListener(this);
    }

    @Override
    protected void initData() {
        dictPresenter.getDictList(Constants.DICT_TYPE5);

        if (!TextUtils.isEmpty(RecruitApplication.getInstance().getTokenId()))
            presenter.getUser(RecruitApplication.getInstance().getTokenId(), RecruitApplication.getInstance().getUserId());

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; i > 0; i--) {
                    handler.sendEmptyMessage(-1);
                    if (i <= 0) {
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(1);
            }
        }).start();
    }

    int i = 3;

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                startActivity(new Intent(StartActivity.this, MainActivity.class));
                finish();
            }
        }
    };

    @Override
    public void onDictSuccess(String type, List<TipModel> models) {
        BaseData.educations.addAll(models);
    }

    @Override
    public void onUserSuccess(UserModel userModel) {
        if (userModel != null)
            RecruitApplication.getInstance().setUserModel(userModel);
    }

    @OnClick(R.id.btnSureCode)
    public void onViewClicked() {
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v.getId() == R.id.editStartCode)
            if (hasFocus) {
                i = 30;
                textStartCodeTip.setBackgroundColor(ResourceUtil.resToColor(this, R.color.colorAccent));
            } else {
                textStartCodeTip.setBackgroundColor(ResourceUtil.resToColor(this, R.color.gray));
            }
    }
}
