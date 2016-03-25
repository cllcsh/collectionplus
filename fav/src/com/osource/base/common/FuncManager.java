/**
 * @author luoj
 * @create 2009-6-24
 * @file FuncManager.java
 * @since v0.1
 * 
 */
package com.osource.base.common;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osource.base.common.menu.FuncNode;
import com.osource.base.dao.PermissionDao;

/**
 * @author luoj
 * 
 */
@SuppressWarnings( { "serial", "unchecked" })
@Component
public class FuncManager implements Serializable {

    private static final Log logger = LogFactory.getLog(FuncManager.class);

    @Autowired
    private PermissionDao permissionDao;

    private FuncBindingManager funcBinding;

    private List<FuncNode> funcs = new ArrayList();

    private Map<Integer, FuncNode> funcMap = new HashMap();

    private Map<String, FuncNode> funcLinkMap = new HashMap();

    private Map<String, FuncNode> whiteMap = new HashMap();

    @PostConstruct
    public boolean init() {
        initAllFuns();
        return true;
    }

    private void initAllFuns() {
        logger.info("初始化所有功能...");
        funcs = permissionDao.getAllFunc();
        List<FuncNode> bindingList = new ArrayList();
        List<FuncNode> whiteList = new ArrayList();
        for (FuncNode func : funcs) {
            funcMap.put(func.getId(), func);
            funcLinkMap.put(func.getLink(), func);
            logger.debug("功能:" + func.getLink());
            if (func.getBinding() != null) {
                if (func.getBinding().equals("3")) {
                    bindingList.add(func);
                } else if (func.getBinding().equals("4")) {
                    whiteList.add(func);
                }
            }
        }
        Iterator<FuncNode> it = funcMap.values().iterator();
        while (it.hasNext()) {
            FuncNode funcNode = it.next();
            funcNode.setParent(funcMap.get(funcNode.getFrontFuncId()));
        }
        logger.info("初始化所有功能完成");
        initBindingRelation(bindingList);
        initWhiteList(whiteList);
    }

    /**
     * 初始化绑定关系
     * 
     * @param bindingList
     */
    private void initBindingRelation(List<FuncNode> bindingList) {
        logger.info("初始化功能绑定关系...");
        funcBinding = new FuncBindingManager();
        for (FuncNode funcNode : bindingList) {
            FuncNode _pNode = funcMap.get(funcNode.getFrontFuncId());
            if (_pNode != null) {
                funcBinding.regedit(funcNode.getLink(), funcMap.get(funcNode.getFrontFuncId()).getLink());
            } else {
                logger.error(MessageFormat.format("没有找到上级节点 {0} ; {1}绑定失败", funcNode.getFrontFuncId(), funcNode
                        .getLink()));
            }
        }
        logger.info("初始化功能绑定关系完成");
    }

    /**
     * 初始化功能白名单
     * 
     * @param bindingList
     */
    private void initWhiteList(List<FuncNode> whiteList) {
        logger.info("初始化功能白名单...");
        for (FuncNode funcNode : whiteList) {
            whiteMap.put(funcNode.getLink(), funcNode);
            logger.debug("功能白名单:" + funcNode.getLink());
        }
        logger.info("初始化功能白名单完成");
    }

    public String getBindingLink(String link) {
        return funcBinding.getBinding(link);
    }

    public boolean isInWhiteList(String link) {
        if (whiteMap.containsKey(link)) {
            return true;
        }
        return false;
    }

    /**
     * 获取模块菜单
     * 
     * @return
     */
    public List<FuncNode> getFuncs(String userType) {
        List<FuncNode> result = new ArrayList();
        for (FuncNode funcNode : funcs) {
            if (Integer.valueOf(funcNode.getUserType()) >= Integer.valueOf(userType)) {
                result.add(funcNode);
            }
        }
        return result;
    }

    public FuncNode getFuncNodeByLink(String link) {
        return funcLinkMap.containsKey(link) ? funcLinkMap.get(link) : null;
    }

    public Set<Integer> getParents(Integer funcId, Set set) {
        FuncNode funcNode = funcMap.get(funcId);
        if (funcNode != null && funcNode.getFrontFuncId() != null) {
            set.add(funcNode.getFrontFuncId());
            return getParents(funcNode.getFrontFuncId(), set);
        } else {
            return set;
        }
    }

    public List<Integer> getFuncsAndParents(List list) {
        Set<Integer> set = new HashSet();
        for (Object obj : list) {
            Integer funcId;
            if (obj instanceof Integer) {
                funcId = (Integer) obj;
            } else if (obj instanceof String) {
                funcId = Integer.valueOf((String) obj);
            } else {
                funcId = Integer.valueOf(obj.toString());
            }
            if (funcMap.containsKey(funcId)) {
                set.add(funcId);
                getParents(funcId, set);
            }
        }
        List<Integer> result = new ArrayList();
        result.addAll(set);
        return result;
    }

    public void reloadData() {
        funcs.clear();
        funcMap.clear();
        funcLinkMap.clear();
        whiteMap.clear();
        init();
        logger.info("重新加载成功！");
    }

    public PermissionDao getPermissionDao() {
        return permissionDao;
    }

    public void setPermissionDao(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

}
