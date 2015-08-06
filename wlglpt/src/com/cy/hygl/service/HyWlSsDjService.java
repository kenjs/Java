package com.cy.hygl.service;

import java.util.List;

import com.cy.common.bo.HyWlssdjZp;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;

/**
 * The SERVICE for 调度成本审核.
 * 
 * @author HJH
 */

public interface HyWlSsDjService extends BaseBusinessService {
	public void getHw(HyZyglFydjDomain domain, UserDomain userDomain) throws Exception;
	public void checkWlDj(HyZyglFydjDomain domain, UserDomain userDomain) throws Exception;
	public void toLook(HyZyglFydjDomain domain, UserDomain userDomain) throws Exception;
	public void deleteWlssdjMx(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception;
	public void initSsMx(HyZyglFydjDomain baseBusinessDomain, UserDomain userDomain)throws Exception;
	public void querySsMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	public void saveSsZp(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain)throws Exception;
	public List<HyWlssdjZp> queryPhoto(String wlssdjxh)throws Exception;
	public String deletePhoto(String zpscxh,HyZyglFydjDomain domain, UserDomain userDomain)throws Exception;
	public List<String> deletePhotoes(HyZyglFydjDomain domain, UserDomain userDomain)throws Exception;
}