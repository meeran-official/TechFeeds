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
    
    // AI-Enhanced fields for social impact
    private String aiSummary;
    private String sentimentScore; // "positive", "negative", "neutral"
    private boolean isSolutionOriented;
    private boolean isFactChecked;
    private String impactCategory; // "climate", "health", "education", "poverty", "technology"
    private int readabilityScore; // 1-10 scale
    private boolean hasContentWarning;
    
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
    
    // Getter for aiSummary
    public String getAiSummary() {
        return aiSummary;
    }
    
    // Setter for aiSummary
    public void setAiSummary(String aiSummary) {
        this.aiSummary = aiSummary;
    }
    
    // Getter for sentimentScore
    public String getSentimentScore() {
        return sentimentScore;
    }
    
    // Setter for sentimentScore
    public void setSentimentScore(String sentimentScore) {
        this.sentimentScore = sentimentScore;
    }
    
    // Getter for isSolutionOriented
    public boolean isSolutionOriented() {
        return isSolutionOriented;
    }
    
    // Setter for isSolutionOriented
    public void setSolutionOriented(boolean solutionOriented) {
        isSolutionOriented = solutionOriented;
    }
    
    // Getter for isFactChecked
    public boolean isFactChecked() {
        return isFactChecked;
    }
    
    // Setter for isFactChecked
    public void setFactChecked(boolean factChecked) {
        isFactChecked = factChecked;
    }
    
    // Getter for impactCategory
    public String getImpactCategory() {
        return impactCategory;
    }
    
    // Setter for impactCategory
    public void setImpactCategory(String impactCategory) {
        this.impactCategory = impactCategory;
    }
    
    // Getter for readabilityScore
    public int getReadabilityScore() {
        return readabilityScore;
    }
    
    // Setter for readabilityScore
    public void setReadabilityScore(int readabilityScore) {
        this.readabilityScore = readabilityScore;
    }
    
    // Getter for hasContentWarning
    public boolean hasContentWarning() {
        return hasContentWarning;
    }
    
    // Setter for hasContentWarning
    public void setHasContentWarning(boolean hasContentWarning) {
        this.hasContentWarning = hasContentWarning;
    }
    
    // AI-powered helper methods
    public boolean isPositiveNews() {
        return "positive".equals(sentimentScore);
    }
    
    public String getReadabilityLevel() {
        if (readabilityScore >= 8) return "Easy";
        if (readabilityScore >= 5) return "Medium";
        return "Complex";
    }
    
    public String getImpactEmoji() {
        if (impactCategory == null) return "📰";
        switch (impactCategory.toLowerCase()) {
            case "climate": return "🌍";
            case "health": return "🏥";
            case "education": return "📚";
            case "poverty": return "🤝";
            case "technology": return "💻";
            default: return "📰";
        }
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
                ", aiSummary='" + aiSummary + '\'' +
                ", sentimentScore='" + sentimentScore + '\'' +
                ", isSolutionOriented=" + isSolutionOriented +
                ", isFactChecked=" + isFactChecked +
                ", impactCategory='" + impactCategory + '\'' +
                ", readabilityScore=" + readabilityScore +
                ", hasContentWarning=" + hasContentWarning +
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
