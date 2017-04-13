package cn.zty.recruit.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zty.baselib.utils.MyTextUtils;
import cn.zty.recruit.R;
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.bean.UserModel;
import cn.zty.recruit.listener.VisitListener;
import cn.zty.recruit.presenter.GetUserPresenter;
import cn.zty.recruit.utils.ToastUtils;
import cn.zty.recruit.view.UserView;

/**
 * Created by zty on 2017/4/13.
 */

public class VisitFragment extends DialogFragment implements
        UserView {

    @BindView(R.id.editVisitName)
    EditText editVisitName;
    @BindView(R.id.editVisitPhone)
    EditText editVisitPhone;
    @BindView(R.id.btnVisitCancel)
    TextView btnVisitCancel;
    @BindView(R.id.btnVisitSure)
    TextView btnVisitSure;
    Unbinder unbinder;

    private UserModel userModel;

    private GetUserPresenter getUserPresenter;

    private VisitListener listener;

    public static VisitFragment newInstance(VisitListener listener) {
        VisitFragment fragment = new VisitFragment();
        fragment.setListener(listener);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_visit, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE, 0);

        userModel = RecruitApplication.getInstance().getUserModel();

        getUserPresenter = new GetUserPresenter();
        getUserPresenter.attach(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.6f;
        windowParams.gravity = Gravity.CENTER;
        windowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(windowParams);

        if (userModel != null) {
            editVisitName.setText(MyTextUtils.notNullStr(userModel.getFullNm()));
            editVisitPhone.setText(userModel.getMobile());
        } else {
            if (!TextUtils.isEmpty(RecruitApplication.getInstance().getUserId()))
                getUserPresenter.getUser(RecruitApplication.getInstance().getTokenId(),
                        RecruitApplication.getInstance().getUserId());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        getUserPresenter.detach();
    }

    @OnClick({R.id.btnVisitCancel, R.id.btnVisitSure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnVisitCancel:
                dismiss();
                break;
            case R.id.btnVisitSure:
                if (check()) {
                    listener.onVisit(editVisitName.getText().toString(), editVisitPhone.getText().toString());
                    dismiss();
                }
                break;
        }
    }

    private boolean check() {
        if (TextUtils.isEmpty(editVisitName.getText().toString())) {
            ToastUtils.show("请输入回访人姓名");
            return false;
        }
        if (TextUtils.isEmpty(editVisitPhone.getText().toString())) {
            ToastUtils.show("请输入回访人电话");
            return false;
        }
        return true;
    }

    @Override
    public void onUserSuccess(UserModel userModel) {
        if (userModel != null) {
            RecruitApplication.getInstance().setUserModel(userModel);
            this.userModel = userModel;
            editVisitName.setText(MyTextUtils.notNullStr(userModel.getFullNm()));
            editVisitPhone.setText(userModel.getMobile());
        }
    }

    public VisitListener getListener() {
        return listener;
    }

    public void setListener(VisitListener listener) {
        this.listener = listener;
    }
}
