package com.meeran.techfeed;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.tabs.TabLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;

public class MainActivity extends AppCompatActivity {
      private static final String TAG = "MainActivity";
    private static final String BASE_URL = "https://newsapi.org/";
    private static final String API_KEY = BuildConfig.NEWS_API_KEY;
      private RecyclerView newsRecyclerView;
    private NewsAdapter newsAdapter;
    private NewsApiService newsApiService;
    private SwipeRefreshLayout swipeRefresh;
    private ProgressBar loadingProgress;
    private TabLayout tabLayout;
    
    private String currentCategory = "technology";
    private int currentPage = 1;
    private final int pageSize = 20; // Get 20 articles per request
    
    // Categories for the tabs
    private final String[] categories = {"technology", "business", "entertainment", "health", "science", "sports"};
    private final String[] categoryTitles = {"Tech", "Business", "Entertainment", "Health", "Science", "Sports"};
      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          // Setup toolbar
        setupToolbar();
        
        // Setup tabs
        setupTabs();
        
        // Setup RecyclerView
        setupRecyclerView();
        
        // Setup SwipeRefresh
        setupSwipeRefresh();
        
        // Create Retrofit instance
        setupRetrofit();
        
        // Fetch news articles
        fetchTopHeadlines();
    }
    
    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("TechFeed");
        }
    }
    
    private void setupTabs() {
        tabLayout = findViewById(R.id.tab_layout);
        
        // Add tabs for different categories
        for (String categoryTitle : categoryTitles) {
            tabLayout.addTab(tabLayout.newTab().setText(categoryTitle));
        }
        
        // Set tab selection listener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentCategory = categories[tab.getPosition()];
                currentPage = 1; // Reset page when changing category
                fetchTopHeadlines();
            }
            
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            
            @Override
            public void onTabReselected(TabLayout.Tab tab) {                // Refresh current category when tab is tapped again
                currentPage = 1;
                fetchTopHeadlines();
            }
        });
    }
    
    private void setupRecyclerView() {
        newsRecyclerView = findViewById(R.id.news_recycler_view);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        // Initialize adapter with click listener
        newsAdapter = new NewsAdapter(new NewsAdapter.OnArticleClickListener() {
            @Override
            public void onArticleClick(Article article) {
                // Open article in browser when clicked
                if (article != null && article.getUrl() != null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
                    startActivity(intent);
                } else {
                    showToast("No URL available for this article");
                }
            }
        });
        newsRecyclerView.setAdapter(newsAdapter);
    }
    
    private void setupRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        
        newsApiService = retrofit.create(NewsApiService.class); //test
    }
    
    private void setupSwipeRefresh() {        swipeRefresh = findViewById(R.id.swipe_refresh);
        loadingProgress = findViewById(R.id.loading_progress);
        
        swipeRefresh.setColorSchemeColors(
            ContextCompat.getColor(this, R.color.tech_blue),
            ContextCompat.getColor(this, R.color.tech_blue_light)
        );
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                fetchTopHeadlines();
            }
        });
    }
      private void fetchTopHeadlines() {
        // Show loading indicator
        if (currentPage == 1) {
            showLoading(true);
        }
        
        Call<NewsResponse> call = newsApiService.getTopHeadlines(
            currentCategory, 
            "us", // country code
            pageSize, 
            currentPage, 
            API_KEY
        );
        
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                hideLoading();
                
                if (response.isSuccessful() && response.body() != null) {
                    NewsResponse newsResponse = response.body();
                    List<Article> articles = newsResponse.getArticles();
                    
                    if (articles != null && !articles.isEmpty()) {
                        if (currentPage == 1) {
                            // Replace articles for first page
                            newsAdapter.updateArticles(articles);
                        } else {
                            // Add articles for subsequent pages
                            newsAdapter.addArticles(articles);
                        }
                        Log.d(TAG, "Successfully loaded " + articles.size() + " articles for page " + currentPage);
                    } else {
                        Log.w(TAG, "No articles found in response");
                        if (currentPage == 1) {
                            showToast("No articles found for " + currentCategory);
                        }
                    }
                } else {
                    Log.e(TAG, "Response unsuccessful: " + response.code());
                    showToast("Failed to load news: " + response.message());
                }
            }
            
            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                hideLoading();
                Log.e(TAG, "Network request failed", t);
                showToast("Network error: " + t.getMessage());
            }
        });
    }
    
    private void showLoading(boolean show) {
        if (show) {
            loadingProgress.setVisibility(View.VISIBLE);
            swipeRefresh.setVisibility(View.GONE);
        } else {
            loadingProgress.setVisibility(View.GONE);
            swipeRefresh.setVisibility(View.VISIBLE);
        }
    }
      private void hideLoading() {
        loadingProgress.setVisibility(View.GONE);        swipeRefresh.setVisibility(View.VISIBLE);
        swipeRefresh.setRefreshing(false);
    }
    
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
