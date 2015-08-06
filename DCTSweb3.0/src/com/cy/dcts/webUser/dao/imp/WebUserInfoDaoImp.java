package com.cy.dcts.webUser.dao.imp;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.dao.BaseDao;
import com.cy.dcts.common.domain.WebUserInfoDamain;
import com.cy.dcts.webUser.dao.IWebUserInfoDao;

public class WebUserInfoDaoImp extends BaseDao implements IWebUserInfoDao {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public WebUserInfoDamain queryWebUserCompanyoyById(String id) {
		try {
			return (WebUserInfoDamain)this.queryForObject("query_web_user_companyoy_ById", id);
		} catch (Exception e) {
			logger.error("query_web_user_companyoy_ById error!",id);
			throw new RuntimeException();
		}
	}
	

	public Integer queryWebUserByParentIdUsertypeEncoded(Map <String, Object> queryMap) {
		
		try {
			return (Integer)queryForObject("query_web_user_by_parentIdUsertypeEncoded",queryMap);
		}catch(Exception e) {
			logger.error("query_web_user_by_parentIdUsertypeEncoded error!",e);
			throw new RuntimeException();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<WebUserInfo> queryWebUserInfo(Map <String, Object> queryMap) {
		try {
			return (List<WebUserInfo>) queryForList("query_web_user_info",queryMap);
		} catch (Exception e) {
			logger.error("query_web_user_info error!",e);
			throw new RuntimeException();
		}
	}
	
	public WebUserInfo queryWebUserInfoById(String userId) {
		try {
			return (WebUserInfo)queryForObject("query_web_user_info_by_id",userId);
		}catch(Exception e) {
			logger.error("query_web_user_info_by_id error!",e);
			throw new RuntimeException();
		}
	}

	public WebUserInfo queryWebUserInfoByCode(String code) {
		try {
			return (WebUserInfo)queryForObject("query_web_user_info_by_code",code);
		}catch(Exception e) {
			logger.error("query_web_user_info_by_code error!",e);
			throw new RuntimeException();
		}
	}

	public WebUserInfo queryWebUserInfoByMobilephone(String mobilephone) {
		try {
			return (WebUserInfo)queryForObject("query_web_user_info_by_mobilephone",mobilephone);
		}catch(Exception e) {
			logger.error("query_web_user_info_by_mobilephone error!",e);
			throw new RuntimeException();
		}
	}

	public String addWebUserInfo(WebUserInfo webUserInfo) {
		try {
			return addObjectKeyString("insert_web_user_info",webUserInfo);
		}catch (Exception e) {
			logger.error("insert_web_user_info error!",e);
			throw new RuntimeException();
		}
	}

	public int modifyWebUserInfoById(WebUserInfo webUserInfo) {
		try {
			return saveObject("modify_web_user_info_by_id",webUserInfo);
		} catch (Exception e) {
			logger.error("modify_web_user_info_by_id error!",e);
			throw new RuntimeException();
		}
	}

	public boolean modifyWebUserInfoEnterpriseFlagById(WebUserInfo webUserInfo) {
		try {
			return saveObject("modify_web_user_info_enterpriseflag_by_id",webUserInfo) == 1;
		} catch (Exception e) {
			logger.error("modify_web_user_info_enterpriseflag_by_id error!",e);
			throw new RuntimeException();
		}
	}

	public boolean modifyWebUserInfoMobilephoneById(WebUserInfo webUserInfo) {
		try {
			return saveObject("modify_web_user_info_mobilephone_by_id",webUserInfo) == 1;
		} catch (Exception e) {
			logger.error("modify_web_user_info_mobilephone_by_id error!",e);
			throw new RuntimeException();
		}
	}

	public boolean modifyWebUserInfoPanymentFlagById(WebUserInfo webUserInfo) {
		try {
			return saveObject("modify_web_user_info_panymentflag_by_id",webUserInfo) == 1;
		} catch (Exception e) {
			logger.error("modify_web_user_info_panymentflag_by_id error!",e);
			throw new RuntimeException();
		}
	}

	public boolean modifyWebUserInfoPasswordById(WebUserInfo webUserInfo) {
		try {
			return saveObject("modify_web_user_info_password_by_id",webUserInfo) == 1;
		} catch (Exception e) {
			logger.error("modify_web_user_info_password_by_id error!",e);
			throw new RuntimeException();
		}
	}

	public boolean modifyWebUserInfoPersonageFlagById(WebUserInfo webUserInfo) {
		try {
			return saveObject("modify_web_user_info_personageflag_by_id",webUserInfo) == 1;
		} catch (Exception e) {
			logger.error("modify_web_user_info_personageflag_by_id error!",e);
			throw new RuntimeException();
		}
	}

	public void updateUserFlag(Map<String,Object> map) {
		try {
			saveObject("iBatisUpdateFlagById",map);
		} catch (Exception e) {			
			throw new RuntimeException();
		}
	}

	
	public WebUserInfo queryWebUserInfoEncoded() {
		try {
			return (WebUserInfo)queryForObject("query_web_user_info_encoded");
		}catch(Exception e) {
			logger.error("query_web_user_info_encoded error!",e);
			throw new RuntimeException();
		}
	}

	public boolean modifyWebUserInfoEncodedById(WebUserInfo webUserInfo) {
		try {
			return saveObject("modify_web_user_info_encoded_by_id",webUserInfo) == 1;
		} catch (Exception e) {
			logger.error("modify_web_user_info_encoded_by_id error!",e);
			throw new RuntimeException();
		}
	}

	public WebUserInfo queryWebUserInfoEncodedByParentId(Map<String, Object> queryMap) {
		try {
			return (WebUserInfo) queryForObject("query_web_user_info_encoded_by_parent_id",queryMap);
		} catch (Exception e) {
			logger.error("query_web_user_info_encoded_by_parent_id error!",e);
			throw new RuntimeException();
		}
	}


	public List<WebUserInfoDamain> querySonWebUserInfoPage(
			Map<String, Object> queryMap) {
		try {
			return (List<WebUserInfoDamain>) queryForList("query_son_web_user_info_page",queryMap);
		} catch (Exception e) {
			logger.error("query_son_web_user_info_page error!",e);
			throw new RuntimeException();
		}
	}

	public Integer querySonWebUserInfoPageCount(Map<String, Object> queryMap) {
		try {
			return (Integer)this.queryForObject("query_son_web_user_info_page_count", queryMap);
		} catch (Exception e) {
			logger.error("query_son_web_user_info_page_count error!",e);
			throw new RuntimeException();
		}
	}

	public List<WebUserInfoDamain> querySonWebUserInfoList(Map<String, Object> queryMap) {
		try {
			return (List<WebUserInfoDamain>) queryForList("query_son_web_user_info_list",queryMap);
		} catch (Exception e) {
			logger.error("query_son_web_user_info_list error!",e);
			throw new RuntimeException();
		}
	}

	public boolean modifySonWebUserInfoById(WebUserInfo webUserInfo) {
		try {
			return saveObject("modify_son_web_user_info_by_id",webUserInfo) == 1;
		} catch (Exception e) {
			logger.error("modify_son_web_user_info_by_id error!",e);
			throw new RuntimeException();
		}
	}

	


}
