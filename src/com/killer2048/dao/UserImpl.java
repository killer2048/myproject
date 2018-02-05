package com.killer2048.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.killer2048.DbUtil.JdbcConnect;
import com.killer2048.bean.Question;
import com.killer2048.bean.User;

public class UserImpl implements UserFunc {
	protected Connection getConn(){
		Connection conn = null;
		try {
			conn = JdbcConnect.getInstance().getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	protected PreparedStatement getStatement(Connection conn,String sql){
		PreparedStatement ps= null;
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcConnect.close(conn);
		}
		return ps;
	}
	
	@Override
	public int reg(String username, String password) {
		String sql = "insert into tab_user (userid,username,password,rule) values(SEQ_USERID.nextval,?,?,0)";
		Connection conn = getConn();
		PreparedStatement ps= getStatement(conn,sql);
		int ret = 0;
		try {
			ps.setString(1, username);
			ps.setString(2, password);
			ret = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcConnect.close(ps,conn);
		}
		return ret;
	}

	@Override
	public int checkUsername(String username) {
		String sql = "select count(*) num from tab_user where username=?";
		Connection conn = getConn();
		PreparedStatement ps= getStatement(conn,sql);
		ResultSet rs= null;
		int ret = 0;
		try {
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()){
				ret = rs.getInt("num");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcConnect.close(rs,ps,conn);
		}
		return ret;
	}

	@Override
	public User login(String username, String password) {
		String sql = "select userid,rule from tab_user where username=? and password=?";
		Connection conn = getConn();
		PreparedStatement ps = getStatement(conn, sql);
		ResultSet rs= null;
		User ret = null;
		try {
			ps.setString(1, username);
			ps.setString(2, "password");
			rs = ps.executeQuery();
			if(rs.next()){
				ret = new User();
				ret.setUserid(rs.getInt(1));
				ret.setRule(rs.getInt(2));
				ret.setPassword(password);
				ret.setUsername(username);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcConnect.close(rs,ps,conn);
		}
		return ret;
	}

	@Override
	public List<Question> startExam(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
