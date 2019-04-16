package com.androidcourse.manisha.instapost.Model;


public class Post {
    public Post(){

    }

    String url;
    String description;
    User user;

    public Post(String url, String description, User user) {
        this.url = url;
        this.description = description;
        this.user = user;

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
