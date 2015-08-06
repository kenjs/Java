package com.cy.driver.bo;

/**
 * Created by Richie.Lee on 2014/9/12.
 */
public class JSonResponse {
    private String result;
    private String errorMessage;
    private Object content;

    public static JSonResponse makeHasContentJSonRespone(String result, String errorMessage, Object content) {
        JSonResponse jSonResponse = new JSonResponse();
        jSonResponse.result = result;
        jSonResponse.errorMessage = errorMessage;
        jSonResponse.content = content;
        return jSonResponse;
    }

    public static JSonResponse makeHasContentJSonRespone(String result, String errorMessage) {
        JSonResponse jSonResponse = new JSonResponse();
        jSonResponse.result = result;
        jSonResponse.errorMessage = errorMessage;
        return jSonResponse;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
