package com.front.fa.action;

import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.qiniu.http.Response;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.express.database.DBManager;
import org.express.portal.Mapping;
import org.express.portal.MultipartHttpServletRequest;
import org.express.portal.PathUtil;
import org.express.portal.renderer.Renderer;
import org.express.portal.renderer.TemplateRenderer;
import org.express.util.QiniuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.front.cache.SysConfigCache;
import com.front.db.bean.CollectionBean;
import com.front.db.bean.CollectionCategoryBean;
import com.front.db.bean.CollectionImagesBean;
import com.front.db.bean.CollectionPeriodBean;
import com.front.db.bean.DynamicBean;
import com.front.db.bean.DynamicImagesBean;
import com.front.db.bean.FavUserBean;
import com.front.fa.service.FaService;
import com.front.web.common.Constant;
import com.front.web.common.base.GenericPageAction;
import com.front.web.util.IdCreaterTool;

public class FaAction extends GenericPageAction {
    private final static Logger logger = LoggerFactory.getLogger(FaAction.class);

    @Mapping("/faProduct")
    public Renderer goFaProduct() {
        Map<String, Object> out = getOutputMap();
        List<CollectionCategoryBean> collectionCategoryList = SysConfigCache.getCollectionCategorys();
        List<CollectionPeriodBean> collectionPeriodList = SysConfigCache.getCollectionPeriods();
        out.put("categoryList", collectionCategoryList);
        out.put("periodList", collectionPeriodList);
        return new TemplateRenderer("/html/fa/product.html", Constant.DEFAULT_MODULE_STRING, out);
    }

    @Mapping("/addCollection")
    public Renderer addCollection() {
        Map<String, Object> out = getOutputMap();
        FavUserBean fuBean = getSessionUser();
        List<Object> ImagesBeanList = new ArrayList<Object>();

        CollectionImagesBean ciBean = null;
        int displayOrder = 0;
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) getRequest();
        String title = multipartRequest.getParameter("title");
        CollectionBean cBean = new CollectionBean();
        BigDecimal collectionId = IdCreaterTool.getCollectionId();
        cBean.setId(collectionId);
        cBean.setTitle(title);
        String categoryId = multipartRequest.getParameter("categoryId");
        cBean.setCategory_id(new BigDecimal(categoryId));
        String periodId = multipartRequest.getParameter("periodId");
        cBean.setCollection_period_id(new BigDecimal(periodId));
        String introduction = multipartRequest.getParameter("introduction");
        cBean.setIntroduction(introduction);
        String isSendRacket = "Y".equalsIgnoreCase(multipartRequest.getParameter("isSendRacket")) ? "Y" : "N";
        cBean.setIs_send_racket(isSendRacket);
        String isSold = "Y".equalsIgnoreCase(multipartRequest.getParameter("isSold")) ? "Y" : "N";
        cBean.setIs_sold(isSold);
        cBean.setInsert_id(fuBean.getId());
        cBean.setUpdate_id(fuBean.getId());
        BigDecimal defaultId = new BigDecimal(0);
        cBean.setLabel_id(defaultId);
        cBean.setAuction_id(defaultId);
        cBean.setAppraisal_user_id(defaultId);
        cBean.setTransaction_user_id(defaultId);
        //上传图片集合
        List<FileItem> fileItems = multipartRequest.getUpFileData();
        for (FileItem fileItem : fileItems) {
            String fileName = fileItem.getName();
            if (StringUtils.isBlank(fileName)) {
                continue;
            }
            String uuid = "cln_" + UUID.randomUUID().toString(); // 重命名文件
//			File file = new File(PathUtil.getUploadImgPath()).getAbsoluteFile();
//			if (!file.exists()) {
//				file.mkdirs();
//			}
            int pos = fileName.lastIndexOf(".");// 取图片文件格式
            if (pos > 0) {
                fileName = fileName.substring(pos);
            }
            fileName = uuid + fileName;
//			String filepathString = file.getPath() + "/" + uuid  + fileName;
//			String imgUrl = PathUtil.getUploadImgFileName() + "/" + uuid  + fileName;
            try {
                // 写到磁盘
//				fileItem.write(new File(filepathString));

                Response response = QiniuUtil.upload(IOUtils.toByteArray(fileItem.getInputStream()), fileName);
                if(response != null){
                    String imgUrl = fileName;
                    ciBean = new CollectionImagesBean();
                    ciBean.setId(IdCreaterTool.getCollectionImagesId());
                    if (displayOrder == 0) {
                        cBean.setIcon_img(imgUrl);
                    }
                    ciBean.setDisplay_order(displayOrder);
                    displayOrder++;
                    ciBean.setCollection_id(collectionId);
                    ciBean.setInsert_id(fuBean.getId());
                    ciBean.setUpdate_id(fuBean.getId());
                    ciBean.setImage_url(imgUrl);
                    ImagesBeanList.add(ciBean);
                }
            } catch (Exception e) {
                logger.error("addCollection write file [" + fileItem.getName() + "]error", e);
            }
        }
        boolean result = false;
        try {
            result = FaService.saveCollection(cBean, ImagesBeanList);
        } catch (Exception e) {
            logger.error("addCollection", e);
            try {
                if (DBManager.isTrans()) {
                    DBManager.rollback();
                }
            } catch (SQLException e1) {
                logger.error("addCollection handleExecution", e1);
            }
        }
        String msg = "发布藏品成功";
        if (!result) {
            msg = "发布藏品失败";
        }
        out.put("isShow", true);
        out.put("msg", msg);
        out.put("result", result);
        return new TemplateRenderer("/html/fa/product.html", Constant.DEFAULT_MODULE_STRING, out);
    }

    @Mapping("/releaseDynamic")
    public Renderer releaseDynamic() {
        Map<String, Object> out = getOutputMap();
        return new TemplateRenderer("/html/fa/dynamic.html", Constant.DEFAULT_MODULE_STRING, out);
    }


    @Mapping("/addDynamic")
    public Renderer addDynamic() {
        Map<String, Object> out = getOutputMap();
        FavUserBean fuBean = getSessionUser();
        List<Object> ImagesBeanList = new ArrayList<Object>();

        DynamicImagesBean diBean = null;
        int displayOrder = 0;
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) getRequest();
        String dynamicContent = multipartRequest.getParameter("content");
        DynamicBean dBean = new DynamicBean();
        BigDecimal dynamicId = IdCreaterTool.getDynamicId();
        dBean.setId(dynamicId);
        dBean.setDynamic_content(dynamicContent);
        dBean.setRelease_by(fuBean.getId());
        dBean.setRelease_time(new Date());
        dBean.setInsert_id(fuBean.getId());
        dBean.setUpdate_id(fuBean.getId());
        //上传图片集合
        List<FileItem> fileItems = multipartRequest.getUpFileData();
        for (FileItem fileItem : fileItems) {
            String fileName = fileItem.getName();
            if (StringUtils.isBlank(fileName)) {
                continue;
            }
            String uuid = "dyc_" + UUID.randomUUID().toString(); // 重命名文件
//            File file = new File(PathUtil.getUploadImgPath()).getAbsoluteFile();
//            if (!file.exists()) {
//                file.mkdirs();
//            }
            int pos = fileName.lastIndexOf(".");// 取图片文件格式
            if (pos > 0) {
                fileName = fileName.substring(pos);
            }
            fileName = uuid + fileName;
//            String filepathString = file.getPath() + "/" + uuid + fileName;
//            String imgUrl = PathUtil.getUploadImgFileName() + "/" + uuid + fileName;
            try {
                // 写到磁盘
//                fileItem.write(new File(filepathString));
                Response response = QiniuUtil.upload(IOUtils.toByteArray(fileItem.getInputStream()), fileName);
                if(response != null) {
                    String imgUrl = fileName;
                    diBean = new DynamicImagesBean();
                    diBean.setId(IdCreaterTool.getDynamicimagesId());
                    diBean.setDisplay_order(displayOrder);
                    displayOrder++;
                    diBean.setDynamic_id(dynamicId);
                    diBean.setInsert_id(fuBean.getId());
                    diBean.setUpdate_id(fuBean.getId());
                    diBean.setImage(imgUrl);
                    ImagesBeanList.add(diBean);
                }
            } catch (Exception e) {
                logger.error("addDynamic write file [" + fileItem.getName() + "]error", e);
            }
        }
        boolean result = false;
        try {
            result = FaService.saveDynamic(dBean, ImagesBeanList);
        } catch (Exception e) {
            logger.error("addDynamic error", e);
            try {
                if (DBManager.isTrans()) {
                    DBManager.rollback();
                }
            } catch (SQLException e1) {
                logger.error("addDynamic handleExecution", e1);
            }
        }
        String msg = "发布个人动态成功";
        if (!result) {
            msg = "发布个人动态失败";
        }
        out.put("isShow", true);
        out.put("msg", msg);
        out.put("result", result);
        return new TemplateRenderer("/html/fa/dynamic.html", Constant.DEFAULT_MODULE_STRING, out);
    }
}
