package com.cy.jcgl.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.jcgl.domain.JcFcQkMxDomain;

/**
 * THE ACTION FOR ����-�ɳ���Ϣ����
 * @author LYY
 * time  2013-5-4
 */
@Controller
@Scope("prototype")
@Action(value = "/jcgl/fcqkmx", results = {
		@Result(name = "init", location = "/work/jcgl/jc_fcqkmx.jsp"),
		@Result(name = "query", type = "json")
		})
@SuppressWarnings("unchecked")
public class JcFcQkMxAction extends ExtendAction {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		JcFcQkMxDomain domain = (JcFcQkMxDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//��š��ɳ����š����˵�λ���ɳ��ˡ��ɳ����ڡ������˵��š��ͻ���λ���µ����ڡ���վ��
		//�ջ������������ơ��������������տʽ���ص��š���˾���˷ѡ�Ԥ���˷ѡ�����״̬
		headList.add("���");
		headList.add("�ɳ�����");
		headList.add("���˵�λ");
		headList.add("�ɳ���");
		headList.add("�ɳ�����");
		headList.add("���˵���");
		headList.add("�ͻ���λ");
		headList.add("�µ�����");
		headList.add("��վ");
		headList.add("�ջ���");
		headList.add("��������");
		headList.add("����");
		headList.add("����");
		headList.add("�տʽ");
		headList.add("�ص���");
		headList.add("˾���˷�");
		headList.add("Ԥ���˷�");
		headList.add("״̬");
		heads.add(headList);
		
		int i = 1;
		for (BaseBusinessDomain e : (List<BaseBusinessDomain>) domain.getDataList()) {
			JcFcQkMxDomain element = (JcFcQkMxDomain) e;
			List list = new ArrayList();
			list.add(i++ + "");
			list.add(element.getPcdh());
			list.add(element.getClhm());
			list.add(element.getPcrMc());
			if(StringUtils.isNotBlank(element.getPcrq())){
				list.add(sdf.format(sdf.parse(element.getPcrq())));
			}else{
				list.add("");
			}
			list.add(element.getTydh());
			list.add(element.getKhmc());
			if(StringUtils.isNotBlank(element.getXdrq())){
				list.add(sdf.format(sdf.parse(element.getXdrq())));
			}else{
				list.add("");
			}	
			list.add(element.getShrDz());
			list.add(element.getShrMc());
			list.add(element.getHwmc());
			list.add(element.getSl());
			list.add(element.getZl());
			list.add(element.getSkfs());
			list.add(element.getHdh());
			list.add(element.getSjyf());
			list.add(element.getYfyf());
			list.add(element.getSpzt());
			
			result.add(list);
		}
		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);
		ExcelExportPOI.createExpXls(ps, "0.00", request);
		return "download";
	}
	
	@Resource(name = "jcFcQkMxServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public BaseBusinessDomain getDomain() {
		if (domain == null) {
			domain = new JcFcQkMxDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = domain;
	}
}
	
