package cn.zty.recruit.view;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.UserModel;

/**
 * Created by zty on 2017/3/21.
 */

public interface UserView extends IBaseView {
    void onSuccess(UserModel userModel);
}
