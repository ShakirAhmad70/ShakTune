Spotify Clone Android App
Overview
This repository contains a full-stack Android application that serves as a Spotify clone. The app replicates key features of Spotify—including user authentication, music streaming, playlist management, and a modern, animated UI—to deliver an immersive and responsive music experience.

Features
User Authentication:
Sign up and login with email, social logins, or phone number.

Music Streaming:
Browse, search, and stream a wide range of music tracks.

Playlist Management:
Create and manage custom playlists with ease.

Custom UI Animations:
Enjoy smooth slide and transition animations between activities.

Local Data Persistence:
Securely store user data and app settings using SQLite, with additional support for storing profile pictures and social relationships.

API Integration:
Leverage external APIs for music data, recommendations, and more to enrich the user experience.

Architecture
Front-End:
The Android app is built using Java, AndroidX libraries, and Material Design components for a modern and intuitive user interface.

Back-End:
A full-stack solution that integrates with external music APIs for streaming and data retrieval.

Local Storage:
Utilizes SQLite (via a custom helper class) to persist user details, playlists, and social data (followers/following), with lists stored as JSON.

Networking:
Implements libraries like Retrofit and OkHttp for reliable and efficient API calls.

Installation
Clone the Repository:

"git clone https://github.com/ShakirAhmad70/ShakTune.git" 

Open in Android Studio:

Open Android Studio.
Select File > Open and navigate to the cloned repository folder.
Build and Run:

Allow Gradle to sync and build the project.
Run the app on an emulator or physical device.
Database Initialization:

On first launch, the app creates the necessary SQLite database.
Note: If you modify the database schema during development, you might need to clear the app data or uninstall/reinstall the app.
Usage
Sign Up / Login:
Get started by creating a new account or logging in to access personalized music features.

Explore Music:
Discover, search, and stream music tracks from a rich library of songs.

Manage Playlists:
Build and manage your playlists for an organized music experience.

Seamless Navigation:
Enjoy smooth slide animations when transitioning between screens, enhancing the overall user experience.

Contributing
Contributions are welcome! If you’d like to help improve this project, please follow these steps:

Fork the repository.
Create a new branch for your feature or bug fix.
Commit your changes and push your branch.
Open a pull request with a detailed description of your changes.


Acknowledgements
Android Developers: For extensive documentation and resources.
Open Source Community: For the libraries and tools that made this project possible.
Inspiration: Spotify and other leading music streaming apps for the design and feature set.
