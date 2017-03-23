package cn.zty.recruit.bean;

/**
 * Created by zty on 2017/3/4.
 */

public class LoginModel {

    private String userId;
    private String tokenId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
