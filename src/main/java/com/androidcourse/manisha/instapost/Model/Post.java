package com.androidcourse.manisha.instapost.Model;


import java.util.List;
import java.util.Set;

public class Post {
    public Post(){

    }

    String url;
    String description;
    List<String> hashtags;
    User user;

    public Post(String url, String description,List<String> hashtags, User user) {
        this.url = url;
        this.description = description;
        this.hashtags = hashtags;
        this.user = user;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
