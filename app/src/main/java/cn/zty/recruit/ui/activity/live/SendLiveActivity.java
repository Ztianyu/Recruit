package cn.zty.recruit.ui.activity.live;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.widget.UnderLineEditText;

/**
 * Created by zty on 2017/6/22.
 */

public class SendLiveActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.editLiveTitle)
    UnderLineEditText editLiveTitle;
    @BindView(R.id.editLiveNote)
    EditText editLiveNote;
    @BindView(R.id.btnAddLive)
    ImageView btnAddLive;
    @BindView(R.id.btnSend)
    TextView btnSend;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_send_live;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("我要发布");
        initToolbar(toolbar);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btnAddLive, R.id.btnSend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAddLive:
                break;
            case R.id.btnSend:
                break;
        }
    }
}
