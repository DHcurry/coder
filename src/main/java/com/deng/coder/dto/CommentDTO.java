package com.deng.coder.dto;

public class CommentDTO {
    private int commentor;
    private String content;
    private int articleId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getCommentor() {
        return commentor;
    }

    public void setCommentor(int commentor) {
        this.commentor = commentor;
    }
}
