package com.yc.dao;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.User;
import com.yc.commons.DbHelper;

public class UserDAO implements BaseDao<User>{
	DbHelper db=new DbHelper();
	@Override
	public int add(User t) throws Exception {
		String sql="insert into tb_user values(null,?,MD5(?))";
		return db.update(sql, t.getUname(),t.getPwd());
	}

	@Override
	public List<User> findByTrem(User t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select usid,uname,pwd from tb_user where 1=1 ");
		List<Object> params=null;
		if(null!=t) {
			params=new ArrayList<Object>();
			if(null!=t.getUsid()) {//有值就匹配
				sb.append(" and usid= ? ");
				params.add(t.getUsid());
			}
			if(null!=t.getUname()) {
				sb.append(" and uname= ? ");
				params.add(t.getUname());
			}
			if(null!=t.getPwd()) {
				sb.append(" and pwd=MD5(?) ");
				params.add(t.getPwd());
			}
		}
		sb.append(" order by usid ");
		return db.findMutipl(sb.toString(), params, User.class);
	}

	@Override
	public int update(User t) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public int delete(User t) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}

}
