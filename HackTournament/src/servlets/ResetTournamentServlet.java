package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ressources.Tournament;
import ressources.TournamentsCluster;

public class ResetTournamentServlet extends HttpServlet {
	
	private static final long serialVersionUID = 983659146797270952L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        Tournament tournament = TournamentsCluster.getTournament();
        tournament.clearTournament();
        response.getWriter().println("Tournament has been resetted");
    }

}
