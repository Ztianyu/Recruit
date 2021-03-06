package cn.zty.recruit.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zty.baselib.widget.StripMenuView;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.OrderModel;
import cn.zty.recruit.listener.PayListener;
import cn.zty.recruit.pay.Payment;
import cn.zty.recruit.view.StringView;

/**
 * Created by zty on 2017/4/1.
 */

public class PayFragment extends DialogFragment implements StringView {

    @BindView(R.id.payAli)
    StripMenuView payAli;
    @BindView(R.id.payWeChat)
    StripMenuView payWeChat;

    Unbinder unbinder;

    private OrderModel orderModel;

    private PayListener payListener;

    public static PayFragment newInstance(OrderModel orderModel, PayListener payListener) {
        PayFragment fragment = new PayFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("orderModel", orderModel);
        fragment.setArguments(bundle);
        fragment.setPayListener(payListener);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        orderModel = getArguments().getParcelable("orderModel");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog2);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.view_pay_style);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

        unbinder = ButterKnife.bind(this, dialog);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.6f;
        windowParams.gravity = Gravity.BOTTOM;
        windowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(windowParams);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.payAli, R.id.payWeChat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.payAli:
                Payment payment = new Payment(getActivity(), payListener);
                payment.setPayModel(orderModel);
                payment.payNow();
                dismiss();
                break;
            case R.id.payWeChat:
                payListener.onWeChatPay();
                dismiss();
                break;
        }
    }

    public PayListener getPayListener() {
        return payListener;
    }

    public void setPayListener(PayListener payListener) {
        this.payListener = payListener;
    }

    @Override
    public void onSuccess(String msg) {

    }
}
