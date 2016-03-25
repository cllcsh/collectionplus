package com.osource.base.servlet;



/**
 * Copyright All rights reserved
 * @fileComment: 
 * @author: penghp
 * @time: 2011-6-14 上午10:50:35
 * @version: 1.0.0
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.osource.core.IDgenerator;
import com.osource.core.PropertiesManager;
import com.osource.core.exception.IctException;
import com.osource.util.StringUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class UploadFileServlet extends HttpServlet {
	private static Logger log = Logger.getLogger(UploadFileServlet.class);

	private static final String OBLIQUE_LINE = "/";

	private static final String OPPOSITE_OBLIQUE_LINE = "\\\\";

	private static final String WEBPOSITION = "webapps";

	//public static final String SBPATH = "../virtualdir/upload/";
	
	//Modified by lif,2013-1-28.
	public static final String SBPATH = PropertiesManager.getProperty("common.properties", "PIC_PATH", "../virtualdir/upload/");
	
	private static final String PIC_ROOT_FOLDER =PropertiesManager.getProperty("common.properties", "PIC_ROOT_FOLDER", "file/");

	//File outdir = null;

	//File outfile = null;

	//FileOutputStream fos = null;

	//BufferedInputStream bis = null;

	//byte[] bs = new byte[1024];

	//String uploadName = null;//上传的文件原始名称
	//String uploadFName = null; //上传文件修改后的名称
	String orderNo = null;
	String userId = null;
	String attachType = "2";

	/**
	 * Constructor of the object.
	 */
	public UploadFileServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy();
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (log.isDebugEnabled()) {
			log.debug("进入doPost()方法！！！");
		}
		
		StringBuffer destFName = new StringBuffer();
		String SFName = null;
		//BufferedInputStream bis = null;
		String folderName = "";
		String jsonstr = "";
		
		String root = this.getServletContext().getRealPath("/"); // 系统根目录
		root = root.replaceAll("\\\\", "/");
		folderName = new SimpleDateFormat("yyyy-MM").format(new Date());//Modified by lif,2013-1-28.
		
		try {
			//String projectName = request.getContextPath();//按工程名建相应文件夹，用来保存该工程上传的文件
			
			//destFName.append(getRealDir(root)).append(SBPATH).append(projectName+"/");
			if(SBPATH != null && SBPATH.indexOf(":")<0)
				destFName.append(getRealDir(root));
			//destFName.append(SBPATH).append("file/"+folderName+"/");
			destFName.append(SBPATH).append(PIC_ROOT_FOLDER+folderName+"/");//Modified by lif,2013-1-28.
			
			
			File outdir = new File(destFName.toString());//文件存放目录
			log.debug("outdir:" + outdir.getPath());
			if(!outdir.exists())
				outdir.mkdir();

			request.setCharacterEncoding("UTF-8");

			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("text/html");

			String glFlag = request.getParameter("glFlag");
			String ajaxUpload = request.getParameter("ajaxUpload");//异步文件上传
			String uploadName = request.getParameter("filename"); // name of uploaded  file
			if(uploadName == null || uploadName.length() <= 0)
				uploadName = "mobile.jpg";
			
			//校验session
			String sessionId = request.getParameter("sessionId");

			//uploadFName = getDatedFName(uploadName);//修改照片名字，可以有不同的命名方法，此为通用方法
			String uploadFName = getDatedFNameZls(uploadName);

			//SFName = "file/"+folderName+"/"+uploadFName;
			SFName = PIC_ROOT_FOLDER+folderName+"/"+uploadFName;//Modified by lif,2013-1-28.
			destFName.append(uploadFName);
			File outfile = new File(destFName.toString());

			if(ajaxUpload != null && ajaxUpload.equals("1")){
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				
				//upload.setSizeMax(10 * 1024 * 1024); // 允许上传的最大值
				//request.setCharacterEncoding("UTF-8");  
				   
				// 3. 判断是否是上传表单
				boolean isUpload = ServletFileUpload.isMultipartContent(request);
				if(!isUpload){
					System.out.println("对不起，不是文件上传表单！");
				}
				
				
				List<FileItem> items = upload.parseRequest(request); //解析request请求
				Iterator iter = items.iterator();
				while (iter.hasNext()) {
				   FileItem item = (FileItem) iter.next();
				   if (item.isFormField()) {                     //如果是表单域 ，就是非文件上传元素 
					   String name = item.getFieldName();            //获取name属性的值
					   String value = item.getString();              //获取value属性的值
					} else { 
					   String fieldName = item.getFieldName();      //文件域中name属性的值
					   String fileName = item.getName();            //文件的全路径，绝对路径名加文件名           
					   String contentType = item.getContentType(); //文件的类型
					   long size = item.getSize();                  //文件的大小，以字节为单位 
					   
					   //File saveFile = new File("D:/test.jpg");      //定义一个file指向一个具体的文件
					   //item.write(saveFile);                        //把上传的内容写到一个文件中
					   uploadFile(item.getInputStream(), outfile);       
					}
				}

			}else{
				/*bis = new BufferedInputStream(request.getInputStream());
				uploadFile(bis, outfile);*/
				uploadFile(request.getInputStream(), outfile);//上传照片
			}

			System.out.println("原始名称:" + request.getParameter("filename"));
			System.out.println("修改后名称:" + uploadFName);
			System.out.println("相对路径" + SFName);
			System.out.println("上传文件长度:" + outfile.length());
			
			log.debug("修改后名称:" + uploadFName);
			log.debug("相对路径" + SFName);
						
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug(e);
			}
			e.printStackTrace();
			
			jsonstr = "{\"code\":-1,\"msg\":\"upload failed\",\"resultPath\":\"upload/"+SFName+"\"}";
			
			System.out.println("上传文件返回json字符串："+jsonstr);
			
			log.debug("上传文件返回json字符串："+jsonstr);
			
			response.getOutputStream().print(jsonstr);
			
			return;
		} finally {
			/*if (null != bis)
				bis.close();*/
		}

		response.setHeader("resultPath", "upload/" + SFName);
		
		/*Map resultMap = new HashMap();
		resultMap.put("code", 0);
		resultMap.put("msg", "上传成功");
		resultMap.put("resultPath", "upload/" + SFName);
		
		JSON json = JSONSerializer.toJSON(resultMap);
		
		*/
		
		jsonstr = "{\"code\":0,\"msg\":\"upload success\",\"resultPath\":\"upload/"+SFName+"\"}";
		
		System.out.println("上传文件返回json字符串："+jsonstr);
		
		log.debug("上传文件返回json字符串："+jsonstr);
		
		response.getOutputStream().print(jsonstr);
	}

	/**
	 * 给图片添加水印
	 * 
	 * @param filePath
	 *            需要添加水印的图片的路径
	 * @param markContent
	 *            水印的文字
	 * @param markContentColor
	 *            水印文字的颜色
	 * @param qualNum
	 *            图片质量
	 * @param fontType 
	 * 			    字体
     * @param fontsize 
     * 			  字体大小
     * @param separator
     * 			  换行分隔符(可选，针对需要分多行打印的内容)
	 * @return
	 */
	public static boolean createMark(String filePath, String markContent,Color markContentColor, float qualNum, String separator) {
		String fontType = "宋体" ;//水印字体
		int fontSize = 12;//水印字体大小
		int lineHeight = 15;//行与行之间的距离
		 
		ImageIcon imgIcon = new ImageIcon(filePath);
		Image theImg = imgIcon.getImage();
		int width = theImg.getWidth(null);
		int height = theImg.getHeight(null);
		
		if(width<0 || height<0){
			return false;
		}
		
		
		Font f = new Font(fontType,Font.BOLD, fontSize);
		BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bimage.createGraphics();
		g.setColor(markContentColor);
		g.setBackground(Color.white);
		g.drawImage(theImg, 0, 0, null);
		g.setFont(f);
		
//		AttributedString ats = new AttributedString(markContent);
//      ats.addAttribute(TextAttribute.FONT, f, 0, 8);
//      AttributedCharacterIterator iter = ats.getIterator();

        width = width * 3/5;
    	height = height * 9/10;	
    	
        if(separator != null && !separator.equals("")){//针对需要分行的内容
        	if(markContent.indexOf(separator)>0){
	        	String[] marksArr = markContent.split(separator);
	        	for(int i=0;i<marksArr.length;i++){
	        		g.drawString(marksArr[i], width, height);
	        		height += lineHeight;
	        	}
        	}
        }else{//针对不需要分行的内容
        	g.drawString(markContent, width, height); // 添加水印的文字和设置水印文字出现的内容
        }
        
		g.dispose();
		
		try {
			FileOutputStream out = new FileOutputStream(filePath);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
			param.setQuality(qualNum, true);
			encoder.encode(bimage, param);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//通用改名方法
	private String getDatedFName(String fname) {
		StringBuffer result = new StringBuffer();

		SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmssSSSS");
		String dateSfx = "_" + df.format(new Date());

		int idx = fname.lastIndexOf('.');
		if (idx != -1) {
			// result.append(fname.substring(0, idx));
			result.append(dateSfx);
			result.append(fname.substring(idx));
		} else {
			result.append(fname);
			result.append(dateSfx);
		}

		System.out.println("改名后的图片文件名："+"mobile" + result.toString());
		return "mobile" + result.toString();
	}
	
	//南京自来水专用命名方法
	private String getDatedFNameZls(String fname) throws IctException {
		
		StringBuffer result = new StringBuffer();

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
		String dateSfx = df.format(new Date())+ StringUtil.leftPadding(IDgenerator.getNextID("photoid"),8, '0');

		int idx = fname.lastIndexOf('.');
		if (idx != -1) {
			//result.append(fname.substring(0, idx));
			result.append(dateSfx);
			result.append(fname.substring(idx));
		} else {
			result.append(fname);
			result.append(dateSfx);
		}

		System.out.println("改名后的图片文件名：" + result.toString());
		return result.toString();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		if (log.isDebugEnabled()) {
			log.debug("进入init()方法！！！");
		}
	}

	/**
	 * Method getRealDir search webapps position
	 * 
	 * @param despath
	 * 
	 * @return
	 * 
	 */
	private String getRealDir(String newFileNameRoot) throws Exception {
		if (newFileNameRoot == null)
			throw new Exception("get real dir failed !");
		int dp = newFileNameRoot.lastIndexOf(OBLIQUE_LINE);
		if (dp == -1)
			throw new Exception("invalid path !");
		int dpbefore = newFileNameRoot.lastIndexOf(OBLIQUE_LINE, dp - 1);
		if (dpbefore == -1)
			throw new Exception("invalid path !");
		String needSubStr = newFileNameRoot.substring(dpbefore + 1, dp);
		String nextStr = newFileNameRoot.substring(0, dpbefore + 1);
		if (!needSubStr.trim().equals(WEBPOSITION)) {
			return getRealDir(nextStr);
		} else{
			return newFileNameRoot;
		}
	}

	private void uploadFile(InputStream inputStream, File outfile) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("outfile:" + outfile.getPath());
		}
		if (!outfile.exists())
			outfile.createNewFile();
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try{
			bis = new BufferedInputStream(inputStream);
			bos = new BufferedOutputStream(new FileOutputStream(outfile));
			
			byte[] bs = new byte[1024];
			int length;
			while ((length = bis.read(bs)) != -1) {
				bos.write(bs, 0, length);
			}
	
			bis.close();
			bos.close();
			log.debug("uploadFile() ends:上传文件成功");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(bis != null)
					bis.close();
				if(bos != null)
					bos.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
				throw ioe;
			}
		}
	}
	
	
	
	private synchronized void uploadFile_V1(BufferedInputStream bis, File outfile) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("outfile:" + outfile.getPath());
		}
		if (!outfile.exists())
			outfile.createNewFile();
		
		byte[] bs = new byte[1024];
		FileOutputStream fos = new FileOutputStream(outfile);

		int i;
		while ((i = bis.read(bs)) != -1) {
			fos.write(bs, 0, i);
		}

		fos.close();
		log.debug("uploadFile() ends:上传文件成功");
	}

	public static String getUrlFName(String fname, HttpServletRequest request) {
		String result = "";
		if (fname == null || fname.length() <= 0)
			return result;

		try {
			if (fname.startsWith("http://")) {
				result = fname;
			} else {
				// HttpServletRequest request =
				// ServletActionContext.getServletContext().getRgetRequest();
				// UserAndOrganAndRole user =
				// (UserAndOrganAndRole)request.getSession().getAttribute("user");

				String ip = request.getServerName();
				int port = request.getServerPort();

				result = fname.substring(fname
						.indexOf(UploadFileServlet.SBPATH));
				StringBuffer tmpBuff = new StringBuffer();
				tmpBuff.append("http://").append(ip).append(":").append(port)
						.append(OBLIQUE_LINE).append(result);
				// Sample:
				// http://localhost:8083/UploadedFiles/IMAGE_067_100222102521.jpg

				result = tmpBuff.toString();

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println("result is: " + result);
		return result;
	}
	public static void main(String[] args) {
		/*UploadFileServlet ss = new UploadFileServlet();
		System.out.println(ss.getDatedFName("mole.jpg"));*/
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String markContent = "上传时间："+sdf.format(new Date());
		createMark("D:\\Tomcat-6.0\\virtualdir\\upload\\file\\mobile.jpg", markContent,Color.RED, 70f, null);
		
	}
}
