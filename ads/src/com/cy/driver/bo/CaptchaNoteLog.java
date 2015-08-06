package com.cy.driver.bo;

/**
 * Created by haoy on 2014/10/22.
 */
public class CaptchaNoteLog {
    private long id;
    private long noteSendedId;      //短信日志记录表主键(pk-t_note_log_info.id)
    private String mobilephone;     //手机号
    private String captcha;         //验证码
    private int webOrApp;           //发送验证码的应用来源
    private int purpose;            //该验证码的用途(0 注册 1修改密码 2忘记密码 3 修改手机号 999 老用户登录补充手机号)
    private String createTime;

    public CaptchaNoteLog() {
    }

    public CaptchaNoteLog(long noteSendedId, String mobilephone, String captcha, int webOrApp, int purpose) {
        this.noteSendedId = noteSendedId;
        this.mobilephone = mobilephone;
        this.captcha = captcha;
        this.webOrApp = webOrApp;
        this.purpose = purpose;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNoteSendedId() {
        return noteSendedId;
    }

    public void setNoteSendedId(long noteSendedId) {
        this.noteSendedId = noteSendedId;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public int getWebOrApp() {
        return webOrApp;
    }

    public void setWebOrApp(int webOrApp) {
        this.webOrApp = webOrApp;
    }

    public int getPurpose() {
        return purpose;
    }

    public void setPurpose(int purpose) {
        this.purpose = purpose;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
