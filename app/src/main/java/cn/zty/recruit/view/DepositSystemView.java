package cn.zty.recruit.view;

import java.util.List;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.DepositSystemModel;

/**
 * Created by zty on 2017/3/23.
 */

public interface DepositSystemView extends IBaseView {

    void onDepositSystemSuccess(List<DepositSystemModel> models);
}
