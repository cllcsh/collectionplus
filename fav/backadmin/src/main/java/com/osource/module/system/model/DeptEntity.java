package com.osource.module.system.model;

import com.osource.base.model.BaseEntity;
import com.osource.base.model.ColBean;
import com.osource.util.StringUtil;

/**
 * @author : Feng Jingzhun
 * @version : 1.0
 * @date : 2009-11-16 11:22:38
 */
public class DeptEntity extends BaseEntity
{
    private ColBean<Integer> domain = new ColBean<Integer>("domain", ColBean.INTEGER);
    private ColBean<String> code = new ColBean<String>("code", ColBean.STRING);
    private ColBean<String> name = new ColBean<String>("name", ColBean.STRING);
    private ColBean<String> manager = new ColBean<String>("manager", ColBean.STRING);
    private ColBean<String> phoneNo = new ColBean<String>("phone_no", ColBean.STRING);
    private ColBean<String> faxNo = new ColBean<String>("fax_no", ColBean.STRING);
    private ColBean<String> address = new ColBean<String>("address", ColBean.STRING);
    private ColBean<String> postcode = new ColBean<String>("postcode", ColBean.STRING);
    private ColBean<String> rank = new ColBean<String>("rank", ColBean.STRING);
    private ColBean<String> description = new ColBean<String>("description", ColBean.STRING);
    private ColBean<Integer> upperDept = new ColBean<Integer>("upper_dept", ColBean.INTEGER);
    private ColBean<String> node = new ColBean<String>("node", ColBean.STRING);
    
    private ColBean<String> jgbm = new ColBean<String>("jgbm", ColBean.STRING);
    private ColBean<Integer> organizationTypeId = new ColBean<Integer>("organization_type_id", ColBean.INTEGER);
    private ColBean<String> longitude = new ColBean<String>("longitude", ColBean.STRING);
    private ColBean<String> latitude = new ColBean<String>("latitude", ColBean.STRING);
    private ColBean<Float> uvalue = new ColBean<Float>("uvalue", ColBean.FLOAT);
    private ColBean<Float> fvalue = new ColBean<Float>("fvalue", ColBean.FLOAT);
    private ColBean<Float> svalue = new ColBean<Float>("svalue", ColBean.FLOAT);
    private ColBean<Float> f1 = new ColBean<Float>("f1", ColBean.FLOAT);
    private ColBean<Float> f2 = new ColBean<Float>("f2", ColBean.FLOAT);
    private ColBean<Float> f3 = new ColBean<Float>("f3", ColBean.FLOAT);
    private ColBean<Float> f4 = new ColBean<Float>("f4", ColBean.FLOAT);
    private ColBean<Integer> viewOrder = new ColBean<Integer>("view_order", ColBean.INTEGER);
   // private ColBean<String> dzyx = new ColBean<String>("dzyx", ColBean.STRING);
    
    private String province;
    private String city;
    private String area;
    
    private String jgbmpro;
    private String dwbm;
    private String bmsxm;
    //关联字段
    private String domainName;
    private String upperDeptName;
    private String organizationTypeName;
    
	//建立实体与表映射
    public DeptEntity()
    {
        this.setTableName("tb_dept");
    }

    public ColBean<Integer> getDomain()
    {
        return domain;
    }

    public void setDomain(ColBean<Integer> domain)
    {
        this.domain = domain;
    }

    public ColBean<String> getCode()
    {
        return code;
    }

    public void setCode(ColBean<String> code)
    {
        this.code = code;
    }

    public ColBean<String> getName()
    {
        return name;
    }

    public void setName(ColBean<String> name)
    {
        this.name = name;
    }

    public ColBean<String> getManager()
    {
        return manager;
    }

    public void setManager(ColBean<String> manager)
    {
        this.manager = manager;
    }

    public ColBean<String> getPhoneNo()
    {
        return phoneNo;
    }

    public void setPhoneNo(ColBean<String> phoneNo)
    {
        this.phoneNo = phoneNo;
    }

    public ColBean<String> getFaxNo()
    {
        return faxNo;
    }

    public void setFaxNo(ColBean<String> faxNo)
    {
        this.faxNo = faxNo;
    }

    public ColBean<String> getAddress()
    {
        return address;
    }

    public void setAddress(ColBean<String> address)
    {
        this.address = address;
    }

    public ColBean<String> getPostcode()
    {
        return postcode;
    }

    public void setPostcode(ColBean<String> postcode)
    {
        this.postcode = postcode;
    }

    public ColBean<String> getRank()
    {
        return rank;
    }

    public void setRank(ColBean<String> rank)
    {
        this.rank = rank;
    }

    public ColBean<Integer> getUpperDept()
    {
        return upperDept;
    }

    public void setUpperDept(ColBean<Integer> upperDept)
    {
        this.upperDept = upperDept;
    }

    public ColBean<String> getNode()
    {
        return node;
    }

    public void setNode(ColBean<String> node)
    {
        this.node = node;
    }

    public String getDomainName()
    {
        return domainName;
    }

    public void setDomainName(String domainName)
    {
        this.domainName = domainName;
    }
    
    public String getUpperDeptName() {
		return upperDeptName;
	}

	public void setUpperDeptName(String upperDeptName) {
		this.upperDeptName = upperDeptName;
	}

	public ColBean<String> getJgbm() {
		return jgbm;
	}

	public void setJgbm(ColBean<String> jgbm) {
		this.jgbm = jgbm;
	}


	public ColBean<String> getDescription() {
		return description;
	}

	public void setDescription(ColBean<String> description) {
		this.description = description;
	}

	public ColBean<Integer> getOrganizationTypeId() {
		return organizationTypeId;
	}

	public void setOrganizationTypeId(ColBean<Integer> organizationTypeId) {
		this.organizationTypeId = organizationTypeId;
	}

	public ColBean<String> getLongitude() {
		return longitude;
	}

	public void setLongitude(ColBean<String> longitude) {
		this.longitude = longitude;
	}

	public ColBean<String> getLatitude() {
		return latitude;
	}

	public void setLatitude(ColBean<String> latitude) {
		this.latitude = latitude;
	}

	
	/*public ColBean<String> getDzyx() {
		return dzyx;
	}

	public void setDzyx(ColBean<String> dzyx) {
		this.dzyx = dzyx;
	}*/

	public ColBean<Float> getUvalue() {
		return uvalue;
	}

	public void setUvalue(ColBean<Float> uvalue) {
		this.uvalue = uvalue;
	}

	public ColBean<Float> getFvalue() {
		return fvalue;
	}

	public void setFvalue(ColBean<Float> fvalue) {
		this.fvalue = fvalue;
	}

	public ColBean<Float> getSvalue() {
		return svalue;
	}

	public void setSvalue(ColBean<Float> svalue) {
		this.svalue = svalue;
	}

	public String getProvince() {
		if(jgbm != null && !StringUtil.isEmpty(jgbm.getStringValue()) && jgbm.getStringValue().length()>6){
			return jgbm.getStringValue().substring(0, 2)+"0000";
		}
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		if(jgbm != null && !StringUtil.isEmpty(jgbm.getStringValue()) && jgbm.getStringValue().length()>6){
			return jgbm.getStringValue().substring(0, 4)+"00";
		}
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		if(jgbm != null && !StringUtil.isEmpty(jgbm.getStringValue()) && jgbm.getStringValue().length()>6){
			return jgbm.getStringValue().substring(0, 6);
		}
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getJgbmpro() {
		if(jgbm != null && !StringUtil.isEmpty(jgbm.getStringValue()) && jgbm.getStringValue().length()>12){
			return jgbm.getStringValue().substring(0, 12);
		}
		return jgbmpro;
	}
	
	public void setJgbmpro(String jgbmpro) {
		this.jgbmpro = jgbmpro;
	}

	public String getDwbm() {
		if(jgbm != null && !StringUtil.isEmpty(jgbm.getStringValue()) && jgbm.getStringValue().length()>17){
			return jgbm.getStringValue().substring(12, 14);
		}
		return dwbm;
	}

	public void setDwbm(String dwbm) {
		this.dwbm = dwbm;
	}

	public String getBmsxm() {
		if(jgbm != null && !StringUtil.isEmpty(jgbm.getStringValue()) && jgbm.getStringValue().length()>17){
			return jgbm.getStringValue().substring(14, 18);
		}
		return bmsxm;
	}

	public void setBmsxm(String bmsxm) {
		this.bmsxm = bmsxm;
	}

	public String getOrganizationTypeName() {
		return organizationTypeName;
	}

	public void setOrganizationTypeName(String organizationTypeName) {
		this.organizationTypeName = organizationTypeName;
	}

	public ColBean<Float> getF1() {
		return f1;
	}

	public void setF1(ColBean<Float> f1) {
		this.f1 = f1;
	}

	public ColBean<Float> getF2() {
		return f2;
	}

	public void setF2(ColBean<Float> f2) {
		this.f2 = f2;
	}

	public ColBean<Float> getF3() {
		return f3;
	}

	public void setF3(ColBean<Float> f3) {
		this.f3 = f3;
	}

	public ColBean<Float> getF4() {
		return f4;
	}

	public void setF4(ColBean<Float> f4) {
		this.f4 = f4;
	}

	public ColBean<Integer> getViewOrder() {
		return viewOrder;
	}

	public void setViewOrder(ColBean<Integer> viewOrder) {
		this.viewOrder = viewOrder;
	}

	

}
