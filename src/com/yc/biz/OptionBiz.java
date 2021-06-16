package com.yc.biz;

import java.util.List;

import com.yc.bean.Option;
import com.yc.dao.OptionDAO;

public class OptionBiz {
	OptionDAO dao=new OptionDAO();
	public List<Option> find(Option bean) throws Exception {
		List<Option> o=dao.findByTrem(bean);
		if(o==null||o.size()==0) {
			return null;
		}
		return o;
	}
}
