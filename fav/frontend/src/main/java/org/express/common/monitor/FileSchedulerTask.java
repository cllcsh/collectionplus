package org.express.common.monitor;

import java.io.*;
import java.util.*;

import org.express.common.event.*;


/**
 * 文件监控任务 
 * 只监控文件，如果指定的是文件夹，则找到该文件夹下面的所有文件
 *@date 2012-02-09
 *@author ZhengMin Chen
 */
public class FileSchedulerTask implements Runnable , FileStateEvent
{
	private boolean firstRun = true;
	// 唯一路径下的文件信息
	private HashMap<String, HashMap<String,Long>> directMap = new HashMap<String, HashMap<String,Long>>();
	// 当前文件信息
	private Set<String> newFiles = new HashSet<String>();
	// 要监视的文件夹
	private ArrayList<String> directorys = null;
	// 事件Handler
	private EventHandler handler = new EventHandler();
	// 需要报告的对象
	private FileStateEvent ReportObject;
	// 文件筛选器
	private FileFilter filter;
	
	/**
	 * 构造函数
	 */
	public FileSchedulerTask()
	{
	}
	
	/**
	 * 构造函数
	 * @param 单一文件路径
	 */
	public FileSchedulerTask(String directory)
	{
		directorys = new ArrayList<String>();
		directorys.add(directory);
	}
	
	/**
	 * 构造函数
	 * @param 多个文件路径
	 */
	public FileSchedulerTask(ArrayList<String> list)
	{
		this.directorys = list;
	}
	
	/**
	 * 注册事件句柄
	 * 初始化完成，创建文件，修改文件，删除文件
	 */
	public void initEventHandler()
	{
		handler.setObject(ReportObject);
		handler.registerHandler("OnMonitorInitComplete");
		handler.registerHandler("OnFileCreated");
		handler.registerHandler("OnFileModified");
		handler.registerHandler("OnFileDeleted");
	}
	
	/**
	 * 设置报告对象
	 * @param xx类
	 */
	public void setReportObject(FileStateEvent obj)
	{
		this.ReportObject = obj;
		initEventHandler();
	}
	
	/**
	 * 得到文件过滤器
	 * @retuen 文件过滤器
	 */
	public FileFilter getFilter()
	{
		return this.filter;
	}
	
	/**
	 * 设置文件过滤器
	 * @param 文件过滤器
	 */
	public void setFilter(FileFilter filter)
	{
		this.filter = filter;
	}
	
	/**
	 * 执行监控任务
	 */
	public void run() 
	{	
		if(firstRun)
		{
			//初始化
			initFileInfo();
	    } 
		else
	    {
			//执行监控
			execFileInfo();
	    }	
	}
	
	/**
	 *  初始化文件信息
	 */
	private void initFileInfo()
	{
		firstRun = false;
		File file;
		if(directorys != null && directorys.size() > 0)
		{
			for (int i = 0; i < directorys.size(); i++)
			{
				HashMap<String,Long> currentFiles = new HashMap<String,Long>(); 
				file = new File(directorys.get(i));
				loadFileInfo(file , currentFiles);
				directMap.put(file.getAbsolutePath(), currentFiles);
			}
		}

		OnMonitorInitComplete(directorys);
	}
	
	/**
	 * 初始化文件信息
	 * @param file
	 * @param 初始化对照表
	 */
	private void loadFileInfo(File file , HashMap<String,Long>  currentFiles)
	{
		if(file.isFile())
		{
			currentFiles.put(file.getAbsolutePath(), file.lastModified());
		}
		else 
		{
			File[] files = file.listFiles();
			for(int i = 0;files != null && i<files.length;i++)
			{
				loadFileInfo(files[i] , currentFiles);
			}
		}
	}
	
	/**
	 *  检查文件更新状态
	 */
	private void execFileInfo()
	{
		File file;
		
		for (int i = 0; i < directorys.size(); i++)
		{
			file = new File(directorys.get(i));
			HashMap<String,Long> currentFiles = directMap.get(file.getAbsolutePath());
			
			// 检查文件更新状态[add,update]
	    	checkFileUpdate(file , currentFiles);
	    	// 检查被移除的文件[remove]
	    	checkRemovedFiles(currentFiles);
	    	// 清空临时文件集合
	    	newFiles.clear();
		}
	}
	
	/**
	 * 检查文件更新状态
	 * @param file
	 * @param 初始化对照表
	 */
	private void checkFileUpdate(File file , HashMap<String,Long> currentFiles)
	{
		if(file.isFile())
		{	
			// 将当前文件加入到 newFiles 集合中
			newFiles.add(file.getAbsolutePath());
			// 
			Long lastModified = currentFiles.get(file.getAbsolutePath());
			if(lastModified == null){
				// 新加入文件
				currentFiles.put(file.getAbsolutePath(), file.lastModified());
				this.OnFileCreated(file);
				return;
			}
			if(lastModified.doubleValue() != file.lastModified()){
				// 更新文件
				currentFiles.put(file.getAbsolutePath(), file.lastModified());
				this.OnFileModified(file);
				return;
			}
			return;
		} else if(file.isDirectory()){
			File[] files = file.listFiles();
			if(files == null || files.length == 0){
				// 没有子文件或子目录时返回
				return;
			}
			for(int i=0;i<files.length;i++){
				checkFileUpdate(files[i] , currentFiles);
			}
		}
	}
	
	/**
	 * 检查被移除的文件
	 * @param 初始化对照表（删除了木有文件了。。。）
	 */
	private void checkRemovedFiles(HashMap<String,Long> currentFiles)
	{
		if(newFiles.size() == currentFiles.size()){
			// 增加或更新时没有被移除的文件,直接返回
			return;
		}
		Iterator<String> it = currentFiles.keySet().iterator();
		while(it.hasNext()){
			String filename = it.next();
			if(!newFiles.contains(filename)){
				// 此处不能使用 currentFiles.remove(filename);从 map 中移除元素,
				// 否则会引发同步问题.
				// 正确的做法是使用 it.remove();来安全地移除元素.
				it.remove();
				this.OnFileDeleted(filename);
			}
		}
	}
	
	/**
	 * 得到多个监视的目标
	 * @return
	 */
	public ArrayList<String> getDirectorys() {
	    return directorys;
    }
	
	/**
	 * 设置多个监视的目标
	 * @param 设置多个监视的目标
	 */
	public void setDirectorys(ArrayList<String> directorys) {
	    this.directorys = directorys;
    }
	
	/**
	 * 当有文件创建
	 * @param 创建的文件
	 */
	@Override
	public void OnFileCreated(File file)
	{	
		try
		{
			handler.Invoke("OnFileCreated", file);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 当有被文件删除
	 * @param 删除文件的渣渣....
	 */
	@Override
	public void OnFileDeleted(String filename)
	{
		try
		{
			handler.Invoke("OnFileDeleted", filename);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 当文件被修改
	 * @param 修改的文件
	 */
	@Override
	public void OnFileModified(File file)
	{
		try
		{
			handler.Invoke("OnFileModified", file);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 监控初始化完成
	 * @param 监控的路径列表
	 */
	@Override
	public void OnMonitorInitComplete(ArrayList<String> directs)
	{
		try
		{
			handler.Invoke("OnMonitorInitComplete", directs);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
}