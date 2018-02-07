package com.killer2048.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.killer2048.DbUtil.JdbcConnect;
import com.killer2048.bean.Answer;
import com.killer2048.bean.Question;

/**
 * 管理员功能实现类
 * 
 * @author killer2048
 *
 */
public class AdminImpl extends UserImpl implements AdminFunc {

	/**
	 * 将问题插入数据库
	 * 
	 * @param question
	 *            不含选项的问题对象
	 * @param conn
	 *            使用的数据库连接
	 * @return 插入后的qid,为0时出错
	 * @throws SQLException
	 */
	protected int saveQuestion(Question question, Connection conn) throws SQLException {
		int ret = 0;
		String sql = "insert into tab_question (qid,question,point) values (SEQ_QID.nextval,?,?)";
		PreparedStatement ps = getStatement(conn, sql);
		ps.setString(1, question.getQuestion());
		ps.setInt(2, question.getPoint());
		int flag = ps.executeUpdate();
		if (flag > 0) {
			// 选择qid序列当前的值
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select SEQ_QID.currval qid from dual");
			if (rs.next()) {
				ret = rs.getInt("qid");
			}
			JdbcConnect.close(rs, st, null);
		}
		JdbcConnect.close(ps, null);
		return ret;
	}

	/**
	 * 保存对应问题的选项
	 * 
	 * @param qid
	 *            问题id
	 * @param answers
	 *            选项数组
	 * @param conn
	 *            使用的数据库连接
	 * @return 成功或失败<br/>
	 * 		成功：所有选项全部插入成功<br/>
	 * 		失败：至少有一个插入失败
	 * @throws SQLException
	 */
	protected boolean saveAnswers(int qid, List<Answer> answers, Connection conn) throws SQLException {
		if (qid == 0) {
			return false;
		}
		String sql = "insert into tab_answer (aid,answer,isright,qid) values (SEQ_AID.nextval,?,?," + qid + ")";
		PreparedStatement ps = getStatement(conn, sql);
		for (Answer answer : answers) {
			ps.setString(1, answer.getAnswer());
			ps.setInt(2, answer.getIsright());
			if (ps.executeUpdate() != 1) {
				// 插入影响条目数不为1
				JdbcConnect.close(ps, null);
				return false;
			}
		}
		JdbcConnect.close(ps, null);
		return true;

	}

	@Override
	public boolean addQuestion(Question question) {
		if(question.getAnswers()==null){
			return false;
		}
		return addQuestion(question, new ArrayList<>(question.getAnswers().values()));

	}

	@Override
	public boolean addQuestion(Question question, List<Answer> answers) {
		if(answers.size()<1){
			//选项数小于1
			return false;
		}
		// 添加题目和选项用事务封装
		Connection conn = getConn();
		try {
			// 设置禁止自动提交
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			JdbcConnect.close(conn);
			e.printStackTrace();
			return false;
		}

		try {
			int qid = saveQuestion(question, conn);
			Boolean success = saveAnswers(qid, answers, conn);
			if (success) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return false;
			}
			return false;
		} finally {
			JdbcConnect.close(conn);
		}
		return true;

	}

}
