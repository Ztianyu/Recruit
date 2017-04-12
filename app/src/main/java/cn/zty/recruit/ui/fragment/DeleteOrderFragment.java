package cn.zty.recruit.ui.fragment;

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
import cn.zty.recruit.listener.DeleteOrderListener;

/**
 * Created by zty on 2017/4/12.
 */

public class DeleteOrderFragment extends DialogFragment {

    @BindView(R.id.textOpenPic)
    TextView textOpenPic;
    @BindView(R.id.textTakePhoto)
    TextView textTakePhoto;
    @BindView(R.id.viewSelectLine)
    View viewSelectLine;
    Unbinder unbinder;

    private DeleteOrderListener listener;

    private int position;

    public static DeleteOrderFragment newInstance(int position, DeleteOrderListener listener) {
        DeleteOrderFragment fragment = new DeleteOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        fragment.setListener(listener);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.view_select_pic, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("position");
    }

    @Override
    public void onStart() {
        super.onStart();
        textOpenPic.setVisibility(View.GONE);
        viewSelectLine.setVisibility(View.INVISIBLE);
        textTakePhoto.setText("删除该订单");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.textOpenPic, R.id.textTakePhoto})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textOpenPic:
                break;
            case R.id.textTakePhoto:
                dismiss();
                listener.onDelete(position);
                break;
        }
    }

    public DeleteOrderListener getListener() {
        return listener;
    }

    public void setListener(DeleteOrderListener listener) {
        this.listener = listener;
    }
}
