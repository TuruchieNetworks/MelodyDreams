package com.turuchie.melodydreams.utils.CountryCodeUtilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class PhoneNumberValidator {

    private static final String PHONE_NUMBER_REGEX = "^\\(\\d{3}\\)-\\(\\d{3}\\)-\\(\\d{4}\\)$";

    public boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public boolean isValidPhoneNumberDashes(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }

        int dashCount = 0;
        for (char c : phoneNumber.toCharArray()) {
            if (c == '-') {
                dashCount++;
            }
        }

        return dashCount == 2;
    }

    public String formatPhoneNumberAndSet(String phoneNumber, BindingResult result) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            result.rejectValue("phoneNumber", "required", "Phone number is required.");
            return "Phone number is required.";
        }

        String cleanedNumber = phoneNumber.replaceAll("\\D", "");

        if (cleanedNumber.isEmpty()) {
            result.rejectValue("phoneNumber", "invalid.format", "Invalid phone number format.");
            return "Invalid phone number format.";
        } else if (cleanedNumber.length() != 10) {
            result.rejectValue("phoneNumber", "invalid.length", "Phone number should have 10 digits.");
            return "Phone number should have 10 digits!";
        } else {
            String areaCode = cleanedNumber.substring(0, 3);
            String prefix = cleanedNumber.substring(3, 6);
            String lineNumber = cleanedNumber.substring(6);

            String formattedPhoneNumber = String.format("(%s)-(%s)-(%s)", areaCode, prefix, lineNumber);
            return formattedPhoneNumber;
        }
    }

    public boolean isPhoneNumberValidLength(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }

        String[] parts = phoneNumber.split(",");
        if (parts.length != 2) {
            return false;
        }

        String inputNumber = parts[1].trim();
        String cleanedNumber = inputNumber.replaceAll("\\D", "");

        return cleanedNumber.length() == 10;
    }
}
