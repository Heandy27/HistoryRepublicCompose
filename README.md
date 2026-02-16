# History Republic - Android App (Jetpack Compose)

**History Republic** is an Android app built with Jetpack Compose that brings the past to life by showcasing influential historical figures and their legacies.

<div style="display: flex; justify-content: center; align-items: center;">
<img src="https://i.postimg.cc/T1Z7fr3M/Chat_GPT_Image_Feb_6_2026_07_53_46_PM.png" alt="map" width="200">
<img src="https://i.postimg.cc/qqtbPm4k/Chat_GPT_Image_Feb_6_2026_07_52_30_PM.png" alt="map" width="200">
<img src="https://i.postimg.cc/Fz2WNjHt/Chat_GPT_Image_Feb_6_2026_07_55_42_PM.png" alt="details" width="200">
<img src="https://i.postimg.cc/qq9b48vT/Chat_GPT_Image_Feb_6_2026_07_56_55_PM.png" alt="favorites" width="200">
</div>

---

## 📱 Features

- **Heroes List:** Browse a curated list of historical heroes from around the world.
- **Search:** Find heroes by name (and optionally filter/sort).
- **Hero Details:** Dedicated detail screen (supports rich content and external links).
- **Offline Support (Room):** Persist favorites (and optionally cached heroes) on device.
- **Modern Navigation (NavHost):** Smooth navigation between screens in Compose.
- **MVVM:** Clear separation of UI, state, and business logic.
- **Dependency Injection (Hilt):** Clean, scalable DI setup.
- **Networking (Retrofit):** Connects to a REST API backend.
- **Backend built with Vapor:** Swift-based server-side framework that powers the API.

---

## 🛠 Technologies

- Kotlin
- Jetpack Compose
- Android Studio
- Navigation Compose (NavHost)
- Retrofit + OkHttp
- Dagger Hilt
- Room
- Coroutines (async)
- MVVM (ViewModel + StateFlow)

## 🛠 Technologies

UI (Compose Screens): Renders state and triggers events.

ViewModel: Holds UI state, calls use-cases/repositories.

Repository: Single source of truth (API + local database).

Data Sources:

Remote: Retrofit services

Local: Room DAO

---

## 🚀 Installation

1. Clone this repository:
   ```bash
   https://github.com/Heandy27/HistoryRepublicCompose.git
2. Open the project in Android Studio.
3. Run on an emulator or physical device.

## Usage

### Browse Heroes
Open the app to see a list of historical heroes.

### View Details
Tap a hero to see their story and key information in the details screen.

**Website:** https://historyrepublic.com/

<hr></hr>
Thank you for reviewing my app. If you want to know more about me or my work, you can find me on these social networks:<br></br>

[![LinkedIn](https://img.shields.io/badge/LinkedIn-%230077B5.svg?logo=linkedin&logoColor=white)](https://www.linkedin.com/in/heandy27/) 
[![GitHub](https://img.shields.io/badge/GitHub-%23121011.svg?logo=github&logoColor=white)](https://github.com/Heandy27)
