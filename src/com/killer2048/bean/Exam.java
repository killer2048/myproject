package com.killer2048.bean;

import java.sql.Timestamp;
import java.util.List;

public class Exam {
	private int examid;
	private List<Question> questions;
	private String answers;
	private int point;
	private int userid;
	private Timestamp starttime;
	private int lasted;
	private Timestamp endtime;
	public int getLasted() {
		return lasted;
	}
	public void setLasted(int lasted) {
		this.lasted = lasted;
	}
	
	public Timestamp getStarttime() {
		return starttime;
	}
	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}
	public Timestamp getEndtime() {
		return endtime;
	}
	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}
	public int getExamid() {
		return examid;
	}
	public void setExamid(int examid) {
		this.examid = examid;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public String getAnswers() {
		return answers;
	}
	public void setAnswers(String answers) {
		this.answers = answers;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
}
