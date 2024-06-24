//import java.awt.Canvas;
//
//import javax.print.attribute.standard.Media;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.layout.StackPane;
//import javafx.scene.media.MediaPlayer;
//
//public abstract class AudioProcessingApp extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        // Create a canvas
//        Canvas canvas = new Canvas(800, 600);
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//
//        // Create a media player
//        String mediaUrl = "your_audio_file.mp3";
//        Media media = new Media(mediaUrl);
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//
//        // Add an audio spectrum listener to the media player
//        mediaPlayer.setAudioSpectrumListener(new MediaPlayer.AudioSpectrumListener() {
//            @Override
//            public void spectrumDataUpdate(AudioSpectrumEvent event) {
//                double[] magnitudes = event.getMagnitudes();
//                double height = canvas.getHeight();
//                double width = canvas.getWidth();
//                double barWidth = width / magnitudes.length;
//
//                gc.clearRect(0, 0, width, height);
//
//                for (int i = 0; i < magnitudes.length; i++) {
//                    double x = i * barWidth;
//                    double barHeight = magnitudes[i] * height / 2; // Scale magnitude to canvas height
//                    gc.fillRect(x, height - barHeight, barWidth, barHeight);
//                }
//            }
//        });
//
//        // Play the audio
//        mediaPlayer.play();
//
//        // Set up the scene
//        StackPane root = new StackPane();
//        root.getChildren().add(canvas);
//        Scene scene = new Scene(root, 800, 600);
//
//        primaryStage.setTitle("Audio Processing with JavaFX");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}


