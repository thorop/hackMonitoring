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
	
	public void startTournament() throws AlreadyStarted{
		if(!isStarted){
			isStarted = true;
		}else{
			throw new AlreadyStarted();
		}
	}
	
	protected void initializeTournament(){
		int nbOfRound = Math.min(players.size() / 2, (players.size() + 1) /2);
		rounds = new Round[nbOfRound];
	}
	

	
	class AlreadyStarted extends Exception{
		private static final long serialVersionUID = -2476371482688460780L;
	}
	
	
}
