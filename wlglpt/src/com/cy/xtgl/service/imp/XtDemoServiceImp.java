package com.cy.xtgl.service.imp;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.xtgl.dao.XtDemoDao;
import com.cy.xtgl.domain.XtDemoDomain;
import com.cy.xtgl.service.XtDemoService;

@Service
public class XtDemoServiceImp extends BaseBusinessServiceImp implements	XtDemoService {

	@Autowired
	private XtDemoDao dao;
	
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XtDemoDomain demoDomain = (XtDemoDomain) baseBusinessDomain;
		
		List<DmbGgDomain> list = dao.queryGsBmList(userDomain);
		StringBuffer ztreeNodes = new StringBuffer();
		ztreeNodes.append("[");
		if (list != null && list.size() > 0) {
			for (Iterator<DmbGgDomain> it=list.iterator(); it.hasNext(); ) {
				DmbGgDomain domain = it.next();
				ztreeNodes.append("{id:").append(domain.getDm())
					.append(", pId:").append(domain.getDm())
					.append(", name:\"").append(StringUtils.trim(domain.getMc())).append("\"");
				
				if ("Y".equals(domain.getDm())) {
					ztreeNodes.append(", open:true");
				}
				ztreeNodes.append("},");
			}
		}
		
		if (ztreeNodes.length() > 1) {
			ztreeNodes.deleteCharAt(ztreeNodes.length()-1);
		}
		
		ztreeNodes.append("]");
		
		demoDomain.setZtreeNodes(ztreeNodes.toString());
	}

	
}
