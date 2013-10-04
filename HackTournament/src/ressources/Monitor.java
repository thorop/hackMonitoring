package ressources;

import simulator.*;

public class Monitor extends Thread{
	protected Match match;
	protected boolean stopped = false;
	
	
	public Monitor(Match match){
		this.match = match;
	}
	
    
    public void run(){
    	int result = Simulator.getResult(match.getPlayer1());
    	while(!stopped && result == -1){
    		try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		result = Simulator.getResult(match.getPlayer1());
    	}
    	
    	if(!stopped){
    		if(result == 1){
    			match.setPlayer1Winner();
    		}else{
    			match.setPlayer1Loser();
    		}
    	}
    	
    }
    
    public void stopThread(){
    	stopped = true;
    }

}

