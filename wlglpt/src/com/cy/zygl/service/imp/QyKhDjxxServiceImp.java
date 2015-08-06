package com.cy.zygl.service.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.exception.ServiceException;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.domain.UserDomain;
import com.cy.zygl.dao.QyKhDjxxDao;
import com.cy.zygl.domain.QyKhDjxxDomain;
import com.cy.zygl.service.QyKhDjxxService;

@Service
/**
 * The SERVICEIMP for 企业-客户-登记信息.
 * 
 * @author HJH
 */
public class QyKhDjxxServiceImp extends BaseBusinessServiceImp implements QyKhDjxxService {
	@Autowired
	private QyKhDjxxDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		//QyKhDjxxDomain domain = (QyKhDjxxDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhDjxxDomain domain = (QyKhDjxxDomain)baseBusinessDomain;
		
		//从别的地方直接点新增客户时进入，默认为当前所在公司
		if (StringUtils.isBlank(domain.getKhDjxh()) && StringUtils.isBlank(domain.getSsJgbm())
				&& StringUtils.isNotBlank(domain.getCallOpenWinFun())) {
			domain.setSsJgbm(userDomain.getGsbm());
			domain.setKhmc(SysEncodeUtil.ISO2GBK(domain.getKhmc()));
		}
		dao.initDomainMx(domain);
		
		if (StringUtils.isBlank(domain.getXxgxfsDm())) {
			domain.setXxgxfsDm("3");
		}
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhDjxxDomain domain = (QyKhDjxxDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhDjxxDomain domain = (QyKhDjxxDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhDjxxDomain domain = (QyKhDjxxDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhDjxxDomain domain=(QyKhDjxxDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
	
	/**
	 * 校验客户名称是否重复
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void check(QyKhDjxxDomain domain, UserDomain userDomain) throws Exception{
		int num=dao.queryKhmcCount(domain);
		if(num>0){
			ServiceException ex=new ServiceException();
			ex.setErrorCode("120101");
			throw ex;
		}
	}

	@Override
	protected void doMySaveBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		super.doMySaveBefore(baseBusinessDomain, userDomain);
		QyKhDjxxDomain domain=(QyKhDjxxDomain)baseBusinessDomain;
		this.check(domain, userDomain);
	}
	
	
	public String[] queryXzqhList(QyKhDjxxDomain domain, UserDomain userDomain) throws Exception{
		StringBuffer sb = new StringBuffer("");
		StringBuffer dataSb=new StringBuffer("[");
		List<QyKhDjxxDomain> dataList=dao.queryXzqhList(domain);
		if(dataList!=null && !dataList.isEmpty()){
			int i=0;
			for (QyKhDjxxDomain domain2 : dataList) {
				
				if(null==domain2.getXzqhDm())
					domain2.setXzqhDm("");
				if(null==domain2.getXzqhMc())
					domain2.setXzqhMc("");
				if(null==domain2.getPyjc())
					domain2.setPyjc("");
				if(null==domain2.getPyjc())
					domain2.setPyjc("");
				//sb.append("\"").append(domain2.getXzqhMc()).append("^").append(domain2.getPyqc()).append("^").append(domain2.getPyjc()).append("\"");
				//sb.append("\"").append(domain2.getXzqhMc()).append("^").append(domain2.getPyjc()).append("\"");
				//sb.append("\"").append(domain2.getXzqhQc()).append("^").append(domain2.getPyqc()).append("^").append(domain2.getPyjc()).append("\"");
				sb.append("\"").append(domain2.getXzqhQc()).append("^").append(domain2.getXzqhMc()).append("^").append(domain2.getPyqc()).append("^").append(domain2.getPyjc()).append("\"");
				dataSb.append("{'xzqhQc':'").append(domain2.getXzqhQc()).append("',")
					.append("'xzqhMc':'").append(domain2.getXzqhMc()).append("',")
					.append("'xzqhDm':'").append(domain2.getXzqhDm()).append("'}");
				if(i<dataList.size()-1){
					sb.append(",");
					dataSb.append(",");
				}
				i++;
			}
		}
		dataSb.append("]");
		String[] returnData = new String[2];
		returnData[0] = dataSb.toString();
		returnData[1] = sb.toString();
		return returnData;
	}
}
