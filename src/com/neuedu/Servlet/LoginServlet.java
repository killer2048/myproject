package com.neuedu.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.killer2048.bean.User;
import com.killer2048.service.Service;
import com.killer2048.service.ServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Service service=new ServiceImpl();
		User loginuser=service.login(username,password);
		HttpSession session=request.getSession();
		if(loginuser==null){
			//µÇÂ¼Ê§°Ü
			response.sendRedirect(request.getContextPath()+"/login.jsp?fail=true");
		}else{
			//µÇÂ¼³É¹¦
			session.setAttribute("loginuser", loginuser);
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
	}

}
