package RimmingtonBuyer;

import org.osbot.rs07.script.MethodProvider;

public class walkToRimmington extends Task {
	
	public walkToRimmington(MethodProvider api) {
		super(api);
	}

	@Override
	public boolean canProcess() {
		return !rimmingtonBuyer.rimmingtonStoresArea.contains(api.myPlayer());
	}	
	@Override
	public void process() throws InterruptedException{
		api.getWalking().webWalk(rimmingtonBuyer.rimmingtonStoresArea.getRandomPosition());
	}
}