package com.killer2048.service;

import java.util.List;

import com.killer2048.bean.Answer;
import com.killer2048.bean.Exam;
import com.killer2048.bean.Question;
import com.killer2048.bean.User;

public interface Service {
	/**
	 * ע��
	 * @param username �û���
	 * @param password ����
	 * @return 1�ɹ���0ʧ��
	 */
	public int reg(String username,String password);
	
	/**
	 * �û����Ƿ��ظ�
	 * @param username �û���
	 * @return 0 δ�ظ���>0 �ظ�
	 */
	public int checkUsername(String username);
	
	/**
	 * ��¼
	 * @param username �û���
	 * @param password ����
	 * @return ��ǰ��¼��User����
	 */
	public User login(String username,String password);
	
	/**
	 * ������Ⲣѡ��10����
	 * @param userid �û�id
	 * @return Exam���󣬰�����Ŀ��userid,starttime
	 */
	public Exam startExam(int userid);
	
	/**
	 * ������Ⲣѡ�������������
	 * @param userid �û�id
	 * @param quesCount ��������
	 * @return Exam���󣬰�����Ŀ��userid,starttime
	 */
	public Exam startExam(int userid,int quesCount);
	
	/**
	 * ���Խ�����������Ϣ
	 * @param exam �Ѿ��о��Exam������дpoint��answers��
	 */
	public void endExam(Exam exam);
	
	/**
	 * �鿴��¼
	 * @param userid �û�id
	 * @return һ��List<Exam>��Ԫ����ֻ��examid,point,starttime,endtime,userid
	 */
	public List<Exam> getExamList(int userid);
	
	/**
	 * ��ȡ��ϸ���Լ�¼
	 * @param examid ����id
	 * @return ������Exam����
	 */
	public Exam getExamById(int examid);

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
