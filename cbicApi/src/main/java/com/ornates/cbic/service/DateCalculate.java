package com.ornates.cbic.service;

import java.time.LocalDate;

public class DateCalculate {
	
	public static String getPreviousMonth(String newDate) {
		LocalDate curr_month = LocalDate.parse(newDate);
		LocalDate prev_month = curr_month.minusMonths(1);
		return prev_month.toString();
	}

}