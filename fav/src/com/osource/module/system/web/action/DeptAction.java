package com.osource.module.system.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.common.ztree.ZNode;
import com.osource.base.model.ColBean;
import com.osource.base.util.Entry;
import com.osource.base.web.UserSession;
import com.osource.base.web.action.BaseAction;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.system.model.DeptEntity;
import com.osource.module.system.model.DeptInfo;
import com.osource.module.system.service.DeptService;
import com.osource.module.system.service.DomainService;
import com.osource.module.system.web.form.DeptForm;
import com.osource.module.system.web.form.DeptInfoForm;

/**
 * @author : Feng Jingzhun
 * @version : 1.0
 * @date : 2009-11-13 14:32:14
 */

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class DeptAction extends BaseAction
{
    private DeptForm deptForm;
    
    private DeptInfoForm deptInfoForm = new DeptInfoForm();
    
    @Autowired
    private DomainService domainService;
    @Autowired
    private DeptService deptService;

    


    public DeptInfoForm getDeptInfoForm() {
		return deptInfoForm;
	}

	public void setDeptInfoForm(DeptInfoForm deptInfoForm) {
		this.deptInfoForm = deptInfoForm;
	}

	public DomainService getDomainService()
    {
        return domainService;
    }

    public void setDomainService(DomainService domainService)
    {
        this.domainService = domainService;
    }

    public DeptService getdeptService()
    {
        return deptService;
    }

    public void setdeptService(DeptService deptService)
    {
        this.deptService = deptService;
    }

    public String init()
    {
//        if (deptForm == null)
//        {
//            deptForm = new DeptForm();
//        }
//        deptForm.setDomainList(domainService.getDomainSelectList());
//        deptForm.setDeptList(deptService.getDeptSelectList(getUserSession().getDeptId()));
    	
	      if (deptInfoForm == null)
	      {
	    	  deptInfoForm = new DeptInfoForm();
	      }
	      deptInfoForm.setDomainList(domainService.getDomainSelectList());
	      deptInfoForm.setDeptList(deptService.getDeptSelectList(getUserSession().getDeptId()));
	      this.setId(getUserSession().getDeptId());
     
          return RESULT_INIT;
    }

    public String query()
    {
    	DeptEntity queryEntity = new DeptEntity();
    	
    	if(deptInfoForm != null){
//    		ColBean<String> address = new ColBean("address",ColBean.STRING);
//        	address.setValue(deptInfoForm.getAddress());
//        	queryEntity.setAddress(address);
        	
        	ColBean<String> name = new ColBean("name",ColBean.STRING);
        	name.setValue(deptInfoForm.getName());
        	queryEntity.setName(name);
        	
        	ColBean<String> code = new ColBean("code",ColBean.STRING);
        	code.setValue(deptInfoForm.getCode());
        	queryEntity.setCode(code);

//        	ColBean<String> jgbm = new ColBean("jgbm",ColBean.STRING);
//        	jgbm.setValue(deptInfoForm.getJgbm());
//        	queryEntity.setJgbm(jgbm);
        	
//        	ColBean<String> rank = new ColBean("rank",ColBean.STRING);
//        	rank.setValue(deptInfoForm.getRank());
//        	queryEntity.setRank(rank);
//        	
//        	ColBean<String> manager = new ColBean("manager",ColBean.STRING);
//        	manager.setValue(deptInfoForm.getManager());
//        	queryEntity.setManager(manager);
        	
        	ColBean<Integer> upperDept = new ColBean("upperDept",ColBean.INTEGER);
        	upperDept.setValue(deptInfoForm.getUpperDept());
        	queryEntity.setUpperDept(upperDept);
        	
    	}
    	
    	
       // DeptEntity queryEntity = deptForm.getDeptEntity();
        queryEntity.setPages(new Pages(page, limit));
        /* 修改机构管理查询时显示全部上级机构的bug add by luoj start*/
        UserSession us = getUserSession();
        if(us != null && us.getDeptId() != null){
        	ColBean<Integer> colDeptId = new ColBean("deptId",ColBean.INTEGER);
        	colDeptId.setValue(getUserSession().getDeptId());
        	queryEntity.setDeptId(colDeptId);
        }
        /* 修改机构管理查询时显示全部上级机构的bug add by luoj end*/
        List<DeptEntity> queryResultList = deptService.queryDeptList(queryEntity);
        if (queryResultList.size() == 0)
        {
            ajaxMessagesJson.setMessage("NIL", "没有查询到任何记录");
			pageList = (PageList)queryResultList;
			pageList.getPages().setTotal(0);
        }
        else
        {
            pageList = new PageList();
            pageList.setPages(((PageList) queryResultList).getPages());
            pageList.addAll(queryResultList);
        }
        return RESULT_LIST;
    }

    public String view()
    {
    	deptInfoForm.setEditFlag(false);
        DeptEntity queryEntity = deptService.findDeptById(deptInfoForm.getDeptId());
        deptInfoForm.setDeptEntity(queryEntity);
        if (queryEntity == null)
        {
            ajaxMessagesJson.setMessage("FAILED", "查询用户失败");
        }
        return RESULT_VIEW;
    }

    /**
	 * 生成树形组织结构
	 */
	public String getZTree(){
		Integer deptId = 1;
		
		if(this.getId() != null){
			deptId = this.getId();
		}else {
			if(getUserSession() != null){
				deptId = getUserSession().getDeptId();
			}
		}
		
		ZNode znode = generateZTree(deptId);//获得下级部门列表(仅包含下级部门)
		
		Map resultMap = new HashMap();
		resultMap.put("status", "success");
		resultMap.put("znode", znode);
		
		JSON json = JSONSerializer.toJSON(resultMap);
		this.setJsonToString(json.toString());
		System.out.println("(生成树形组织结构)"+json.toString());
		
		return RESULT_JSONSTRING;
	}
	
	/**
	 * 生成ZTree
	 */
	private ZNode generateZTree(Integer parentDeptId){
		if(parentDeptId == null)
			return null;
		
		DeptInfo parentDept = deptService.findDeptInfoById(parentDeptId);
		if(parentDept == null)
			return null;
		
		ZNode parentNode = new ZNode();
		parentNode.setName(parentDept.getName());
		parentNode.setId(parentDept.getId());
		parentNode.setLatitude(parentDept.getLatitude());
		parentNode.setLongitude(parentDept.getLongitude());
	
		List<DeptInfo> childDeptList = deptService.getLowerDeptById(parentDeptId);//获得下级部门列表(仅包含下级部门)
		
		if(null !=childDeptList && !childDeptList.isEmpty()){
			parentNode.setIsParent(true);
			parentNode.setOpen(true);
			
			for(int i=0;i<childDeptList.size();i++){
				DeptInfo childDept = childDeptList.get(i);
				ZNode childNode = generateZTree(childDept.getId());
				parentNode.getChildren().add(childNode);
			}
		}else{
			parentNode.setIsParent(false);
		}
		
		return parentNode;
		
	}
	
    public String edit()
    {
    	deptInfoForm.setDomainList(domainService.getDomainSelectList());
    	
    	
        deptInfoForm.setEditFlag(true);
        if (deptInfoForm.getDeptId() != null)
        {//修改
            DeptEntity queryEntity = deptService.findDeptById(deptInfoForm.getDeptId());
            deptInfoForm.setDeptEntity(queryEntity);
            if (queryEntity == null)
            {
                ajaxMessagesJson.setMessage("FAILED", "查询用户失败");
            }
            if(getUserSession().getDeptId().equals(deptInfoForm.getDeptId())){
            	DeptEntity updept = deptService.findDeptById(queryEntity.getUpperDept().getValue());
            	if (updept != null) {
                    Entry entry = new Entry(updept.getDeptId().getStringValue(), updept.getName().getValue());
                    List deptList = new ArrayList();
                    deptList.add(entry);
                    this.deptInfoForm.setDeptList(deptList);
                } else {
                    this.deptInfoForm.setDeptList(this.deptService.getDeptSelectListWithoutLower(getUserSession().getDeptId(), this.deptInfoForm.getDeptId()));
                }
            } else {
            	deptInfoForm.setDeptList(deptService.getDeptSelectListWithoutLower(getUserSession().getDeptId(), deptInfoForm.getDeptId()));
            }
            
            
        }
        else
        {//新增
        	if(deptForm != null){//添加时默认选择当前上级部门
        		ColBean<Integer> colDeptId = new ColBean("upper_dept",ColBean.INTEGER);
            	colDeptId.setValue(deptForm.getDeptId());
            	
            	DeptEntity entity = new DeptEntity();
            	entity.setUpperDept(colDeptId);
                deptInfoForm.setDeptEntity(entity);
        	}
            deptInfoForm.setDeptList(deptService.getDeptSelectList(getUserSession().getDeptId()));
        }
        return RESULT_VIEW;
    }

    public String save()
    {
        try
        {
            deptService.saveDept(deptInfoForm.getDeptEntity(),getUserSession());
            ajaxMessagesJson.setMessage("0", "保存成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ajaxMessagesJson.setMessage("FAILED", "保存失败");
        }
        return RESULT_AJAXJSON;
    }

    public String delete()
    {
        try
        {
            //deptService.deleteDeptById(deptForm.getDeptEntity().getId().getValues());
        	
        	List<Integer> idList = new ArrayList();
        	idList.add(this.getId());
        	deptService.deleteDeptById(idList);
        	
            ajaxMessagesJson.setMessage("0", "删除成功");
        }
        catch (IctException e)
        {
            e.printStackTrace();
            ajaxMessagesJson.setMessage("FAILED", "删除失败");
        }
        return RESULT_AJAXJSON;
    }

	public DeptForm getDeptForm() {
		return deptForm;
	}

	public void setDeptForm(DeptForm deptForm) {
		this.deptForm = deptForm;
	}

}
