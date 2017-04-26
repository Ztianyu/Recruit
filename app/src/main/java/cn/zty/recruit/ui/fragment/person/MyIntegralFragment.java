package cn.zty.recruit.ui.fragment.person;

import android.widget.TextView;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseFragment;
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.bean.UserModel;
import cn.zty.recruit.presenter.GetUserPresenter;
import cn.zty.recruit.view.UserView;

/**
 * Created by zty on 2017/3/20.
 */

public class MyIntegralFragment extends BaseFragment implements UserView {
    @BindView(R.id.textIntegral)
    TextView textIntegral;
    @BindView(R.id.invitationCode)
    TextView invitationCode;

    private GetUserPresenter presenter;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_integral;
    }

    @Override
    protected void initView() {
        presenter = new GetUserPresenter();
        presenter.attach(this);
        presenters.add(presenter);

        presenter.getUser(RecruitApplication.getInstance().getTokenId(), RecruitApplication.getInstance().getUserId());
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onUserSuccess(UserModel userModel) {
        if (userModel != null) {
            textIntegral.setText(userModel.getIntegral() + "");
            if (userModel.getInviteType().equals("0")) {
                invitationCode.setText("邀请码：" + userModel.getInviteCode());
            } else {
                invitationCode.setText("邀请码：" + userModel.getVipInviteCode());
            }
        }
    }
}
