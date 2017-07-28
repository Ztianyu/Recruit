package cn.zty.recruit.ui.activity.person;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.utils.AppManager;
import cn.zty.baselib.utils.MyImageLoader;
import cn.zty.baselib.utils.MyTextUtils;
import cn.zty.baselib.utils.ResourceUtil;
import cn.zty.baselib.widget.CircleImageView;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.Constants;
import cn.zty.recruit.bean.OrderModel;
import cn.zty.recruit.listener.PayListener;
import cn.zty.recruit.presenter.OrderPresenter;
import cn.zty.recruit.wxapi.UnifiedorderPresenter;
import cn.zty.recruit.utils.DialogUtils;
import cn.zty.recruit.view.OrderView;
import cn.zty.recruit.wxapi.WeChatPayManager;

/**
 * Created by zty on 2017/3/31.
 */

public class OrderDetailActivity extends BaseActivity implements
        OrderView,
        PayListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgOrderHeader)
    CircleImageView imgOrderHeader;
    @BindView(R.id.textOrderName)
    TextView textOrderName;
    @BindView(R.id.textOrderEducation)
    TextView textOrderEducation;
    @BindView(R.id.textOrderAge)
    TextView textOrderAge;
    @BindView(R.id.imgOrderSex)
    ImageView imgOrderSex;
    @BindView(R.id.textOrderSex)
    TextView textOrderSex;
    @BindView(R.id.textOrderPhone)
    TextView textOrderPhone;
    @BindView(R.id.textOrderCode)
    TextView textOrderCode;
    @BindView(R.id.imgOrder)
    ImageView imgOrder;
    @BindView(R.id.textOrderSchoolNm)
    TextView textOrderSchoolNm;
    @BindView(R.id.textOrderSchoolAdd)
    TextView textOrderSchoolAdd;
    @BindView(R.id.textOrderContentName)
    TextView textOrderContentName;
    @BindView(R.id.textOrderPs)
    TextView textOrderPs;
    @BindView(R.id.textOrderState)
    TextView textOrderState;
    @BindView(R.id.textOrderSignTime)
    TextView textOrderSignTime;
    @BindView(R.id.textOrderPrise)
    TextView textOrderPrise;
    @BindView(R.id.textOrderDeposit)
    TextView textOrderDeposit;
    @BindView(R.id.textOrderActual)
    TextView textOrderActual;
    @BindView(R.id.textPayStyle)
    TextView textPayStyle;
    @BindView(R.id.textBillMoney)
    TextView textBillMoney;
    @BindView(R.id.btnSubmit)
    TextView btnSubmit;
    @BindView(R.id.layoutBill)
    LinearLayout layoutBill;

    private String orderCode;

    private OrderPresenter orderPresenter;

    private UnifiedorderPresenter presenter;

    private OrderModel model;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initView() {
        orderCode = getIntent().getStringExtra("orderCode");

        toolbar.setTitle("订单详情");
        initToolbar(toolbar);
        btnSubmit.setText("立即支付");

        orderPresenter = new OrderPresenter();
        orderPresenter.attach(this);
        presenters.add(orderPresenter);

        presenter = new UnifiedorderPresenter();
    }

    @Override
    protected void initData() {
        orderPresenter.getOrder(null, orderCode);
    }

    @OnClick(R.id.btnSubmit)
    public void onViewClicked() {
        if (model != null)
            DialogUtils.showPayDialog(getSupportFragmentManager(), model, this);
    }

    @Override
    public void onOrderListSuccess(List<OrderModel> models) {
    }

    @Override
    public void onOrderDetail(OrderModel model) {
        this.model = model;

        MyImageLoader.load(this, model.getPhoto(), imgOrderHeader);
        textOrderName.setText(model.getFullNm());
        textOrderEducation.setText(model.getEducationLabel());
        textOrderAge.setText(model.getAge() + "岁");
        textOrderSex.setText(model.getSex());
        if (model.getSex().equals("男")) {
            imgOrderSex.setBackground(ResourceUtil.resToDrawable(this, R.mipmap.ic_boy_tip));
        } else {
            imgOrderSex.setBackground(ResourceUtil.resToDrawable(this, R.mipmap.ic_girl_tip));
        }
        textOrderPhone.setText(model.getMobile());
        textOrderCode.setText("报名单号：" + model.getOrderCode());
        MyImageLoader.load(this, model.getImgUrl(), imgOrder);
        textOrderSchoolNm.setText(model.getSchoolNm());
        textOrderSchoolAdd.setText(model.getSchoolAddress());

        StringBuffer contentName = new StringBuffer();
        contentName.append(MyTextUtils.notNullStr(model.getDepartmentNm()))
                .append(model.getCourseNm())
                .append("   ")
                .append(TextUtils.isEmpty(model.getStudyTypeLabel()) ? "" : "    进修" + model.getStudyTypeLabel());
        textOrderContentName.setText(contentName.toString());

        textOrderPs.setText(MyTextUtils.notNullStr(model.getRemarks()));
        textOrderSignTime.setText(model.getCreateDate());

        StringBuffer prise = new StringBuffer();
        prise.append((int) model.getMoney())
                .append("元/")
                .append(model.getChargeStandard().equals("1") ? Constants.chargeStandard1 : Constants.chargeStandard2);
        textOrderPrise.setText(prise.toString());

        textOrderDeposit.setText((int) model.getDeposit() + "元");
        textOrderActual.setText((int) model.getActualPayment() + "元");

        String state = model.getState();
        if (state.equals(Constants.ORDER_STATE1)) {
            textOrderState.setText(Constants.ORDER_STATE_VALUE1);
            textPayStyle.setText("定 金：");
            textBillMoney.setText((int) model.getDeposit() + "元");
        } else if (state.equals(Constants.ORDER_STATE2)) {
            textOrderState.setText(Constants.ORDER_STATE_VALUE2);
            textPayStyle.setText("尾 款：");
            textBillMoney.setText((int) model.getActualPayment() + "元");
        } else if (state.equals(Constants.ORDER_STATE3)) {
            layoutBill.setVisibility(View.INVISIBLE);
            textOrderState.setText(Constants.ORDER_STATE_VALUE3);
        }
    }

    @Override
    public void onPaySuccess() {
        finish();
        AppManager.getInstance().finishActivity(OrderActivity.class);
        if (model.getState().equals("0")) {
            OrderActivity.page = 1;
        } else if (model.getState().equals("1")) {
            OrderActivity.page = 2;
        }
        startActivity(new Intent(this, OrderActivity.class));
    }

    @Override
    public void onWeChatPay() {
        String body = "报名：" + model.getCourseNm() + " " + MyTextUtils.notNullStr(model.getDepartmentNm());
        String nonce_str = WeChatPayManager.getInstance().getNonceStr();
        String orderState = model.getState();
        String out_trade_no = orderState.equals("0") ? model.getOrderCode() : model.getId();
        String total_fee = orderState.equals("0") ? (int) model.getDeposit() * 100 + "" : (int) model.getActualPayment() * 100 + "";

        if (orderState.equals("0")) {
            OrderActivity.page = 1;
        } else {
            OrderActivity.page = 2;
        }
        presenter.unifiedorder(body, nonce_str, out_trade_no, total_fee);
    }
}
