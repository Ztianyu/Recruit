package cn.zty.recruit.view;

import java.util.List;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.TipModel;

/**
 * Created by zty on 2017/3/22.
 */

public interface DictListView extends IBaseView {
    void onDictSuccess(String type, List<TipModel> models);
}
