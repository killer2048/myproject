package com.killer2048.service;

import java.util.List;

import com.killer2048.bean.Answer;
import com.killer2048.bean.Exam;
import com.killer2048.bean.Question;
import com.killer2048.bean.User;

public interface Service {
	/**
	 * 注册
	 * @param username 用户名
	 * @param password 密码
	 * @return 1成功，0失败
	 */
	public int reg(String username,String password);
	
	/**
	 * 用户名是否重复
	 * @param username 用户名
	 * @return 0 未重复，>0 重复
	 */
	public int checkUsername(String username);
	
	/**
	 * 登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 当前登录的User对象
	 */
	public User login(String username,String password);
	
	/**
	 * 进入答题并选择10道题
	 * @param userid 用户id
	 * @return Exam对象，包含题目和userid,starttime
	 */
	public Exam startExam(int userid);
	
	/**
	 * 进入答题并选择给定数量的题
	 * @param userid 用户id
	 * @param quesCount 出题数量
	 * @return Exam对象，包含题目和userid,starttime
	 */
	public Exam startExam(int userid,int quesCount);
	
	/**
	 * 考试结束，保存信息
	 * @param exam 已经判卷的Exam对象（填写point和answers）
	 */
	public void endExam(Exam exam);
	
	/**
	 * 查看记录
	 * @param userid 用户id
	 * @return 一个List<Exam>，元素中只有examid,point,starttime,endtime,userid
	 */
	public List<Exam> getExamList(int userid);
	
	/**
	 * 获取详细考试记录
	 * @param examid 考试id
	 * @return 完整的Exam对象
	 */
	public Exam getExamById(int examid);

	/**
	 * 添加单个问题
	 * @param question <b>包含</b>选项的Question对象
	 * @return 是否成功
	 */
	public boolean addQuestion(Question question);
	
	/**
	 * 添加单个问题
	 * @param question <b>不包含</b>选项的Question对象
	 * @param answers  选项的集合
	 * @return 是否成功
	 */
	public boolean addQuestion(Question question,List<Answer> answers);
}
