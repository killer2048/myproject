package com.neuedu.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.killer2048.bean.Exam;
import com.killer2048.service.Service;
import com.killer2048.service.ServiceImpl;

/**
 * Servlet implementation class GetExamServlet
 */
@WebServlet("/GetExamServlet")
public class GetExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetExamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("examid");
		HttpSession session = request.getSession();
		Exam exam = null;
		if(id==null||id.equals("")){
			exam = (Exam)session.getAttribute("exam");
		} else {
			int examid = Integer.parseInt(id);
			Service s = new ServiceImpl();
			exam = s.getExamById(examid);
		}
		request.setAttribute("exam", exam);
		request.getRequestDispatcher("examinfo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
