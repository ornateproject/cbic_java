package com.ornates.cbic.service;

import java.time.LocalDate;

public class DateCalculate {

	public static String getPreviousMonth(String newDate) {
		LocalDate curr_month = LocalDate.parse(newDate);
		LocalDate prev_month = curr_month.minusMonths(1);
		return prev_month.toString();
	}

	public static String getPreviousMonthTwo(String newDate) {
		LocalDate curr_month = LocalDate.parse(newDate);
		LocalDate prev_month = curr_month.minusMonths(2);
		return prev_month.toString();
	}
	public static String getPreviousMonthfirst(String newDate) {
		LocalDate curr_month = LocalDate.parse(newDate);
		LocalDate prev_month = curr_month.minusMonths(3);
		return prev_month.toString();
	}

	public static String getNextMonth(String newDate) {
		LocalDate curr_month = LocalDate.parse(newDate);
		LocalDate next_month = curr_month.plusMonths(1);
		return next_month.toString();
	}
	public static String getPreviousMonthTwo(String newDate) {
		LocalDate curr_month = LocalDate.parse(newDate);
		LocalDate prev_month = curr_month.minusMonths(2);
		return prev_month.toString();
	}
	public static String getPreviousMonthfirst(String newDate) {
		LocalDate curr_month = LocalDate.parse(newDate);
		LocalDate prev_month = curr_month.minusMonths(3);
		return prev_month.toString();
	}
}