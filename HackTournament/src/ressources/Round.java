package ressources;

public class Round {
	protected Match[] matches;
	protected Integer remainingMatches;
	protected Tournament tournament;
	protected Player[] winners;
	
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
	
	public void startMonitoring(Match match){
		//TODO start monitoring the match
		//TODO create a vector of monitored tasks (use when we want to kill the tournament)
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
