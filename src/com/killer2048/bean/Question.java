package com.killer2048.bean;

import java.util.List;
import java.util.Map;

public class Question {
	private int qid;
	private String question;
	private int point;
	//         选项编号  选项对象
	//          aid =>Answer对象
	private Map<String,Answer> answers;
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public Map<String,Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(Map<String,Answer> answers) {
		this.answers = answers;
	}
	
}
