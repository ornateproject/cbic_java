package com.ornates.cbic.service;

import java.time.LocalDate;

public class DateCalculate {

	public static String getPreviousMonth(String newDate) {
		LocalDate curr_month = LocalDate.parse(newDate);
		LocalDate prev_month = curr_month.minusMonths(1);
		return prev_month.toString();
	}

	public static String getFinancialYearStart(String monthDate) {
		LocalDate inputDate = LocalDate.parse(monthDate);
		int year = inputDate.getMonthValue() >= 4 ? inputDate.getYear() : inputDate.getYear() - 1;
		LocalDate financialYearStart = LocalDate.of(year, 4, 1);
		return financialYearStart.toString();
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

	public static String get_1_MonthBack(String newDate) {
		LocalDate curr_month = LocalDate.parse(newDate);
		LocalDate MonthBack_1 = curr_month.minusMonths(1);
		return MonthBack_1.toString();
	}
	public static String get_2_MonthBack(String newDate) {
		LocalDate curr_month = LocalDate.parse(newDate);
		LocalDate MonthBack_2 = curr_month.minusMonths(2);
		return MonthBack_2.toString();
	}
	public static String get_3_MonthBack(String newDate) {
		LocalDate curr_month = LocalDate.parse(newDate);
		LocalDate MonthBack_3 = curr_month.minusMonths(3);
		return MonthBack_3.toString();
	}
	public static String get_4_MonthBack(String newDate) {
		LocalDate curr_month = LocalDate.parse(newDate);
		LocalDate MonthBack_4 = curr_month.minusMonths(4);
		return MonthBack_4.toString();
	}
	public static String get_5_MonthBack(String newDate) {
		LocalDate curr_month = LocalDate.parse(newDate);
		LocalDate MonthBack_5 = curr_month.minusMonths(5);
		return MonthBack_5.toString();
	}
	public static String get_6_MonthBack(String newDate) {
		LocalDate curr_month = LocalDate.parse(newDate);
		LocalDate MonthBack_6 = curr_month.minusMonths(6);
		return MonthBack_6.toString();
	}
	public static String get_7_MonthBack(String newDate) {
		LocalDate curr_month = LocalDate.parse(newDate);
		LocalDate MonthBack_7 = curr_month.minusMonths(7);
		return MonthBack_7.toString();
	}


	public static String getNextMonth(String newDate) {
		LocalDate curr_month = LocalDate.parse(newDate);
		LocalDate next_month = curr_month.plusMonths(1);
		return next_month.toString();
	}

}