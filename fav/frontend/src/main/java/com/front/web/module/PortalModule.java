package com.front.web.module;

import com.front.cang.action.CangAction;
import com.front.fa.action.FaAction;
import com.front.home.action.HomeAction;
import com.front.my.action.MyAction;
import com.front.shou.action.ShouAction;
import com.front.user.action.UserAction;
import com.google.inject.Binder;
import com.google.inject.Module;

public class PortalModule implements Module{

	@Override
	public void configure(Binder binder) {
		binder.bind(UserAction.class).asEagerSingleton();
		binder.bind(HomeAction.class).asEagerSingleton();
		binder.bind(ShouAction.class).asEagerSingleton();
		binder.bind(FaAction.class).asEagerSingleton();
		binder.bind(MyAction.class).asEagerSingleton();
		binder.bind(CangAction.class).asEagerSingleton();
	}
}
