package cn.zty.recruit.view;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.IntroductionModel;

/**
 * Created by zty on 2017/3/22.
 */

public interface IntroductionView extends IBaseView {
    void onIntroductionSuccess(IntroductionModel model);
}
