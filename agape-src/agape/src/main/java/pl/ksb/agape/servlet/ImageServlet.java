package pl.ksb.agape.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.ksb.agape.domain.dao.QuestionAdditionDAOBean;
import pl.ksb.agape.domain.model.QuestionAddition;

/**
 * Servlet do pobierana plików graficznych załączanych do pytań.
 */

@WebServlet(name = "imageServlet", urlPatterns = { "/imageServlet" })
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@EJB
	private QuestionAdditionDAOBean questionAdditionDAOBean;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id")); 
		QuestionAddition qa = questionAdditionDAOBean.getById(id);
		byte[] data = qa.getContent();
		String filename = qa.getFileName();

		response.setContentType(getServletContext().getMimeType(filename));
		response.setContentLength(data.length);
		response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");

		BufferedInputStream input = null;
		BufferedOutputStream output = null;

		try {
		    input = new BufferedInputStream(new ByteArrayInputStream(data));
		    output = new BufferedOutputStream(response.getOutputStream());
		    byte[] buffer = new byte[8192];
		    int length;
		    while ((length = input.read(buffer)) > 0) {
		        output.write(buffer, 0, length);
		    }
		} finally {
		    if (output != null) try { output.close(); } catch (IOException logOrIgnore) {}
		    if (input != null) try { input.close(); } catch (IOException logOrIgnore) {}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
