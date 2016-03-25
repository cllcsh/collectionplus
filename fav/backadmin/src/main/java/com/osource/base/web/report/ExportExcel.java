/**
 * @author luoj
 * @create 2009-9-3
 * @file ExportExcel.java
 * @since v0.1
 * 
 */
package com.osource.base.web.report;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.osource.core.exception.IctException;

/**
 * @author luoj
 *
 */
public class ExportExcel {
	
	public void export(String filename, Map dataMap, String model, HttpServletResponse response) throws IctException{
		XLSTransformer transformer = new XLSTransformer();
		HSSFWorkbook workbook = null;
		try {
			InputStream in= getClass().getResource(model).openStream();
			workbook = transformer.transformXLS(in, dataMap);
			outExcel(workbook, response, filename);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IctException(e);
		}
	}
	
	protected void outExcel(HSSFWorkbook workbook, HttpServletResponse response,
		    String filename) {
		   response.setContentType("application ns.ms-excel");
		   response.setHeader("Expires", "0");
		   response.setHeader("Cache-Control",
		     "must-revalidate, post-check=0, pre-check=0");
		   response.setHeader("Pragma", "public");
		   response.setHeader("Content-disposition", "attachment;filename="+filename+".xls");

		   try {
				workbook.write(response.getOutputStream());
		   } catch (ParsePropertyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
