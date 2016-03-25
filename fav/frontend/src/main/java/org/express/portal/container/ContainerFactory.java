package org.express.portal.container;

import java.util.List;
import org.express.portal.Config;

/**
 * Factory instance for creating IoC container.
 */
public interface ContainerFactory
{
	/**
	 * Init container factory.
	 */
	void init(Config config);

	/**
	 * Find all beans in container.
	 */
	List<AdvanceInjector> findAllAdvanceBeans();

	/**
	 * When container destroyed.
	 */
	void destroy();
}
