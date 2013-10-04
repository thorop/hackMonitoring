package ressources;

import java.util.HashMap;

public class TournamentsCluster {
	
	public static HashMap<String, Tournament> tournaments = new HashMap<String, Tournament>();
	protected static Tournament tournament = new Tournament();
	
	public synchronized static Tournament getTournament() {
		if(tournament == null){
			tournament = new Tournament();
		}
		return tournament;
	}
}
