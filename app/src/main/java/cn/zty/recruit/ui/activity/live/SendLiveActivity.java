package cn.zty.recruit.ui.activity.live;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.utils.ResourceUtil;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.pick.FileUtils;
import cn.zty.recruit.pick.OnSelectListener;
import cn.zty.recruit.pick.SelectPicUtils;
import cn.zty.recruit.pick.SelectVideoUtils;
import cn.zty.recruit.utils.DialogUtils;
import cn.zty.recruit.widget.UnderLineEditText;

import static cn.zty.recruit.pick.SelectVideoUtils.CODE_PICK;
import static cn.zty.recruit.pick.SelectVideoUtils.CODE_TAKE;

/**
 * Created by zty on 2017/6/22.
 */

public class SendLiveActivity extends BaseActivity implements OnSelectListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.editLiveTitle)
    UnderLineEditText editLiveTitle;
    @BindView(R.id.editLiveNote)
    EditText editLiveNote;
    @BindView(R.id.btnAddLive)
    ImageView btnAddLive;
    @BindView(R.id.btnSend)
    TextView btnSend;
    @BindView(R.id.imgVideoIcon)
    ImageView imgVideoIcon;
    @BindView(R.id.textVideoSize)
    TextView textVideoSize;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_send_live;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("我要发布");
        initToolbar(toolbar);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btnAddLive, R.id.btnSend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAddLive:
                SelectPicUtils.showDialog(getSupportFragmentManager());
                break;
            case R.id.btnSend:
                break;
        }
    }

    @Override
    public void onTakePhoto() {
        SelectVideoUtils.hidePicFragment(getSupportFragmentManager());
        SelectVideoUtils.recordVideo(this);
    }

    @Override
    public void onPickPic() {
        SelectVideoUtils.hidePicFragment(getSupportFragmentManager());
        SelectVideoUtils.selectVideo(this);
    }

    private String videoPath;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CODE_PICK:
            case CODE_TAKE:
                Uri recordVideo = data.getData();
                videoPath = FileUtils.getRealPath(this, recordVideo);
                if (!TextUtils.isEmpty(videoPath)) {
                    String size = FileUtils.getFileSize(videoPath);
                    Bitmap bitmap = SelectVideoUtils.getVideoBitmap(videoPath, 150, 150, MediaStore.Images.Thumbnails.MINI_KIND);
                    if (bitmap != null) {
                        btnAddLive.setImageBitmap(bitmap);
                    } else {
                        btnAddLive.setBackgroundColor(ResourceUtil.resToColor(this, R.color.gray2));
                    }
                    imgVideoIcon.setVisibility(View.VISIBLE);
                    textVideoSize.setVisibility(View.VISIBLE);
                    textVideoSize.setText(size);
                }
                break;
        }
    }
}
