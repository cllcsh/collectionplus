/**
 * 
 */
package com.osource.module.map.task;

import java.util.List;

import com.osource.base.common.LocationManager;
import com.osource.core.exception.IctException;

/**
 * @author luoj
 *
 */
@SuppressWarnings("deprecation")
public class LocationTask implements Runnable {

	private final List<Integer> locationIds;
	
	public LocationTask(List<Integer> locationIds) {
		this.locationIds = locationIds;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			LocationManager.locationByLocationIds(locationIds);
		} catch (IctException e) {
			e.printStackTrace();
		}

	}

}
