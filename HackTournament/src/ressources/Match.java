package ressources;

public class Match {
	protected final String player1;
	protected final String player2;
	protected String winner = null;
	protected Boolean isFinished = false;
	protected final Round round;
	
	
	public Match(String player1, String player2, Round round){
		this.player1 = player1;
		this.player2 = player2;
		this.round = round;
	}
	
	public String getWinner(){
		return winner;
	}
	
	public String getPlayer1(){
		return player1;
	}
	
	public void setPlayer1Winner(){
		synchronized(isFinished){
			if ( !isFinished ) {
				winner = player1;
				isFinished = true;
			}
		}
	}

	
	public void setPlayer1Loser(){
		synchronized(isFinished){
			if ( !isFinished ) {
				winner = player2;
				isFinished = true;
			}	
		}
	}
}
