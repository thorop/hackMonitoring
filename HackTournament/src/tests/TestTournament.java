package tests;

import ressources.*;
import ressources.Tournament.AlreadyStarted;
import ressources.Tournament.NotEnoughPlayer;

public class TestTournament {
	
	public final static String base = "player";
	protected static int nbOfPlayers = 5;

	public static void main(String[] args) {
		Tournament tournament = new Tournament();
		Player player;
		for(int i = 0; i<nbOfPlayers; i++){
			player = new Player(base+i);
			tournament.addPlayer(player);
		}
		try {
			tournament.startTournament();
		} catch (AlreadyStarted e) {
			System.out.println("Already started");
		} catch( NotEnoughPlayer e){
			System.out.println("Not enough player");
		}

	}

}
