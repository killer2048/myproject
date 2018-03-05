<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
剩余时间：<span id="timer"></span>
</div>
<form action="ExamServlet" method="post">
	<input type="hidden" name="answers" value="">
</form>
</body>
<script>
/*
var exam ={
		examid:${sessionScope.exam.examid},
		starttime:"${sessionScope.exam.starttime}",
		lasted:${sessionScope.exam.lasted},
		question:[<c:forEach items="${sessionScope.exam.questions}" var="questionitem" varStatus="status">{
					qid:${questionitem.qid},
					question:"${questionitem.question}",
					point:${questionitem.point},
					answers:[<c:forEach items="${questionitem.answers}" var="answeritem" varStatus="status2">{qid:${answeritem.value.qid},answer:"${answeritem.value.answer}"}${status2.last?"":","}</c:forEach>]
				}${status.last?"":","}</c:forEach>]
		};
		*/
(function(e){
	var examitem = {};
	var examid=${sessionScope.exam.examid};
	var starttime=Date.parse("${sessionScope.exam.starttime}+08:00".replace(" ","T"));//ISO 8601 的完整日期格式是 YYYY-MM-DDThh:mm:ss.sTZD
	var lasted=${sessionScope.exam.lasted}*1000;//毫秒
	var endtime = starttime+lasted;
	var question=[<c:forEach items="${sessionScope.exam.questions}" var="questionitem" varStatus="status">{
						qid:${questionitem.qid},
						question:"${questionitem.question}",
						point:${questionitem.point},
						answers:[<c:forEach items="${questionitem.answers}" var="answeritem" varStatus="status2">{qid:${answeritem.value.qid},answer:"${answeritem.value.answer}"}${status2.last?"":","}</c:forEach>]
					}${status.last?"":","}</c:forEach>];
	var currentQuestion=0;
	var count=question.length;
	var answerarr = [];
	var timerele = document.getElementById("timer");
	//初始化答案
	for(var i=0;i<count;i++){
		answerarr[i]=0;
	}
	function setQuestion(id){
		//设置并显示指定问题
		currentQuestion = id;
	}
	//设置考试
	examitem.setExam=function(){
		//设置计时器，调用显示第一题
		var timer = setInterval(function(){
			var now = new Date().getTime();
			if(now>=endtime){
				clearInterval(timer);
				examitem.endExam();
			} else {
				timerele.innerHTML=parseInt(((lasted-(now-starttime))/1000));
			}
		},200);
		var now = new Date().getTime();
		setQuestion(0);
	},
	//下一题
	examitem.nextQuestion=function(){
		if(currentQuestion>=count-1){
			alert("已经是最后一道题");
			return;
		}
		examitem.selectQuestion(currentQuestion+1);
	},
	//上一题
	examitem.previousQuestion=function(){
		if(currentQuestion<=0){
			alert("已经是第一道题");
			return;
		}
		examitem.selectQuestion(currentQuestion-1);
	},
	//选择题号
	examitem.selectQuestion=function(id){
		if(id<0||id>=count){
			alert("无效题号");
			return;
		}
		//更新答案数组
		examitem.updateAnswers();
		setQuestion(id);
	}
	//更新答案
	examitem.updateAnswers=function(){
		//获取当前的答案并用对应的id加入answerarr
		
		//获取aid
		var aid = 2;
		
		answerarr[currentQuestion] = aid;
		
		console.log(answerarr);
	},
	examitem.endExam = function(){
		examitem.updateAnswers();
		//提交答案
		console.log(answerarr);
	}
	e.exam=examitem;
}(window))


		
		window.onload=function(){
			exam.setExam();
		}
		
	

//最终样子
/*
var a = {
		examid:0,
		starttime:"2018-02-09 16:57:29.156",
		lasted:900,
		question:[
			{
				qid:2,
				question:"dksajfhaskjdfhlaskdjfhsd",
				point:20,
				answers:[
					{
						aid:4,
						answer:11113
					},
					{
						aid:4,
						answer:11113
					},
					{
						aid:4,
						answer:11113
					},
				]
			},
			{
				qid:2,
				question:"dksajfhaskjdfhlaskdjfhsd",
				point:20,
				answers:[
					{
						aid:4,
						answer:11113
					},
					{
						aid:4,
						answer:11113
					},
					{
						aid:4,
						answer:11113
					},
				]
			}
		]


	}*/
</script>
</html>