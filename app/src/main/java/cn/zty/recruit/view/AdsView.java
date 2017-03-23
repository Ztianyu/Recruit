package cn.zty.recruit.view;

import java.util.List;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.AdsModel;

/**
 * Created by zty on 2017/3/22.
 */

public interface AdsView extends IBaseView{
    void onAdsSuccess(List<AdsModel> models);
}
