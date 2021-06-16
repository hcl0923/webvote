package com.yc.commons;

import java.io.IOException;
import java.util.Properties;

/**
 * �Զ����� ���������ļ� ��֤�ö���ֻ��һ������
 * ����ģʽ
 * @author hp
 *
 */
public class MyProperties extends Properties{
	private static MyProperties instance=new MyProperties();
	private MyProperties(){
		try{
			this.load(MyProperties.class.getClassLoader().getResourceAsStream("db.properties"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static MyProperties getInstance(){
		return instance;
	}
}
