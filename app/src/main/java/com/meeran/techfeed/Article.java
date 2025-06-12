package com.meeran.techfeed;

import com.google.gson.annotations.SerializedName;

public class Article {
    
    @SerializedName("title")
    private String title;
    
    @SerializedName("description")
    private String description;
    
    @SerializedName("publishedAt")
    private String publishedAt;
    
    @SerializedName("source")
    private Source source;
    
    @SerializedName("url")
    private String url;
    
    @SerializedName("urlToImage")
    private String urlToImage;
    
    // Default constructor
    public Article() {
    }
    
    // Constructor with title
    public Article(String title) {
        this.title = title;
    }
    
    // Getter for title
    public String getTitle() {
        return title;
    }
    
    // Setter for title
    public void setTitle(String title) {
        this.title = title;
    }
    
    // Getter for description
    public String getDescription() {
        return description;
    }
    
    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }
    
    // Getter for publishedAt
    public String getPublishedAt() {
        return publishedAt;
    }
    
    // Setter for publishedAt
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
    
    // Getter for source
    public Source getSource() {
        return source;
    }
    
    // Setter for source
    public void setSource(Source source) {
        this.source = source;
    }
    
    // Getter for url
    public String getUrl() {
        return url;
    }
    
    // Setter for url
    public void setUrl(String url) {
        this.url = url;
    }
    
    // Getter for urlToImage
    public String getUrlToImage() {
        return urlToImage;
    }
    
    // Setter for urlToImage
    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
    
    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source=" + source +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                '}';
    }
    
    // Inner class for Source
    public static class Source {
        @SerializedName("id")
        private String id;
        
        @SerializedName("name")
        private String name;
        
        public Source() {
        }
        
        public String getId() {
            return id;
        }
        
        public void setId(String id) {
            this.id = id;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        @Override
        public String toString() {
            return "Source{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
