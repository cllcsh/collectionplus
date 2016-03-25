<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String filename = request.getParameter("filename");
	filename = new String(filename.getBytes("ISO-8859-1"),"UTF-8");
	String filedownload = "/template/" + filename;
	response.setContentType("application/x-download");
	response.addHeader("Content-Disposition", "attachment;filename=" + filename);
	try {
		RequestDispatcher dispatcher = application
				.getRequestDispatcher(filedownload);
		if (dispatcher != null) {
			dispatcher.forward(request, response);
			response.flushBuffer();
		}
		else 
			return;
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
	}
%>