package com.killer2048.dao;

import java.util.List;

import com.killer2048.bean.Question;
import com.killer2048.bean.User;

public interface UserFunc {
	//ע��
	public int reg(String username,String password);
	//�û����Ƿ��ظ�
	public int checkUsername(String username);
	//ע��
	public User login(String username,String password);
	//�������
	public List<Question> startExam(int userid);
	//������Ⲣѡ�������������
	public List<Question> startExam(int userid,int quesCount);
	
	//�ύ�ɼ�
	
	//�鿴��¼
	
}
