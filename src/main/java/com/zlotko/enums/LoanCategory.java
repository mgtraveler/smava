package com.zlotko.enums;

public enum LoanCategory {

    ACCOMODATION("886", "Wohnen");

    private String optionValue;
    private String optionText;

    LoanCategory(String optionValue, String optionText) {
        this.optionValue = optionValue;
        this.optionText = optionText;
    }

    public String optionValue() {
        return optionValue;
    }

    public String optionText() {
        return optionText;
    }
}
