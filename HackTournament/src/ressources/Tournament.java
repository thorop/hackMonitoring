package ressources;

import java.util.HashSet;
import java.util.Set;


public class Tournament {
	protected Set<Player> players = new HashSet<Player>();
	protected boolean isStarted = false;
	protected boolean isFinsished = false;
	protected String winner = null;
	protected int currentRound = 0;
	protected Round[] rounds;
	
	public boolean addPlayer(String name){
		Player player = new Player(name);
		return addPlayer(player);
	}
	
	public boolean addPlayer(Player player){
		return players.add(player);
	}
	
	public void startTournament() throws AlreadyStarted, NotEnoughPlayer{
		if(!isStarted){
			if(players.size() < 2){
				throw new NotEnoughPlayer();
			}
			isStarted = true;
			initializeTournament();
		}else{
			throw new AlreadyStarted();
		}
	}
	
	protected void initializeTournament(){
		int nbOfRound = Math.min(players.size() / 2, (players.size() + 1) /2);
		rounds = new Round[nbOfRound];
		initializeNextRound();
	}
	
	//Synchronized should not be usefull because only one instance of Round is supposed to be active
	public synchronized void notifyRoundIsFinished(){
		currentRound ++;
		if(currentRound > rounds.length-1){//Game is finished
			winner = rounds[rounds.length - 1].getWinners()[0].name;//Ugly as *******
		}else{
			initializeNextRound();
		}
	}
	
	protected void initializeNextRound(){
		Player[] remainingPlayers;
		if(currentRound == 0){
			remainingPlayers = new Player[players.size()];
			remainingPlayers = players.toArray(remainingPlayers);
		}else{
			remainingPlayers = rounds[currentRound - 1].getWinners();
		}
		createRound(remainingPlayers);
	}
	
	protected void createRound(Player[] players){
		rounds[currentRound] = Round.createRound(this,players);
	}
	
	
	public class NotEnoughPlayer extends Exception{
		private static final long serialVersionUID = 25868284670650885L;
		protected static final String message = "Not enough player";
		
		public NotEnoughPlayer(){
			super(message);
		}
	}
	
	public class AlreadyStarted extends Exception{
		private static final long serialVersionUID = -2476371482688460780L;
		protected static final String message = "Tournament already started";
		
		public AlreadyStarted(){
			super(message);
		}
	}
	
}
