package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ressources.Tournament;
import ressources.Tournament.AlreadyStarted;
import ressources.Tournament.NotEnoughPlayer;
import ressources.TournamentsCluster;

public class StartTournamentServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Tournament tournament = TournamentsCluster.getTournament();
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Start tournament</title></head>");
			try {
				tournament.startTournament();
				out.println("<p>Tournament is now running (yeah this is really a bad phrase but it's late</p>");
			} catch (AlreadyStarted e) {
				out.println("<p>Tournament is already running </p>");
			} catch (NotEnoughPlayer e) {
				out.println("<p>There is not enough player to start a tournament</p>");
				out.println("<body><p>Add a player.</p>");
				out.println("<form method='POST' action='/addPlayer'/>");
				out.println("<input name='name' type='text' />");
				out.println("<input type='submit' value='Submit' />");
				out.println("</form>");
			}
			out.println("<a href='/'> See tournament </a>");
			out.println("</body>");
			out.println("</html>");
	}
}
