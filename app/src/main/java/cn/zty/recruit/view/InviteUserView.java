package cn.zty.recruit.view;

import java.util.List;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.UserModel;

/**
 * Created by zty on 2017/4/20.
 */

public interface InviteUserView extends IBaseView {
    void onInviteUser(List<UserModel> models);
}
