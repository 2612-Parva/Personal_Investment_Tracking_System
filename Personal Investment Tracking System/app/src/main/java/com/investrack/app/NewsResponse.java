package com.investrack.app;

import java.util.List;

public class NewsResponse {

    private List<News> articles;

    public NewsResponse() {
    }

    public NewsResponse(List<News> articles) {
        this.articles = articles;
    }

    public List<News> getArticles() {
        return articles;
    }

    public void setArticles(List<News> articles) {
        this.articles = articles;
    }
}
