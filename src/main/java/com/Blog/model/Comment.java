package com.Blog.model;

import java.util.Date;

public class Comment {
    private int comment_id;
    private int post_id;
    private String username;
    private String content;
    private Date date;

    public Comment() {
    }

    public Comment(int post_id, String username, String content) {
        this.post_id = post_id;
        this.username = username;
        this.content = content;
    }

    public Comment(String username, String content, Date date) {
        this.username = username;
        this.content = content;
        this.date = date;
    }

    public Comment(int comment_id, int post_id, String username, String content, Date date) {
        this.comment_id = comment_id;
        this.post_id = post_id;
        this.username = username;
        this.content = content;
        this.date = date;
    }

    public Comment(int comment_id, String username, String content, Date date) {
        this.comment_id = comment_id;
        this.username = username;
        this.content = content;
        this.date = date;
    }

    public int getComment_id() {
        return comment_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
