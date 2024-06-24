package com.pcwk.ehr.tld;

import java.text.DecimalFormat;

public class FormatUtil {

	/**
	 * TLD작성 : 반듯이 static
	 * @param number
	 * @param pattern
	 * @return 숫자 format정의된 숫자
	 */
	public static String number(long number, String pattern) {
		DecimalFormat  format=new DecimalFormat(pattern);
		
		
		return format.format(number);
	}
}
