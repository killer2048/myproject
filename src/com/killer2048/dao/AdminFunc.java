package com.killer2048.dao;

import java.util.List;

import com.killer2048.bean.Answer;
import com.killer2048.bean.Question;

public interface AdminFunc extends UserFunc {
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
