package com.cy.swp.common.util;

/**
 * Created by Richie.Lee on 2014/9/12.
 */
public class JSonRespone {
    private String result;
    private String errorMessage;
    private Object content;

    public static JSonRespone makeHasContentJSonRespone(String result, String errorMessage, Object content) {
        JSonRespone jSonRespone = new JSonRespone();
        jSonRespone.result = result;
        jSonRespone.errorMessage = errorMessage;
        jSonRespone.content = content;
        return jSonRespone;
    }

    public static JSonRespone makeHasContentJSonRespone(String result, String errorMessage) {
        JSonRespone jSonRespone = new JSonRespone();
        jSonRespone.result = result;
        jSonRespone.errorMessage = errorMessage;
        return jSonRespone;
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
