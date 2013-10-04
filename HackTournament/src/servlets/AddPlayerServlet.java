package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ressources.Tournament;
import ressources.TournamentsCluster;

public class AddPlayerServlet extends HttpServlet {


	private static final long serialVersionUID = -5857276909716834684L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Add player</title></head>");
		out.println("<body><p>Add a player.</p>");
		out.println("<form method='POST' action='/addPlayer'/>");
		out.println("<input name='name' type='text' />");
		out.println("<input type='submit' value='Submit' />");
		out.println("</form>");
		out.println("<a href='/'> See tournament </a>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String playerName = request.getParameter("name");
		Tournament tournament = TournamentsCluster.getTournament();
		Boolean completed = tournament.addPlayer(playerName);
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		PrintWriter out = response.getWriter();
		out.println("<html>");
		if(completed){
			out.println("<head><title>Player added</title></head>");
			out.println("<body><p>Player added</p>");
		}else{
			out.println("<head><title>Player alredy enrolled in the tournament</title></head>");
			out.println("<body><p>layer alredy enrolled in the tournament. Please choose an other name</p>");
		}
		out.println("<head><title>Add player</title></head>");
		out.println("<body><p>Add a player.</p>");
		out.println("<form method='POST' action='/addPlayer'/>");
		out.println("<input name='name' type='text' />");
		out.println("<input type='submit' value='Submit' />");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");

		out.println("<a href='/'> See tournament </a>");
		out.println("</body>");
		out.println("</html>");
	}
}
