package cn.zty.recruit.bean;

/**
 * Created by zty on 2017/2/24.
 */

public class LoadFileModel {
    /**
     * audioUrl : /healthArchives/2017/02/870aa5f0b19749b09828d8584c2b0b43.jpg
     * audioThumbUrl :
     * website : http://123.56.42.172:81
     */

    private String audioUrl;
    private String website;
    private String audioThumbUrl;

    public String getAudioThumbUrl() {
        return audioThumbUrl;
    }

    public void setAudioThumbUrl(String audioThumbUrl) {
        this.audioThumbUrl = audioThumbUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
