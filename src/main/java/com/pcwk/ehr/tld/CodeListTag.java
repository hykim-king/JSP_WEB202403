package com.pcwk.ehr.tld;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.ehr.code.CodeVO;

public class CodeListTag extends SimpleTagSupport {
	private static final long serialVersionUID = 1L;

	private List<CodeVO> pageCode;
	private String selectedVal;

	public void setPageCode(List<CodeVO> pageCode) {
		this.pageCode = pageCode;
	}

	public void setSelectedVal(String selectedVal) {
		this.selectedVal = selectedVal;
	}

	@Override
	public void doTag() throws JspException, IOException {
		String codeHtml = StringUtil.getCodeList(pageCode, selectedVal);
		getJspContext().getOut().write(codeHtml);
	}

}
