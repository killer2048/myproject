package com.killer2048.service;

import java.util.List;

import com.killer2048.bean.Answer;
import com.killer2048.bean.Exam;
import com.killer2048.bean.Question;
import com.killer2048.bean.User;
import com.killer2048.dao.AdminFunc;
import com.killer2048.dao.AdminImpl;
import com.killer2048.dao.UserFunc;
import com.killer2048.dao.UserImpl;

public class ServiceImpl implements Service {
	@Override
	public int reg(String username, String password) {
		UserFunc u = new UserImpl();
		return u.reg(username, password);
	}

	@Override
	public int checkUsername(String username) {
		UserFunc u = new UserImpl();
		return u.checkUsername(username);
	}

	@Override
	public User login(String username, String password) {
		UserFunc u = new UserImpl();
		return u.login(username, password);
	}

	@Override
	public Exam startExam(int userid) {
		// Ä¬ÈÏ³é10¸öÌâ
		return startExam(userid,10);
	}

	@Override
	public Exam startExam(int userid, int quesCount) {
		UserFunc u = new UserImpl();
		return u.startExam(userid,quesCount);
	}

	@Override
	public void endExam(Exam exam) {
		UserFunc u = new UserImpl();
		u.endExam(exam);
		
	}

	@Override
	public List<Exam> getExamList(int userid) {
		UserFunc u = new UserImpl();
		return u.getExamList(userid);
	}

	@Override
	public Exam getExamById(int examid) {
		UserFunc u = new UserImpl();
		return u.getExamById(examid);
	}

	@Override
	public boolean addQuestion(Question question) {
		AdminFunc a = new AdminImpl();
		return a.addQuestion(question);
	}

	@Override
	public boolean addQuestion(Question question, List<Answer> answers) {
		AdminFunc a = new AdminImpl();
		return a.addQuestion(question,answers);
	}

	
	

}
