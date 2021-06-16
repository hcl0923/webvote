package com.yc.dao;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.Option;
import com.yc.commons.DbHelper;
/**
 * 选项表的数据库操作
 * @author hp
 *
 */
public class OptionDAO implements BaseDao<Option>{
	DbHelper db=new DbHelper();
	@Override
	public int add(Option t) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}
	/**
	 * 选项查询
	 */
	@Override
	public List<Option> findByTrem(Option t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append(" select vid,opid,opname,views,usid from tb_options where 1=1 ");
		List<Object> params=null;
		if(null!=t) {
			params=new ArrayList<Object>();
			if(null!=t.getVid()) {
				sb.append(" and vid=? ");//别名
				params.add(t.getVid());
			}
			if(null!=t.getOpid()) {
				sb.append(" and opid=? ");
				params.add(t.getOpid());
			}
			if(null!=t.getOpname()) {
				sb.append(" and opname=? ");
				params.add(t.getOpname());
			}
		}
		return db.findMutipl(sb.toString(), params, Option.class);
	}

	@Override
	public int update(Option t) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public int delete(Option t) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}

}
