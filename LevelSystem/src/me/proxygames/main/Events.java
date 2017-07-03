package me.proxygames.main;


import me.proxygames.main.updatechecker.Updater;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
public class Events implements Listener {


	private main mains;
	public Events(main main) {
		this.mains = main;
	}	
	
	private Updater updatechecker;
	public String version;

	@EventHandler
	  public void chatcolor(AsyncPlayerChatEvent event)
	  {
	        String message = event.getMessage();
	        event.setMessage(ChatColor.translateAlternateColorCodes('&', message));
	        
	    }
	  @EventHandler
	  public void onJoin(PlayerJoinEvent event)
	  {   
          
		  if (main.getConfigFile().getBoolean("update-checker")) {
		  if(event.getPlayer().hasPermission("levelsystem.checkupdates")) {
			  updatechecker = new Updater(mains);
             if(updatechecker.SendUpdates() != "none")
			  event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', updatechecker.SendUpdates()));
	
		  }

	  }
		   Database database = new Database();
		   database.CreateFile(event);
			  if(main.getConfigFile().getString("TabListBelowName").equalsIgnoreCase("true")) {
		  for(Player online : Bukkit.getOnlinePlayers()) {
          LevelUpdater.UpdateTabList(online);
		  }
			  }

			  LevelSystem.SetBar(event.getPlayer());
	  }
	  
	  @EventHandler
	  public void WorldChange(PlayerChangedWorldEvent event)
	  {		
		  if(main.getConfigFile().getString("TabListBelowName").equalsIgnoreCase("true")) {
		  for(Player online : Bukkit.getOnlinePlayers()) {
          LevelUpdater.UpdateTabList(online);
		  }
		  }
		  LevelSystem.SetBar(event.getPlayer());
		  
	  }
	@EventHandler
	  public void UpdateLevel(AsyncPlayerChatEvent event)
	  {	
		  if(main.getConfigFile().getString("ChatFormat").equalsIgnoreCase("true")) {
           LevelUpdater.UpdateChat(event.getPlayer(), event);
		  }
	  }
	  
	  
	  }

