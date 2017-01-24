package RimmingtonBuyer;

import java.awt.Graphics2D;
import java.util.ArrayList;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

@ScriptManifest(name = "Rimmington Buyer V0.01", author = "Naaiz", version = 0.01, info = "", logo = "")
public class rimmingtonBuyer extends Script{
	ArrayList<Task> tasks = new ArrayList<Task>(); //Declaring the array of tasks
	public static Area rimmingtonStoresArea = new Area (2946,3202, 2960, 3208);
	public static boolean tradedRommik = false;
	public static boolean tradedBrian = false;
	public static boolean stopScript = false;
	@Override 
	public void onStart() throws InterruptedException{
		tasks.add(new walkToRimmington(this));
		tasks.add(new tradeCycle(this));
		tasks.add(new hopWorlds(this));
	}
	@Override
	public int onLoop() throws InterruptedException {
		if(stopScript == true) {
			stop();
		}
		for (Task task : tasks) {
    	    task.run();
    	}
		return random(700,1300);
	}
	
	public void onPaint(Graphics2D gr) { 
		int x = getMouse().getPosition().x;
        int y = getMouse().getPosition().y;              
        gr.drawLine(0, y, 765, y);
        gr.drawLine(x, 0, x, 503);
	}

}