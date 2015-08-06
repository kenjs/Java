package com.cy.interfaceService.domain;

/**
 * Created by haoy on 2014/10/16.
 */
public class JsonResponse {
    private String errorCode;  //错误代码
    private String errorMsg;//错误信息
    private Object object;  //内容

    public static JsonResponse sendJsonResponse(String errorCode, String errorMsg) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.errorCode = errorCode;
        jsonResponse.errorMsg = errorMsg;
        return jsonResponse;
    }

    public static JsonResponse sendJsonResponse(String errorCode, String errorMsg, Object object) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.errorCode = errorCode;
        jsonResponse.errorMsg = errorMsg;
        jsonResponse.object = object;
        return jsonResponse;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
