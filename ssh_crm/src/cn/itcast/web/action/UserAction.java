package cn.itcast.web.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User> {
	private User user = new User();
	
	@Resource(name="userService")
	private UserService us ;

	public void setUs(UserService us) {
		this.us = us;
	}

	//用户登录功能
	public String login() throws Exception {
			//1 调用Service执行登陆逻辑
			User u = us.getUserByCodePassword(user);
			//2 将返回的User对象放入session域
			ActionContext.getContext().getSession().put("user", u);
			//3 重定向到项目首页
		return "toHome";
	}
	
	//用户注册功能
	public String regist() {
		
		//1.调用service保存注册用户
		try {
			us.saveUser(user);
		} catch (Exception e) {
			ActionContext.getContext().put("error", e.getMessage());
			return "regist";
		}
		//2.重定向到登录页面
		return "toLogin";
	}
	
	@Override
	public User getModel() {
		return user;
	}

	
	
}
