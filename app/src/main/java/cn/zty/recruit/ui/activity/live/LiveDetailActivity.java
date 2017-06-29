package cn.zty.recruit.ui.activity.live;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.utils.ResourceUtil;
import cn.zty.baselib.widget.CircleImageView;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.listener.SendReplayListener;
import cn.zty.recruit.utils.DialogUtils;
import cn.zty.recruit.utils.ViewAdaptionUtils;

/**
 * Created by zty on 2017/6/27.
 */

public class LiveDetailActivity extends BaseActivity implements SendReplayListener {
    @BindView(R.id.videoSmall)
    NiceVideoPlayer videoSmall;
    @BindView(R.id.btnVideoBack)
    TextView btnVideoBack;
    @BindView(R.id.textVideoTime)
    TextView textVideoTime;
    @BindView(R.id.textVideoCommentCount)
    TextView textVideoCommentCount;
    @BindView(R.id.imgVideoAuthor)
    CircleImageView imgVideoAuthor;
    @BindView(R.id.textVideoTitle)
    TextView textVideoTitle;
    @BindView(R.id.textVideoAuthor)
    TextView textVideoAuthor;
    @BindView(R.id.textVideoDetail)
    TextView textVideoDetail;
    @BindView(R.id.btnComment)
    TextView btnComment;
    @BindView(R.id.listComment)
    RecyclerView listComment;

    private String videoPath = "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-33-30.mp4";

    @Override
    protected int initLayoutId() {
        return R.layout.activity_live_detail;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setColor(this, ResourceUtil.resToColor(this, R.color.transparent60));
        ViewAdaptionUtils.RelativeAdaptation(videoSmall, 400);
    }

    @Override
    protected void initData() {

        videoSmall.setPlayerType(NiceVideoPlayer.PLAYER_TYPE_IJK); // IjkPlayer or MediaPlayer
        videoSmall.setUp(videoPath, null);
        TxVideoPlayerController controller = new TxVideoPlayerController(this);
        controller.setTitle("美女在办公室给老板洗澡");
        videoSmall.setController(controller);
        videoSmall.start();
    }

    @OnClick({R.id.btnVideoBack,R.id.btnComment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnVideoBack:
                finish();
                break;
            case R.id.btnComment:
                DialogUtils.showReplay(getSupportFragmentManager(), "", 0);
                break;
        }
    }

    @Override
    public void onSend(String forumId, String userId, int position, String message) {

    }

    @Override
    public void onBackPressed() {
        if (NiceVideoPlayerManager.instance().onBackPressd()) {
            return;
        }
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

    @Override
    protected void onDestroy() {
        // 很重要，在Activity和Fragment的onStop方法中一定要调用，释放的播放器。
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
        super.onDestroy();
    }
}
