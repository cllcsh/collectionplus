/**
 * @author lifa
 * @create 2009-10-20
 * @file ChartGenerator.java
 * @since v0.1
 * 
 */
package com.osource.base.common.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.RenderingHints;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.PieDataset;

public class ChartGenerator {

	/**
	 * 生成具有3D效果的柱状图，对柱状图显示属性都进行了默认设置和封装
	 * 
	 * @param title 图表标题
	 * @param categoryAxisLabel 图表横坐标标题
	 * @param valueAxisLabel 图表纵坐标标题
	 * @param dataset 数据集
	 * @param orientation 图表方向
	 * @param legend 是否显示图例
	 * @param tooltips 是否产生工具提示
	 * @param urls 是否产生url
	 * 
	 * @return JFreeChart
	 */
	public static JFreeChart get3DBarChart( String title,
								            String categoryAxisLabel,
								            String valueAxisLabel,
								            CategoryDataset dataset,
								            PlotOrientation orientation,
								            boolean legend,
								            boolean tooltips,
								            boolean urls)
	{
		  
		  JFreeChart barChart = ChartFactory.createBarChart3D(title, categoryAxisLabel, valueAxisLabel, dataset, orientation, legend, tooltips, urls);
		 
		  CategoryPlot plot = barChart.getCategoryPlot();
		  
		//设置X轴标题的倾斜程度
		  CategoryAxis domainAxis = plot.getDomainAxis();
		  //domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(1.57));

		// 设置柱状体与图片边框的左右间距
		  domainAxis.setLowerMargin(0.05);
		  //domainAxis.setLowerMargin(0.01);
		  //domainAxis.setUpperMargin(0.01);

		// 设置柱状体与图片边框的上下间距
		  ValueAxis rAxis = plot.getRangeAxis();
		  rAxis.setUpperMargin(0.15);
		  rAxis.setLowerMargin(0.15);
		 
		//设置图表的显示部分的背景色
		  barChart.setBackgroundPaint(new Color(0xE1E1E1));
		//设置消除字体的锯齿渲染（解决中文问题）
		  barChart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		// 设置标题字体
		  TextTitle textTitle = barChart.getTitle();
		  textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
		// 设置X轴坐标上的文字
		  domainAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 12));
		// 设置X轴的标题文字
		  domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
		  domainAxis.setLabelPaint(Color.BLUE);
		// 设置Y轴坐标上的文字
		  rAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 12));
		// 设置Y轴的标题文字
		  rAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
		  rAxis.setLabelPaint(Color.BLUE);
		//设置图例文字
		  if(barChart.getLegend()!= null)
			  barChart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12)); 
		  
		 // 设置Y轴显示整数
		  NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		  rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		  
		 //设置X轴坐标上的文字垂直显示
		  domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.4));
		  //domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createDownRotationLabelPositions(Math.PI/2)); 
	      //domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
		  
		  
		  BarRenderer render = (BarRenderer) plot.getRenderer();
		//设置是否在柱状图的状态条上显示边框
		  render.setItemMargin(0.1);
		// 设置每一组柱状体之间的间隔
		  render.setItemMargin(0.0);
		  
		  BarRenderer3D renderer = new BarRenderer3D();

		  // 设置柱状图的渐变色
		  //CategoryItemRenderer renderer = (CategoryItemRenderer) plot.getRenderer();
		  GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, new Color(50,
				    255, 50), 0.0f, 0.0f, new Color(100, 255, 100));
		  GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, new Color(255,
				    200, 80), 0.0f, 0.0f, new Color(255, 255, 40));
		  GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red, 0.0f,
				  	0.0f, new Color(255, 100, 100));
		  /*GradientPaint gp3 = new GradientPaint(0.0f, 0.0f, new Color(108,
		    108, 255), 0.0f, 0.0f, new Color(150, 150, 200));*/

		//设置柱的颜色
		  //renderer.setSeriesPaint(0, new Color(0xff00));
		  renderer.setSeriesPaint(0, gp0);
		  renderer.setSeriesPaint(1, gp1);
		  renderer.setSeriesPaint(2, gp2);
		  //renderer.setSeriesPaint(3, gp3);

		 
		  //显示每个柱的数值，并修改该数值的字体属性   
		  renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());   
		  renderer.setBaseItemLabelFont(new Font("黑体",Font.PLAIN,10));//10号黑体  
		  renderer.setBaseItemLabelPaint(Color.black);//字体为黑色   
		  renderer.setBaseItemLabelsVisible(true);  
		  
		  // 使用我们设计的效果
		  plot.setRenderer(renderer);
		  
		  //设置纵横坐标的显示位置
          //plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);//显示在下端(柱子竖直)或左侧(柱子水平)
          //plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT); //显示在下端(柱子水平)或左侧(柱子竖直)

		  
		  return barChart;
	}
	
	/**
	 * 生成饼图，对饼图显示属性都进行了默认设置和封装
	 * 
	 * @param title 图表标题
	 * @param dataset 饼图数据集
	 * @param legend 是否显示图例
	 * @param tooltips 是否产生工具提示
	 * @param urls 是否产生url
	 * @return JFreeChart
	 */
	public static JFreeChart getPieChart(String title, PieDataset dataset, boolean legend, boolean tooltips, boolean urls){
		 
		//用工厂类创建饼图   
		  JFreeChart pieChart = ChartFactory.createPieChart(title, dataset, legend, tooltips, urls);
		  
		// RenderingHints做文字渲染参数的修改 ，VALUE_TEXT_ANTIALIAS_OFF表示将文字的抗锯齿关闭.   
	      pieChart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);   

	      // 设置Legend图例的位置   
	      // ((JFreeChart) pieChart).getLegend().setPosition(RectangleEdge.RIGHT);   

	      // 设置图片背景色   
	      //pieChart.setBackgroundPaint(new Color(191, 191, 255));
	      pieChart.setBackgroundPaint(new Color(0xE1E1E1));

	      // 设置图标题的字体  
	      TextTitle pieTitle = pieChart.getTitle();   
	      pieTitle.setFont(new Font("黑体", Font.PLAIN, 20));
	      pieChart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));

	      // 得到饼图的Plot对象   
	      PiePlot piePlot = (PiePlot) pieChart.getPlot();   

	      // 指定饼图轮廓线的颜色   
	      piePlot.setBaseSectionOutlinePaint(Color.BLACK);   
	      piePlot.setBaseSectionPaint(Color.BLACK);   

	      // 设置背景区域背景色   
	      //piePlot.setBackgroundPaint(new Color(191, 191, 255)); 
	      piePlot.setBackgroundPaint(new Color(0xC0C0C0));
	      
	   	  // 扇区颜色设置   
	      piePlot.setSectionPaint("0", new Color(153, 153, 255));   
	      piePlot.setSectionPaint("1", new Color(153, 53, 102));   
	      piePlot.setSectionPaint("2", new Color(255, 255, 204));   
	      piePlot.setSectionPaint("3", new Color(204, 255, 255));   
	      piePlot.setSectionPaint("4", new Color(102, 0, 102));// 紫色   
	      piePlot.setSectionPaint("5", new Color(255, 128, 128));// 桃红   
	      piePlot.setSectionPaint("6", new Color(0, 102, 204));// 蓝   
	      piePlot.setSectionPaint("7", new Color(204, 204, 255));   

	      // 设置扇区分离显示   
	      // piePlot.setExplodePercent("测试数据1", 0.2D);   
	      // 设置扇区边框不可见   
	      // piePlot.setSectionOutlinesVisible(false); 
	      
		  // 设置扇区标签显示格式：关键字：值(百分比)   
	      piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} {2}", NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));   
	      // 设置扇区标签颜色   
	      piePlot.setLabelBackgroundPaint(new Color(220, 220, 220));   
	      piePlot.setLabelFont((new Font("宋体", Font.PLAIN, 15)));// 设置扇区标签字体大小   
	            
	      // 设置没有数据时显示的信息   
	      piePlot.setNoDataMessage("无数据");   
	      // 设置没有数据时显示的信息的字体   
	      piePlot.setNoDataMessageFont(new Font("宋体", Font.BOLD, 14));   
	      // 设置没有数据时显示的信息的颜色   
	      piePlot.setNoDataMessagePaint(Color.red); 
	      
	      // 设置对于null数据和0数据的处理 ，设置是否忽略0和null值   
	      piePlot.setIgnoreNullValues(false);   
	      piePlot.setIgnoreZeroValues(false); 
	      
	      return pieChart;
	}
}
