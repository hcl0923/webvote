package com.yc.dao;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.Vote;
import com.yc.bean.VoteVO;
import com.yc.commons.DbHelper;

public class VoteDAO implements BaseDao<Vote>{
	DbHelper db=new DbHelper();
	
	@Override
	public int add(Vote t) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}
	/**
	 * 添加主题
	 * @param t
	 * @param options
	 * @return
	 * @throws Exception
	 */
	public int add(Vote t,String []options)throws Exception{
		String sql01="insert into tb_vote values(?,?,?,?,?)";
		StringBuffer sql02=new StringBuffer();
		List<String> sqls=new ArrayList<String>();
		List<List<Object>> params=new ArrayList<List<Object>>();
		//添加主题
		List<Object> param01=new ArrayList<Object>();
		param01.add(t.getVid());
		param01.add(t.getVname());//类型各部相同，所以Object
		param01.add(t.getVtype());
		param01.add(t.getStartDate());
		param01.add(t.getEndDate());
		//添加到集合中
		params.add(param01);
		sqls.add(sql01);
		
		List<Object> param02=null;
		if(null!=options&&options.length>0) {
			param02=new ArrayList<Object>();
			sql02.append("insert into tb_options (vid,opname,views,usid) values ");
			for(int i=0;i<options.length;i++) {
				sql02.append("(?,?,0,null)");
				if(i<options.length-1) {
					sql02.append(",");//(?,?,0,null),(?,?,0,null)
				}
				param02.add(t.getVid());
				param02.add(options[i]);
			}
			//添加到集合中
			sqls.add(sql02.toString());
			params.add(param02);
		}
		return db.update(sqls,params);
	}
	/**
	 * 多表查询
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public List<VoteVO> findByTrem(VoteVO t)throws Exception{
		StringBuffer sb=new StringBuffer();
		sb.append("select v.vid.vname,vtype,startDate,endDate,opid,opname,views,usid from "
				 +" tb_vote v inner join tb_options o on v.vid=o.vid where 1=1 ");
		List<Object> params=null;
		if(null!=t) {
			params=new ArrayList<Object>();
			if(null!=t.getVid()) {
				sb.append(" and v.vid=? ");//别名
				params.add(t.getVid());
			}
			if(null!=t.getVname()) {
				sb.append(" and vname=? ");
				params.add(t.getVname());
			}
			if(null!=t.getVtype()) {
				sb.append(" and vtype=? ");
				params.add(t.getVtype());
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
		sb.append(" order by v.vid desc ");
		return db.findMutipl(sb.toString(), params, VoteVO.class);
	}
	
	/**
	 * 单表查询
	 */
	@Override
	public List<Vote> findByTrem(Vote t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select vid,vname,vtype,startDate,endDate from tb_vote where 1=1 ");
		List<Object> params=null;
		if(null!=t) {
			params=new ArrayList<Object>();
			if(null!=t.getVid()) {
				sb.append(" and vid=? ");
				params.add(t.getVid());
			}
			if(null!=t.getVname()) {
				sb.append(" and vname=? ");
				params.add(t.getVname());
			}
			if(null!=t.getVtype()) {
				sb.append(" and vtype=? ");
				params.add(t.getVtype());
			}
		}
		sb.append(" order by vid desc ");
		return db.findMutipl(sb.toString(), params, Vote.class);
	}

	@Override
	public int update(Vote t) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public int delete(Vote t) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}

}
