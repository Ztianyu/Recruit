package cn.zty.recruit.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.utils.AppManager;
import cn.zty.baselib.utils.MyTextUtils;
import cn.zty.baselib.utils.TimeUtils;
import cn.zty.baselib.widget.StripMenuView;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.OrderModel;
import cn.zty.recruit.bean.WeChatPayModel;
import cn.zty.recruit.listener.PayListener;
import cn.zty.recruit.pay.Payment;
import cn.zty.recruit.presenter.OrderPresenter;
import cn.zty.recruit.presenter.UnifiedorderPresenter;
import cn.zty.recruit.ui.activity.learn.EnrollActivity;
import cn.zty.recruit.ui.activity.learn.StudyEnrollActivity;
import cn.zty.recruit.ui.activity.person.OrderActivity;
import cn.zty.recruit.ui.activity.person.OrderDetailActivity;
import cn.zty.recruit.view.OrderView;
import cn.zty.recruit.view.StringView;
import cn.zty.recruit.wechat.WeChatPayManager;

/**
 * Created by zty on 2017/3/30.
 */

public class PayActivity extends BaseActivity implements
        OrderView,
        PayListener,
        StringView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textPayMoney)
    TextView textPayMoney;
    @BindView(R.id.payAli)
    StripMenuView payAli;
    @BindView(R.id.payWeChat)
    StripMenuView payWeChat;
    @BindView(R.id.textPayPs)
    TextView textPayPs;

    private int payType = 0;//支付方式 0：支付宝；1：微信

    private String orderCode;
    private String money;

    private OrderPresenter orderPresenter;

    private UnifiedorderPresenter presenter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("订单提交成功");
        toolbar.inflateMenu(R.menu.see_order);
        toolbar.setNavigationIcon(R.drawable.ic_white_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.seeOrder) {
                    startActivity(new Intent(PayActivity.this, OrderDetailActivity.class)
                            .putExtra("orderCode", orderCode));
                    AppManager.getInstance().finishActivity(EnrollActivity.class);
                    AppManager.getInstance().finishActivity(StudyEnrollActivity.class);
                    finish();
                    return true;
                }
                return false;
            }
        });

        orderCode = getIntent().getStringExtra("orderCode");
        money = getIntent().getStringExtra("money");

        orderPresenter = new OrderPresenter();
        orderPresenter.attach(this);
        presenters.add(orderPresenter);

        presenter = new UnifiedorderPresenter();
        presenter.attach(this);
        presenters.add(presenter);
    }

    @Override
    protected void initData() {
        textPayMoney.setText("¥ " + money);
    }

    @OnClick({R.id.payAli, R.id.payWeChat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.payAli:
                payType = 0;
                orderPresenter.getOrder(null, orderCode);
                break;
            case R.id.payWeChat:
                payType = 1;
                orderPresenter.getOrder(null, orderCode);
                break;
        }
    }

    @Override
    public void onOrderListSuccess(List<OrderModel> models) {

    }

    @Override
    public void onOrderDetail(OrderModel model) {
        if (payType == 0) {
            Payment payment = new Payment(this, this);
            payment.setPayModel(model);
            payment.payNow();
        } else {
//            String body = "报名：" + model.getCourseNm() + " " + MyTextUtils.notNullStr(model.getDepartmentNm());
//            String nonce_str = WeChatPayManager.getInstance().getNonceStr();
//            presenter.unifiedorder(body, nonce_str, model.getOrderCode(), model.getDeposit() + "");
        }

    }

    @Override
    public void onPaySuccess() {
        OrderActivity.page = 1;
        startActivity(new Intent(this, OrderActivity.class));
        AppManager.getInstance().finishActivity(EnrollActivity.class);
        AppManager.getInstance().finishActivity(StudyEnrollActivity.class);
        finish();
    }

    @Override
    public void onWeChatPay() {

    }

    @Override
    public void onSuccess(String msg) {
        WeChatPayModel weChatPayModel = presenter.pullParser(msg);

        WeChatPayManager.getInstance().pay(weChatPayModel.getPrepay_id(),
                weChatPayModel.getNonce_str(),
                TimeUtils.dateToStamp(TimeUtils.getFullDate()) + "");
    }
}
