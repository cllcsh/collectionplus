package com.osource.base.form;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.osource.base.form.model.FormTable;
import com.osource.base.form.util.Struts2Util;
import com.osource.base.form.util.ZIPUtil;

/**
 * 模块生成器
 * 
 * <p>
 * 用于生成JEE模块。
 * <p>
 * 包括JAVA类：action,dao,dao.impl,service,service.impl；
 * 配置文件：action配置,spring配置；ftl页面：list.html,add.html,edit.html。
 * 数据验证文件：Act-Com_save
 * -validation.xml,Act-Com_edit-validation.xml,Act-Com_update-validation.xml
 * <p>
 * 可设置的参数有：模块实体类名、java类包地址、配置文件地址、ftl页面地址。
 * 
 * @author liufang
 * 2009-12-22, modified by luoj,lifa.
 * 
 */
public class ModuleGenerator {
	private static final Log log = LogFactory.getLog(ModuleGenerator.class);
	public static final String SPT = File.separator;

	private Properties prop = new Properties();
	private String packName;
	private String fileName;

	private String basePagePath = "WebContent/WEB-INF/";
	
	/* file */
	private File daoImplFile;
	private File daoFile;
	private File serviceFile;
	private File serviceImplFile;
	private File actionFile;
	private File configFile;
	private File formFile;
	private File beanFile;
	
	private File springFile;
	private File strutsFile;
	
	private File pageMgrFile;
	private File pageListFile;
	private File pageViewFile;
	private File pageSetFile;//添加修改共用一个页面
	
	private File jsFile;
	
	private File vldSaveFile;
	private File vldEditFile;
	private File vldUpdateFile;

	/* Tpl file */
	private File daoImplTpl;
	private File daoTpl;
	private File serviceTpl;
	private File serviceImplTpl;
	private File actionTpl;
	private File configTpl;
	private File formTpl;
	private File beanTpl;
	
	private File springTpl;
	private File strutsTpl;
	
	private File pageMgrTpl;
	private File pageListTpl;
	private File pageViewTpl;
	private File pageSetTpl;
	
	private File jsTpl;
	
	private File vldSaveTpl;
	private File vldEditTpl;
	private File vldUpdateTpl;
	
	private FormTable formTable;
	String tempFilePath = "";

	public ModuleGenerator(String packName, String fileName) {
		this.packName = packName;
		this.fileName = fileName;
	}

	public ModuleGenerator(String packName, String fileName, String basePagePath) {
		this.packName = packName;
		this.fileName = fileName;
		this.basePagePath = basePagePath;
	}
	
	public ModuleGenerator(String packName, String fileName, String basePagePath, FormTable formTable) {
		this.packName = packName;
		this.fileName = fileName;
		this.basePagePath = basePagePath;
		this.formTable = formTable;
	}
	
	@SuppressWarnings("unchecked")
	private void loadProperties() {
		try {
			log.debug("packName=" + packName);
			log.debug("fileName=" + fileName);
			FileInputStream fileInput = new FileInputStream(getTemplateFilePath(
					packName, fileName));
			prop.load(fileInput);
			
			loadFromTableInfo(formTable);
			
			String entity_BeanUp = prop.getProperty("Entity_Bean");
			log.debug("entity_BeanUp:" + entity_BeanUp);
			if (entity_BeanUp == null || entity_BeanUp.trim().equals("")) {
				log.warn("Entity_Bean not specified, exit!");
				return;
			}
			String entity_BeanLow = entity_BeanUp.substring(0, 1).toLowerCase()
					+ entity_BeanUp.substring(1);
			log.debug("entity_BeanLow:" + entity_BeanLow);
			
			String entityUp = prop.getProperty("Entity");
			log.debug("entityUp:" + entityUp);
			if (entityUp == null || entityUp.trim().equals("")) {
				log.warn("Entity not specified, exit!");
				return;
			}
			String entityLow = entityUp.substring(0, 1).toLowerCase()
					+ entityUp.substring(1);
			log.debug("entityLow:" + entityLow);
			
			prop.put("entity_Bean", entity_BeanLow);
			prop.put("entity", entityLow);
			
			prop.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			
			if (log.isDebugEnabled()) {
				Set ps = prop.keySet();
				for (Object o : ps) {
					log.debug(o + "=" + prop.get(o));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void prepareFile() {
		String daoImplFilePath = getFilePath(prop.getProperty("dao_impl_p"),
				prop.getProperty("Entity") + "DaoImpl.java");
		daoImplFile = new File(daoImplFilePath);
		log.debug("daoImplFile:" + daoImplFile.getAbsolutePath());

		String daoFilePath = getFilePath(prop.getProperty("dao_p"), prop
				.getProperty("Entity")+ "Dao.java");
		daoFile = new File(daoFilePath);
		log.debug("daoFile:" + daoFile.getAbsolutePath());

		String serviceFilePath = getFilePath(prop.getProperty("service_p"),
				prop.getProperty("Entity") + "Service.java");
		serviceFile = new File(serviceFilePath);
		log.debug("serviceFile:" + serviceFile.getAbsolutePath());

		String serviceImplFilePath = getFilePath(prop.getProperty("service_impl_p"), 
				prop.getProperty("Entity")+ "ServiceImpl.java");
		serviceImplFile = new File(serviceImplFilePath);
		log.debug("serviceImplFile:" + serviceImplFile.getAbsolutePath());
		
		String isActionAbstract = prop.getProperty("is_action_abstract");
		String abs = "";
		if (isActionAbstract != null && isActionAbstract.equals("true")) {
			abs = "Abstract";
		}
		String actionFilePath = getFilePath(prop.getProperty("action_p"), 
				prop.getProperty("Entity")+ abs + "Action.java");
		actionFile = new File(actionFilePath);
		log.debug("actionFile:" + actionFile.getAbsolutePath());
		
		String formFilePath = getFilePath(prop.getProperty("form_p"), 
				prop.getProperty("Entity")+ "Form.java");
		formFile = new File(formFilePath);
		log.debug("formFile:" + formFile.getAbsolutePath());
		
		String beanFilePath = getFilePath(prop.getProperty("entity_p"), 
				prop.getProperty("Entity_Bean")+ ".java");
		beanFile = new File(beanFilePath);
		log.debug("beanFile:" + beanFile.getAbsolutePath());
		
		String configFilePath = getFilePath(prop.getProperty("config_p"), 
				prop.getProperty("entity")+ "-sqlmap.xml");
		configFile = new File(configFilePath);
		log.debug("configFile:" + configFile.getAbsolutePath());

		String vldSaveFilePath = getFilePath(prop.getProperty("action_p"), 
				prop.getProperty("Entity")+ "Act-Com_save-validation.xml");
		vldSaveFile = new File(vldSaveFilePath);
		log.debug("vldSaveFile:" + vldSaveFile.getAbsolutePath());

		String vldEditFilePath = getFilePath(prop.getProperty("action_p"), 
				prop.getProperty("Entity")+ "Act-Com_edit-validation.xml");
		vldEditFile = new File(vldEditFilePath);
		log.debug("vldEditFile:" + vldEditFile.getAbsolutePath());

		String vldUpdateFilePath = getFilePath(prop.getProperty("action_p"),
				prop.getProperty("Entity") + "Act-Com_update-validation.xml");
		vldUpdateFile = new File(vldUpdateFilePath);
		log.debug("vldUpdateFile:" + vldUpdateFile.getAbsolutePath());

		String springFilePath = tempFilePath + "/" + prop.getProperty("spring_file");
		springFile = new File(springFilePath);
		log.debug("springFile:" + springFile.getAbsolutePath());

		String strutsFilePath = tempFilePath + "/" + prop.getProperty("struts_file");
		strutsFile = new File(strutsFilePath);
		if(!strutsFile.exists()){
			try {
//				FileUtils.copyFile(new File(getFilePath(prop.getProperty("template_dir"), "struts-module.txt")), strutsFile);
				FileUtils.writeStringToFile(strutsFile, readTpl(new File(getTemplateFilePath(prop.getProperty("template_dir"), "struts-module.txt"))));
			} catch (IOException e) {
				log.debug("复制文件" + strutsFile + "失败");
				e.printStackTrace();
			}
		}
		log.debug("strutsFile:" + strutsFile.getAbsolutePath());
		
		if (isActionAbstract != null && isActionAbstract.equals("true")) {
			abs = "/abstract";
		}
		String pagePath = tempFilePath + "/" + basePagePath + prop.getProperty("module_path") + abs ;
		
		pageMgrFile = new File(pagePath + "/jsp/" + prop.getProperty("entity") + "Mgr.jsp");
		log.debug("pageMgrFile:" + pageMgrFile.getAbsolutePath());
		
		pageListFile = new File(pagePath + "/jsp/" +  prop.getProperty("entity") + "List.jsp");
		log.debug("pageListFile:" + pageListFile.getAbsolutePath());
		
		pageSetFile = new File(pagePath + "/jsp/" + prop.getProperty("entity") + "Set.jsp");
		log.debug("pageSetFile:" + pageSetFile.getAbsolutePath());
		
		pageViewFile = new File(pagePath + "/jsp/" + prop.getProperty("entity") + "View.jsp");
		log.debug("pageViewFile:" + pageViewFile.getAbsolutePath());
		
		jsFile = new File(pagePath + "/js/" + prop.getProperty("entity") + "Mgr.js");
		log.debug("jsFile:" + jsFile.getAbsolutePath());
	}

	private void prepareTemplate() {
		String tplPack = prop.getProperty("template_dir");
		log.debug("tplPack:" + tplPack);
		
		daoImplTpl = new File(getTemplateFilePath(tplPack, "dao_impl.txt"));
		daoTpl = new File(getTemplateFilePath(tplPack, "dao.txt"));
		serviceImplTpl = new File(getTemplateFilePath(tplPack, "service_impl.txt"));
		serviceTpl = new File(getTemplateFilePath(tplPack, "service.txt"));
		actionTpl = new File(getTemplateFilePath(tplPack, "action.txt"));
		configTpl = new File(getTemplateFilePath(tplPack, "config.txt"));
		formTpl = new File(getTemplateFilePath(tplPack, "form.txt"));
		beanTpl = new File(getTemplateFilePath(tplPack, "bean.txt"));
		
		springTpl = new File(getTemplateFilePath(tplPack, "xml_spring_config.txt"));
		strutsTpl = new File(getTemplateFilePath(tplPack, "xml_struts_config.txt"));
		
		pageMgrTpl = new File(getTemplateFilePath(tplPack, "page_mgr.txt"));
		pageListTpl = new File(getTemplateFilePath(tplPack, "page_list.txt"));
		pageSetTpl = new File(getTemplateFilePath(tplPack, "page_set.txt"));
		pageViewTpl = new File(getTemplateFilePath(tplPack, "page_view.txt"));
		
		jsTpl = new File(getTemplateFilePath(tplPack, "js.txt"));
		
		vldSaveTpl = new File(getTemplateFilePath(tplPack, "validation_save.xml"));
		vldEditTpl = new File(getTemplateFilePath(tplPack, "validation_edit.xml"));
		vldUpdateTpl = new File(getTemplateFilePath(tplPack, "validation_update.xml"));
	}

	private void writeFile() {
		try {
			if (prop.getProperty("is_dao").equals("true")) {
				FileUtils.writeStringToFile(daoImplFile, readTpl(daoImplTpl));
				FileUtils.writeStringToFile(daoFile, readTpl(daoTpl));
			}
			if (prop.getProperty("is_service").equals("true")) {
				FileUtils.writeStringToFile(serviceImplFile, readTpl(serviceImplTpl));
				FileUtils.writeStringToFile(serviceFile, readTpl(serviceTpl));
			}
			if (prop.getProperty("is_form").equals("true")) {
				FileUtils.writeStringToFile(formFile, readTpl(formTpl));
			}
			if (prop.getProperty("is_bean").equals("true")) {
				FileUtils.writeStringToFile(beanFile, readTpl(beanTpl));
			}
			if (prop.getProperty("is_action").equals("true")) {
				FileUtils.writeStringToFile(actionFile, readTpl(actionTpl));
			}
			if (prop.getProperty("is_config").equals("true")) {
				FileUtils.writeStringToFile(configFile, readTpl(configTpl));
			}
			if (prop.getProperty("is_js").equals("true")) {
				FileUtils.writeStringToFile(jsFile, readTpl(jsTpl));
			}
			if (prop.getProperty("is_page").equals("true")) {
				FileUtils.writeStringToFile(pageMgrFile, readTpl(pageMgrTpl));
				FileUtils.writeStringToFile(pageListFile, readTpl(pageListTpl));
				FileUtils.writeStringToFile(pageSetFile, readTpl(pageSetTpl));
				FileUtils.writeStringToFile(pageViewFile, readTpl(pageViewTpl));
			}
			if (prop.getProperty("is_spring").equals("true")) {
				String springTplStr = readTpl(springTpl);
				String origSpring = FileUtils.readFileToString(springFile,
						"UTF-8");
				if (origSpring.indexOf(springTplStr) == -1) {
					String newSpring = origSpring.replaceAll("</beans>",
							springTplStr + "</beans>");
					FileUtils.writeStringToFile(springFile, newSpring, "UTF-8");
				}
			}
			if (prop.getProperty("is_struts").equals("true")) {
				String strutsTplStr = readTpl(strutsTpl);
				String origStruts = FileUtils.readFileToString(strutsFile,
						"UTF-8");
				if (origStruts.indexOf(strutsTplStr) == -1) {
					String newStruts = origStruts.replaceAll("</package>",
							strutsTplStr + "</package>");
					FileUtils.writeStringToFile(strutsFile, newStruts, "UTF-8");
				}
			}
			if (!"false".equals(prop.getProperty("is_validate"))) {
				FileUtils.writeStringToFile(vldSaveFile, readTpl(vldSaveTpl,
						"UTF-8"), "UTF-8");
				FileUtils.writeStringToFile(vldEditFile, readTpl(vldEditTpl,
						"UTF-8"), "UTF-8");
				FileUtils.writeStringToFile(vldUpdateFile, readTpl(
						vldUpdateTpl, "UTF-8"), "UTF-8");
			}
			
			log.info("Success Finished!");
		} catch (IOException e) {
			log.warn("write file faild! " + e.getMessage());
		}
	}
	
	private void downFile(){
		//File srcdir = new File(tempFilePath);
		String zipPathString = tempFilePath + ".zip";
		ZIPUtil.getInstance().CreateZipFile(tempFilePath, zipPathString);
		
		InputStream instream = null;
	    try
	    {
	      String xmlFilePath = zipPathString;
	      String xmlFileName = null;
	      File xmlFile = null;

	      if (xmlFilePath != null) {
	        xmlFileName = xmlFilePath.substring(
	          xmlFilePath.lastIndexOf('\\') + 1, xmlFilePath.length());
	      }
	      if (xmlFileName != null) {
	        xmlFile = new File(xmlFilePath);
	      }
	      if ((xmlFile != null) && (xmlFile.exists()) && (xmlFile.isFile())) {
	        instream = new FileInputStream(xmlFile);

	        Struts2Util.getResponse().reset();
	        Struts2Util.getResponse().setContentType(
	          "application/x-msdownload");
	        Struts2Util.getResponse().setContentLength(
	          (int)xmlFile.length());
	        Struts2Util.getResponse().addHeader("content-disposition", 
	          "attachment; filename=\"" + xmlFileName + "\"");

	        byte[] b = new byte[4096];
	        int len;
	        while ((len = instream.read(b)) > 0)
	        {
	          Struts2Util.getResponse().getOutputStream()
	            .write(b, 0, len);
	        }
	        Struts2Util.getResponse().getOutputStream().flush();
	      }
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      try
	      {
	        if (instream != null)
	          instream.close();
	      }
	      catch (IOException e1) {
	        e1.printStackTrace();
	      }
	    }
	    finally
	    {
	      try
	      {
	        if (instream != null)
	          instream.close();
	      }
	      catch (IOException e) {
	        e.printStackTrace();
	      }
	    }
	}

	private String readTpl(File tpl) {
		return readTpl(tpl, "UTF-8");
	}

	private String readTpl(File tpl, String charset) {
		String content = null;
		try {
			content = FileUtils.readFileToString(tpl, charset);
			Set<Object> ps = prop.keySet();
			for (Object o : ps) {
				String key = (String) o;
				String value = prop.getProperty(key);
				content = content.replaceAll("\\#\\{" + key + "\\}", value);
			}
		} catch (IOException e) {
			log.warn("read file faild. " + e.getMessage());
		}
		return content;

	}
	
	private String getTemplateFilePath(String packageName, String name){
		log.debug("replace:" + packageName);
		String path = packageName.replaceAll("\\.", "/");
		log.debug("after relpace:" + path);
		return Struts2Util.getServletContext().getRealPath("/")+ File.separator + "WEB-INF"+File.separator+"classes"+File.separator+ path + File.separator + name;
	}

	private String getFilePath(String packageName, String name) {
		log.debug("replace:" + packageName);
		String path = packageName.replaceAll("\\.", "/");
		log.debug("after relpace:" + path);
		//return "src/" + tempFilePath + "/" + path + "/" + name;
		return tempFilePath + File.separator + path + File.separator + name;
	}
	
	private void initTempFilePath(){
		if (tempFilePath == ""){
			int i = (int)(Math.random() * 10000.0D);
			//tempFilePath = String.valueOf(System.currentTimeMillis() + i);
			tempFilePath = String.valueOf(System.currentTimeMillis() + i);
		}
	}
	
	private void deleteTempFile(){
		File file = new File(tempFilePath);
		File zipFile = new File(tempFilePath + ".zip");
		if(file.exists()){
			deleteDirectory(tempFilePath);
		}
		if(zipFile.exists()){
			deleteFile(tempFilePath + ".zip");
		}
	}
	
	public boolean deleteDirectory(String sPath) {   
	    //如果sPath不以文件分隔符结尾，自动添加文件分隔符   
	    if (!sPath.endsWith(File.separator)) {   
	        sPath = sPath + File.separator;   
	    }   
	    File dirFile = new File(sPath);   
	    //如果dir对应的文件不存在，或者不是一个目录，则退出   
	    if (!dirFile.exists() || !dirFile.isDirectory()) {   
	        return false;   
	    }   
	    boolean flag = true;   
	    //删除文件夹下的所有文件(包括子目录)   
	    File[] files = dirFile.listFiles();   
	    for (int i = 0; i < files.length; i++) {   
	        //删除子文件   
	        if (files[i].isFile()) {   
	            flag = deleteFile(files[i].getAbsolutePath());   
	            if (!flag) break;   
	        } //删除子目录   
	        else {   
	            flag = deleteDirectory(files[i].getAbsolutePath());   
	            if (!flag) break;   
	        }   
	    }   
	    if (!flag) return false;   
	    //删除当前目录   
	    if (dirFile.delete()) {   
	        return true;   
	    } else {   
	        return false;   
	    }   
	}  

	
	public boolean deleteFile(String sPath) {   
	    boolean flag = false;   
	    File file = new File(sPath);   
	    // 路径为文件且不为空则进行删除   
	    if (file.isFile() && file.exists()) {   
	        file.delete();   
	        flag = true;   
	    }   
	    return flag;   
	} 

	public void generate() {
		initTempFilePath();
		loadProperties();
		prepareFile();
		prepareTemplate();
		writeFile();
		downFile();
		deleteTempFile();
	}
	
	public void loadFromTableInfo(FormTable formTable){
//		FormTable formTable = new FormTable();
//		formTable.setModuleName("集中学习");
//		formTable.setAuthor("zzz");
//		formTable.setEntityBean("ArchiveCommonBean");
//		formTable.setEntity("ArchiveCommon");
//		formTable.setTableName("tb_sms");
		prop.put("config_comment", formTable.getModuleName());
		prop.put("self_module_name", formTable.getModuleName());
		prop.put("author", formTable.getAuthor());
		prop.put("Entity_Bean", formTable.getEntityBean());
		prop.put("Entity", formTable.getEntity());
		prop.put("table_name", formTable.getTableName());
	}

	public static void main(String[] args) {
		String packName = "com.osource.form";
		String fileName = "config_template.properties";
		new ModuleGenerator(packName, fileName).generate();
	}
}
