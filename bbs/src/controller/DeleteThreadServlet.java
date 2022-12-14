package controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ThreadDAO;
import etc.Token;

/**
 * Servlet implementation class DeleteThreadServlet
 */
@WebServlet("/DeleteThreadServlet")
public class DeleteThreadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteThreadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession(false);

	    String url;

		if (!Token.csrfCheck(session, request, response)) {
			url = "ThreadServlet";
	    	response.sendRedirect(url);
	    	return;
		}
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    HttpSession session = request.getSession(false);

		String message;
		String url;

		if (!Token.csrfCheck(session, request, response)) {
			url = "ThreadServlet";
	    	response.sendRedirect(url);
	    	return;
		}

		ThreadDAO dao = new ThreadDAO();

		int thread_id = Integer.parseInt(request.getParameter("thread_id"));
		int user_id = (int)session.getAttribute("user_id");
		dao.deleteThread(thread_id, user_id);
		message = URLEncoder.encode("スレッドを削除しました。", "UTF-8");
		url = "ThreadServlet?message_done=" + message;
	    response.sendRedirect(url);
	}
}