package com.killer2048.tools;

import java.util.ArrayList;
import java.util.List;

import com.killer2048.bean.Answer;
import com.killer2048.bean.Question;

/**
 * ������صĹ�����
 * @author killer2048
 *
 */
public class Questions {
	
	
	/**
	 * ����ɺͷ�������һ��Question����
	 * @param qstr �������
	 * @param point ��ֵ
	 * @return ����answers��Question����
	 */
	public static Question createQuestion(String qstr,int point){
		
		Question question = new Question();
		question.setQuestion(qstr);
		question.setPoint(point);
		return question;
	}
	
	
	/**
	 * �����ֺ��Ƿ���ȷ����һ��ѡ�����
	 * @param astr ѡ������
	 * @param isright ѡ���Ƿ�Ϊ��ȷ��
	 * @return һ��Answer����
	 */
	public static Answer createAnswer(String astr,int isright){
		Answer answer = new Answer();
		answer.setAnswer(astr);
		answer.setIsright(isright);
		return answer;
	}
	
	
	/**
	 * ���ַ���������������Answer��������鳤�Ȳ�ͬ��Ϊ0����null
	 * @param astrarr ����ַ������飬"@@"�ָ�
	 * @param isrightarr �Ƿ���ȷ���ַ������飬"@@"�ָ�
	 * @return �𰸵�List����
	 */
	public static List<Answer> createAnswers(String[] astrarr,String[] isrightarr){
		if(astrarr.length!=isrightarr.length||astrarr.length==0){
			//���Ȳ�ͬ��Ϊ��
			return null;
		}
		List<Answer> answers = new ArrayList<>();
		for(int i = 0;i<astrarr.length;i++){
			answers.add(createAnswer(astrarr[i], Integer.parseInt(isrightarr[i])));
		}
		return answers;
	}

}
