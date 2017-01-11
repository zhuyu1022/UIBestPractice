package com.example.zhuyu.uibestpractice;

/**
 * Created by ZHUYU on 2017/1/10 0010.
 */

public class Msg {
    public static int TYPE_RECEIVE = -1;
    public static int TYPE_SEND = 1;

    private String content;
    private int type;

    public Msg(String content, int type) {

        this.content = content;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
