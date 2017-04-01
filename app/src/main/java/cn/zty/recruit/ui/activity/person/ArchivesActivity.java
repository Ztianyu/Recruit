package cn.zty.recruit.ui.activity.person;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.utils.MyImageLoader;
import cn.zty.baselib.utils.MyTextUtils;
import cn.zty.baselib.widget.CircleImageView;
import cn.zty.baselib.widget.StripViewNoImg;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.bean.LoadFileModel;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.bean.UserModel;
import cn.zty.recruit.listener.BirthSelectListener;
import cn.zty.recruit.listener.EducationSelectListener;
import cn.zty.recruit.listener.SexSelectListener;
import cn.zty.recruit.pick.OnSelectListener;
import cn.zty.recruit.pick.SelectPicUtils;
import cn.zty.recruit.presenter.LoadFilePresenter;
import cn.zty.recruit.presenter.UpdateUserPresenter;
import cn.zty.recruit.utils.DialogUtils;
import cn.zty.recruit.utils.FileUtil;
import cn.zty.recruit.utils.ToastUtils;
import cn.zty.recruit.view.LoadFileView;
import cn.zty.recruit.view.UserView;

import static cn.zty.recruit.pick.SelectPicUtils.tempFile;

/**
 * Created by zty on 2017/3/14.
 */

public class ArchivesActivity extends BaseActivity implements OnSelectListener,
        UserView,
        LoadFileView,
        SexSelectListener,
        EducationSelectListener,
        BirthSelectListener {

    public static final int RESULT_SET_POSITION = 0;
    public static final int RESULT_SET_TEXT = 1;

    @BindView(R.id.textTitle)
    TextView textTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgUserHeader)
    CircleImageView imgUserHeader;
    @BindView(R.id.textUserNickName)
    StripViewNoImg textUserNickName;
    @BindView(R.id.textUserName)
    StripViewNoImg textUserName;
    @BindView(R.id.textUserSex)
    StripViewNoImg textUserSex;
    @BindView(R.id.textUserAge)
    StripViewNoImg textUserAge;
    @BindView(R.id.textUserPhone)
    StripViewNoImg textUserPhone;
    @BindView(R.id.textUserEducation)
    StripViewNoImg textUserEducation;
    @BindView(R.id.textUserPosition)
    StripViewNoImg textUserPosition;

    private UpdateUserPresenter presenter;

    private LoadFilePresenter loadFilePresenter;

    int sexType = -1;
    String education;

    private UserModel userModel;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_user_message;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("个人信息");
        initToolbar(toolbar);

        presenter = new UpdateUserPresenter();
        presenter.attach(this);
        presenters.add(presenter);
        loadFilePresenter = new LoadFilePresenter();
        loadFilePresenter.attach(this);
        presenters.add(loadFilePresenter);
    }

    @Override
    protected void initData() {
        userModel = RecruitApplication.getInstance().getUserModel();
        if (userModel != null)
            setUser(userModel);
    }

    private void setUser(UserModel model) {
        education = userModel.getEducationLabel();
        MyImageLoader.load(this, model.getPhoto(), imgUserHeader);
        textUserNickName.setAdditionText(MyTextUtils.notNullStr(userModel.getNickNm()));
        textUserName.setAdditionText(MyTextUtils.notNullStr(userModel.getFullNm()));
        textUserSex.setAdditionText(MyTextUtils.notNullStr(userModel.getSex()));
        textUserAge.setAdditionText(MyTextUtils.notNullStr(userModel.getBirthDate()));
        textUserPhone.setAdditionText(MyTextUtils.notNullStr(userModel.getMobile()));
        textUserEducation.setAdditionText(MyTextUtils.notNullStr(userModel.getEducationLabel()));
        textUserPosition.setAdditionText(MyTextUtils.notNullStr(userModel.getAreaNm()));

        if (!TextUtils.isEmpty(userModel.getSex()))
            if (userModel.getSex().equals("男")) {
                sexType = 0;
            } else {
                sexType = 1;
            }
    }

    @OnClick({R.id.imgUserHeader, R.id.textUserNickName, R.id.textUserName, R.id.textUserSex, R.id.textUserAge, R.id.textUserPhone, R.id.textUserEducation, R.id.textUserPosition})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgUserHeader:
                SelectPicUtils.showDialog(getSupportFragmentManager());
                break;
            case R.id.textUserNickName:
                Bundle bundle = new Bundle();
                bundle.putString("title", "昵 称");
                bundle.putString("message", MyTextUtils.notNullStr(userModel.getNickNm()));
                bundle.putInt("type", 2);
                startActivityForResult(new Intent(this, SetTextActivity.class).putExtras(bundle), RESULT_SET_TEXT);
                break;
            case R.id.textUserName:
                Bundle bundle1 = new Bundle();
                bundle1.putString("title", "姓 名");
                bundle1.putString("message", MyTextUtils.notNullStr(userModel.getFullNm()));
                bundle1.putInt("type", 0);
                startActivityForResult(new Intent(this, SetTextActivity.class).putExtras(bundle1), RESULT_SET_TEXT);
                break;
            case R.id.textUserSex:
                DialogUtils.showSexSelect(getSupportFragmentManager(), sexType, this);
                break;
            case R.id.textUserAge:
                DialogUtils.showDataSelect(this, this);
                break;
            case R.id.textUserPhone:
                Bundle bundle2 = new Bundle();
                bundle2.putString("title", "手机号码");
                bundle2.putString("message", MyTextUtils.notNullStr(userModel.getMobile()));
                bundle2.putInt("type", 1);
                startActivityForResult(new Intent(this, SetTextActivity.class).putExtras(bundle2), RESULT_SET_TEXT);
                break;
            case R.id.textUserEducation:
                DialogUtils.showEducationSelect(getSupportFragmentManager(), education, this);
                break;
            case R.id.textUserPosition:
                startActivityForResult(new Intent(this, SetPositionActivity.class), RESULT_SET_POSITION);
                break;
        }
    }

    @Override
    public void onTakePhoto() {
        SelectPicUtils.hidePicFragment(getSupportFragmentManager());
        SelectPicUtils.takePhoto(this);
    }

    @Override
    public void onPickPic() {
        SelectPicUtils.hidePicFragment(getSupportFragmentManager());
        SelectPicUtils.pickPicture(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case SelectPicUtils.PHOTO_REQUEST_GALLERY:
                if (data != null)
                    SelectPicUtils.startPhotoZoom(this, data.getData());
                break;
            case SelectPicUtils.PHOTO_REQUEST_TAKE_PHOTO:
                SelectPicUtils.startPhotoZoom(this, Uri.fromFile(tempFile));
                break;
            case SelectPicUtils.PHOTO_REQUEST_CUT:
                if (data != null)
                    saveImage(data);
                break;
            case RESULT_SET_POSITION:
                if (data != null) {
                    Bundle bundle1 = data.getExtras();
                    textUserPosition.setAdditionText(bundle1.getString("province") + "." + bundle1.getString("city"));
                    presenter.update(null, null, null, null, null, null, bundle1.getString("provinceKey"),
                            bundle1.getString("cityKey"), null);
                }
                break;
            case RESULT_SET_TEXT:
                if (data != null) {
                    Bundle bundle2 = data.getExtras();
                    int type = bundle2.getInt("type");
                    String message = bundle2.getString("value");
                    if (type == 0) {
                        textUserName.setAdditionText(message);
                        presenter.update(null, message, null, null, null, null, null, null, null);
                    } else if (type == 1) {
                        textUserPhone.setAdditionText(message);
                        presenter.update(null, null, null, message, null, null, null, null, null);
                    } else if (type == 2) {
                        textUserNickName.setAdditionText(message);
                        presenter.update(message, null, null, null, null, null, null, null, null);
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 保存图片到SD卡
     */
    private void saveImage(Intent data) {
        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                Bitmap photo = bundle.getParcelable("data");
                imgUserHeader.setImageBitmap(photo);
                try {
                    File file = new File(SelectPicUtils.PHOTO_CUT_DIR, SelectPicUtils.setHeaderFileName());
                    FileUtil.saveMyBitmap(file, photo);
                    loadFilePresenter.load(file, "");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onUserSuccess(final UserModel userModel) {
        if (userModel != null) {
            ToastUtils.show("修改成功");
            RecruitApplication.getInstance().setUserModel(userModel);

//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    setUser(userModel);
//                }
//            });
        }
    }

    @Override
    public void onSexListener(String sex, int type) {
        textUserSex.setAdditionText(sex);
        sexType = type;
        presenter.update(null, null, sex, null, null, null, null, null, null);
    }

    @Override
    public void onDateSelect(String date) {
        textUserAge.setAdditionText(date);
        presenter.update(null, null, null, null, date, null, null, null, null);
    }

    @Override
    public void onLoadFileSuccess(LoadFileModel model) {
        if (model != null)
            presenter.update(null, null, null, null, null, null, null, null, model.getAudioUrl());
    }

    @Override
    public void onEducationListener(TipModel tipModel) {
        textUserEducation.setAdditionText(tipModel.getValue());
        education = tipModel.getValue();
        presenter.update(null, null, null, null, null, tipModel.getKey(), null, null, null);
        DialogFragment dialogFragment = (DialogFragment) getSupportFragmentManager().findFragmentByTag(DialogUtils.EDUCATION_SELECT);
        dialogFragment.dismiss();
    }
}
