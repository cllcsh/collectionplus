/**   
 * 文件名：RpTypeDao.java   

 *   
 */
package com.osource.module.system.dao;

import java.util.List;

import com.osource.core.exception.IctException;
import com.osource.module.system.model.RpTypeBean;


/**   
 *    
 * 项目名称：osource   
 * 类名称：RpTypeDao   
 * 类描述：   
 * 创建人：zhangyan   
 * 创建时间：Nov 6, 2009 2:57:52 PM   
 * 修改人：zhangyan   
 * 修改时间：Nov 6, 2009 2:57:52 PM   
 * 修改备注：   
 * @version    
 *    
 */
public interface RpTypeDao {
	
    public void insertRpType(RpTypeBean ri) throws IctException;
	
	public void deletesRpType(String ids) throws IctException;
	
	public boolean updateRpType(RpTypeBean ri) throws IctException;
	
	public RpTypeBean findRpTypeBean(String id) throws IctException;
	
	public List<RpTypeBean> queryRpTypeList(RpTypeBean rpTypeBean, int start, int limit) throws IctException;
	
	public Integer getRpTypeCount(RpTypeBean rpTypeBean) throws IctException;

}
