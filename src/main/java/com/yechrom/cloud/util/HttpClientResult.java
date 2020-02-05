package com.yechrom.cloud.util;

import java.io.Serializable;

public class HttpClientResult implements Serializable{
    
    private static final long serialVersionUID = -5152302877825031721L;

    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private String content;

    public int getCode() {
        return code;
    }

    public String getContent() {
        return content;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public HttpClientResult() {
        super();
    }

    public HttpClientResult(int code) {
        super();
        this.code = code;
    }
    
    public HttpClientResult(String content) {
        super();
        this.content = content;
    }
    
    public HttpClientResult(int code, String content) {
        super();
        this.code = code;
        this.content = content;
    }

	@Override
	public String toString() {
		return "HttpClientResult [code=" + code + ", content=" + content + "]";
	}
    
    
}

