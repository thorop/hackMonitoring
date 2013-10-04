

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import servlets.*;

public class HackTournament {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
 
        context.addServlet(new ServletHolder(new SeeTournamentServlet()),"/*");
        context.addServlet(new ServletHolder(new AddPlayerServlet()),"/addPlayer/*");
        context.addServlet(new ServletHolder(new ResetTournamentServlet()),"/resetTournament/*");
        context.addServlet(new ServletHolder(new StartTournamentServlet()), "/startTournament");
        
        
        
        server.start();
        server.join();
    }
 

}
