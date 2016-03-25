package com.osource.module.system.web.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.web.action.BaseAction;
import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.module.system.model.NoticeInfo;
import com.osource.module.system.service.NoticeService;
import com.osource.module.system.web.form.NoticeForm;
import com.osource.util.IctUtil;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class NoticeAction extends BaseAction {

    @Autowired
    private NoticeService noticeService;

    private NoticeForm noticeForm;

    /** action methods **/

    public NoticeAction() {
        super();
    }

    /**
     * 功能初始页面跳转
     */
    public String init() {

        return RESULT_INIT;
    }

    /**
     * 根据查询条件进行查询
     */
    public String query() {
        Map condition = new HashMap();

        if (noticeForm == null) {
            noticeForm = new NoticeForm();
        }
        condition.put("title", noticeForm.getTitle());

        Pages pages = new Pages(this.getPage(), this.getLimit());
        this.setPageList(noticeService.findByCondition(condition, pages));

        return RESULT_LIST;
    }

    /**
     * 跳转到添加通知消息信息页面
     */
    public String add() {
        this.setActionName("notice_save");
        return RESULT_SET;
    }

    /**
     * 添加通知消息信息信息
     */
    public String save() {
        NoticeInfo noticeInfo = new NoticeInfo();
        try {
            if (noticeForm != null)
                IctUtil.copyProperties(noticeInfo, noticeForm);
            noticeInfo.setInsertId(getUserSession().getUserId());
            noticeService.save(noticeInfo);
            this.getAjaxMessagesJson().setMessage("0", "添加通知消息信息成功");
            logger.debug("添加通知消息信息成功");
        } catch (Exception e) {
            this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加通知消息信息失败");
            logger.debug(e);
        }

        return RESULT_AJAXJSON;
    }

    /**
     * 跳转到编辑通知消息信息页面
     */
    public String edit() throws IctException {
        NoticeInfo noticeInfo;
        noticeInfo = noticeService.findById(this.getId());

        NoticeForm noticeForm = new NoticeForm();
        IctUtil.copyProperties(noticeForm, noticeInfo);

        this.setNoticeForm(noticeForm);

        return RESULT_SET;
    }

    /**
     * 跳转到查看通知消息信息页面
     */
    public String view() throws IctException {
        NoticeInfo noticeInfo;
        noticeInfo = noticeService.findById(Integer.valueOf(this.getId()));

        NoticeForm noticeForm = new NoticeForm();
        IctUtil.copyProperties(noticeForm, noticeInfo);

        this.setNoticeForm(noticeForm);

        return RESULT_VIEW;
    }

    /**
     * 修改通知消息信息信息
     */
    public String update() {
        NoticeInfo noticeInfo = new NoticeInfo();
        String ckeditor1 = request.getParameter("ckeditor1");
        noticeInfo.setContent(ckeditor1);
        try {
            IctUtil.copyProperties(noticeInfo, noticeForm);
            noticeInfo.setUpdateId(getUserSession().getUserId());

            noticeService.update(noticeInfo);
            this.getAjaxMessagesJson().setMessage("0", "修改通知消息信息成功");
            logger.debug("修改通知消息信息成功");
        } catch (Exception e) {
            this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改通知消息信息失败");
            logger.debug(e);
        }

        return RESULT_AJAXJSON;
    }

    /**
     * 删除会员信息信息
     */
    public String deletes() {
        try {
            noticeService.deleteById(this.getIds());
            this.getAjaxMessagesJson().setMessage("0", "删除成功");
            logger.debug("删除成功");
        } catch (Exception e) {
            this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
            logger.debug(e);
        }
        return RESULT_AJAXJSON;
    }

    /** getter and setter methods **/

    public NoticeService getNoticeService() {
        return noticeService;
    }

    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    public NoticeForm getNoticeForm() {
        return noticeForm;
    }

    public void setNoticeForm(NoticeForm noticeForm) {
        this.noticeForm = noticeForm;
    }

}