package com.killer2048.tools;

import java.util.ArrayList;
import java.util.List;

import com.killer2048.bean.Answer;
import com.killer2048.bean.Question;

/**
 * 问题相关的工具类
 * @author killer2048
 *
 */
public class Questions {
	
	
	/**
	 * 由题干和分数创建一个Question对象
	 * @param qstr 问题提干
	 * @param point 分值
	 * @return 不含answers的Question对象
	 */
	public static Question createQuestion(String qstr,int point){
		
		Question question = new Question();
		question.setQuestion(qstr);
		question.setPoint(point);
		return question;
	}
	
	
	/**
	 * 由文字和是否正确创建一个选项对象
	 * @param astr 选项文字
	 * @param isright 选项是否为正确答案
	 * @return 一个Answer对象
	 */
	public static Answer createAnswer(String astr,int isright){
		Answer answer = new Answer();
		answer.setAnswer(astr);
		answer.setIsright(isright);
		return answer;
	}
	
	
	/**
	 * 由字符串数组批量创建Answer，如果数组长度不同或为0返回null
	 * @param astrarr 题干字符串数组，"@@"分割
	 * @param isrightarr 是否正确的字符串数组，"@@"分割
	 * @return 答案的List集合
	 */
	public static List<Answer> createAnswers(String[] astrarr,String[] isrightarr){
		if(astrarr.length!=isrightarr.length||astrarr.length==0){
			//长度不同或为空
			return null;
		}
		List<Answer> answers = new ArrayList<>();
		for(int i = 0;i<astrarr.length;i++){
			answers.add(createAnswer(astrarr[i], Integer.parseInt(isrightarr[i])));
		}
		return answers;
	}

}
