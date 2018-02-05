package com.killer2048.dao;

import java.util.List;

import com.killer2048.bean.Question;
import com.killer2048.bean.User;

public interface UserFunc {
	//注册
	public int reg(String username,String password);
	//用户名是否重复
	public int checkUsername(String username);
	//注册
	public User login(String username,String password);
	//进入答题
	public List<Question> startExam(int id);
	//提交成绩
	
	//查看记录
	
}
