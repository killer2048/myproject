package com.neuedu.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.killer2048.bean.Exam;
import com.killer2048.bean.User;
import com.killer2048.service.Service;
import com.killer2048.service.ServiceImpl;

/**
 * Servlet implementation class ExamServlet
 */
@WebServlet("/StartExamServlet")
public class StartExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartExamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("loginuser");
		if(user==null){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}else{
			int userid=user.getUserid();
			Service service=new ServiceImpl();
			Exam exam=null;
			exam=service.startExam(userid);
			session.setAttribute("exam", exam);
			response.sendRedirect(request.getContextPath()+"/exam.jsp");
		}
	
	}

}
