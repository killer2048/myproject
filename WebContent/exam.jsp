<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>答题</title>
</head>
<body>
	<div class="exam-container">
		<div>剩余时间：<span id="timer"></span></div>
		<div id="question"></div>
		<div>分值: <span id="point"></span></div>
		<hr>
		<ul id="answerlist">
			
		</ul>
	</div>
	
	<div id="question-selector">
		<button id="previous">上一题</button>
		<button id=next>下一题</button>
	</div>
	<hr>
	<button id="endExam">交卷</button>

<form action="ExamServlet" method="post" id="answerform">
	<input type="hidden" name="answers" value="" id="answerstr">
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
					answers:[<c:forEach items="${questionitem.answers}" var="answeritem" varStatus="status2">{aid:${answeritem.value.aid},answer:"${answeritem.value.answer}"}${status2.last?"":","}</c:forEach>]
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
						answers:[<c:forEach items="${questionitem.answers}" var="answeritem" varStatus="status2">{aid:${answeritem.value.aid},answer:"${answeritem.value.answer}"}${status2.last?"":","}</c:forEach>]
					}${status.last?"":","}</c:forEach>];
	var currentQuestion=0;
	var count=question.length;
	var answerarr = [];
	var timerele = document.getElementById("timer");
	var questinele = document.getElementById("question");
	var answerlist = document.getElementById("answerlist");
	var pointele = document.getElementById("point");
	//初始化答案
	for(var i=0;i<count;i++){
		answerarr[i]=0;
	}
	function setQuestion(id){
		//设置并显示指定问题
		currentQuestion = id;
		var curr = question[id];
		questinele.innerHTML=id+1+"."+curr.question;
		pointele.innerHTML=curr.point;
		var answers = curr.answers;
		while(answerlist.hasChildNodes()) 
    	{
    		//删除answerlist下的所有节点
        	answerlist.removeChild(answerlist.firstChild);
    	}
    	for(var i=0;i<answers.length;i++){
    		var li = document.createElement("li");
    		var radio = document.createElement("input");
    		var label = document.createElement("label");
    		radio.type="radio";
    		radio.name="answeritems";
    		radio.value=answers[i].aid;
    		radio.id="ans"+i;
    		label.for="ans"+i;
    		label.innerHTML=answers[i].answer;
    		li.appendChild(radio);
    		li.appendChild(label);
    		answerlist.appendChild(li);
    		}

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
		var previous = document.getElementById("previous");
		var next = document.getElementById("next");
		var end = document.getElementById("endExam");
		previous.addEventListener("click",examitem.previousQuestion);
		next.addEventListener("click",examitem.nextQuestion);
		end.addEventListener("click",examitem.endExam);
	};
	//下一题
	examitem.nextQuestion=function(){
		if(currentQuestion>=count-1){
			alert("已经是最后一道题");
			return;
		}
		examitem.selectQuestion(currentQuestion+1);
	};
	//上一题
	examitem.previousQuestion=function(){
		if(currentQuestion<=0){
			alert("已经是第一道题");
			return;
		}
		examitem.selectQuestion(currentQuestion-1);
	};
	//选择题号
	examitem.selectQuestion=function(id){
		if(id<0||id>=count){
			alert("无效题号");
			return;
		}
		//更新答案数组
		examitem.updateAnswers();
		setQuestion(id);
	};
	//更新答案
	examitem.updateAnswers=function(){
		//获取当前的答案并用对应的id加入answerarr
		var answers = document.getElementsByName("answeritems");
		var aid = 0;
		for(var i=0;i<answers.length;i++){
			if(answers[i].checked==true){
				aid = parseInt(answers[i].value);
				break;
			}
		}
		answerarr[currentQuestion] = aid;
		console.log(answerarr);
	};
	examitem.endExam = function(){
		examitem.updateAnswers();
		//确认交卷
		if(!confirm("确认交卷")){
			return;
		}
		var str = "";
		for(var i=0;i<count;i++){
			str+=answerarr[i];
			if(i!=count-1){
				str+=",";
			}
		}
		console.log(str);
		var answerstrele = document.getElementById("answerstr");
		answerstrele.value=str;
		var form = document.getElementById("answerform");
		form.submit();
	};
	e.exam=examitem;
}(window))


		
		window.onload=function(){
			exam.setExam();
		}
		
</script>
</html>