package cn.zty.recruit.ui.activity.learn;

import android.net.Uri;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * 在线试听
 * Created by zty on 2017/3/17.
 */

public class AuditionActivity extends BaseActivity implements
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener {

    @BindView(R.id.videoView)
    VideoView videoView;

    private MediaController mMediaController;

    private String PATH_URL1 = "http://gslb.miaopai.com/stream/3D~8BM-7CZqjZscVBEYr5g__.mp4";

    @Override
    protected int initLayoutId() {
        return R.layout.view_video;
    }

    @Override
    protected void initView() {

        setTitleBar();

        videoView.setVideoURI(Uri.parse(PATH_URL1)); //实例化控制器
        mMediaController = new MediaController(this); //绑定控制器
        videoView.setMediaController(mMediaController); //控制器显示9s后自动隐藏 mMediaController.show(9000); //设置播放画质 高画质
        videoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);
        videoView.setVideoLayout(VideoView.VIDEO_LAYOUT_STRETCH, 0); //取得焦点 mVideoView.requestFocus(); //设置相关的监听
        videoView.setOnPreparedListener(this);
        videoView.setOnErrorListener(this);
        videoView.setOnCompletionListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        videoView.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }
}
