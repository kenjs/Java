package tf56.contract.services;

import java.util.List;
import java.util.Map;

public interface OrganizationService {
    /**
     * 根据合同签订双方名称查询partyId
     * @author lianggui.zhou
     * @param organizationName
     * @return OrganizationId
     * @since 2013年9月16日
     */
    public String selecteOrganizationIdByName(String organizationName);
    /**
     * 获取发货方信息
     * @author wei.huang
     * @return 发货方信息
     * @since 2013年9月30日
     */
    public String getConsignorInf(Map map);
	public String queryOrgName(Map map);
	public String selectOrganizationNameByPartyId(Map map);
    /***
     * 根据组织名或者备注查询满足条件的组织
     * @author lianggui.zhou
     * @param map(organization,description)
     * @return description,organization,partyidlist(id集合)
     */
    public List queryDecription(Map map);
}
