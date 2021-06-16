package com.yc.commons;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbHelper {
	private Connection conn;// 连接对象
	private PreparedStatement pstmt;// 预编译对象
	private ResultSet rs;// 结果集
	// 当类第第一加载时执行

	static {
		try {
			Class.forName(MyProperties.getInstance().getProperty("driverClass"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取连接对象
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConn() throws SQLException {
		// conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","a");
		conn = DriverManager.getConnection(MyProperties.getInstance().getProperty("url"), MyProperties.getInstance());// 自动调MyProperties.getInstance()中的密码
		return conn;
	}
	
	// 关闭所有资源
	public void closeAll(Connection conn, Statement stmt, ResultSet rs) {
		try {
			// 关闭结果集
			if (null != rs) {
				rs.close();
			}
			// 关闭编译对象
			if (null != stmt) {
				stmt.close();
			}
			// 关闭连接对象
			if (null != conn) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 单条sql语句的更新，不定长  查询较复杂用list
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int update(String sql,Object...params)throws Exception{
		int result=0;
		try {
			//获取连接对象
			conn=this.getConn();
			//获取预编译对象
			pstmt=conn.prepareStatement(sql);
			//设置参数
			//数组
			if(null!=params) {
				for(int i=0;i<params.length;i++) {
					pstmt.setObject(i+1, params[i]);//设置参数
				}
			}
			result=pstmt.executeUpdate();
		}finally {
			this.closeAll(conn, pstmt, null);
		}
		return result;
	}
	
	public <T> List<T> findMutipl(String sql,List<Object> params,Class<T> cls)throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, InstantiationException{
		List<T> list=new ArrayList<T>();
		T t;
		try {
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			setParams(pstmt,params);
			rs=pstmt.executeQuery();
			//获取所有的方法和字段名
			Method []methods=cls.getDeclaredMethods();
			Field []fields=cls.getDeclaredFields();
			while(rs.next()) {
				//创建对象  根据反射创建
				t=cls.newInstance();
				for(Method m:methods) {
					for(Field f:fields) {
						String fieldName=f.getName();
						//set与字段名拼接  称为setName  与方法名进行比较
						if(("set"+fieldName).equalsIgnoreCase(m.getName())) {
							String type=m.getParameterTypes()[0].getName();
							if("java.lang.Integer".equals(type)) {
								m.invoke(t, rs.getInt(fieldName));//数据库的字段名要和对象属性field一致
							}else if("java.lang.Double".equals(type)){
								m.invoke(t, rs.getDouble(fieldName));
							}else if("java.lang.Float".equals(type)){
								m.invoke(t, rs.getFloat(fieldName));
							}else if("java.lang.Long".equals(type)){
								m.invoke(t, rs.getLong(fieldName));
							}else if("java.lang.String".equals(type)){
								m.invoke(t, rs.getString(fieldName));
							}else {
								//后期拓展
							}
						}
					}
				}
				list.add(t);//对象添加到list集合中去
			}
		}finally {
			closeAll(conn, pstmt, rs);
		}
		
		return list;
	}
	/**
	 * 查询一条记录  封装在javaBean对象中
	 * @param <T>
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 * @throws SQLException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public <T> T find(String sql,List<Object> params,Class<T> cls) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, InstantiationException{//类名   class对象
		T t=null;//t实例
		
		try {
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			setParams(pstmt,params);
			rs=pstmt.executeQuery();
			Method []methods=cls.getDeclaredMethods();
			Field []fields=cls.getDeclaredFields();
			if(rs.next()) {
				//创建对象  根据反射创建
				t=cls.newInstance();
				for(Method m:methods) {
					for(Field f:fields) {
						String fieldName=f.getName();
						//set与字段名拼接  称为setName  与方法名进行比较
						if(("set"+fieldName).equalsIgnoreCase(m.getName())) {
							String type=m.getParameterTypes()[0].getName();
							if("java.lang.Integer".equals(type)) {
								m.invoke(t, rs.getInt(fieldName));//数据库的字段名要和对象属性field一致
							}else if("java.lang.Double".equals(type)){
								m.invoke(t, rs.getDouble(fieldName));
							}else if("java.lang.Float".equals(type)){
								m.invoke(t, rs.getFloat(fieldName));
							}else if("java.lang.Long".equals(type)){
								m.invoke(t, rs.getLong(fieldName));
							}else if("java.lang.String".equals(type)){
								m.invoke(t, rs.getString(fieldName));
							}
						}
						
					}
				}
			}
		}finally {
			closeAll(conn, pstmt, rs);
		}
		return t;
	}

	/**
	 * 单条sql语句更新操作
	 * 
	 * @param sql    sql语句
	 * @param params 参数集合 参数添加顺序必须和？顺序一致
	 * @return
	 * @throws SQLException
	 */
	public int update(String sql, List<Object> params) throws SQLException {
		int result = 0;
		try {
			// 获取连接对象
			conn = this.getConn();
			// 获取预编译对象
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			setParams(pstmt, params);
			result = pstmt.executeUpdate();
		} finally {
			this.closeAll(conn, pstmt, null);
		}
		return result;
	}

	/**
	 * 设置参数
	 * 
	 * @param pstmt2 预编译对象
	 * @param params 参数集合
	 */
	private void setParams(PreparedStatement pstmt, List<Object> params) throws SQLException {
		if (null == params || params.size() <= 0) {
			return;
		}
		for (int i = 0; i < params.size(); i++) {
			pstmt.setObject(i + 1, params.get(i));// 问号索引号是从1开始的
		}
	}

	/**
	 * 查询操作 select * from tb_name where id = ? 返回一条记录
	 * 
	 * @param sql    sql语句
	 * @param params 参数
	 * @return
	 * @throws Exception
	 */
//	public Map<String, Object> findSingle(String sql, List<Object> params) throws Exception {
//		Map<String,Object> map=null;
//		try{
//			//获取连接对象
//			conn=getConn();
//			//获取编译对象
//			pstmt=conn.prepareStatement(sql);
//			//设置参数
//			setParams(pstmt,params);
//			//执行查询操作，返回结果集
//			rs=pstmt.executeQuery();
//			//获取所有的列名
//			List<String> columnNames=getColumnNames(rs);
//			
//			if(rs.next()){
//				//创建Map对象
//				map=new HashMap<String,Object>();
//				//map.put("",rs.getObject(""));
//				//循环列名
//				for(String name:columnNames){
//					//获取到值
//					Object obj=rs.getObject(name);
//					if(obj==null){
//						continue;//执行下一次的循环
//					}
//					String typeName=obj.getClass().getName();//类型名
//					//System.out.println(typeName);
//					if("oracle.sql.BLOB".equals(typeName)){
//						//图片  以字节数组的形式存储到Map中
//						BLOB blob=(BLOB) rs.getBlob(name);
//						InputStream in=blob.getBinaryStream();
//						byte[] bt=new byte[(int) blob.length()];
//						in.read(bt);
//						in.close();
//						map.put(name, bt);
//					}else if("oracle.sql.CLOB".equals(typeName)){
//						CLOB clob=(CLOB)rs.getClob(name);
//						Reader rd=clob.getCharacterStream();
//						char[] cs=new char[(int)clob.length()];
//						rd.read(cs);
//						String str=new String(cs);
//						rs.close();
//						map.put(name, str);
//					}else{
//						map.put(name, rs.getObject(name));
//					}
//				}
//			}
//		}finally{
//			this.closeAll(conn, pstmt, rs);
//		}
//		return map;
//	}
	/**
	 * 根据结果集获取所有的列名
	 * 
	 * @param rs2
	 * @return
	 * @throws SQLException
	 */
	private List<String> getColumnNames(ResultSet rs) throws SQLException {
		List<String> columnNames = new ArrayList<String>();
		ResultSetMetaData data = rs.getMetaData();// 列数据
		int count = data.getColumnCount();
		// 获取列名
		for (int i = 1; i <= count; i++) {
			columnNames.add(data.getColumnName(i));
		}
		return columnNames;
	}

	/**
	 * 返回多条记录
	 * 
	 * @param sql
	 * @param params
	 * @return 返回多条数据
	 * @throws SQLException
	 * @throws IOException
	 */
	public List<Map<String, Object>> findMutiple(String sql, List<Object> params) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		try {
			// 获取连接对象
			conn = getConn();
			// 获取编译对象
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			setParams(pstmt, params);
			// 执行查询操作，返回结果集
			rs = pstmt.executeQuery();
			// 获取所有的列名
			List<String> columnNames = getColumnNames(rs);
			// System.out.println(columnNames.size());
			while (rs.next()) {
				// 创建Map对象
				map = new HashMap<String, Object>();
				// map.put("",rs.getObject(""));
				// 循环列名
				for (String name : columnNames) {
					// 获取到值
					Object obj = rs.getObject(name);
					if (obj == null) {
						continue;// 执行下一次的循环
					}
					String typeName = obj.getClass().getName();
					// System.out.println(typeName);
					if ("oracle.sql.BLOB".equals(typeName)) {
//						//图片  以字节数组的形式存储到Map中
//						BLOB blob=(BLOB) rs.getBlob(name);
//						InputStream in=blob.getBinaryStream();
//						byte[] bt=new byte[(int) blob.length()];
//						in.read(bt);
//						in.close();
//						map.put(name, bt);
					} else if ("oracle.sql.CLOB".equals(typeName)) {
//						CLOB clob=(CLOB)rs.getClob(name);
//						Reader rd=clob.getCharacterStream();
//						char[] cs=new char[(int)clob.length()];
//						rd.read(cs);
//						String str=new String(cs);
//						rd.close();
//						map.put(name, str);
					} else {
						map.put(name, rs.getObject(name));
					}
				}
				// 添加List集合中
				list.add(map);
			}
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return list;
	}

	/**
	 * 重载update用于事务处理
	 * 
	 * @param sqls
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public int update(List<String> sqls, List<List<Object>> params) throws SQLException {
		int result = 0;
		try {
			conn = getConn();
			// 查看API conn 模式自动提交 事务设置为手动提交
			conn.setAutoCommit(false);
			// 循环sql语句，以及sql语句对应的参数list集合
			for (int i = 0; i < sqls.size(); i++) {
				// 通过指定的sql语句 连接对象获取预编译对象
				pstmt = conn.prepareStatement(sqls.get(i));// 获取sql语句对应的参数集合
				// 设置参数
				List<Object> param = params.get(i);
				setParams(pstmt, param);
				result = pstmt.executeUpdate();
				if (result <= 0) {
					conn.rollback();// 事务回滚
					return result;
				}
			}
			// 事务提交
			conn.commit();
		} catch (Exception e) {
			conn.rollback();// 事务回滚
			e.printStackTrace();
		} finally {
			// 还原事务的状态
			conn.rollback();
			closeAll(conn, pstmt, null);
		}
		return result;
	}
	
	/**
	 * 聚合函数查询  返回一列的值 sum avg count max min
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public double getPolymer(String sql,List<Object>params) throws SQLException {
		double result=0;
		try {
			// 获取连接对象
			conn = getConn();
			// 获取编译对象
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			setParams(pstmt, params);
			// 执行查询操作，返回结果集
			rs = pstmt.executeQuery();
			// 获取所有的列名
			List<String> columnNames = getColumnNames(rs);
			if (rs.next()) {
				result=rs.getDouble(1);//只有一列
			}
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return result;
	}
//	public static void main(String[] args) throws SQLException {
//		DbHelper db=new DbHelper();
//		System.out.println(db.getConn().getClass().getName());
//	}
}
