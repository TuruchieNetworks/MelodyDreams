package com.turuchie.melodydreams.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.turuchie.melodydreams.models.Song;
import com.turuchie.melodydreams.models.User;
import com.turuchie.melodydreams.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class FileUtils {
    @Value("${file.upload-dir}")
    private static String uploadDir;

    @Autowired
    private UserService userServ;

    @Autowired
	private MusicListUtils playlistUtil;

    public FileUtils(@Value("${file.upload-dir}") String uploadDir ) {
        FileUtils.uploadDir = uploadDir;
    }

    // Helper method to handle file save error
    public void handleFileSaveError(IOException e, BindingResult result, Model model) {
        e.printStackTrace();
        System.err.println("Error saving files: " + e.getMessage());
        result.rejectValue("imageData", "file.error", "Error saving files. Please try again.");
        model.addAttribute("errorAudioData", "Error saving files. Please try again.");
    }

    // Helper method to validate user and get user id
    public Long validateUserAndGetId(HttpSession session) {
        return (Long) session.getAttribute("user_id");
    }

    // Helper method to set user attributes in the model
    public void setUserAttributes(Model model, Long userId) {
        User loggedInUser = userServ.getOne(userId);
        model.addAttribute("loggedInUser", loggedInUser);
    }
	
    // Helper method to validate files and set errors
    public boolean validateFilesAndSetErrors(BindingResult result, MultipartFile imageData, MultipartFile audioData,Song song, Model model) {
        if (imageData.getSize() <= 0 || audioData.getSize() <= 0) {
            result.rejectValue("trackImageDataError", "file.empty", "Track Image File Is Empty!");
        }

        if (imageData.getSize() <= 0 || audioData.getSize() <= 0) {
            result.rejectValue("audioDataError", "file.empty", "Audio File Is Empty!");
        }

        return result.hasErrors();
    }
    
    public static String encodeToBase64(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }


	/**
	 * Main Custom Validation Errors
	 * Handle validation errors and set model attributes.
	 */
    // 
    public static boolean validateTrackFields(BindingResult result,
    	MultipartFile imageData, MultipartFile audioData, String genre,
    	String trackTitle, String trackArtist, String description, Model model) {
        boolean hasErrors = false;
        if (genre == null || genre.length() < 1) {
            model.addAttribute("genreError", "Genre cannot be empty!");
            hasErrors = true;
        }
        if (trackTitle == null || trackTitle.length() < 1) {
            model.addAttribute("trackTitleError", "Track title cannot be blank");
            hasErrors = true;
        }
        if (trackArtist == null || trackArtist.length() < 1) {
            model.addAttribute("trackArtistError", "Please enter Track Artist!");
            hasErrors = true;
        }
        if (description == null || description.length() < 1) {
            model.addAttribute("descriptionError", "Please Describe Your Track!");
            hasErrors = true;
        }     
        if (imageData.getSize() <= 0) {
            model.addAttribute("trackImageDataError", "Track Image File Is Empty!");
            hasErrors = true;
        }

        if (audioData.getSize() <= 0) {
        	model.addAttribute("audioDataError", "Audio File Is Empty!");
        	hasErrors = true;
        }

        return hasErrors;
    }

	/**
	 * File Related  Validation Errors
	 * Handle validation errors and set model attributes.
	 */
	public void handleFileValidationErrors(BindingResult result, Model model, String trackImageDataError, String audioDataError) {
	    model.addAttribute("trackImageDataError", trackImageDataError);
	    model.addAttribute("audioDataError", audioDataError);
	}

    // Save Uploaded File and return the URL
    public String saveFileAndSetUrl(MultipartFile file, String directoryName, Long userId) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileDirectory = uploadDir + "/" + directoryName + "/" + userId + "/" + fileName;

        java.nio.file.Path uploadPath = Paths.get(fileDirectory);

        if (Files.notExists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        java.nio.file.Path filePath = uploadPath.resolve(fileName);

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        // Construct the file URL without leading trail
        String fileUrl = directoryName + "/" + userId + "/" + fileName;

        return fileUrl;
    }    

    // Update File and return the URL
    public String updateFileAndSetUrl(MultipartFile file, String directoryName, Long userId) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileDirectory = uploadDir + "/" + directoryName + "/" + userId + "/" + fileName;

        java.nio.file.Path uploadPath = Paths.get(fileDirectory);

        if (Files.notExists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        java.nio.file.Path filePath = uploadPath.resolve(fileName);

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        // Construct the file URL without leading trail
        String fileUrl = directoryName + "/" + userId + "/" + fileName;

        return fileUrl;
    }

    // Save uploaded song data and set attributes For Track Upload
    public void saveSongDataAndSetAttributes(Song song, MultipartFile imageData, MultipartFile audioData, Long userId) throws IOException {
        if (imageData.isEmpty() || audioData.isEmpty()) {
            throw new IllegalArgumentException("Image and audio files cannot be empty.");
        }

        // Save file data and set URLs
        String trackImageDirectory = saveFileAndSetUrl(imageData, "TrackArtImages", userId);
        String audioFileDirectory = saveFileAndSetUrl(audioData, "TrackAudioData", userId);

        // Compress file bytes
        byte[] imageFileBytes = FileUtils.compressFileBytes(imageData.getBytes());
        byte[] audioFileBytes = FileUtils.compressFileBytes(audioData.getBytes());

        // Set names and URLs in the song object
        song.setTrackImageFileName(imageData.getOriginalFilename());
        song.setAudioFileName(audioData.getOriginalFilename());
        song.setTrackImageDataUrl(trackImageDirectory);
        song.setAudioDataUrl(audioFileDirectory);

        // Set file data in the song object
        song.setAudioData(audioFileBytes);
        song.setImageData(imageFileBytes);

        // Output info
        System.out.println("Image Data Size: " + song.getImageData().length);
        System.out.println("Track Image Bytes: " + song.getImageData().toString());
        System.out.println("Audio Data Bytes: " + song.getAudioData().toString());
        System.out.println("Audio Data Size: " + song.getAudioData().length);
    }

    // Update song data and set attributes For Track Update
    public void updateSongDataAndSetAttributes(Song song, MultipartFile imageData, MultipartFile audioData, Long userId) throws IOException {
        if (!imageData.isEmpty()) {
            // Check if the new image file is different from the old one
            if (!imageData.getOriginalFilename().equals(song.getTrackImageFileName())) {
                // Delete old image file if it exists and is different
                String trackCoverImageUrl = song.getTrackImageDataUrl() + "/" + song.getTrackImageFileName();
                String trackCoverImagePath = uploadDir + "/" + trackCoverImageUrl;
                Files.deleteIfExists(Paths.get(trackCoverImagePath));
                
                // Save new image file data and set URL
                String trackImageDirectory = updateFileAndSetUrl(imageData, "TrackArtImages", userId);
                byte[] imageFileBytes = FileUtils.compressFileBytes(imageData.getBytes());

                //song.setTrackImageFileName(imageData.getOriginalFilename());
                song.setTrackImageDataUrl(trackImageDirectory);
                song.setImageData(imageFileBytes);
                song.setTrackImageFileName(imageData.getOriginalFilename());
                System.out.print("Updated Image File Name: " + song.getTrackImageFileName());
            }
        }

        if (!audioData.isEmpty()) {
            // Check if the new audio file is different from the old one
            if (!audioData.getOriginalFilename().equals(song.getAudioFileName())) {
                // Delete old audio file if it exists and is different
                String audioFileUrl = song.getAudioDataUrl() + "/" + song.getAudioFileName();
                String audioFilePath = uploadDir + "/" + audioFileUrl;
                Files.deleteIfExists(Paths.get(audioFilePath));
                
                // Save new audio file data and set URL
                String audioFileDirectory = updateFileAndSetUrl(audioData, "TrackAudioData", userId);
                byte[] audioFileBytes = FileUtils.compressFileBytes(audioData.getBytes());

                //song.setAudioFileName(audioData.getOriginalFilename());
                song.setAudioDataUrl(audioFileDirectory);
                song.setAudioData(audioFileBytes);
                song.setAudioFileName(audioData.getOriginalFilename());
                System.out.print("Updated Audio File Name: " + song.getAudioFileName());
            }
        }


        // Output info if files are updated
        if (!imageData.isEmpty()) {
            System.out.println("Updated Track Image Size: " + song.getImageData().length);
            System.out.println("Updated Track Image Url: " + song.getTrackImageDataUrl());
            //System.out.println("Track Image Bytes: " + Arrays.toString(song.getImageData()));
        }
        if (!audioData.isEmpty()) {
            System.out.println("Updated Audio Data Size: " + song.getAudioData().length);
            System.out.println("Updated Audio Data Url: " + song.getAudioDataUrl());
            //System.out.println("Updated Audio Data Bytes: " + Arrays.toString(song.getAudioData()));
        }
    }

    // Alternative method to save file to local disk
    public static String saveFileToLocalDisk(MultipartFile file, String subDirectory, Long userId) throws IOException {
        String fileName = cleanFileName(file.getOriginalFilename());
        String filePath = uploadDir + File.separator + subDirectory + File.separator + userId + File.separator + fileName;

        // Create directories if they don't exist
        File directory = new File(uploadDir + File.separator + subDirectory + File.separator + userId + File.separator + fileName);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(file.getBytes());
        }

        return filePath;
    }

    // Delete File
    public void deleteFile(String filePath) {
        try {
            java.nio.file.Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                Files.delete(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Add appropriate error handling here (e.g., logging, throwing a custom exception, etc.)
        }
    }
 
    // Method to read file bytes from disk
    public byte[] readFileBytes(String filePath) throws IOException {
        java.nio.file.Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }

    // Method to set transient attributes
    public void setTransientFileAttributes(Song song) throws IOException {
        if (song.getTrackImageDataUrl() != null) {
            byte[] imageFileBytes = readFileBytes(song.getTrackImageDataUrl());
            song.setImageData(imageFileBytes);
        }

        if (song.getAudioDataUrl() != null) {
            byte[] audioFileBytes = readFileBytes(song.getAudioDataUrl());
            song.setAudioData(audioFileBytes);
        }
    }

    public List<Integer> generateTimeFormat() {        
    	List<Integer> timeFormat = new ArrayList<>();
	    for (int i = 1; i <= 12; i++) {
	        timeFormat.add(i);
	    }
	    return timeFormat;
    }

    public void addErrorAttributes(Model model, HttpServletRequest request, Long id, Long userId, Song existingSong) {
        playlistUtil.setMusicList(model, request);
        playlistUtil.setSingleMusicList(model, request, id);
        playlistUtil.setUsersMusicList(model, request, userId);
        model.addAttribute("song", existingSong);
        model.addAttribute("timeFormat", generateTimeFormat());
        model.addAttribute("loggedInUser", userServ.getOne(userId));
    }

    public byte[] readFileAsBytes(MultipartFile fileData) throws IOException {
        return Files.readAllBytes(Paths.get(uploadDir));
    }

    public static String cleanFileName(String filename) {
        return filename.replaceAll("[^a-zA-Z0-9.-]", "_");
    }

    public static String cleanPath(String filename) {
        return org.springframework.util.StringUtils.cleanPath(filename);
    }

    public byte[] compressFile(byte[] data) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipOut = new GZIPOutputStream(baos)) {
            gzipOut.write(data);
        }
        return baos.toByteArray();
    }

    public byte[] decompressFile(byte[] compressedData) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ByteArrayInputStream bais = new ByteArrayInputStream(compressedData);
             GZIPInputStream gzipIn = new GZIPInputStream(bais)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = gzipIn.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
        }
        return baos.toByteArray();
    }

    public static byte[] alternativeCompressFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Input file is empty");
        }

        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(file.getBytes());
        deflater.finish();

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream((int) file.getSize())) {
            byte[] buffer = new byte[4 * 1024];
            while (!deflater.finished()) {
                int count = deflater.deflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            return outputStream.toByteArray();
        }
    }

    public static byte[] alternativeDecompressFile(MultipartFile file) throws IOException, DataFormatException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Input file is empty");
        }

        // Check if the file is in gzip compressed format
        if (!file.getOriginalFilename().endsWith(".gz")) {
            throw new IllegalArgumentException("Input file is not in the expected compressed format (e.g., .gz)");
        }

        Inflater inflater = new Inflater();
        inflater.setInput(file.getBytes());

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream((int) file.getSize())) {
            byte[] buffer = new byte[4 * 1024];
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            return outputStream.toByteArray();
        } finally {
            inflater.end();
        }
    }

    private static String generateUniqueFileName() {
        // TODO Auto-generated method stub
        return null;
    }

    public static String saveFileAndGetUrl(MultipartFile file, String subDirectory) throws IOException {
        String fileName = generateUniqueFileName();
        java.nio.file.Path filePath = Paths.get(uploadDir, subDirectory, fileName);
        Files.write(filePath, file.getBytes());
        return filePath.toString(); // You might want to return a URL instead of the file path
    }

    public static void alternativeSaveFile(MultipartFile file, String directory) throws IOException {
        String fileName = cleanFileName(file.getOriginalFilename());
        String filePath = directory + File.separator + fileName;

        // Create directories if they don't exist
        File targetDirectory = new File(directory);
        if (!targetDirectory.exists()) {
            targetDirectory.mkdirs();
        }

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(file.getBytes());
        }
    }

    public static void validateFileNotEmpty(MultipartFile file, String fieldName, String errorMessage) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static byte[] getCompressedFileData(MultipartFile file) throws IOException {
        return alternativeCompressFile(file);
    }

    public static byte[] getDecompressedFileData(MultipartFile file) throws IOException, DataFormatException {
        return alternativeDecompressFile(file);
    }

    public static byte[] getFileBytes(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Input file is empty");
        }
        return file.getBytes();
    }

    public static byte[] compressFileBytes(byte[] fileBytes) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(fileBytes);
        deflater.finish();

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(fileBytes.length)) {
            byte[] buffer = new byte[4 * 1024];
            while (!deflater.finished()) {
                int count = deflater.deflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            return outputStream.toByteArray();
        }
    }

    public static byte[] decompressFileBytes(byte[] compressedFileBytes) throws IOException, DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(compressedFileBytes);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(compressedFileBytes.length)) {
            byte[] buffer = new byte[4 * 1024];
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            return outputStream.toByteArray();
        } finally {
            inflater.end();
        }
      }

	  public String saveFile(MultipartFile file) throws IOException {
	      String cleanedFileName = FileUtils.cleanFileName(file.getOriginalFilename());
	      String filePath = Paths.get(uploadDir, cleanedFileName).toString();
	      Files.write(Paths.get(filePath), file.getBytes());
	      return cleanedFileName;
	  }
	
	  public void deletePathFile(String filePath) throws IOException {
	      Files.deleteIfExists(Paths.get(uploadDir, filePath));
	  }
}
