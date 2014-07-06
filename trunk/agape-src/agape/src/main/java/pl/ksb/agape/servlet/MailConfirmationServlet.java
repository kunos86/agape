package pl.ksb.agape.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.ksb.agape.domain.dao.ActivationTokenDAOBean;
import pl.ksb.agape.domain.dao.UserDAOBean;
import pl.ksb.agape.domain.model.ActivationToken;
import pl.ksb.agape.domain.model.User;

/**
 * Servlet implementation class MailConfirmationServlet
 */
@WebServlet(name = "mailConfirmationServlet", urlPatterns = { "/mailConfirmationServlet" })
public class MailConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private ActivationTokenDAOBean activationTokenDAOBean;

	@EJB
	private UserDAOBean userDAOBean;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MailConfirmationServlet() {
		super();
	}

	private void activation(HttpServletRequest request,
			HttpServletResponse response) {

		String tokenParam = request.getParameter("t");
		ActivationToken at = activationTokenDAOBean.getToken(tokenParam);
		if (at != null) {
			User u = userDAOBean.getById(at.getUserId());
			u.setMailConfirmation(true);
			userDAOBean.saveOrUpdate(u);
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		activation(request, response);
		redirect(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		activation(request, response);
		redirect(request, response);
	}

	private void redirect(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String loginURL = request.getContextPath()
				+ "/pages/home.jsf?reloadUser";
		response.sendRedirect(loginURL);

	}

}
