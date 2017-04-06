package cn.zty.recruit.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.zty.recruit.R;
import cn.zty.recruit.listener.ToastSureListener;

/**
 * Created by zty on 2017/3/20.
 */

public class ToastFragment extends DialogFragment {

    @BindView(R.id.text_dialog_cancel_order)
    TextView textDialogCancelOrder;
    @BindView(R.id.dialog_btn_cancel)
    Button dialogBtnCancel;
    @BindView(R.id.dialog_btn_sure)
    Button dialogBtnSure;

    private ToastSureListener listener;

    private String toast;

    public static ToastFragment newInstance(String toast, ToastSureListener listener) {
        ToastFragment fragment = new ToastFragment();
        Bundle bundle = new Bundle();
        bundle.putString("toast", toast);
        fragment.setArguments(bundle);
        fragment.setListener(listener);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE, 0);

        toast = getArguments().getString("toast");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_toast, null);
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
        windowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(windowParams);

        textDialogCancelOrder.setText(toast);
    }

    @OnClick({R.id.dialog_btn_cancel, R.id.dialog_btn_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_btn_cancel:
                dismiss();
                break;
            case R.id.dialog_btn_sure:
                dismiss();
                listener.onSure();
                break;
        }
    }

    public ToastSureListener getListener() {
        return listener;
    }

    public void setListener(ToastSureListener listener) {
        this.listener = listener;
    }
}
