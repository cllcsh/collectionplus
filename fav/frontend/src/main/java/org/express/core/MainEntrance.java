package org.express.core;

import java.io.File;

import org.express.common.monitor.FileMonitorTest;
import org.express.core.environment.Win;

public class MainEntrance 
{
	public static void main(String... args)
	{
		System.out.println("Org.Express.Core  Startup");
		if(args != null && args.length > 0)
		{
			for(int i=0 ; i < args.length ; i++)
			{
				System.out.println("Param" + (i+1) + " : " + args[i]);
			}
		}
		
		FileMonitorTest.main(null);
		String path = args[0];
		path += File.separator + "tomcat" + File.separator + "bin" + File.separator + args[1];
		System.out.println(path);
		Win.callCmd(path);
	}
}
