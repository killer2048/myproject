package com.neuedu.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.killer2048.service.Service;
import com.killer2048.service.ServiceImpl;





/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RegServlet() {
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
		int number;
		number = service.reg(username,password);
		if(number==0){
			//×¢²áÊ§°Ü
			response.sendRedirect(request.getContextPath()+"/reg.jsp");
		}else if(number==1){
			//×¢²á³É¹¦    ½øÈëµÇÂ¼Ò³Ãæ
			response.sendRedirect(request.getContextPath()+"/lgoin.jsp");
		}
	}

}
