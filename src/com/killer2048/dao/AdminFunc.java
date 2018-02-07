package com.killer2048.dao;

import java.util.List;

import com.killer2048.bean.Answer;
import com.killer2048.bean.Question;

public interface AdminFunc extends UserFunc {
	/**
	 * ��ӵ�������
	 * @param question <b>����</b>ѡ���Question����
	 * @return �Ƿ�ɹ�
	 */
	public boolean addQuestion(Question question);
	
	/**
	 * ��ӵ�������
	 * @param question <b>������</b>ѡ���Question����
	 * @param answers  ѡ��ļ���
	 * @return �Ƿ�ɹ�
	 */
	public boolean addQuestion(Question question,List<Answer> answers);
	
}
