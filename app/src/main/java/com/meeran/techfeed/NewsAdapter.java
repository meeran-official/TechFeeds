package com.meeran.techfeed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    
    private List<Article> articles;
    private OnArticleClickListener clickListener;
    
    // Interface for click listener
    public interface OnArticleClickListener {
        void onArticleClick(Article article);
    }
    
    // Constructor
    public NewsAdapter() {
        this.articles = new ArrayList<>();
    }
    
    // Constructor with click listener
    public NewsAdapter(OnArticleClickListener clickListener) {
        this.articles = new ArrayList<>();
        this.clickListener = clickListener;
    }
    
    // Constructor with initial data
    public NewsAdapter(List<Article> articles) {
        this.articles = articles != null ? articles : new ArrayList<>();
    }
    
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_article, parent, false);
        return new NewsViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.bind(article);
    }
    
    @Override
    public int getItemCount() {
        return articles.size();
    }
    
    // Method to update the adapter data
    public void updateArticles(List<Article> newArticles) {
        this.articles.clear();
        if (newArticles != null) {
            this.articles.addAll(newArticles);
        }
        notifyDataSetChanged();
    }
    
    // Method to add articles to existing list
    public void addArticles(List<Article> newArticles) {
        if (newArticles != null && !newArticles.isEmpty()) {
            int startPosition = this.articles.size();
            this.articles.addAll(newArticles);
            notifyItemRangeInserted(startPosition, newArticles.size());
        }
    }
    
    public int getArticlePosition(Article article) {
        return articles.indexOf(article);
    }
    
    // ViewHolder class
    public class NewsViewHolder extends RecyclerView.ViewHolder {
        
        private TextView articleTitle;
        private TextView articleDescription;
        private TextView articleSource;
        private TextView articleDate;
        
        // AI Insights views
        private LinearLayout aiInsightsSection;
        private TextView impactCategory;
        private TextView sentimentIndicator;
        private TextView solutionBadge;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            articleTitle = itemView.findViewById(R.id.article_title);
            articleDescription = itemView.findViewById(R.id.article_description);
            articleSource = itemView.findViewById(R.id.article_source);
            articleDate = itemView.findViewById(R.id.article_date);
            
            // AI Insights views
            aiInsightsSection = itemView.findViewById(R.id.ai_insights_section);
            impactCategory = itemView.findViewById(R.id.impact_category);
            sentimentIndicator = itemView.findViewById(R.id.sentiment_indicator);
            solutionBadge = itemView.findViewById(R.id.solution_badge);// Set click listener for the entire item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && clickListener != null) {
                        clickListener.onArticleClick(articles.get(position));
                    }
                }
            });
        }
        
        public void bind(Article article) {
            if (article != null) {
                // Set title
                if (article.getTitle() != null && !article.getTitle().isEmpty()) {
                    articleTitle.setText(article.getTitle());
                } else {
                    articleTitle.setText("No title available");
                }
                
                // Set description (if available)
                if (article.getDescription() != null && !article.getDescription().isEmpty()) {
                    articleDescription.setText(article.getDescription());
                    articleDescription.setVisibility(View.VISIBLE);
                } else {
                    articleDescription.setVisibility(View.GONE);
                }
                
                // Set source
                if (article.getSource() != null && article.getSource().getName() != null) {
                    articleSource.setText(article.getSource().getName());
                } else {
                    articleSource.setText("Unknown Source");
                }
                  // Set date (simple format for now)
                if (article.getPublishedAt() != null && !article.getPublishedAt().isEmpty()) {
                    // Extract just the date part (first 10 characters: YYYY-MM-DD)
                    String dateOnly = article.getPublishedAt().length() >= 10 ? 
                        article.getPublishedAt().substring(0, 10) : article.getPublishedAt();
                    articleDate.setText(dateOnly);
                } else {
                    articleDate.setText("No date");
                }
                
                // Show AI insights if available
                bindAIInsights(article);
            } else {
                // Handle null article
                articleTitle.setText("No title available");
                articleDescription.setVisibility(View.GONE);
                articleSource.setText("Unknown Source");
                articleDate.setText("No date");
                aiInsightsSection.setVisibility(View.GONE);
            }
        }
        
        private void bindAIInsights(Article article) {
            Log.d("NewsAdapter", "Binding AI insights for: " + (article.getTitle() != null ? article.getTitle() : "null title"));
            Log.d("NewsAdapter", "Impact category: " + article.getImpactCategory());
            Log.d("NewsAdapter", "Sentiment: " + article.getSentimentScore());
            Log.d("NewsAdapter", "Solution oriented: " + article.isSolutionOriented());
            
            boolean hasAnyInsights = false;
            
            // Impact Category
            if (article.getImpactCategory() != null && !article.getImpactCategory().equals("general")) {
                impactCategory.setText(article.getImpactEmoji() + " " + capitalizeFirst(article.getImpactCategory()));
                impactCategory.setVisibility(View.VISIBLE);
                hasAnyInsights = true;
            } else {
                impactCategory.setVisibility(View.GONE);
            }
            
            // Sentiment
            if (article.getSentimentScore() != null) {
                String emoji = getSentimentEmoji(article.getSentimentScore());
                sentimentIndicator.setText(emoji + " " + capitalizeFirst(article.getSentimentScore()));
                sentimentIndicator.setVisibility(View.VISIBLE);
                hasAnyInsights = true;
            } else {
                sentimentIndicator.setVisibility(View.GONE);
            }
            
            // Solution-oriented badge
            if (article.isSolutionOriented()) {
                solutionBadge.setVisibility(View.VISIBLE);
                hasAnyInsights = true;
            } else {
                solutionBadge.setVisibility(View.GONE);
            }
            
            // Show/hide the entire AI insights section
            aiInsightsSection.setVisibility(hasAnyInsights ? View.VISIBLE : View.GONE);
        }
        
        private String getSentimentEmoji(String sentiment) {
            if (sentiment == null) return "😐";
            switch (sentiment.toLowerCase()) {
                case "positive": return "😊";
                case "negative": return "😟";
                default: return "😐";
            }
        }
        
        private String capitalizeFirst(String str) {
            if (str == null || str.isEmpty()) return str;
            return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        }
    }
}
