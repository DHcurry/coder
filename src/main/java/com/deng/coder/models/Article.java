package com.deng.coder.models;

public class Article {
    private int id;
    private String title;
    private String content;
    private String tag;
    private long gmtCreate;
    private long gmtModify;
    private int writerId;
    private int viewAccount;
    private int commentAccount;
    private int likeAccount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public long getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(long gmtModify) {
        this.gmtModify = gmtModify;
    }

    public int getWriterId() {
        return writerId;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }

    public int getViewAccount() {
        return viewAccount;
    }

    public void setViewAccount(int viewAccount) {
        this.viewAccount = viewAccount;
    }

    public int getCommentAccount() {
        return commentAccount;
    }

    public void setCommentAccount(int commentAccount) {
        this.commentAccount = commentAccount;
    }

    public int getLikeAccount() {
        return likeAccount;
    }

    public void setLikeAccount(int likeAccount) {
        this.likeAccount = likeAccount;
    }
}
