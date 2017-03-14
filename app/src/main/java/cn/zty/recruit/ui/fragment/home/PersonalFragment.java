package cn.zty.recruit.ui.fragment.home;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.widget.CircleImageView;
import cn.zty.baselib.widget.StripMenuView;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseFragment;
import cn.zty.recruit.ui.activity.person.ArchivesActivity;
import cn.zty.recruit.widget.LabView;

/**
 * Created by zty on 2017/3/4.
 */

public class PersonalFragment extends BaseFragment {
    @BindView(R.id.imgHeader)
    CircleImageView imgHeader;
    @BindView(R.id.textName)
    TextView textName;
    @BindView(R.id.textSex)
    TextView textSex;
    @BindView(R.id.labOrder1)
    LabView labOrder1;
    @BindView(R.id.labOrder2)
    LabView labOrder2;
    @BindView(R.id.labOrder3)
    LabView labOrder3;
    @BindView(R.id.labResume1)
    LabView labResume1;
    @BindView(R.id.labResume2)
    LabView labResume2;
    @BindView(R.id.labResume3)
    LabView labResume3;
    @BindView(R.id.strip1)
    StripMenuView strip1;
    @BindView(R.id.strip2)
    StripMenuView strip2;
    @BindView(R.id.strip3)
    StripMenuView strip3;
    @BindView(R.id.strip4)
    StripMenuView strip4;
    @BindView(R.id.strip5)
    StripMenuView strip5;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.imgHeader, R.id.labOrder1, R.id.labOrder2, R.id.labOrder3, R.id.labResume1, R.id.labResume2, R.id.labResume3, R.id.strip1, R.id.strip2, R.id.strip3, R.id.strip4, R.id.strip5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgHeader:
                startActivity(new Intent(context, ArchivesActivity.class));
                break;
            case R.id.labOrder1:
                break;
            case R.id.labOrder2:
                break;
            case R.id.labOrder3:
                break;
            case R.id.labResume1:
                break;
            case R.id.labResume2:
                break;
            case R.id.labResume3:
                break;
            case R.id.strip1:
                break;
            case R.id.strip2:
                break;
            case R.id.strip3:
                break;
            case R.id.strip4:
                break;
            case R.id.strip5:
                break;
        }
    }
}
