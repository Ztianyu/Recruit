package cn.zty.recruit.view;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.LoginModel;

/**
 * Created by zty on 2017/3/4.
 */

public interface LoginView extends IBaseView {
    void onSuccess(LoginModel loginModel);
}
