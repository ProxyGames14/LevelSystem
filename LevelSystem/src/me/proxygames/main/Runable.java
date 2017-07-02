package me.proxygames.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import me.proxygames.main.api.LevelSystemAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
public class Runable {
	  final int[] ran = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};	
	private main main;
	public Runable(main main) {
		this.main = main;
	}	
	
	public int GetRan() {
        Random random = new Random();
		return ran[random.nextInt(ran.length)];
        }
	public static Map<UUID, Integer> playertime = new HashMap<UUID, Integer>();
	
    public void Launch(final Player p) {
    	if(playertime.containsKey(p.getUniqueId())) return;
		playertime.put(p.getUniqueId(), 10);
    	new BukkitRunnable()
    	{
    	  @SuppressWarnings("deprecation")
		public void run()
    	  {
    		  int t = playertime.get(p.getUniqueId());
    		  
      	    if (t == 0) {
      	    	cancel();
      	    	playertime.remove(p.getUniqueId());
      	    	return;
      	    }	    
    		  playertime.put(p.getUniqueId(), playertime.get(p.getUniqueId()) - 1);
    		  
    		  if(p.isOnline()) {
    		  if(LevelSystemAPI.PlayerIsMaxed(Bukkit.getOfflinePlayer(p.getName()))) {
        	  fw.spawnRandomFirework(p.getLocation().add(GetRan(), Integer.parseInt(String.valueOf(GetRan()).replace("-", "")), GetRan()));
    		  } else {
        	  fw.spawnRandomFirework(p.getLocation().add(GetRan(), Integer.parseInt(String.valueOf(GetRan()).replace("-", "")), GetRan()));
    		  }
    		  return;
    		  }
    		  
    		  
    	 
    	  }
    	 
    	}.runTaskTimer(main, 0 , 20);
        }

}
