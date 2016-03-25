package org.express.portal;

/**
 * Object which has resource to release should implement this interface.
 * 
 */
public interface Destroyable
{
	/**
	 * Called when container destroy the object.
	 */
	void destroy();
}
