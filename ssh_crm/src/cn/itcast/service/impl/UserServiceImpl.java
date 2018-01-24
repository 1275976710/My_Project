package cn.itcast.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.utils.MD5Utils;

@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource(name="userDao")
	private UserDao ud;
	
	@Override
	//用户登录功能
	public User getUserByCodePassword(User u) {
			//1 根据登陆名称查询登陆用户
			User existU = ud.getByUserCode(u.getUser_code());
			//2 判断用户是否存在.不存在=>抛出异常,提示用户名不存在
			if(existU==null){
				throw new RuntimeException("用户名不存在!");
			}
			//3 判断用户密码是否正确=>不正确=>抛出异常,提示密码错误
			if(!existU.getUser_password().equals(MD5Utils.md5(u.getUser_password()))){
				throw new RuntimeException("密码错误!");
			}
			//4 返回查询到的用户对象
		
		return existU;
	}

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
	//注册用户功能
	public void saveUser(User u) {
		//先去判断数据库中是否已经存在该用户
		User user = ud.getByUserCode(u.getUser_code());
		//如果不存在，则去保存用户
		if (user!=null) {
			//数据库中已存在该用户
			throw new RuntimeException("用户名已经存在");
		}
		//使用MD5对密码进行加密
		u.setUser_password(MD5Utils.md5(u.getUser_password()));
		//不存在的话调用dao进行保存
		ud.save(u);
	}

	public void setUd(UserDao ud) {
		this.ud = ud;
	}

}
