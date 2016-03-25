package org.express.portal.container;

public class AdvanceInjectorImpl implements AdvanceInjector {

	private String key;
	private Object obj;
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return key;
	}

	@Override
	public void setKey(String key) {
		// TODO Auto-generated method stub
		this.key = key;
	}

	@Override
	public Object getObject() {
		// TODO Auto-generated method stub
		return obj;
	}

	@Override
	public void setObject(Object object) {
		// TODO Auto-generated method stub
		this.obj = object;
	}
}
