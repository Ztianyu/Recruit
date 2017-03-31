package cn.zty.recruit.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.widget.StripMenuView;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.ui.activity.person.OrderDetailActivity;

/**
 * Created by zty on 2017/3/30.
 */

public class PayActivity extends BaseActivity {
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

    private String orderCode;
    private String money;

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
                    return true;
                }
                return false;
            }
        });

        orderCode = getIntent().getStringExtra("orderCode");
        money = getIntent().getStringExtra("money");
    }

    @Override
    protected void initData() {
        textPayMoney.setText("¥ " + money);
    }

    @OnClick({R.id.payAli, R.id.payWeChat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.payAli:
                break;
            case R.id.payWeChat:
                break;
        }
    }
}
