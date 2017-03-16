package cn.zty.recruit.ui.activity.person;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.widget.CircleImageView;
import cn.zty.baselib.widget.StripViewNoImg;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.pick.OnSelectListener;
import cn.zty.recruit.pick.SelectPicUtils;
import cn.zty.recruit.utils.DialogUtils;
import cn.zty.recruit.utils.FileUtil;

import static cn.zty.recruit.pick.SelectPicUtils.tempFile;

/**
 * Created by zty on 2017/3/14.
 */

public class ArchivesActivity extends BaseActivity implements OnSelectListener {
    @BindView(R.id.textTitle)
    TextView textTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgUserHeader)
    CircleImageView imgUserHeader;
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

    @Override
    protected int initLayoutId() {
        return R.layout.activity_user_message;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("个人信息");
        initToolbar(toolbar);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.imgUserHeader, R.id.textUserName, R.id.textUserSex, R.id.textUserAge, R.id.textUserPhone, R.id.textUserEducation, R.id.textUserPosition})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgUserHeader:
                SelectPicUtils.showDialog(getSupportFragmentManager());
                break;
            case R.id.textUserName:
                Bundle bundle = new Bundle();
                bundle.putString("title", "姓名");
                bundle.putString("message", "春暖花开");
                bundle.putString("key", "name");
                bundle.putInt("type", 0);
                startActivity(new Intent(this, SetTextActivity.class).putExtras(bundle));
                break;
            case R.id.textUserSex:
                startActivity(new Intent(this, SetSexActivity.class));
                break;
            case R.id.textUserAge:
                DialogUtils.showDataSelect(this, textUserAge);
                break;
            case R.id.textUserPhone:
                Bundle bundle2 = new Bundle();
                bundle2.putString("title", "手机号码");
                bundle2.putString("message", "15515797465");
                bundle2.putString("key", "phone");
                bundle2.putInt("type", 2);
                startActivity(new Intent(this, SetTextActivity.class).putExtras(bundle2));
                break;
            case R.id.textUserEducation:
                startActivity(new Intent(this, SetEducationActivity.class));
                break;
            case R.id.textUserPosition:
                startActivity(new Intent(this, SetPositionActivity.class));
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
                if (data != null)
                    SelectPicUtils.startPhotoZoom(this, Uri.fromFile(tempFile));
                break;
            case SelectPicUtils.PHOTO_REQUEST_CUT:
                if (data != null)
                    saveImage(data);
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
