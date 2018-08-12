package com.zlotko.enums;

public enum LoanPeriod {

    TWENTY_FOUR_MONTHS(2, "24 Monate");

    private int index;
    private String optionText;

    LoanPeriod(int index, String optionText) {
        this.index = index;
        this.optionText = optionText;
    }

    public int index() {
        return index;
    }

    public String optionText() {
        return optionText;
    }
}
