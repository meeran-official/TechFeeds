package com.meeran.techfeed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

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
        if (newArticles != null) {
            int startPosition = this.articles.size();
            this.articles.addAll(newArticles);
            notifyItemRangeInserted(startPosition, newArticles.size());
        }
    }    // ViewHolder class
    public class NewsViewHolder extends RecyclerView.ViewHolder {
        
        private TextView articleTitle;
        private TextView articleDescription;
        private TextView articleSource;
        private TextView articleDate;
          public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            articleTitle = itemView.findViewById(R.id.article_title);
            articleDescription = itemView.findViewById(R.id.article_description);
            articleSource = itemView.findViewById(R.id.article_source);
            articleDate = itemView.findViewById(R.id.article_date);            // Set click listener for the entire item
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
            } else {
                // Handle null article
                articleTitle.setText("No title available");
                articleDescription.setVisibility(View.GONE);
                articleSource.setText("Unknown Source");
                articleDate.setText("No date");
            }
        }
    }
}
