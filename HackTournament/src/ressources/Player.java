package ressources;

public class Player {
	public final String name;
	public final String playerId;
	
	public Player(String name, String playerId){
		this.name = name;
		this.playerId = playerId;
	}
	
	public Player(String name){
		this.name = name;
		playerId = "NULL";
	}
	
	public static Player getNullPlayer(){
		return new Player("");
	}
	
	public int hashCode(){
		return name.hashCode();
	}
	
}
