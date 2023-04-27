package com.api.cotacaodolar.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class DateUtil {

	private DateUtil() {

	}

	public static LocalDate toLocalDate(String yyyy, String mm, String dd) throws IllegalArgumentException {
		try {
			DateTimeFormatter dfs = new DateTimeFormatterBuilder()
					.appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toFormatter();

			return LocalDate.parse(buildDate(yyyy, mm, dd), dfs);
		} catch (Exception e) {
			throw new IllegalArgumentException("Não foi possível converter a data informada.");
		}
	}

	public static String buildDate(String yyyy, String mm, String dd) {
		StringBuilder date = new StringBuilder();

		date.append(yyyy);
		date.append("-");
		date.append(mm);
		date.append("-");
		date.append(dd);

		return date.toString();
	}
}
