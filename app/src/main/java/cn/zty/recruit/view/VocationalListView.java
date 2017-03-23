package cn.zty.recruit.view;

import java.util.List;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.VocationalModel;

/**
 * Created by zty on 2017/3/22.
 */

public interface VocationalListView extends IBaseView {
    void onVocationalListSuccess(List<VocationalModel> models);
}
