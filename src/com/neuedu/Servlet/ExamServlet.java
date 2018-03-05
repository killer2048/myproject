package com.neuedu.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.killer2048.bean.Answer;
import com.killer2048.bean.Exam;
import com.killer2048.bean.Question;
import com.killer2048.service.Service;
import com.killer2048.service.ServiceImpl;



/**
 * Servlet implementation class ExamServlet
 */
@WebServlet("/ExamServlet")
public class ExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		Exam exam=new Exam();
		exam=(Exam)session.getAttribute("exam");
		Answer answer=new Answer();
		Question question=new Question();
		List<Question>list=new ArrayList<>();
		String aids[]=new String[list.size()];
		list=exam.getQuestions();
		String answers;
		answers=request.getParameter("answers");
		aids=answers.split(",");
		Service service=new ServiceImpl();
		int point=0;
		int isright;
		for(int i=0;i<list.size();i++){
			if(!aids[i].equals("0")){
			//list的第一个元素.得到map类型的answer.得到键对应的answer对象.是不是对的（0错,1对）
			question=list.get(i);
			answer=question.getAnswers().get(aids[i]);
			if(answer!=null){
				isright=answer.getIsright();
				if(isright==1){
					point+=question.getPoint();
				}
			}
			}
		}
		exam.setAnswers(answers);
		exam.setPoint(point);
		service.endExam(exam);
		response.sendRedirect(request.getContextPath()+"/GetExamServlet");
		
		
	}

}
