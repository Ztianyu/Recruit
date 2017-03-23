package cn.zty.recruit.view;


import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.LoadFileModel;

/**
 * Created by zty on 2017/2/24.
 */

public interface LoadFileView extends IBaseView {
    void onLoadFileSuccess(LoadFileModel model);
}
