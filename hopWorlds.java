package RimmingtonBuyer;

import org.osbot.rs07.script.MethodProvider;

public class hopWorlds extends Task {
	
	public hopWorlds(MethodProvider api) {
		super(api);
	}

	@Override
	public boolean canProcess() {
		return rimmingtonBuyer.tradedRommik == true && rimmingtonBuyer.tradedBrian == true;
	}	
	@Override
	public void process() throws InterruptedException{
		api.log("Trying to hop worlds...");
		if(api.getWorlds().hopToF2PWorld()){
			rimmingtonBuyer.tradedRommik = false;
			rimmingtonBuyer.tradedBrian = false;
		}
	}
}