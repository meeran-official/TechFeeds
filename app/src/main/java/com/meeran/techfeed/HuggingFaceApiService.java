package com.meeran.techfeed;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface HuggingFaceApiService {
    
    // Free Hugging Face API for text analysis
    @Headers({
        "Content-Type: application/json"
    })
    @POST("models/facebook/bart-large-cnn")
    Call<SummarizationResponse> summarizeText(@Body SummarizationRequest request);
    
    @Headers({
        "Content-Type: application/json"
    })
    @POST("models/unitary/toxic-bert")
    Call<ToxicityResponse> analyzeToxicity(@Body ToxicityRequest request);
    
    @Headers({
        "Content-Type: application/json"
    })
    @POST("models/cardiffnlp/twitter-roberta-base-sentiment-latest")
    Call<SentimentResponse> analyzeSentiment(@Body SentimentRequest request);
}

// Request/Response classes for AI services
class SummarizationRequest {
    public String inputs;
    public Parameters parameters;
    
    public SummarizationRequest(String text) {
        this.inputs = text;
        this.parameters = new Parameters();
    }
    
    class Parameters {
        public int max_length = 100;
        public int min_length = 30;
        public boolean do_sample = false;
    }
}

class SummarizationResponse {
    public SummaryResult[] summary_text;
    
    class SummaryResult {
        public String summary_text;
    }
}

class ToxicityRequest {
    public String inputs;
    
    public ToxicityRequest(String text) {
        this.inputs = text;
    }
}

class ToxicityResponse {
    public ToxicityResult[][] results;
    
    class ToxicityResult {
        public String label;
        public double score;
    }
}

class SentimentRequest {
    public String inputs;
    
    public SentimentRequest(String text) {
        this.inputs = text;
    }
}

class SentimentResponse {
    public SentimentResult[][] results;
    
    class SentimentResult {
        public String label;
        public double score;
    }
}
