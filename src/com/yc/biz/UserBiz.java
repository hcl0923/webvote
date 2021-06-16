package com.yc.biz;

import java.util.List;

import com.yc.bean.User;
import com.yc.dao.UserDAO;

public class UserBiz {
	UserDAO dao=new UserDAO();
	/**
	 * 用户登录
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(User user)throws Exception{
		List<User> list=dao.findByTrem(user);
		if(null!=list&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	public int add(User user)throws Exception{
		return dao.add(user);
	}
}
