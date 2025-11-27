# File & Document Tools Android App

A comprehensive Android application featuring file conversion, document editing, image optimization, PDF tools, and utility services organized into expandable category sections.

## 🎯 Features

### 15+ Categories with 80+ Tools:

1. **Convert From eBook** - EPUB, MOBI, AZW, FB2, LIT, PDB to PDF
2. **Convert To eBook** - PDF to various eBook formats
3. **Converter** - PDF ↔ Word, Excel, PowerPoint, Images
4. **GIF Tools** - GIF Maker and Optimizer
5. **ZIP Tools** - Create and extract ZIP archives
6. **Others** - File renamer, duplicate finder, comparator, metadata editor
7. **Optimize Images** - JPEG, PNG, WebP, GIF, SVG, TIFF, BMP optimization
8. **Convert Images** - JPG↔PNG, WebP↔PNG, GIF↔MP4 conversions
9. **Edit Images** - Crop, resize, rotate, flip, watermark, background removal
10. **Optimize PDF** - PDF compression
11. **Merge & Split PDF** - Combine, split, extract pages
12. **View & Edit PDF** - Viewer, editor, annotations, forms, signatures
13. **Convert To PDF** - Word, Excel, PowerPoint, Image, HTML, Text to PDF
14. **Convert From PDF** - PDF to various formats
15. **PDF Security** - Encrypt and decrypt PDF files

## 🏗️ Architecture

**Tech Stack:**
- Jetpack Compose with Material 3
- MVVM Architecture with Navigation Component
- Kotlin Coroutines & Flow
- Lottie for animations
- Material Design 3 with dynamic theming
- Gradle with version catalog

**Key Libraries:**
- androidx.compose:*:1.6.x
- androidx.lifecycle:lifecycle-viewmodel-compose
- androidx.navigation:navigation-compose
- com.airbnb.android:lottie-compose
- com.google.android.material:material
- io.coil-kt:coil-compose

## 📱 UI Features

- **Splash Screen** - Animated logo with smooth fade-in transition
- **Dashboard** - Grid-based category sections with animated expand/collapse
- **Category Cards** - Glass-morphism design with gradient overlays
- **Feature Tiles** - Colorful icons in lazy grid with ripple effects
- **Glass Effect** - Blur and frosted glass aesthetic on cards
- **Shimmer Loading** - Smooth skeleton loading for async data
- **Search Screen** - Real-time filtering with smooth animations
- **Settings Screen** - Theme toggle, language selector, contact/feedback
- **Dark Mode Support** - Complete Material 3 dynamic theming
- **Haptic Feedback** - Enhanced user interaction feedback

## 🎨 Visual Elements

- Material 3 color scheme with dynamic theming
- Gradient backgrounds (primary to secondary colors)
- Rounded 20dp corners throughout
- 3D elevation with shadow effects
- Smooth animations for state changes
- Particle effects on dashboard background
- Animated icons with Lottie
- Ripple press effects on all interactive elements

## 🚀 Getting Started

1. Clone this repository
2. Open in Android Studio
3. Sync Gradle files
4. Run the application

## 📋 Requirements

- Android Studio Hedgehog | 2023.1.1 or later
- Android SDK API 24+ (Android 7.0)
- Kotlin 1.9.10+
- Gradle 8.4+

## 🏆 Project Structure

```
app/
├── src/main/kotlin/com/filetools/
│   ├── MainActivity.kt
│   ├── AppNavigation.kt
│   ├── theme/           # Material 3 theme
│   ├── ui/
│   │   ├── screens/     # All screens
│   │   ├── components/  # Reusable UI components
│   │   └── effects/     # Visual effects
│   ├── viewmodel/       # ViewModels
│   ├── data/
│   │   ├── model/       # Data models
│   │   ├── repository/  # Repository pattern
│   │   └── local/       # Local data
│   └── util/           # Utilities and extensions
└── res/
    ├── raw/            # Lottie animations
    ├── values/         # Strings, colors, themes
    ├── drawable/       # Icons and drawables
    └── mipmap/         # Launcher icons
```

## ✨ Key Features Implemented

- ✅ Complete folder structure with all files
- ✅ All 15+ categories with accurate tool listings
- ✅ Dashboard screen with expandable categories and animations
- ✅ Search functionality with real-time filtering
- ✅ Settings screen with theme toggle and language selector
- ✅ All screens with proper navigation routes
- ✅ Material 3 theming applied consistently
- ✅ Smooth animations (category expand, transitions, button press)
- ✅ Glass-morphism effect on cards
- ✅ Dark mode support with proper color contrast
- ✅ Haptic feedback on interactions
- ✅ Ripple effects on clickable elements
- ✅ Shimmer loading effect
- ✅ All composables properly memoized
- ✅ Well-commented and organized code
- ✅ Gradle file with all dependencies listed
- ✅ Responsive design for different screen sizes

## 🎮 Usage

1. **Dashboard** - Browse through categories and explore tools
2. **Search** - Find specific tools quickly
3. **Settings** - Customize your experience
4. **Tool Details** - View detailed information about each tool

## 🤝 Contributing

This is a demonstration project showcasing modern Android development practices with Jetpack Compose.

## 📄 License

This project is for educational purposes.
