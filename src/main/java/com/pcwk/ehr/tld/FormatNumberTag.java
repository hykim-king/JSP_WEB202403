package com.pcwk.ehr.tld;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class FormatNumberTag extends SimpleTagSupport {
	private long number;
	private String pattern;

	public void setNumber(long number) {
		this.number = number;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public void doTag() throws JspException, IOException {
		String formattedNumber = FormatUtil.number(number, pattern);
		getJspContext().getOut().write(formattedNumber);
	}

}
