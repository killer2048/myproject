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
 * ����Ա����ʵ����
 * 
 * @author killer2048
 *
 */
public class AdminImpl extends UserImpl implements AdminFunc {

	/**
	 * ������������ݿ�
	 * 
	 * @param question
	 *            ����ѡ����������
	 * @param conn
	 *            ʹ�õ����ݿ�����
	 * @return ������qid,Ϊ0ʱ����
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
			// ѡ��qid���е�ǰ��ֵ
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
	 * �����Ӧ�����ѡ��
	 * 
	 * @param qid
	 *            ����id
	 * @param answers
	 *            ѡ������
	 * @param conn
	 *            ʹ�õ����ݿ�����
	 * @return �ɹ���ʧ��<br/>
	 * 		�ɹ�������ѡ��ȫ������ɹ�<br/>
	 * 		ʧ�ܣ�������һ������ʧ��
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
				// ����Ӱ����Ŀ����Ϊ1
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
			//ѡ����С��1
			return false;
		}
		// �����Ŀ��ѡ���������װ
		Connection conn = getConn();
		try {
			// ���ý�ֹ�Զ��ύ
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
