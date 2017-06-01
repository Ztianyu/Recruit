package cn.zty.recruit.view;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.CompanyModel;

/**
 * Created by zty on 2017/6/1.
 */

public interface CompanyView extends IBaseView {
    void onCompany(CompanyModel companyModel);
}
