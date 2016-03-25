package org.express.common.monitor;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *文件定时访问器
 *@date 2012-02-09
 *@author ZhengMin Chen
 */
public class FileScheduler {
	// 定时器
	private final Timer timer;
	
	private Runnable mainTask;
	
	private TimeStep timeStep;
	
	/**
	 * 构造函数
	 */
	public FileScheduler(){
		timer = new Timer();
	}
	
	/**
	 * 构造函数
	 * @param 是否为守护线程
	 */
	public FileScheduler(boolean isDaemon){
		// 是否为守护线程
		timer = new Timer(isDaemon);
	}
	
	/**
	 * 构造函数
	 * @param 需要执行的任务
	 * @param 时间发生器
	 */
	public FileScheduler(Runnable task,TimeStep step)
	{
		timer = new Timer();
		this.mainTask = task;
		this.timeStep = step;
	}
	
	/**
	 * 构造函数
	 * @param 需要执行的任务
	 * @param 时间发生器
	 * @param 是否为守护线程
	 */
	public FileScheduler(Runnable task,TimeStep step,boolean isDaemon)
	{
		timer = new Timer(isDaemon);
		this.mainTask = task;
		this.timeStep = step;
	}
	
	/**
	 * 为定时器分配可执行任务
	 * @param task
	 * @param step
	 */
	public void schedule(Runnable task,TimeStep step)
	{	
		this.mainTask = task;
		this.timeStep = step;
	}
	
	/**
	 * 启动定时器
	 */
	public void Start()
	{
		Date time = this.timeStep.next();
		SchedulerTimerTask timeTask = new SchedulerTimerTask(this.mainTask,this.timeStep);
		timer.schedule(timeTask, time);
	}
	
	/**
	 * 重新执行任务
	 * @param task
	 * @param step
	 */
	private void reSchedule(Runnable task,TimeStep step)
	{
		Date time = step.next();
		SchedulerTimerTask timeTask = new SchedulerTimerTask(task,step);
		// 安排在指定的时间 time 执行指定的任务 timetask
		timer.schedule(timeTask, time);
	}
	/**
	 * 停止当前定时器
	 */
	public void Stop()
	{
		timer.cancel();
	}
	
	/**
	 * 定时任务
	 *@date 2012-02-09
	 *@author ZhengMin Chen
	 */
	private class SchedulerTimerTask extends TimerTask{
		private Runnable task;
		private TimeStep step;
		
		/**
		 * 构造函数
		 * @param 需要执行的任务
		 * @param 时间发生器
		 */
		public SchedulerTimerTask(Runnable task,TimeStep step){
			this.task = task;
			this.step = step;
		}
		
		/**
		 * 执行任务（并且重复调用）
		 */
		@Override
		public void run() {
			// 执行指定任务
		    task.run();
		    // 继续重复执行任务
		    reSchedule(task, step);
		}
	}
}