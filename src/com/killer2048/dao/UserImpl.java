package com.killer2048.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.killer2048.DbUtil.JdbcConnect;
import com.killer2048.bean.Answer;
import com.killer2048.bean.Exam;
import com.killer2048.bean.Question;
import com.killer2048.bean.User;

public class UserImpl implements UserFunc {

	protected List<Question> getQuestionsByIds(String questions) {
		// 由问题id组字符串获取问题集合,分隔符","
		return getQuestionsByIds(questions.split(","));
	}

	protected List<Question> getQuestionsByIds(String[] questions) {
		// 由字符串的id数组获取问题和选项集合
		String sql = "select qid,question,point from tab_question where qid=?";
		Connection conn = getConn();
		PreparedStatement ps = getStatement(conn, sql);
		ResultSet rs = null;
		List<Question> ret = new ArrayList<>();
		try {
			for (String qidstr : questions) {
				if (qidstr == null || qidstr.equals("")) {
					// 空id跳过
					continue;
				}
				int qid = Integer.parseInt(qidstr);
				ps.setInt(1, qid);
				rs = ps.executeQuery();
				if (rs.next()) {
					Question question = new Question();
					question.setQid(qid);
					question.setQuestion(rs.getString("question"));
					question.setPoint(rs.getInt("point"));
					ret.add(question);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcConnect.close(rs, ps, conn);
		}

		// 获取选项，调用setAnswers
		try {
			setAnswers(ret);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return ret;
	}

	protected List<Question> getQuestions(int count) {
		// 抽题，返回不带选项的问题list
		String sql = "select * from (select qid,question,point from tab_question order by sys_guid()) where rownum<"
				+ count;
		Connection conn = getConn();
		Statement st = null;
		ResultSet rs = null;
		List<Question> ret = null;
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcConnect.close(conn);
			return null;
		}
		try {
			rs = st.executeQuery(sql);
			ret = new ArrayList<Question>();
			while (rs.next()) {
				Question qtemp = new Question();
				qtemp.setQid(rs.getInt("qid"));
				qtemp.setQuestion(rs.getString("question"));
				qtemp.setPoint(rs.getInt("point"));
				ret.add(qtemp);
			}
		} catch (SQLException e) {
			// 出错返回null
			e.printStackTrace();
			return null;
		} finally {
			JdbcConnect.close(rs, st, conn);
		}
		return ret;
	}

	protected void setAnswers(List<Question> questions) throws SQLException {
		// 给题目集合添加选项
		String sql = "select aid,answer,isright from tab_answer where qid=? order by sys_guid()";
		Connection conn = getConn();
		PreparedStatement ps = getStatement(conn, sql);
		ResultSet rs = null;
		for (Question q : questions) {
			Map<String, Answer> answers = new HashMap<String, Answer>();
			int qid = q.getQid();
			ps.setInt(1, qid);
			rs = ps.executeQuery();
			while (rs.next()) {
				Answer answer = new Answer();
				int aid = rs.getInt("aid");
				answer.setAid(aid);
				answer.setAnswer(rs.getString("answer"));
				answer.setIsright(rs.getInt("isright"));
				answer.setQid(qid);
				answers.put(Integer.valueOf(aid).toString(), answer);
			}
			q.setAnswers(answers);
		}
		JdbcConnect.close(rs, ps, conn);
	}

	protected Connection getConn() {
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

	protected PreparedStatement getStatement(Connection conn, String sql) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcConnect.close(conn);
		}
		return ps;
	}

	protected String getQuestionsString(List<Question> questions) {
		// 由问题集合获取id组字符串
		StringBuffer sb = new StringBuffer();
		Iterator<Question> it = questions.iterator();
		while (it.hasNext()) {
			Question question = it.next();
			sb.append(question.getQid());
			if (it.hasNext()) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	protected int saveExam(Exam exam) {
		// 保存Exam到数据库
		String sql = "insert into tab_exam (examid,point,questions,answers,userid,starttime,endtime) values (SEQ_EXAMID.nextval,?,?,?,?,?,?)";
		Connection conn = getConn();
		PreparedStatement ps = getStatement(conn, sql);
		int ret = 0;
		try {
			ps.setInt(1, exam.getPoint());
			ps.setString(2, getQuestionsString(exam.getQuestions()));
			ps.setString(3, exam.getAnswers());
			ps.setInt(4, exam.getUserid());
			ps.setDate(5, exam.getStarttime());
			ps.setDate(6, exam.getEndtime());
			ret = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnect.close(ps, conn);
		}
		return ret;

	}

	protected Exam createExam(int userid, List<Question> questions) {
		Exam exam = new Exam();
		exam.setStarttime(new Date(System.currentTimeMillis()));
		exam.setUserid(userid);
		exam.setQuestions(questions);
		//TODO:考试时间限制，写死15分钟，单位秒
		exam.setLasted(15*60);
		return exam;

	}

	@Override
	public int reg(String username, String password) {
		String sql = "insert into tab_user (userid,username,password,rule) values(SEQ_USERID.nextval,?,?,0)";
		Connection conn = getConn();
		PreparedStatement ps = getStatement(conn, sql);
		int ret = 0;
		try {
			ps.setString(1, username);
			ps.setString(2, password);
			ret = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnect.close(ps, conn);
		}
		return ret;
	}

	@Override
	public int checkUsername(String username) {
		String sql = "select count(*) num from tab_user where username=?";
		Connection conn = getConn();
		PreparedStatement ps = getStatement(conn, sql);
		ResultSet rs = null;
		int ret = 0;
		try {
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				ret = rs.getInt("num");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnect.close(rs, ps, conn);
		}
		return ret;
	}

	@Override
	public User login(String username, String password) {
		String sql = "select userid,rule from tab_user where username=? and password=?";
		Connection conn = getConn();
		PreparedStatement ps = getStatement(conn, sql);
		ResultSet rs = null;
		User ret = null;
		try {
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				ret = new User();
				ret.setUserid(rs.getInt(1));
				ret.setRule(rs.getInt(2));
				ret.setPassword(password);
				ret.setUsername(username);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnect.close(rs, ps, conn);
		}
		return ret;
	}
	@Override
	public Exam startExam(int userid, int quesCount) {
		List<Question> questions = getQuestions(quesCount);
		try {
			setAnswers(questions);
		} catch (SQLException e) {
			// 出错返回null
			e.printStackTrace();
			return null;
		}
		Exam ret = createExam(userid, questions);
		return ret;

	}

	@Override
	public void endExam(Exam exam) {
		// 考试结束
		exam.setEndtime(new Date(System.currentTimeMillis()));
		saveExam(exam);

	}

	@Override
	public Exam getExamById(int examid) {
		// 通过id获取Exam
		String sql = "select point,questions,answers,userid,starttime,endtime from tab_exam where examid="+examid;
		Connection conn = getConn();
		Statement st = null;
		ResultSet rs = null;
		Exam ret = null;
		String questions = null;
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcConnect.close(conn);
			return null;
		}
		try {
			rs = st.executeQuery(sql);
			if(rs.next()){
				ret = new Exam();
				ret.setExamid(examid);
				ret.setPoint(rs.getInt("point"));
				questions = rs.getString("questions");
				ret.setAnswers(rs.getString("answers"));
				ret.setUserid(rs.getInt("userid"));
				ret.setStarttime(rs.getDate("starttime"));
				ret.setEndtime(rs.getDate("endtime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcConnect.close(rs, st, conn);
		}
		ret.setQuestions(getQuestionsByIds(questions));
		return ret;
		
	}

	@Override
	public List<Exam> getExamList(int userid) {
		//获取考试记录
		String sql = "select examid,point,starttime,endtime from tab_exam where userid="+userid;
		Connection conn = getConn();
		Statement st = null;
		ResultSet rs = null;
		List<Exam> ret = null;
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcConnect.close(conn);
			return null;
		}
		try {
			rs = st.executeQuery(sql);
			ret = new ArrayList<>();
			while(rs.next()){
				Exam examitem = new Exam();
				examitem.setExamid(rs.getInt("examid"));
				examitem.setPoint(rs.getInt("point"));
				examitem.setUserid(userid);
				examitem.setStarttime(rs.getDate("starttime"));
				examitem.setEndtime(rs.getDate("endtime"));
				ret.add(examitem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcConnect.close(rs, st, conn);
		}
		return ret;
		
	}

}
