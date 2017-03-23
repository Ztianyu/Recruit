package cn.zty.recruit.view;

import java.util.List;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.NoticeModel;

/**
 * Created by zty on 2017/3/22.
 */

public interface NoticeView extends IBaseView {
    void onNoticeSuccess(List<NoticeModel> models);
}
