package com.cy.driver.dao;

import com.cy.driver.bo.CaptchaNoteLog;
import com.cy.driver.bo.NoteSendRecord;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Map;

@Repository("commonDao")
public interface CommonDao {

	/**
	 * 获取系统参数值
	 * @return
	 * @throws java.sql.SQLException
	 */
	public String getSystemParameter(Map<String, Object> map) throws SQLException ;

    /**
     * save 验证码发送记录
     * @param captchaNoteLog
     * @return
     * @throws SQLException
     */
    public int insertCaptchNoteLog(CaptchaNoteLog captchaNoteLog) throws SQLException;

    /**
     * 查询用户最后一次请求发送的验证码
     * 注册用
     * @return
     * @throws SQLException
     */
    public String queryLatestCode(Map<String, Object> code) throws SQLException;

    /**
     * 其他验证
     * @param code
     * @return
     * @throws SQLException
     */
    public String queryCodeForVerify(String code) throws SQLException;

    public int addNoteSendRecord(NoteSendRecord noteSendRecord) throws SQLException;
}
