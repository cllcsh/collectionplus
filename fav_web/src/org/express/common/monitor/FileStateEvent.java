package org.express.common.monitor;

import java.io.File;
import java.util.ArrayList;

/**
 *文件状态函数接口
 *此接口规定了，文件监控器触发的事件
 *@date 2012-02-09
 *@author ZhengMin Chen
 */
public interface FileStateEvent
{
	public void OnFileCreated(File file);
	
	public void OnFileModified(File file);
	
	public void OnFileDeleted(String filename);
	
	public void OnMonitorInitComplete(ArrayList<String> directs);
}