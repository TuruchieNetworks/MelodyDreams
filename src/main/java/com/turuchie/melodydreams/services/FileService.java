package com.turuchie.melodydreams.services;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    public String getEncodedFilePath(String filePath) {
        try {
            // Encode the file path using UTF-8 encoding
            String encodedFilePath = URLEncoder.encode(filePath, "UTF-8");
            return encodedFilePath;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
