package com.meeran.techfeed;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class NewsResponse {
    
    @SerializedName("articles")
    private List<Article> articles;
    
    // Default constructor
    public NewsResponse() {
    }
    
    // Constructor with articles list
    public NewsResponse(List<Article> articles) {
        this.articles = articles;
    }
    
    // Getter for articles
    public List<Article> getArticles() {
        return articles;
    }
    
    // Setter for articles
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
    
    @Override
    public String toString() {
        return "NewsResponse{" +
                "articles=" + articles +
                '}';
    }
}
