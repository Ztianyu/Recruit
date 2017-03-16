package cn.zty.recruit.ui.activity.person;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;

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
    private String key;

    private int type;//0：字符串；1：数字；2：电话

    private String id;


    @Override
    protected int initLayoutId() {
        return R.layout.activity_set_text;
    }

    @Override
    protected void initView() {

        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("message");
        strTitle = bundle.getString("title");
        key = bundle.getString("key");
        type = bundle.getInt("type");

        if (type == 1) {
            editSetText.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else if (type == 2) {
            editSetText.setInputType(InputType.TYPE_CLASS_PHONE);
        }

        toolbar.setTitle(strTitle);
        toolbar.inflateMenu(R.menu.save);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
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
                        finish();
                        return true;
                }
                return false;
            }
        });

    }

    @Override
    protected void initData() {

    }
}
