package com.deng.coder.enumrate;

public enum NotifyEnum {
    NOTIFY_COMMENT(1,"回复了");

    private int id;
    private String tag;

    NotifyEnum(int id, String tag){
        this.id = id;
        this.tag = tag;
    }

    public int getId(){
        return this.id;
    }

    public String getTag(){
        return this.tag;
    }
}
