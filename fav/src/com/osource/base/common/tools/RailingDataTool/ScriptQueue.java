package com.osource.base.common.tools.RailingDataTool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class ScriptQueue extends Thread {
	private static final Integer DELAY_MS = 10;

    public static PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<String>();

    private static ExecutorService executorService = Executors.newFixedThreadPool(100);

    public static void add(String code)
    {
        queue.add(code);
    }
    
    @Override
    public void run()
    {
        while (true)
        {
            String code;
            try
            {
            	code = queue.take();
                executorService.execute(new ScriptThread(code));
                Thread.sleep(DELAY_MS);
            }
            catch (Exception e)
            {
                System.out.println("执行出错！");
            }
        }
    }
}
