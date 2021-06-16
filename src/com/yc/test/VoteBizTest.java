package com.yc.test;

import org.junit.Test;

import com.yc.biz.VoteBiz;

public class VoteBizTest {
	VoteBiz biz=new VoteBiz();
	@Test
	public void testFindAll() throws Exception{
		System.out.println(biz.findAll());
	}
}
