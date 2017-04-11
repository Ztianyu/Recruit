package cn.zty.recruit.bean;

/**
 * Created by zty on 2017/3/22.
 */

public class AdsModel {


    /**
     * adsPosition : 1
     * id : fd88f0cba6dc48faa96ced10409cf396
     * imgUrl : http://14.29.68.166:8862/homepageAds/2017/03/ddc0ad1a8bba4b1d974a2bf8d9842492.jpg
     * state : 1
     * thumbImg : http://14.29.68.166:8862/homepageAds/2017/03/ddc0ad1a8bba4b1d974a2bf8d9842492_small.jpg
     * title : 最新公告
     * content:
     */

    private int adsPosition;
    private String id;
    private String imgUrl;
    private int state;
    private String thumbImg;
    private String title;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAdsPosition() {
        return adsPosition;
    }

    public void setAdsPosition(int adsPosition) {
        this.adsPosition = adsPosition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getThumbImg() {
        return thumbImg;
    }

    public void setThumbImg(String thumbImg) {
        this.thumbImg = thumbImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
