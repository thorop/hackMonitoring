package ressources;

import java.util.Vector;

public class Round {
	protected Match[] matches;
	protected Integer remainingMatches;
	protected Tournament tournament;
	protected Player[] winners;
	protected Vector<Monitor> monitors = new Vector<Monitor>();
	
	public static Round createRound(Tournament tournament, Player[] players){
		Round newRound = new Round(tournament);
		newRound.populate(players);
		return newRound;
	}
	
	protected Round(Tournament tournament){
		this.tournament = tournament;
	}
	
	protected void populate(Player[] players){
		int nbOfGame = (players.length / 2) + players.length % 2;
		winners = new Player[nbOfGame];
		remainingMatches = players.length / 2;
		matches = new Match[nbOfGame];
		for(int i=0; i< (players.length / 2 ); i+=2){
			Player player1 = players[i];
			Player player2 = players[i+1];
			matches[i]=new Match(player1, player2,this,i);
			startMonitoring(matches[i]);
		}
		
		if ( ( players.length % 2 )  == 1 ) {
			Player player1 = players[players.length - 1];
			Player player2 = Player.getNullPlayer();
			matches[nbOfGame - 1] = Match.getNullMatch(player1, player2,nbOfGame - 1);
			winners[nbOfGame - 1] = player1;
		}
		
	}
	
	public void clearRound(){
		matches = null;
		remainingMatches = 0;
		tournament = null;
		winners = null;
		stopMonitoring();
	}
	
	public Match[] getMatches(){
		return matches;
	}
	
	public void startMonitoring(Match match){
		Monitor monitor = new Monitor(match);
		monitor.start();
		monitors.add(monitor);
	}
	
	public void stopMonitoring(){
		for(Monitor monitor : monitors){
			monitor.stopThread();
		}
		monitors.clear();
	}
	
	public void notifyMatchIsFinsish(Match match){
		synchronized(remainingMatches){
			remainingMatches--;
			winners[match.position] = match.getWinner();
			if(remainingMatches == 0){
				tournament.notifyRoundIsFinished();
			}
		}
	}
	
	public Player[] getWinners(){
		return winners;
	}
	
}
