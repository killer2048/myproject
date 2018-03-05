package com.neuedu.Servlet;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class ExamlistServlet
 */
@WebServlet("/ExamlistServlet")
public class ExamlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamlistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("loginuser");
		if(user!=null){
			int userid = user.getUserid();
			Service s = new ServiceImpl();
			List<Exam> examlist = s.getExamList(userid);
			request.setAttribute("examlist", examlist);
			request.getRequestDispatcher("/examlist.jsp").forward(request, response);
		}else{
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
