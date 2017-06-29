package cn.zty.recruit.ui.activity.learn;

import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;

/**
 * 视频播放
 * Created by zty on 2017/3/17.
 */

public class VideoActivity extends BaseActivity {

    @BindView(R.id.videoView)
    NiceVideoPlayer videoView;

    private String PATH_URL1 = "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-33-30.mp4";
    private String videoUrl;
    private String videoName;

    @Override
    protected int initLayoutId() {
        return R.layout.view_video;
    }

    @Override
    protected void initView() {
        videoUrl = getIntent().getStringExtra("videoUrl");
        videoName = getIntent().getStringExtra("videoName");

        setTitleBar();

        videoView.setPlayerType(NiceVideoPlayer.PLAYER_TYPE_IJK); // IjkPlayer or MediaPlayer
        videoView.setUp(videoUrl, null);
        TxVideoPlayerController controller = new TxVideoPlayerController(this);
        controller.setTitle(videoName);
        videoView.setController(controller);
        videoView.enterFullScreen();
        videoView.start();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        NiceVideoPlayerManager.instance().pauseNiceVideoPlayer();
        super.onStop();
    }

    @Override
    protected void onRestart() {
        NiceVideoPlayerManager.instance().restartNiceVideoPlayer();
        super.onRestart();
    }
}
