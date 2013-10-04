package simulator;

import java.util.*;


public class Simulator{
	protected static HashMap<String,Integer> requests = new HashMap<String,Integer>();
	protected final static int MINNBTRIES = 5;
	protected final static int MAXNBTRIES = 10;
	
	/**
	 * Return 1 if it is a win, ) if it is a lose, -1 if the game is not finished
	 */
	public static int getResult(String playerName){
		if(requests.containsKey(playerName)){
			int nbOfRemainingTry = requests.get(playerName) - 1;
			if(nbOfRemainingTry == 0){
				requests.remove(playerName);
				return Math.random() > 0.5 ? 1 : 0;
			}else{
				requests.put(playerName,nbOfRemainingTry);
				return -1;
			}
		}else{
			int nbOfTry =(int) (Math.random()*(MAXNBTRIES - MINNBTRIES) + MINNBTRIES);
			requests.put(playerName, nbOfTry);
			return -1;
		}
	}
	
	public static void reset(){
	 requests.clear();
	}

}


