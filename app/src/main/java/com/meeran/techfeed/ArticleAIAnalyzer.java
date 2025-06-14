package com.meeran.techfeed;

import android.content.Context;
import android.util.Log;

public class ArticleAIAnalyzer {
    private static final String TAG = "ArticleAIAnalyzer";
    
    private Context context;
    
    public interface AnalysisCallback {
        void onAnalysisComplete(Article article);
        void onAnalysisError(String error);
    }public ArticleAIAnalyzer(Context context) {
        this.context = context;
        // Note: API integration temporarily disabled for immediate keyword-based analysis
    }    public void analyzeArticle(Article article, AnalysisCallback callback) {
        // Combine title and description for analysis
        String fullText = (article.getTitle() != null ? article.getTitle() : "") + 
                         " " + (article.getDescription() != null ? article.getDescription() : "");
        
        if (fullText.trim().isEmpty()) {
            callback.onAnalysisError("No content to analyze");
            return;
        }
        
        Log.d(TAG, "Analyzing article: " + (article.getTitle() != null ? article.getTitle() : "No title"));
        
        // Do keyword-based analysis immediately
        categorizeImpact(article, fullText, callback);
    }
    
    private void categorizeImpact(Article article, String text, AnalysisCallback callback) {
        // Simple keyword-based categorization
        String lowerText = text.toLowerCase();
        
        // Categorize by impact area
        if (lowerText.contains("climate") || lowerText.contains("environment") || lowerText.contains("carbon") || lowerText.contains("renewable")) {
            article.setImpactCategory("climate");
        } else if (lowerText.contains("health") || lowerText.contains("medical") || lowerText.contains("vaccine") || lowerText.contains("disease")) {
            article.setImpactCategory("health");
        } else if (lowerText.contains("education") || lowerText.contains("school") || lowerText.contains("university") || lowerText.contains("learning")) {
            article.setImpactCategory("education");
        } else if (lowerText.contains("poverty") || lowerText.contains("hunger") || lowerText.contains("inequality") || lowerText.contains("homeless")) {
            article.setImpactCategory("poverty");
        } else if (lowerText.contains("ai") || lowerText.contains("artificial intelligence") || lowerText.contains("technology") || lowerText.contains("innovation") || lowerText.contains("tech") || lowerText.contains("computer") || lowerText.contains("software") || lowerText.contains("game") || lowerText.contains("gaming")) {
            article.setImpactCategory("technology");
        } else {
            article.setImpactCategory("general");
        }
        
        // Simple sentiment analysis based on keywords
        boolean hasPositive = lowerText.contains("breakthrough") || lowerText.contains("success") || lowerText.contains("achievement") || lowerText.contains("improvement") || lowerText.contains("innovation") || lowerText.contains("progress");
        boolean hasNegative = lowerText.contains("death") || lowerText.contains("violence") || lowerText.contains("disaster") || lowerText.contains("crisis") || lowerText.contains("war") || lowerText.contains("attack") || lowerText.contains("problem");
        
        if (hasPositive && !hasNegative) {
            article.setSentimentScore("positive");
        } else if (hasNegative && !hasPositive) {
            article.setSentimentScore("negative");
        } else {
            article.setSentimentScore("neutral");
        }
        
        // Check if solution-oriented
        boolean isSolution = lowerText.contains("solution") || lowerText.contains("breakthrough") || 
                           lowerText.contains("innovation") || lowerText.contains("progress") ||
                           lowerText.contains("improvement") || lowerText.contains("success") ||
                           lowerText.contains("creates") || lowerText.contains("develops");
        article.setSolutionOriented(isSolution);
        
        // Simple readability score (length-based)
        int readability = calculateReadabilityScore(text);
        article.setReadabilityScore(readability);
        
        // Content warning for potentially distressing content
        boolean hasWarning = lowerText.contains("death") || lowerText.contains("violence") || 
                           lowerText.contains("disaster") || lowerText.contains("crisis") ||
                           lowerText.contains("war") || lowerText.contains("attack");
        article.setHasContentWarning(hasWarning);
        
        // Mark as analyzed
        article.setFactChecked(true);
        
        Log.d(TAG, "Article analyzed - Category: " + article.getImpactCategory() + 
                  ", Solution-oriented: " + article.isSolutionOriented() +
                  ", Sentiment: " + article.getSentimentScore());
        
        callback.onAnalysisComplete(article);
    }
    
    private int calculateReadabilityScore(String text) {
        // Simple readability based on sentence length and word complexity
        String[] sentences = text.split("[.!?]+");
        int totalWords = text.split("\\s+").length;
        int avgWordsPerSentence = sentences.length > 0 ? totalWords / sentences.length : 0;
        
        if (avgWordsPerSentence < 10) return 9; // Very easy
        if (avgWordsPerSentence < 15) return 7; // Easy
        if (avgWordsPerSentence < 20) return 5; // Medium
        if (avgWordsPerSentence < 25) return 3; // Hard
        return 1; // Very hard
    }
}
