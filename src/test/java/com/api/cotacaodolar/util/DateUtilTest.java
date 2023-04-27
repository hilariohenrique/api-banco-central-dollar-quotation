package com.api.cotacaodolar.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class DateUtilTest {

	@Test
	void testBuildDate() {
		assertEquals("2022-11-02", DateUtil.buildDate("2022", "11", "02"));
	}
	
	@Test
	void testConvertToLocalDate() throws Exception {
		assertNotNull(DateUtil.toLocalDate("2022", "11", "02"));
	}
	
	@Test
	void testExceptionConvertToLocalDate() {
		try {
			DateUtil.toLocalDate("2022", "281982", "30");
		} catch (Exception e) {
			assertEquals("Não foi possível converter a data informada.", e.getMessage());
		}
	}
}
