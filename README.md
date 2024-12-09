# MelodyDreams: The Sophisticated Online Media Player 🎶
Welcome to MelodyDreams, an advanced media streaming platform that integrates dynamic 3D visualizations, adaptive media player features, and robust backend technologies. This full-stack application combines the best of Java Spring Boot, Three.js, GLSL, and BPM-driven CSS transitions to create a groundbreaking user experience.

## Table of Contents
Features
Technologies Used
Architecture Overview
Setup Instructions
Core Functionalities
3D and Dynamic Visual Integrations
BPM-Driven Interactivity
Playlists and File Handling
Future Enhancements

## Features
MelodyDreams offers a rich set of features:

### Multi-User Support: Robust authentication and profile management.
### Dynamic 3D Animations: Track-based GLSL shaders and Three.js objects.
### BPM-Driven Visuals: CSS transitions adapt to track tempo and beats.
### Custom Playlists: Create, modify, and manage multiple playlists.
### Multi-File Uploads: Upload audio files and cover art seamlessly.
### Dynamic Retrieval: Backend-provided JSON feeds for frontend integration.
### Real-Time Visual Feedback: Sync animations and transitions with music playback.

## Technologies Used
### Backend
Java Spring Boot: Manages backend operations and REST APIs.
Spring Security: Ensures secure user authentication.
Hibernate: ORM for structured data management.
AWS S3: Stores and retrieves uploaded media files.

### Frontend
HTML5, CSS3, JSP: For structured and styled user interfaces.
JavaScript: Dynamic interactivity and player control.
Three.js: Powers dynamic 3D visualizations.
GLSL: Shader programming for advanced effects.
Font Awesome: Icons for play counts, favorites, and playlists.

## Architecture Overview
MelodyDreams operates on a modular architecture:
Frontend: Implements responsive and interactive UI.
Controllers: Processes user requests and prepares model data.
Services: Encapsulates business logic and integrations.
DAO Layer: Manages database interactions for songs, users, and playlists.
Storage: AWS S3 stores audio and images, accessible via secure URLs.

## Setup Instructions
### Prerequisites
Java 17 or higher.
Spring Boot 3.x.
Node.js for frontend dependencies.
AWS S3 credentials for storage.
A SQL database for metadata management.
## Steps
### Clone the repository:
### Configure database and AWS credentials in the application properties file.
### Build and run the project:
### mvn clean install  
### java -jar target/melodydreams-1.0.jar  
### Open the application at http://localhost:8080.

## Core Functionalities
### Dynamic Playlists
Users can create, edit, and manage playlists.
Songs are dynamically sorted and displayed using a backend-provided map.
File Upload and Retrieval
Audio files and cover art are uploaded to AWS S3.
Files are retrieved dynamically and rendered as part of the media player.
3D and Dynamic Visual Integrations <a name="3d-and-dynamic-visual-integrations"></a>
Three.js and GLSL
Dynamic Spheres: Represent individual tracks with sphere geometries.
Track Image Textures: Each sphere is mapped with the track's cover art as its texture.
Radius Adaptation: Sphere radii adjust dynamically based on track metadata (e.g., play count or duration).
Track Interaction: Clicking on a sphere triggers playback of the corresponding track.

### Shader Effects
Pulsating Beats: GLSL shaders create pulse effects synced with the music’s BPM.
Dynamic Gradients: Gradients change based on tempo, volume, or genre classification.
3D Positioning: Spheres form constellations where their z-axis positions correlate with playlist creation timestamps.
BPM-Driven Interactivity <a name="bpm-driven-interactivity"></a>

### CSS Transitions and Animations
BPM-Based Effects: CSS animations adapt to the track’s BPM, creating pulsating backgrounds or gradient shifts.
Track Progress Visualization: Progress bars and div transitions are synced with playback speed.
Visual Sync with Music
Volume-Driven Scaling: Sphere sizes oscillate in real-time based on track volume.
Tempo Integration: Visual elements speed up or slow down according to track tempo.

## Playlists and File Handling
Playlist Mapping
Backend utilities map playlists into JSON-ready structures, minimizing frontend processing.
File Uploads
Supports multi-file uploads via JSP forms.
Stores audio and images in AWS S3 with metadata in SQL for seamless integration.
File Retrieval
Dynamically retrieves and prepares media for frontend display and playback.
Future Enhancements <a name="future-enhancements"></a>
AI-Powered Recommendations: Machine learning models for personalized playlists.
Advanced Visualizations: Expanding Three.js animations to include interactive 3D scenes.
Streaming Optimization: Adaptive streaming using AWS MediaConvert.

### Social Features: Enabling collaborative playlists and real-time user interactions.
Contact
Feel free to reach out for queries or contributions:

Email: TuruchieNetworks@outlook.com
LinkedIn: https://www.linkedin.com/in/onyenaturuchi-ijioma-514b97256/
Let MelodyDreams redefine your music experience! 🌟
