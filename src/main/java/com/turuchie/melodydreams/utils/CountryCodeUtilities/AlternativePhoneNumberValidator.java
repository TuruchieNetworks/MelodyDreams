package com.turuchie.melodydreams.utils.CountryCodeUtilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class AlternativePhoneNumberValidator {
    public boolean isValidPhoneNumber(String phoneNumber) {
        // Regular expression to match the desired format: (123)-(456)-7890
        String regex = "^\\(\\d{3}\\)-\\(\\d{3}\\)-\\d{4}$";
        
        // Compile the regular expression pattern
        Pattern pattern = Pattern.compile(regex);
        
        // Match the phone number against the pattern
        Matcher matcher = pattern.matcher(phoneNumber);
        
        // Return true if the phone number matches the pattern, false otherwise
        return matcher.matches();
    } 

    public boolean isValidPhoneNumberDashes(String phoneNumber) {
        // Reject if phoneNumber is null or empty
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }

        // Count the number of dashes
        int dashCount = 0;
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (phoneNumber.charAt(i) == '-') {
                dashCount++;
            }
        }

        // Reject if there are more than 3 dashes
        if (dashCount > 3) {
            return false;
        }
		return false;
    }

    public String formatPhoneNumberAndSet(String phoneNumber, BindingResult result) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            result.rejectValue("phoneNumber", "required", "Phone number is required.");
            return "Phone number is required.";
        }

        // Clean the phone number
        String cleanedNumber = phoneNumber.replaceAll("\\D", ""); // Remove non-digit characters

        // Check if the cleaned number is empty after removing non-digits
        if (cleanedNumber.isEmpty()) {
            result.rejectValue("phoneNumber", "invalid.format", "Invalid phone number format.");
            return "Invalid phone number format.";
        } else if (cleanedNumber.length() != 10) {
            // Check if the cleaned number has at least 10 digits
            result.rejectValue("phoneNumber", "invalid.length", "Phone number should have 10 digits.");
            return "Phone number should have 10 digits!";
        } else {
            // Extract the first three digits for the area code
            String areaCode = cleanedNumber.substring(0, 3);

            // Extract the next three digits for the prefix
            String prefix = cleanedNumber.substring(3, 6);

            // Extract the next four digits for the line number
            String lineNumber = cleanedNumber.substring(6);

            // Format the rest of the number with dashes
            String formattedNumber = areaCode + "-" + prefix + "-" + lineNumber.substring(0, 3) + "-" + lineNumber.substring(3);
            String formattedPhoneNumber = formattedNumber;

            // Return the formatted phone number
            return formattedPhoneNumber;
        }
    }

    public boolean isPhoneNumberValidLength(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false; // Return false if the phone number is null or empty
        }

        // Split the phone number based on the comma
        String[] parts = phoneNumber.split(",");

        // Check if there are two parts (country code and phone number)
        if (parts.length != 2) {
            return false; // Return false if the format is invalid
        }

        // Extract the phone number part and clean it
        String inputNumber = parts[1].trim();
        String cleanedNumber = inputNumber.replaceAll("\\D", ""); // Remove non-digit characters

        // Check if the cleaned number has exactly 10 digits
        return cleanedNumber.length() == 10;
    }


}
