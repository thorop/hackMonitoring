package ressources;

import java.util.HashSet;
import java.util.Set;

import simulator.Simulator;


public class Tournament {
	protected Set<Player> players = new HashSet<Player>();
	protected boolean isStarted = false;
	protected boolean isFinished = false;
	protected String winner = null;
	protected int currentRound = 0;
	protected Round[] rounds;
	protected Bracket bracket;
	
	public int getNbOfPlayers(){
		return players.size();
	}
	
	public Set<Player> getPlayers(){
		return players;
	}
	
	public boolean hasStarted(){
		return isStarted;
	}
	
	public boolean isFinished(){
		return isFinished;
	}
	
	public Round[] getRounds(){
		return rounds;
	}
	
	public void clearTournament(){
		for(int i = 0; i < rounds.length; i++){
			if(rounds[i] != null){
				rounds[i].clearRound();
			}
		}
		players.clear();
		isStarted = false;
		isFinished = false;
		winner = null;
		currentRound = 0;
		Simulator.reset();
	}
	
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
		int nbOfRound = (players.size() / 2 )+ (players.size() % 2);
		rounds = new Round[nbOfRound];
		initializeNextRound();
	}
	
	//Synchronized should not be usefull because only one instance of Round is supposed to be active
	public synchronized void notifyRoundIsFinished(){
		currentRound ++;
		if(currentRound > rounds.length-1){//Game is finished
			winner = rounds[rounds.length - 1].getWinners()[0].name;//Ugly as *******
			isFinished = true;
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
	
	public String getWinner(){
		return winner;
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
