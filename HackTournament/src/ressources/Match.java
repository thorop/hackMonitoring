package ressources;

public class Match {
	protected final Player player1;
	protected final Player player2;
	protected Player winner = null;
	protected Boolean isFinished = false;
	protected final Round round;
	public final int position;
	
	
	public Match(Player player1, Player player2, Round round, int position ){
		this.player1 = player1;
		this.player2 = player2;
		this.round = round;
		this.position = position;
	}
	
	public static Match getNullMatch(Player player, Player nullPlayer, int position){
		return new Match(player,nullPlayer, position);
	}
	
	protected Match(Player player1, Player player2, int position){
		this.player1 = player1;
		this.player2 = player2;
		this.round = null;
		isFinished = true;
		winner = player1;
		this.position = position;
		
	}
	
	public Player getWinner(){
		return winner;
	}
	
	public String getPlayer1(){
		return player1.name;
	}
	
	public String getPlayer2(){
		return player2.name;
	}
	
	public void setPlayer1Winner(){
		synchronized(isFinished){
			if ( !isFinished ) {
				winner = player1;
				isFinished = true;
				round.notifyMatchIsFinsish(this);
			}
		}
	}

	public void setPlayer1Loser(){
		synchronized(isFinished){
			if ( !isFinished ) {
				winner = player2;
				isFinished = true;
				round.notifyMatchIsFinsish(this);
			}
			
		}
	}
	
	public String toString(){
		return "player1 : "+player1.name+" player2 : "+player2.name;
	}
}
