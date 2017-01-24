package RimmingtonBuyer;

import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;


public class tradeCycle extends Task {
	
	public tradeCycle(MethodProvider api) {
		super(api);
	}

	@Override
	public boolean canProcess() {
		return rimmingtonBuyer.rimmingtonStoresArea.contains(api.myPlayer()) && api.getInventory().contains("Coins") && (rimmingtonBuyer.tradedBrian == false || rimmingtonBuyer.tradedRommik == false);
	}	
	@Override
	public void process() throws InterruptedException{
		if(api.getSettings().getRunEnergy() >= MethodProvider.random(10,30)) {
			if(!api.getSettings().isRunning()) {
				api.getSettings().setRunning(true);
			}
		}
		if(!api.getNpcs().closest("Rommik").isVisible()) {
			api.getCamera().toEntity(api.getNpcs().closest("Rommik"));
		}
		api.getNpcs().closest("Rommik").interact("Trade");
		new ConditionalSleep(30000) {
			@Override
            public boolean condition() throws InterruptedException {
				return api.getWidgets().isVisible(300, 1) && !api.myPlayer().isMoving();
            }
		}.sleep();
		if(api.getWidgets().isVisible(300,1)) {
			rimmingtonBuyer.tradedRommik = true;
			for(int i = api.getStore().getAmount("Thread"); i>=1; i-=10) {
				if(api.getInventory().contains("Coins")) {
					if(api.getStore().getAmount("Needle") >= 2) {
						api.getStore().interact("Buy 10", "Needle");
					}
					api.getStore().interact("Buy 10", "Thread");
					if(api.getStore().getAmount("Thread") < 1) {
						break;
					}
				}
				else {
					rimmingtonBuyer.stopScript = true;
				}
			}
		}
		api.getStore().close();
		if(!api.getNpcs().closest("Brian").isVisible()) {
			api.getCamera().toEntity(api.getNpcs().closest("Brian"));
		}
		api.getNpcs().closest("Brian").interact("Trade");
		new ConditionalSleep(30000) {
			@Override
            public boolean condition() throws InterruptedException {
				return api.getWidgets().isVisible(300, 1) && !api.myPlayer().isMoving();
            }
		}.sleep();
		if(api.getWidgets().isVisible(300,1)) {
			rimmingtonBuyer.tradedBrian = true;
			if(api.getInventory().contains("Coins")) {
				for(int i = api.getStore().getAmount("Steel arrow"); i>=1460; i-=10) {
					api.getStore().interact("Buy 10", "Steel arrow");
					if(api.getStore().getAmount("Steel arrow") <= 1459) {
						break;
					}
				}
			}
			else {
				rimmingtonBuyer.stopScript = true;
			}
		}
		api.getStore().close();
		
	}
}