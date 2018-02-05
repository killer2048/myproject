package com.killer2048.DbUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnect {

	private static JdbcConnect db;
	
	public synchronized static JdbcConnect getInstance(){
		if(db==null){
			db = new JdbcConnect();
		}
		return db;
	}
	private String driver;
	private String url;
	private String name;
	private String pw;

	private JdbcConnect() {
		//获取配置
		Properties pr = new Properties();
		try {
			//获取类加载器（src）->获取指定资源的输入流->load
			pr.load(this.getClass().getClassLoader().getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver = pr.getProperty("driver");
		url = pr.getProperty("url");
		name = pr.getProperty("username");
		pw = pr.getProperty("password");
		
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName(driver);
		return DriverManager.getConnection(url, name, pw);
	}
	public static void close(PreparedStatement ps,Connection conn){
		try {
			if(ps!=null){
				ps.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(conn);
	}
	public static void close(Connection conn){
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs,PreparedStatement ps,Connection conn){
		try {
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(ps,conn);
	}
	
}
