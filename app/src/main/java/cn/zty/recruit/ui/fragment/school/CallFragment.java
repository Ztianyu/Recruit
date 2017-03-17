package cn.zty.recruit.ui.fragment.school;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;

/**
 * Created by zty on 2017/3/15.
 */

public class CallFragment extends DialogFragment {

    @BindView(R.id.textSchoolPhone)
    TextView textSchoolPhone;
    @BindView(R.id.textCallTime)
    TextView textCallTime;
    @BindView(R.id.btnNotCall)
    TextView btnNotCall;
    @BindView(R.id.btnCall)
    TextView btnCall;

    private String phone;

    public static CallFragment newInstance(String phone) {
        CallFragment fragment = new CallFragment();
        Bundle bundle = new Bundle();
        bundle.putString("phone", phone);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        phone = getArguments().getString("phone");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_call, null);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.6f;
        windowParams.gravity = Gravity.CENTER;
        windowParams.width = (int) (BaseActivity.screenWidth * 0.8);
        windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(windowParams);
    }

    @OnClick({R.id.btnNotCall, R.id.btnCall})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNotCall:
                dismiss();
                break;
            case R.id.btnCall:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                dismiss();
                break;
        }
    }
}
