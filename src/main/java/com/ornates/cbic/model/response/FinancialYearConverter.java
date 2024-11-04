package com.ornates.cbic.model.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/*
 * Date: May 24, 2024.
 * Rakesh
 * Purpose: This method has core functionality related to Investigation.
 */
public class FinancialYearConverter implements Serializable {

    public static int convertMonthToFinancialMonth(String date) {
        // Parse the date string to LocalDate
        LocalDate localDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            localDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format, please use yyyy-MM-dd");
        }

        int month = localDate.getMonthValue();
        return convertToFinancialMonth(month);
    }

    private static int convertToFinancialMonth(int month) {
        switch (month) {
            case 4:
                return 1;
            case 5:
                return 2;
            case 6:
                return 3;
            case 7:
                return 4;
            case 8:
                return 5;
            case 9:
                return 6;
            case 10:
                return 7;
            case 11:
                return 8;
            case 12:
                return 9;
            case 1:
                return 10;
            case 2:
                return 11;
            case 3:
                return 12;
            default:
                throw new IllegalArgumentException("Invalid month value");
        }
    }
}
