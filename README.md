# 📱 TechFeed - Modern News Reader App

A beautiful, modern Android news application built with Java that delivers the latest news across multiple categories including Technology, Business, Entertainment, Health, Science, and Sports.

![TechFeed App](https://img.shields.io/badge/Platform-Android-green.svg)
![API Level](https://img.shields.io/badge/API-21%2B-brightgreen.svg)
![License](https://img.shields.io/badge/License-MIT-blue.svg)

## ✨ Features

- 📰 **Multiple News Categories**: Technology, Business, Entertainment, Health, Science, Sports
- 🔄 **Pull-to-Refresh**: Swipe down to get the latest articles
- 🎯 **Article Click Handling**: Tap any article to read the full story
- 📱 **Modern Material Design UI**: Clean, professional interface with card-based layout
- 🌙 **Theme Support**: Light and dark theme compatibility
- 🔗 **External Browser Integration**: Seamlessly opens articles in your preferred browser
- ⚡ **Fast Loading**: Optimized performance with efficient data handling

## 🚀 Screenshots

| Home Screen | Category Tabs | Article Details |
|-------------|---------------|-----------------|
| *Coming Soon* | *Coming Soon* | *Coming Soon* |

## 🛠️ Tech Stack

- **Language**: Java
- **UI Framework**: Android Views with Material Design Components
- **Architecture**: MVP (Model-View-Presenter)
- **Networking**: Retrofit 2 + OkHttp
- **JSON Parsing**: Gson
- **API**: [NewsAPI.org](https://newsapi.org/)

## 📋 Prerequisites

- Android Studio Arctic Fox (2020.3.1) or later
- Java 8 or higher
- Android SDK API level 21 or higher
- News API key from [NewsAPI.org](https://newsapi.org/)

## 🔧 Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/TechFeed.git
cd TechFeed
```

### 2. API Key Configuration
1. Get your free API key from [NewsAPI.org](https://newsapi.org/register)
2. Copy the example configuration file:
   ```bash
   cp apikey.properties.example apikey.properties
   ```
3. Open `apikey.properties` and replace `YOUR_NEWS_API_KEY_HERE` with your actual API key:
   ```properties
   NEWS_API_KEY=your_actual_api_key_here
   ```

### 3. Build and Run
1. Open the project in Android Studio
2. Sync the project with Gradle files
3. Run the app on an emulator or physical device

## 📁 Project Structure

```
TechFeed/
├── app/
│   ├── src/main/java/com/meeran/techfeed/
│   │   ├── MainActivity.java          # Main activity with tab navigation
│   │   ├── NewsAdapter.java           # RecyclerView adapter for articles
│   │   ├── Article.java               # Article data model
│   │   ├── NewsResponse.java          # API response model
│   │   └── NewsApiService.java        # Retrofit API interface
│   └── src/main/res/
│       ├── layout/                    # XML layout files
│       ├── values/                    # Colors, strings, themes
│       └── mipmap/                    # App icons
├── apikey.properties                  # API keys (excluded from git)
├── apikey.properties.example          # Example API key file
└── README.md
```

## 🌐 API Integration

This app uses the [NewsAPI.org](https://newsapi.org/) service to fetch news articles. The API provides:

- **Top Headlines**: Latest breaking news
- **Categories**: Technology, Business, Entertainment, Health, Science, Sports
- **Country-specific**: News from specific countries (default: US)
- **Pagination**: Support for loading more articles

### API Endpoints Used:
- `GET /v2/top-headlines` - Fetch top headlines by category
- `GET /v2/everything` - Search for specific news (planned feature)

## 🎨 UI/UX Design

- **Material Design 3**: Modern, clean interface
- **Card-based Layout**: Easy-to-read article cards with elevation
- **Tab Navigation**: Quick category switching
- **Pull-to-Refresh**: Intuitive refresh mechanism
- **Responsive Design**: Works on different screen sizes

## 🔒 Security

- ✅ **API Key Protection**: Keys stored in excluded properties file
- ✅ **BuildConfig Integration**: Secure build-time injection
- ✅ **Environment Variables**: CI/CD friendly configuration
- ✅ **No Hardcoded Secrets**: All sensitive data externalized

## 📱 Supported Android Versions

- **Minimum SDK**: API 21 (Android 5.0 Lollipop)
- **Target SDK**: API 34 (Android 14)
- **Compiled SDK**: API 34

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- [NewsAPI.org](https://newsapi.org/) for providing the news data
- [Material Design](https://material.io/) for design guidelines
- [Retrofit](https://square.github.io/retrofit/) for networking
- Android Open Source Project

## 📞 Contact

Your Name - [@yourtwitter](https://twitter.com/yourtwitter) - your.email@example.com

Project Link: [https://github.com/yourusername/TechFeed](https://github.com/yourusername/TechFeed)

---

⭐ **If you found this project helpful, please give it a star!** ⭐
