package com.example.evernote;

public class VIDEOLIST {
    String name,videourl;
public VIDEOLIST(){

}
    public VIDEOLIST(String name, String url) {
        this.name = name;
        this.videourl = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getvideoUrl() {
        return videourl;
    }

    public void setvideoUrl(String url) {
        this.videourl = url;
    }
}
