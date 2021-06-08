package com.Blog.model;

import java.util.Date;
import java.util.List;

public class Post {
    private int post_id;
    private String username;
    private String title;
    private String content;
    private Date date;
    private String image;
    private List<Comment> comments;

    public Post() {
    }

    public Post(int post_id, String username, String title, String content, Date date, String image, List<Comment> comments) {
        this.post_id = post_id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.date = date;
        this.image = image;
        this.comments = comments;
    }

    public Post(String username, String title, String content, String image) {
        this.username = username;
        this.title = title;
        this.content = content;
        this.image = image;
    }

    public Post(int post_id, String username, String title, String content, Date date, String image) {
        this.post_id = post_id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.date = date;
        this.image = image;
    }

    public Post(String username, String title, String content, Date date, String image) {
        this.username = username;
        this.title = title;
        this.content = content;
        this.date = date;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public Post(int post_id, String username, String title, String content, Date date) {
        this.post_id = post_id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Post(String username, String title, String content, Date date) {
        this.username = username;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public Post(String title, String content, Date date) {
        this.title = title;
        this.content = content;
        this.date = date;
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

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
