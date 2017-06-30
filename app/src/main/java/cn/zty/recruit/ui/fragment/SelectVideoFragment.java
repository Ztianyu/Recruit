package cn.zty.recruit.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zty.recruit.R;
import cn.zty.recruit.pick.OnSelectListener;

/**
 * Created by zty on 2017/6/22.
 */

public class SelectVideoFragment extends DialogFragment {
    @BindView(R.id.textOpenPic)
    TextView textOpenPic;
    @BindView(R.id.textTakePhoto)
    TextView textTakePhoto;
    Unbinder unbinder;

    private OnSelectListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.view_select_pic, null);

        unbinder = ButterKnife.bind(this, view);

        textOpenPic.setText("选择视频");
        textTakePhoto.setText("拍摄");

        return view;
    }

    @OnClick({R.id.textOpenPic, R.id.textTakePhoto})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textOpenPic:
                listener.onPickPic();
                break;
            case R.id.textTakePhoto:
                listener.onTakePhoto();
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnSelectListener) context;
        } catch (ClassCastException e) {
            dismiss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
