package com.pcwk.ehr.customtag;

import java.text.DecimalFormat;

public class FormatUtil {

	/*
	 * TLS 메서드는 static
	 */
	public static String number(long number, String pattern) {
		DecimalFormat  foramt=new DecimalFormat(pattern);
		
		return foramt.format(number);
	}
}
