package org.express.common.monitor;

import java.util.ArrayList;

/**
 * 文件（文件夹）监视器
 * 通过本类实现对文件的监控，监控的指定路径或对象，文件发生创建，修改，删除的操作
 * 可以给需要报告传递对象，添加FileStateEvent接口。则报告对象就能获得监控中的事件
 * @author ZhengMin Chen
 */
public class FileMonitor
{
	private static FileSchedulerTask task;
	private static FileScheduler monitor;
	
	/**
	 * 初始化监视器
	 * @param 报告传递对象
	 * @param 监视的文件夹或者文件列表
	 * @param 监视的频度(比如1000 就是 1秒钟执行一次监视查询)
	 */
	public static void Monitor(FileStateEvent ReportObject, ArrayList<String> directs , int interval) 
	{
	    task = new FileSchedulerTask(directs);
	    task.setReportObject(ReportObject);
	    SetScheduler(task,interval);
    }
	
	/**
	 * 初始化监视器
	 * @param 报告传递对象
	 * @param 监视的文件夹或者文件列表
	 * @param 监视的频度(比如1000 就是 1秒钟执行一次监视查询)
	 */
	public static void Monitor(FileStateEvent ReportObject, String direct , int interval) 
	{
	    task = new FileSchedulerTask(direct);
	    task.setReportObject(ReportObject);
	    SetScheduler(task,interval);
    }
	
	/**
	 * 开始监视
	 */
	public static void StartMonitor()
	{
		monitor.Start();
	}
	
	/**
	 * 停止监视
	 */
	public static void StopMonitor()
	{
		monitor.Stop();
	}
	
	/**
	 * 设置任务和间隔
	 * @param 任务
	 * @param 间隔时间
	 */
	private static void SetScheduler(FileSchedulerTask task , int interval)
	{
		 monitor = new FileScheduler(task, new TimeStep(interval));
	}
}
