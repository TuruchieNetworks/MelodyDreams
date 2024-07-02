package com.turuchie.melodydreams.models;
public class CountryCodes {
    private String countryCode;
    private String phoneCode;
    private String utcDiff;

    public CountryCodes(String countryCode, String phoneCode, String utcDiff) {
        this.countryCode = countryCode;
        this.phoneCode = phoneCode;
        this.utcDiff = utcDiff;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

	public String getUtcDiff() {
		return utcDiff;
	}

	public void setUtcDiff(String utcDiff) {
		this.utcDiff = utcDiff;
	}
   
}
