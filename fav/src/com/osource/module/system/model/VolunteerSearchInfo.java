/**   
 * 文件名：VolunteerSearchInfo.java   
 *   
 */
package com.osource.module.system.model;

/**   
 *    
 * 项目名称：osource   
 * <p>类名称：VolunteerSearchInfo   
 * <p>类描述：心理矫正志愿者登记 查询条件实体
 * <p>registerId:全国志愿者注册编号
 * <p>consultantCertificateId:咨询师证书编号
 * <p>name:姓名
 * <p>rank:等级
 * <p>idNum:身份证号码
 * <p>useFlag:使用状态
 * <p>deptId:机构编号
 * <p>创建人：yangsen   
 * <p>创建时间：2009-11-6 下午04:37:58   
 * <p>修改人：yangsen   
 * <p>修改时间：2009-11-6 下午04:37:58   
 * <p>修改备注：   
 * <p>@version    
 *    
 */
public class VolunteerSearchInfo {

	private String registerId;
	private String name;
	private String rank;
	private String consultantCertificateId;
	private Integer useFlag;
	private String idNum;
	private Integer deptId;

	public String getRegisterId() {
		return registerId;
	}
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getConsultantCertificateId() {
		return consultantCertificateId;
	}
	public void setConsultantCertificateId(String consultantCertificateId) {
		this.consultantCertificateId = consultantCertificateId;
	}
	public Integer getUseFlag() {
		return useFlag;
	}
	public void setUseFlag(Integer useFlag) {
		this.useFlag = useFlag;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
}
