package com.killer2048.bean;

public class Answer {
	private int aid;
	private String answer;
	//0��1��
	private int isright;
	//��Ӧ����id
	private int qid;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public int getIsright() {
		return isright;
	}
	public void setIsright(int isright) {
		this.isright = isright;
	}
	
}
