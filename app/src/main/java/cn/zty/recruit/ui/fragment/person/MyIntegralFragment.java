package cn.zty.recruit.ui.fragment.person;

import android.widget.TextView;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseFragment;
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.bean.UserModel;

/**
 * Created by zty on 2017/3/20.
 */

public class MyIntegralFragment extends BaseFragment {
    @BindView(R.id.textIntegral)
    TextView textIntegral;
    @BindView(R.id.invitationCode)
    TextView invitationCode;

    private UserModel userModel;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_integral;
    }

    @Override
    protected void initView() {
        userModel = RecruitApplication.getInstance().getUserModel();
    }

    @Override
    protected void initData() {
        if (userModel != null) {
            textIntegral.setText(userModel.getIntegral() + "");
            invitationCode.setText("邀请码：" + userModel.getInviteCode());
        }
    }
}
