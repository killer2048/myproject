package com.killer2048.service;

import java.util.List;

import com.killer2048.bean.Question;
import com.killer2048.bean.User;

public interface Service {
	//ע��
		public int reg(String username,String password);
		//�û����Ƿ��ظ�
		public int checkUsername(String username);
		//ע��
		public User login(String username,String password);
		//�������
		public List<Question> startExam(int id);
}
