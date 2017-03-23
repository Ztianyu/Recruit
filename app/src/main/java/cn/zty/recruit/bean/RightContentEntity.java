package cn.zty.recruit.bean;


import cn.zty.linkage.bean.BaseMenuBean;

/**
 * Created by zty on 2017/3/9.
 */

public class RightContentEntity extends BaseMenuBean {

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
