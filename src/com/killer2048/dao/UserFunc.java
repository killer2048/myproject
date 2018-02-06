package com.killer2048.dao;


import com.killer2048.bean.Exam;
import com.killer2048.bean.User;

public interface UserFunc {
	//注册
	public int reg(String username,String password);
	//用户名是否重复
	public int checkUsername(String username);
	//注册
	public User login(String username,String password);
	//进入答题
	public Exam startExam(int userid);
	//进入答题并选择给定数量的题
	public Exam startExam(int userid,int quesCount);
	
	//提交成绩
	public void endExam(Exam exam);
	
	//查看记录
	
}
