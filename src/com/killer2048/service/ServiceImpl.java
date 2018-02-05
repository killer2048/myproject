package com.killer2048.service;

import java.util.List;

import com.killer2048.bean.Question;
import com.killer2048.bean.User;
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
	public List<Question> startExam(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
