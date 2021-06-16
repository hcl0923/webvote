package com.yc.test;

import org.junit.Test;

import com.yc.bean.Vote;
import com.yc.dao.VoteDAO;
import com.yc.util.VoteUtil;

public class VoteDAOTest {
	VoteDAO dao=new VoteDAO();
	@Test
	public void testAdd() throws Exception {
		Vote vote=new Vote();
		vote.setEndDate("2020-04-20");
		vote.setStartDate("2020-04-01");
		vote.setVtype(1);
		vote.setVname("开始二期项目");
		vote.setVid(VoteUtil.genVid());
		String []options=new String[] {"4月上旬","4月中旬","4月下旬","5月上旬"};
		dao.add(vote,options);
	}
}
