package cn.zty.recruit.view;

import java.util.List;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.CompanyJobModel;

/**
 * Created by zty on 2017/5/31.
 */

public interface CompanyJobView extends IBaseView {
    void onCompanyJob(List<CompanyJobModel> models);
}
