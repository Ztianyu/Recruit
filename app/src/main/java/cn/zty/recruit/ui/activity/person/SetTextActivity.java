package cn.zty.recruit.ui.activity.person;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import cn.zty.baselib.utils.ValidateUtil;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.utils.SnackbarUtils;

/**
 * Created by zty on 2017/1/9.
 */

public class SetTextActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.editSetText)
    EditText editSetText;
    private String message;
    private String url;
    private String strTitle;

    private int type;//0：姓名；1：电话；2：昵称；3：邮箱；4：QQ号码

    @Override
    protected int initLayoutId() {
        return R.layout.activity_set_text;
    }

    @Override
    protected void initView() {

        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("message");
        strTitle = bundle.getString("title");
        type = bundle.getInt("type");

        if (type == 1 || type == 4) {
            editSetText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }

        toolbar.setTitle(strTitle);
        toolbar.inflateMenu(R.menu.save);
        toolbar.setNavigationIcon(R.drawable.ic_main_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.save:
                        callBack();
                        finish();
                        return true;
                }
                return false;
            }
        });

    }

    @Override
    protected void initData() {
        editSetText.setText(message);
    }

    private void callBack() {
        String strContent = editSetText.getText().toString();
        String strToast = "";

        if (!TextUtils.isEmpty(strContent)) {
            Intent intent = new Intent();
            intent.putExtra("value", strContent);
            intent.putExtra("type", type);
            setResult(1, intent);
        } else if (type == 3 && ValidateUtil.isEmail(strContent)) {
            strToast = "请输入正确的邮箱";
            SnackbarUtils.showShort(toolbar, strToast);
        } else {
            if (type == 0) {
                strToast = "请输入姓名";
            }
            if (type == 1) {
                strToast = "请输入手机号码";
            }
            if (type == 2) {
                strToast = "请输入昵称";
            }
            if (type == 4) {
                strToast = "请输入QQ号码";
            }
            SnackbarUtils.showShort(toolbar, strToast);
        }
    }
}
