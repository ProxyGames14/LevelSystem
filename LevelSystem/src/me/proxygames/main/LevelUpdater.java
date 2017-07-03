package me.proxygames.main;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class LevelUpdater {

	
	@SuppressWarnings("deprecation")
	public static void UpdateChat(Player p, AsyncPlayerChatEvent event) {
    if(main.getformats().getString("Type").equalsIgnoreCase("prefix")) {
    	event.setFormat(ToColors(GetFormat(event.getPlayer()).replace("%level%", EditPlayerData.getData(Bukkit.getOfflinePlayer(p.getName()), "level")) + "&r" + "%1$s: %2$s"));
    }
    if(main.getformats().getString("Type").equalsIgnoreCase("suffix")) {
    	event.setFormat(ToColors("%1$s" + GetFormat(event.getPlayer()).replace("%level%", EditPlayerData.getData(Bukkit.getOfflinePlayer(p.getName()), "level")) + "&r" +": %2$s"));
    }
    
    
    
	}	
	public static void CheckTabOnlinePlayer(OfflinePlayer p) {
		if(p.isOnline()) UpdateTabList((Player) p);
	}
	
	public static void RemoveTabList(Player p) {
		
	    Scoreboard scoreboard = p.getScoreboard();
	    Team team = scoreboard.getTeam(p.getName());
		team.removeEntry(p.getName());
	}
	
	@SuppressWarnings("deprecation")
	public static void UpdateTabList(Player p) {
		
	    String format = GetFormat(p);
	    
 
        //Teams
		for (Player online : Bukkit.getOnlinePlayers()) {
		    Scoreboard scoreboard = online.getScoreboard();
		    if (scoreboard == Bukkit.getScoreboardManager().getMainScoreboard()) {
		      scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		    }
		    
		    Team team = scoreboard.getTeam(p.getName());
		    if (team == null) {
		        team = scoreboard.registerNewTeam(p.getName());
		    }
		    
		    if(main.getformats().getString("Type").equalsIgnoreCase("prefix")) {
			team.setPrefix(ToColors(format.replace("%level%", EditPlayerData.getData(Bukkit.getOfflinePlayer(p.getName()), "level")) + "&r"));
		    }
		    if(main.getformats().getString("Type").equalsIgnoreCase("suffix")) {
			team.setSuffix(ToColors(format.replace("%level%", EditPlayerData.getData(Bukkit.getOfflinePlayer(p.getName()), "level")) + "&r"));	
		    }
			  if(main.getConfigFile().getString("TabListBelowName").equalsIgnoreCase("true")) {
				    team.addEntry(p.getName());
				    online.setScoreboard(scoreboard);	  
			  } else {
				    team.removeEntry(p.getName());
			  }


	    
	    }
		}
	
	
	@SuppressWarnings("deprecation")
	public static String GetFormat(Player p) {		
		  Set<String> List = main.getformats().getConfigurationSection("Formats").getKeys(false);
		  String format;
		  String formatfile = "";
		  for(int i = 0; i < List.size(); i++) {
			  
			  format = (String) List.toArray()[i];
			  String[] counts = format.split("-");
			  int first = Integer.parseInt(counts[0]);
			  int last = Integer.parseInt(counts[1]) + 1;
			  
			  for(int test = first; test < last; test++) {
				  if(Integer.parseInt(EditPlayerData.getData(Bukkit.getOfflinePlayer(p.getName()), "level")) == test) {
					  formatfile = main.getformats().getString("Formats." + format + ".format");
					  if(main.getformats().getString("Type").equalsIgnoreCase("prefix")) {
						  return formatfile;
					  }
				  }
			  }
		  }
		  return formatfile;
		
	}
    
	  private static String ToColors(String text)
	  {
	    return ChatColor.translateAlternateColorCodes('&', text);
	  }
}
