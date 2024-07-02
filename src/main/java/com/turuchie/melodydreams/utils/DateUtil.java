package com.turuchie.melodydreams.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.turuchie.melodydreams.services.UserService;


@Component
public class DateUtil {
	@SuppressWarnings("unused")
	@Autowired
	private UserService userServ;

    public void DateUtils(UserService userServ) {
        this.userServ = userServ;
    }
    
    // Helper method to set common user attributes
    public void setTrimmedSearchMethods(String trimmedSearchTerm) {
    	
    }

    // All Current Date Attributes
    public void addCurrentDateAttributes(Model model) {

        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter dayformatter = DateTimeFormatter.ofPattern("EEE, yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy");

        String currentDateTimeFormatted = currentDateTime.format(formatter);
        String dayCurrentDateTimeFormatted = currentDateTime.format(dayformatter);

        // Set formatted date attributes in the model
        model.addAttribute("currentDateTime", currentDateTimeFormatted);
        model.addAttribute("dayCurrentDateTime", dayCurrentDateTimeFormatted);

	    // Additional attributes for seconds, minutes, hours, and days
		model.addAttribute("currentSecond", currentDateTime.getSecond());
		model.addAttribute("currentMinute", currentDateTime.getMinute());
		model.addAttribute("currentHour", currentDateTime.getHour());
		model.addAttribute("currentDayOfYear", LocalDate.now().getDayOfYear());
    }
 
    // Calculate the length of a medical condition based on its start date
    public int calculateDateDifferences(LocalDate dateObj) {
        if (dateObj == null) {
            return 0; // If start date is null, the length is zero
        }

        LocalDate currentDate = LocalDate.now();
        int lengthInYears = (int) ChronoUnit.YEARS.between(dateObj, currentDate);

        // Adjust the length if the current date is after the start date
        return currentDate.isAfter(dateObj) ? lengthInYears : lengthInYears + 1;
    }

    // Calculate the length of a medical condition based on its start date
    public int calculateDescDateDifferences(LocalDate dateObj) {
        if (dateObj == null) {
            return 0; // If start date is null, the length is zero
        }

        LocalDate currentDate = LocalDate.now();
        int lengthInYears = currentDate.getYear() - dateObj.getYear();

        // Adjust the length if the current date is before the birthday this year
        if (currentDate.getMonthValue() < dateObj.getMonthValue() || 
            (currentDate.getMonthValue() == dateObj.getMonthValue() && currentDate.getDayOfMonth() < dateObj.getDayOfMonth())) {
            lengthInYears--;
        }

        return lengthInYears;
    }

    // Calculate Length of Entity
    public int calculateLengthOfCreatedDate(LocalDateTime createdAt) {
        if (createdAt == null) {
            return 0; // If start date is null, the length is zero
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        return (int) ChronoUnit.YEARS.between(createdAt, currentDateTime);
    }

	// Helper method to calculate date differences
	public void addDateAttributesToModel(Model model, LocalDate date, String prefix) {
	    long years = calculateDateDifference(date, LocalDate.now(), ChronoUnit.YEARS);
	    long days = calculateDateDifference(date, LocalDate.now(), ChronoUnit.DAYS);
	    long months = calculateDateDifference(date, LocalDate.now(), ChronoUnit.MONTHS);

	    model.addAttribute(prefix + "Years", years);
	    model.addAttribute(prefix + "Days", days);
	    model.addAttribute(prefix + "Months", months);
	}


 
 public boolean isValidBirthDateAndYear(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();

        // Check if the birth date is on or before the current date
        if (birthDate.isAfter(currentDate)) {
            return false;
        }

        // Check if the birth date's year is within the specified range (up to 150 years ago)
        int minValidYear = currentDate.minusYears(150).getYear();
        if (birthDate.getYear() < minValidYear || birthDate.getYear() > currentDate.getYear()) {
            return false;
        }

        // Check if the birth date's month is after the current month
        if (birthDate.getMonthValue() > currentDate.getMonthValue()) {
            return false;
        }

        // Check if the user is at least 21 years old
        LocalDate minAllowedBirthDate = currentDate.minusYears(21);
        if (birthDate.isAfter(minAllowedBirthDate)) {
            return false;
        }

        // Check if the birth date's month is the same as the current month but the day is after the current day
        return !(birthDate.getMonthValue() == currentDate.getMonthValue() && birthDate.getDayOfMonth() > currentDate.getDayOfMonth());
    }
	
	// Calculate LocalDate Difference
    public long calculateDateDifference(LocalDate dateObject1, LocalDate dateObject2, ChronoUnit unit) {
        return unit.between(dateObject1, dateObject2);
    }

	// Calculate LocalDateTime Difference
    public long calculateDateTimeDifference(LocalDateTime localDateTime, LocalDateTime localDateTime2, ChronoUnit unit) {
        return unit.between(localDateTime, localDateTime2);
    }

    // Updated method to check if the birth year is within the specified range and not in the future
    public boolean isValidBirthYear(int birthYear) {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int minValidYear = currentYear - 150; // 150 years ago
        int maxValidYear = currentYear; // Current year

        return birthYear >= minValidYear && birthYear <= maxValidYear && birthYear <= currentYear;
    }

    // Validate Registered User Birth Year
    public boolean isValidRegisteredUserBirthYear(int birthYear) {
        int currentYear = Year.now().getValue();
        int minimumValidYear = currentYear - 150;

        return birthYear >= minimumValidYear && birthYear <= currentYear;
    }

    // Validate Against Future Years
    public boolean isValidBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            return false; // Null birth date is invalid
        }

        LocalDate currentDate = LocalDate.now();
        int minimumValidYear = currentDate.getYear() - 150;

        // Check if birth date is in the past, not in the future
        if (birthDate.isAfter(currentDate)) {
            return false;
        }

        // Check if the birth year is within the specified range
        if (birthDate.getYear() < minimumValidYear) {
            return false;
        }

        // Check if the birth date is in the future month of the current year
        if (birthDate.getYear() == currentDate.getYear() &&
                (birthDate.getMonthValue() > currentDate.getMonthValue() ||
                        (birthDate.getMonthValue() == currentDate.getMonthValue() &&
                                birthDate.getDayOfMonth() > currentDate.getDayOfMonth()))) {
            return false;
        }

        return true;
    }
    
    // Validate Start Date
    public boolean isValidStartDate(LocalDate startDate) {
        if (startDate == null) {
            return false; // Null start date is invalid
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate maxStartDate = currentDate.minusYears(5);

        // Check if start date is in the future or not in the past 5 years
        if (startDate.isAfter(currentDate) || startDate.isBefore(maxStartDate)) {
            return false;
        }

        return true;
    }

    // Validate Expiration Date
    public boolean isValidExpirationDate(LocalDate expirationDate) {
        if (expirationDate == null) {
            return false; // Null expiration date is invalid
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate yesterday = currentDate.minusDays(1);

        // Check if expiration date is today or yesterday
        if (expirationDate.isEqual(currentDate) || expirationDate.isEqual(yesterday)) {
            return false;
        }

        return true;
    }

    // Calculate Coverage Length and Add to Model
    public void addCoverageLengthToModel(Model model, LocalDate expirationDate) {
        long coverageLength = calculateCoverageLength(expirationDate);
        long coverageDays = calculateCoverageLengthInDays(expirationDate);
        model.addAttribute("coverageLength", coverageLength);
        model.addAttribute("dayCoverageLength", coverageDays);
    }

    // Calculate Coverage Length
    public long calculateDatePeriodInDays(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return 0; // If expiration date is null, coverage length is zero
        }
        
        Period period = Period.between(startDate, endDate);

        return period.getDays(); // You can modify this based on your specific requirements
    }

    // Calculate Coverage Length
    public long calculateCoverageLength(LocalDate expirationDate) {
        if (expirationDate == null) {
            return 0; // If expiration date is null, coverage length is zero
        }

        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(currentDate, expirationDate);

        return period.getDays(); // You can modify this based on your specific requirements
    }

    // Calculate Coverage Length in Days
    public long calculateCoverageLengthInDays(LocalDate expirationDate) {
        if (expirationDate == null) {
            return 0; // If expiration date is null, coverage length is zero
        }

        LocalDate currentDate = LocalDate.now();
        return ChronoUnit.DAYS.between(currentDate, expirationDate);
    }

    // Calculate Coverage Length in Months and Years
    public Period calculateCoverageLengthInPeriod(LocalDate expirationDate) {
        if (expirationDate == null) {
            return Period.ZERO; // If expiration date is null, coverage length is zero
        }

        LocalDate currentDate = LocalDate.now();
        return Period.between(currentDate, expirationDate);
    }
	


    // Validate Regular Users
    public boolean isValidBirthDateRegularUser(LocalDate birthDate) {
        if (birthDate == null) {
            return false; // Null birth date is invalid
        }

        LocalDate currentDate = LocalDate.now();
        int minimumValidYear = currentDate.getYear() - 150;

        // Check if birth date is in the past, not in the future
        if (birthDate.isAfter(currentDate)) {
            return false;
        }

        // Check if the birth year is within the specified range
        if (birthDate.getYear() < minimumValidYear) {
            return false;
        }

        // Check if the birth date is in the future month of the current year
        if (birthDate.getYear() == currentDate.getYear() &&
                (birthDate.getMonthValue() > currentDate.getMonthValue() ||
                        (birthDate.getMonthValue() == currentDate.getMonthValue() &&
                                birthDate.getDayOfMonth() > currentDate.getDayOfMonth()))) {
            return false;
        }

        return true;
    }

    // Validate ObyGyn Users
    public boolean isValidBirthDateObstetricUser(LocalDate birthDate) {
        // Reuse the validation logic for regular users
        return isValidBirthDateRegularUser(birthDate);
    }

    
    // Method To Calculate Length Of Created Entity
    public int calculateDateDiffference(LocalDate localDate) {
        int dateDifference = (int) ChronoUnit.YEARS.between(localDate, LocalDate.now());
        return dateDifference;
    }

    // Method To Calculate Length Of Local Date
    public int calculateLocalDateDifference(LocalDate startDate, LocalDate endDate) {  
        int dateDifference = (int) ChronoUnit.YEARS.between(startDate, endDate);
        return dateDifference;
    }

    // Method To Calculate Length Of Local Date Time
    public int calculateLocaleDateTimeDiffference(LocalDateTime startDate, LocalDateTime endDate) {
        int dateDifference = (int) ChronoUnit.YEARS.between(startDate, endDate);
        return dateDifference;
    }

    // Method To Calculate Length Of Local Date
    public int calculateDaysLocalDateDifference(LocalDate startDate, LocalDate endDate) {  
        int dateMonthDifference = (int) ChronoUnit.DAYS.between(startDate, endDate);
        return dateMonthDifference;
    }

    // Method To Calculate Length Of Local Date Time
    public int calculateDaysLocaleDateTimeDiffference(LocalDateTime startDate, LocalDateTime endDate) {
        int dateDifference = (int) ChronoUnit.DAYS.between(startDate, endDate);
        return dateDifference;
    }

    // Method To Calculate Length Of Local Date
    public int calculateMonthsLocalDateDifference(LocalDate startDate, LocalDate endDate) {  
        int dateMonthDifference = (int) ChronoUnit.MONTHS.between(startDate, endDate);
        return dateMonthDifference;
    }

    // Method To Calculate Length Of Local Date Time
    public int calculateMonthsLocaleDateTimeDiffference(LocalDateTime startDate, LocalDateTime endDate) {
        int dateDifference = (int) ChronoUnit.MONTHS.between(startDate, endDate);
        return dateDifference;
    }

    // Method To Calculate Length Of Local Date
    public int calculateWeeksLocalDateDifference(LocalDate startDate, LocalDate endDate) {  
        int dateMonthDifference = (int) ChronoUnit.WEEKS.between(startDate, endDate);
        return dateMonthDifference;
    }

    // Method To Calculate Length Of Local Date Time
    public int calculateWeeksLocaleDateTimeDiffference(LocalDateTime startDate, LocalDateTime endDate) {
        int dateDifference = (int) ChronoUnit.WEEKS.between(startDate, endDate);
        return dateDifference;
    }

}