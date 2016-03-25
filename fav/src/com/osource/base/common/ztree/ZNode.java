/**   
 * 说明：
 * @version： EagleEye v0.1 
 * @date:2013-12-21  
 */
package com.osource.base.common.ztree;

import java.util.ArrayList;
import java.util.List;


/**   
 * 类描述：   
 * 
 * 项目名称：EagleEye   
 * 类名称：ZNode      
 *    
 */
public class ZNode {
	private Integer id;//树节点Id
    private String name;//树节点名称
    private String longitude; //经度
    private String latitude;  //纬度
    private Boolean open;//节点是否打开，默认false不打开
    private Boolean isParent;//是否父节点,，默认false为叶子节点
    private List<ZNode> children = new ArrayList<ZNode>();//子节点
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	public List<ZNode> getChildren() {
		return children;
	}
	public void setChildren(List<ZNode> children) {
		this.children = children;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
    
    
    
}
