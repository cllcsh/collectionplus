package com.osource.module.admin.web.action;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.web.action.BaseAction;
import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.module.admin.model.LoginStaBean;
import com.osource.module.admin.service.LoginStaService;
import com.osource.module.admin.web.form.LoginStaForm;
import com.osource.util.IctUtil;

/**
 * 
 * 项目名称：osource 类名称：LoginStaAction 类描述： 创建人：zhangyan 创建时间：Nov 4, 2009 5:43:06 PM 修改人：Administrator 修改时间：Nov 4, 2009
 * 5:43:06 PM 修改备注：
 * 
 * @version
 * 
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class LoginStaAction extends BaseAction {

    public static final String RESULT_LIST = "list";
    public static final String RESULT_SET = "set";
    public static final String RESULT_INIT = "init";

    @Autowired
    private LoginStaService loginStaService;

    private LoginStaForm loginStaForm;
    private String userId;
    private String actionName;

    public LoginStaAction() {
        super();
    }

    /**
     * 用户管理
     * 
     * @return
     */
    public String init() {
        return RESULT_INIT;
    }

    /**
     * 查询用户列表
     * 
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public String query() throws IctException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        LoginStaBean loginStaBean = new LoginStaBean();
        IctUtil.copyProperties(loginStaBean, loginStaForm);
        // if(loginStaBean.getDeptId() == null)
        // loginStaBean.setDeptId(getUserSession().getDeptId());

        Pages pages = new Pages(this.getPage(), this.getLimit());
        this.setPageList(loginStaService.findLoginStaListByCondition(loginStaBean, pages));
        return RESULT_LIST;
    }

    public void export() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Map condition = new HashMap();

        condition.put("loginName", loginStaForm.getLoginName());
        condition.put("loginResult", loginStaForm.getLoginResult());
        condition.put("loginIp", loginStaForm.getLoginIp());
        condition.put("loginFirDate", loginStaForm.getLoginFirDate());
        condition.put("loginEndDate", loginStaForm.getLoginEndDate());
        condition.put("deptId", getUserSession().getDeptId());

        List<LoginStaBean> exportList = loginStaService.exportQuery(condition);

        String workSheet = "定位时长统计列表";// 输出的excel文件工作表名
        String fileName = "export.xls";
        String[] title = { "账号", "IP", "姓名", "司法单位", "登陆时间", "登陆地址", "登陆时长", "状态" };
        Integer[] width = { 15, 15, 15, 25, 25, 15, 15, 15 };

        LoginStaBean bean = null;

        String ss[] = null;
        List<String[]> list = new ArrayList<String[]>();
        for (int i = 0; i < exportList.size(); i++) {
            bean = exportList.get(i);
            ss = new String[8];
            ss[0] = bean.getLoginName();
            ss[1] = bean.getLoginIp();
            ss[2] = bean.getUserName();
            ss[3] = bean.getDeptName();
            ss[4] = String.valueOf(sdf.format(bean.getLoginDate()));
            ss[5] = bean.getLoginAddr();
            ss[6] = bean.getOnlineTime();
            ss[7] = bean.getLoginResult();
            list.add(ss);
        }

        exportExcel(workSheet, fileName, title, width, list);
    }

    public void exportExcel(String workSheet, String fileName, String[] title, Integer[] width, List<String[]> list) {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel;chartset=utf-8");
        response.addHeader("Content-Disposition", "attachment; filename=" + fileName);

        OutputStream os = null;
        WritableWorkbook workbook = null;
        try {
            os = response.getOutputStream();
            workbook = Workbook.createWorkbook(os);
            WritableSheet sheet = workbook.createSheet(workSheet, 0); // 添加第一个工作表
            jxl.write.Label label;
            sheet.mergeCells(0, 0, title.length - 1, 1);// 左上角到右下角
            jxl.write.WritableFont wf = new jxl.write.WritableFont(WritableFont.ARIAL, 18, WritableFont.BOLD, false);
            jxl.write.WritableCellFormat wcfF = new jxl.write.WritableCellFormat(wf);
            wcfF.setAlignment(jxl.format.Alignment.CENTRE);// 把水平对齐方式指定为居中
            sheet.addCell(new Label(0, 0, workSheet, wcfF));
            for (int i = 0; i < title.length; i++) {
                // Label(列号,行号 ,内容 )
                label = new jxl.write.Label(i, 2, title[i]); // put the title
                sheet.addCell(label);
                sheet.setColumnView(i, width[i]);
            }

            for (int i = 0; i < list.size(); i++) {
                String[] ss = list.get(i);
                for (int j = 0; j < ss.length; j++) {
                    sheet.addCell(new Label(j, i + 3, ss[j]));
                }
            }
            workbook.write();
            workbook.close();
            try {
                if (os != null) {
                    os.flush();
                    os.close();
                    os = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除用户
     * 
     * @return
     * @throws IctException
     */
    public String deletes() throws IctException {

        loginStaService.deleteLoginStaByUserId(getUserId());
        this.getAjaxMessagesJson().setMessage("0", "删除成功");

        return RESULT_AJAXJSON;
    }

    /* getter and setter */

    public LoginStaService getLoginStaService() {
        return loginStaService;
    }

    public void setLoginStaService(LoginStaService loginStaService) {
        this.loginStaService = loginStaService;
    }

    public LoginStaForm getLoginStaForm() {
        return loginStaForm;
    }

    public void setLoginStaForm(LoginStaForm loginStaForm) {
        this.loginStaForm = loginStaForm;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

}
