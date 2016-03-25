package org.express.portal.container;

public class InjectorTypeEnum {
	public enum InjectorType
	{
		/**
		 * 单一的
		 */
		Single,
		/**
		 * 新实例
		 */
		NewInstance,
		/**
		 * 用户级
		 */
		Session
	}
}
