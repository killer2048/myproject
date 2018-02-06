package com.killer2048.dao;


import java.util.List;

import com.killer2048.bean.Exam;
import com.killer2048.bean.User;

public interface UserFunc {
	//ע��
	public int reg(String username,String password);
	//�û����Ƿ��ظ�
	public int checkUsername(String username);
	//ע��
	public User login(String username,String password);
	//�������
	public Exam startExam(int userid);
	//������Ⲣѡ�������������
	public Exam startExam(int userid,int quesCount);
	
	//�ύ�ɼ�
	public void endExam(Exam exam);
	//�鿴��¼(��ȡexamid��ʱ�����,һ��List<Exam>��Ԫ����ֻ��examid,point,starttime,endtime,userid)
	public List<Exam> getExamList(int userid);
	//��id��ȡExam
	public Exam getExamById(int examid);
	
}
