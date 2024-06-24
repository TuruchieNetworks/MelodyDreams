//package com.turuchie.mellowdrive.utils;
//
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.media.AudioEqualizer;
//import javafx.scene.media.MediaMarkerEvent;
//import javafx.scene.media.AudioSpectrumEvent;
//import javafx.scene.media.AudioSpectrumListener;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//
//public class AudioMediaUtils {
//    private MediaPlayer mediaPlayer;
//    private Canvas canvas;
//    private GraphicsContext gc;
//
//    public AudioMediaUtils(Canvas canvas) {
//        this.canvas = canvas; 
//        this.gc = canvas.getGraphicsContext2D();
//    }
//
//    public void loadMedia(String mediaUrl) {
//        Media media = new Media(mediaUrl);
//        mediaPlayer = new MediaPlayer(media);
//    }
//
//    public void play() {
//        mediaPlayer.play();
//    }
//
//    public void stop() {
//        mediaPlayer.stop();
//    }
//
//    public void updateFrequencyCanvas() {
//        mediaPlayer.setAudioSpectrumListener(new AudioSpectrumListener() {
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
//    }
//}


