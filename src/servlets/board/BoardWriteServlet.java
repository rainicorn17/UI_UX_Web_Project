package servlets.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import vo.Board;


@SuppressWarnings("serial")
@WebServlet("/write")
public class BoardWriteServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher rd = req.getRequestDispatcher
							("write.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			ServletContext sc = this.getServletContext();
			BoardDao boardDao = (BoardDao)sc.getAttribute("boardDao");

			boardDao.insert(new Board()
			        .setGenre(req.getParameter("genre"))
			        .setTitle(req.getParameter("title"))
			        .setContent(req.getParameter("content"))
			        .setWriter(req.getParameter("writer"))
			        .setUserId(Integer.parseInt(req.getParameter("userId"))));
			
			//resp.sendRedirect("../auth/login");		
			
		}catch(Exception e) {
			//throw new ServletException(e);
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
		}
	}
}
