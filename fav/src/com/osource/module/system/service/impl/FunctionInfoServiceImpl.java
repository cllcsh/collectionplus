package com.osource.module.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.base.web.UserSession;
import com.osource.core.IDgenerator;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.system.dao.FunctionInfoDao;
import com.osource.module.system.model.FunctionInfo;
import com.osource.module.system.service.FunctionInfoService;
import com.osource.orm.ibatis.BaseServiceImpl;

@SuppressWarnings("unchecked")
@Service
@Scope("prototype")
@Transactional
public class FunctionInfoServiceImpl extends BaseServiceImpl implements FunctionInfoService{
	
	@Autowired
	private FunctionInfoDao functionInfoDao;
	
	public FunctionInfoServiceImpl(){
	}
	
	public Map findAllFunctionList(UserSession userSession){
		List<FunctionInfo> list = functionInfoDao.findAllFunctionList();
		Map<Integer,String> result = new HashMap();
		for(FunctionInfo function : list){
			result.put(function.getId(), function.getFunctionName());
		}
		return result;
	}
	public Map buildFrontList(UserSession userSession){
		List<FunctionInfo> list = functionInfoDao.findFrontList();
		Map<Integer,String> result = new HashMap();
		for(FunctionInfo function : list){
			result.put(function.getId(), function.getFunctionName());
		}
		return result;
	}
	
	/**
	 * 新增角色
	 * @param departmentInfo
	 * @return
	 * @throws IctException
	 */
	public FunctionInfo saveFunctionInfo(FunctionInfo functionInfo) throws IctException {
		functionInfo.setId(IDgenerator.gettNextID("tm_func"));
		return functionInfoDao.saveFunctionInfo(functionInfo);
	}
	
	/**
	 * 更新角色
	 * @param FunctionInfo
	 * @return
	 * @throws IctException
	 */
	public FunctionInfo updateFunctionInfo(FunctionInfo functionInfo) throws IctException {
		return functionInfoDao.updateFunctionInfo(functionInfo);
	}
	
	/**
	 * 根据角色Id删除信息
	 * @param id
	 * @throws IctException
	 */
	public void deleteFunctionInfoById(Integer id) throws IctException {
		functionInfoDao.deleteFunctionInfoById(id);
	}
	
	/**
	 * 根据角色Id组删除信息
	 * @param id
	 * @throws IctException
	 */
	public void deleteFunctionInfoByIds(String ids) throws IctException {
		functionInfoDao.deleteFunctionInfoByIds(ids);
	}
	
	/**
	 * 根据角色Id返回角色信息
	 * @param id
	 * @return
	 */
	public FunctionInfo findFunctionInfoById(Integer id){
		return functionInfoDao.findFunctionInfoById(id);
	}
	/**
	 * 返回所有有效角色数量
	 * @return
	 */
	public long getAllFunctionNum(){
		return functionInfoDao.getAllFunctionNum();
	}
	
	/**
	 * 返回所有功能信息
	 * @param orderby
	 * @param ascOrDesc
	 * @param pages
	 * @return
	 */
	public PageList findFunctionInfoList(String orderby, int ascOrDesc, Pages pages){
		PageList result = new PageList();
		pages.setTotal(getAllFunctionNum());
		pages.executeCount();
		result.setPages(pages);
		result.addAll(functionInfoDao.findFunctionInfoList(orderby, ascOrDesc, pages.getStart(), pages.getLimit()));
		return result;
	}
	/**
	 * 根据条件返回功能数量
	 * @param condition
	 * @return
	 */
	public long getFunctionNumByCondition(Map condition){
		return functionInfoDao.getFunctionNumByCondition(condition);
	}
	
	/**
	 * 根据条件返回功能信息
	 * @param condition
	 * @param pages
	 * @return
	 */
	public PageList findUserInfoListByCondition(Map condition,Pages pages){
		PageList result = new PageList();
		pages.setTotal(getFunctionNumByCondition(condition));
		pages.executeCount();
		result.setPages(pages);
		result.addAll(functionInfoDao.findFunctionInfoListByCondition(condition, pages.getStart(), pages.getLimit()));
		return result;
	}
	
	//edit by weiwu
	/**
	 * 根据Id返回功能列表
	 * @param condition
	 * @param pages
	 * @return
	 */
	public List<FunctionInfo> getFunctionInfoList(Integer userId){
		return functionInfoDao.findFunctionListByUserId(userId);
	}
	//
	
	/*getter and setter*/	
	
	public FunctionInfoDao getFunctionInfoDao() {
		return functionInfoDao;
	}

	public void setFunctionInfoDao(FunctionInfoDao functionInfoDao) {
		this.functionInfoDao = functionInfoDao;
	}
	
	public static void main(String[] args){
		FunctionInfoServiceImpl service = new FunctionInfoServiceImpl();
//		service.testSaveUserInfo();
		//service.testFindUserInfoListByCondition();
		service.testGetFunctionInfoList();
	}
	protected void testSaveUserInfo(){
		FunctionInfo functionInfo = new FunctionInfo();
		functionInfo.setFunctionName("测试");
		functionInfo.setLink("test.do");
		functionInfo.setUserType("1");
		functionInfo.setInsertId(1);
		
		try {
			FunctionInfo u = saveFunctionInfo(functionInfo);
			assert u != null;
			System.out.println("添加用户成功，用户ID为："+u.getId());
			deleteFunctionInfoById(u.getId());
			System.out.println("删除用户ID为："+u.getId() + " 的用户成功");
			System.out.println("saveUserInfo 测试通过");
		} catch (IctException e) {
			System.err.println("saveUserInfo 测试失败");
		}
	}
	protected void testFindUserInfoListByCondition(){
		PageList list = findUserInfoListByCondition(new HashMap(), new Pages());
		System.out.println(list.size());
	}
	
	protected void testGetFunctionInfoList(){
		System.out.println("测试getFunctionInfoList开始");
		List list = getFunctionInfoList(0);
		System.out.println(list.size());
		List list2 = getFunctionInfoList(31);
		System.out.println(list2.size());
		System.out.println("测试getFunctionInfoList结束");
	}
}
