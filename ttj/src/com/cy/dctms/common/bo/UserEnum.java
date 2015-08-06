package com.cy.dctms.common.bo;

/**
 * Created by haoy on 2014/10/17.
 */
public enum UserEnum {

    ONE("胡总","13958037221","Monday"),TWO("胡总","13958037221","Tuesday"),
    THREE("胡总","13958037221","Wednesday"),FOUR("胡总","13958037221","Thursday"),
    FIVE("胡总","13958037221","Friday"),SIX("胡总","13958037221,18757178302","Saturday"),
    SEVEN("胡总","13958037221","Sunday");

    private String userName;
    private String phoneNum;
    private String date;

    UserEnum(String userName, String phoneNum, String date) {
        this.phoneNum = phoneNum;
        this.userName = userName;
        this.date = date;
    }

    public String userName() {
        return userName;
    }

    public String phoneNum() {
        return phoneNum;
    }

    public String date() {
        return date;
    }
}
