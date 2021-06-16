package com.yc.util;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

public class VoteUtil {
	/**
	 * 自动生成主题编号
	 * @return
	 */
	public static String genVid() {
		return System.currentTimeMillis()+""+new Random().nextInt(1000);
	}
	/**
	 * 返回主题的投票状态值 参与投票   即将开始  投票结束
	 * @param start
	 * @param end
	 * @return 
	 * @throws java.text.ParseException 
	 */
	public static String voteStatus(String start,String end) throws java.text.ParseException {
		try {
			Date startDate=parseStr(start);
			Date endDate=parseStr(end);
			if(System.currentTimeMillis()<startDate.getTime()) {
				return "即将开始";
			}else if(System.currentTimeMillis()>endDate.getTime()) {
				return "投票结束";
			}else {
				return "参与投票";
			}
		}catch(java.text.ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 解析时间格式的字符串
	 * @param time
	 * @return
	 * @throws java.text.ParseException 
	 */
	public static Date parseStr(String time) throws java.text.ParseException{
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date dt=(Date) df.parse(time.trim());
		return dt;
	}
}
