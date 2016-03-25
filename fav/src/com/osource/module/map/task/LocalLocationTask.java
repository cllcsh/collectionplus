package com.osource.module.map.task;

import java.util.List;

import com.osource.base.common.location.LocationCenter;

public class LocalLocationTask implements Runnable {

	private final List<Integer> locationIds;
	
	public LocalLocationTask(List<Integer> locationIds) {
		this.locationIds = locationIds;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		LocationCenter.location(locationIds);
	}

}