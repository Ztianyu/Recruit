package cn.zty.recruit.listener;

/**
 * Created by zty on 2017/1/9.
 */

public interface SendReplayListener {
    void onSend(String forumId, String userId, int position, String message);
}
