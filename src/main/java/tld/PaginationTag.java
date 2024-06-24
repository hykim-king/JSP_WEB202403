package com.pcwk.ehr.tld;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.pcwk.ehr.cmn.StringUtil;

public class PaginationTag extends SimpleTagSupport {
	private int maxNum;
    private int currentPageNo;
    private int rowPerPage;
    private int bottomCount;
    private String url;
    private String scriptName;

    
    
    
    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public void setRowPerPage(int rowPerPage) {
        this.rowPerPage = rowPerPage;
    }

    public void setBottomCount(int bottomCount) {
        this.bottomCount = bottomCount;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    @Override
    public void doTag() throws JspException, IOException {
        String pagingHtml = StringUtil.renderingPaging(maxNum, currentPageNo, rowPerPage, bottomCount, url, scriptName);
        getJspContext().getOut().write(pagingHtml);
    }
}
