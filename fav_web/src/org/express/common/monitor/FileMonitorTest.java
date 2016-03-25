package org.express.common.monitor;

import java.io.File;
import java.util.ArrayList;
/**
 * 测试 - 文件监视器测试代码
 *@author ZhengMin Chen
 */
public class FileMonitorTest
{
	
	public static void main(String[] args)
	{
		FileMonitorTest test =new FileMonitorTest();
		test.Start();
	}
	
	public void Start()
	{
		Thread thread = new Thread()
		{
			@Override
			public void run()
			{
				ArrayList<String> arrayList = new ArrayList<String>();
				arrayList.add("D:\\213\\");
				arrayList.add("D:\\123\\");
				arrayList.add("D:\\523\\");
				FileStateEvent fe = new FileMonitorOtherTest();
				
				FileMonitor.Monitor(fe, arrayList , 30000);
				FileMonitor.StartMonitor();
			}
		};
		
		thread.start();
	}
	
	public static class FileMonitorOtherTest implements FileStateEvent
	{	
		/**
		 * 当有文件创建
		 * @param 创建的文件
		 */
		@Override
		public void OnFileCreated(File file)
		{
			// TODO Auto-generated method stub
			System.out.println("创建文件:" + file.getAbsolutePath());
		}

		/**
		 * 当有被文件删除
		 * @param 删除文件的渣渣....
		 */
		@Override
		public void OnFileDeleted(String filename)
		{
			// TODO Auto-generated method stub
			System.out.println("删除文件:" + filename);
		}

		/**
		 * 当文件被修改
		 * @param 修改的文件
		 */
		@Override
		public void OnFileModified(File file)
		{
			System.out.println("更新文件:" + file.getAbsolutePath());
		}

		/**
		 * 监控初始化完成
		 * @param 监控的路径列表
		 */
		@Override
		public void OnMonitorInitComplete(ArrayList<String> directs)
		{
			System.out.println("--------文件监控初始化完成-------");
			if(directs != null)
			{
				for(String a : directs)
				{
					System.out.println( a + " 路径受到监控。");
				}
			}
		}
	}
}