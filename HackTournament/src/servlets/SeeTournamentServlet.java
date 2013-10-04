package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ressources.Match;
import ressources.Round;
import ressources.Tournament;
import ressources.TournamentsCluster;
import ressources.Player;

public class SeeTournamentServlet extends HttpServlet {

	private static final long serialVersionUID = 499243902333383277L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Tournament tournament = TournamentsCluster.getTournament();
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Tournament</title></head>");
		out.println("<body>");
		out.println("<p>Tournament</p>");
		if(tournament.hasStarted()){
			Round[] rounds = tournament.getRounds();
			for(int ii=0;ii<rounds.length;ii++){
				out.println("<ul>");
				if(rounds[ii] == null){
					break;
				}else{
					out.println("<ul>Round "+ii);
					Match[] matches = rounds[ii].getMatches();
					out.println("<ul>");
					for(int jj =0; jj<matches.length; jj++){
						out.println("<li>");
						out.println(matches[jj].getPlayer1()+" vs "+matches[jj].getPlayer2());
						if(matches[jj].getWinner() == null){
							out.println("IN PROGRESS");
						}else{
							out.println("WINNER : "+matches[jj].getWinner().name);
						}
						out.println("</li>");
					}
					out.println("</ul>");
				}
				out.println("</ul>");
			}
			if(tournament.isFinished()){
				out.println("<h1>WINNER IS "+tournament.getWinner());
			}
		}else{
			Set<Player> players = tournament.getPlayers();
			if(players.size() == 0){
				out.println("<p>No players</p>");
			}else{
				out.println("<ul>");
				for(Player player : players){
					out.println("<li>");
					out.println(player.name);
					out.println("</li>");
				}
				out.println("<ul>");
			}
			out.println("<a href='/addPlayer'>Add player</a>");
			if(players.size() >= 2){
				out.println("<a href='/startTournament'>Start tournement</a>");
			}else{
				out.println("<p>Add more player to start the game</p>");
			}
		}
		out.println("</body>");
		out.println("</html>");
	}
}