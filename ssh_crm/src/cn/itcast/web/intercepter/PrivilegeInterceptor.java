package cn.itcast.web.intercepter;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.itcast.domain.User;

public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	//自定义拦截器，看是否登录
	protected String doIntercept(ActionInvocation innovation) throws Exception {
		
		//获得session中的登录标识
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		//判断标识是否存在
		if (user!=null) {
			//已经登录，直接放行
			return innovation.invoke();
		}else {
			//不存在，则重定向到登录页面
			//需要配置到全局结果集里
			return "toLogin";
		}
	}

}
