package me.proxygames.main;

import java.util.ArrayList;
import java.util.List;
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
	
	@SuppressWarnings("deprecation")
	public static String GetBroadCastFormat(Player p) {		
		  Set<String> List = main.getformats().getConfigurationSection("BroadcastMessages").getKeys(false);
		  String format;
		  java.util.List<String> formatfile;
		  for(int i = 0; i < List.size(); i++) {
			  format = (String) List.toArray()[i];
			  String[] counts = format.split("-");
			  int first = Integer.parseInt(counts[0]);
			  int last = Integer.parseInt(counts[1]) + 1;
			  
			  for(int test = first; test < last; test++) {
				  if(Integer.parseInt(EditPlayerData.getData(Bukkit.getOfflinePlayer(p.getName()), "level")) == test) {
					  formatfile = main.getformats().getStringList("BroadcastMessages." + format + ".message");
						  for(int is = 0; is < formatfile.size(); is++) {
							  Bukkit.broadcastMessage(PlaceHolders.Pholders(Bukkit.getOfflinePlayer(p.getName()), LevelSystem.getData(Bukkit.getOfflinePlayer(p.getName())), ToColors(formatfile.get(is))));  

					  }
				  }
			  }
		  }
		return "";
		
	}	
	
	
	
	
	@SuppressWarnings("deprecation")
	public static String GetTitleMessages(Player p) {		
		  Set<String> List = main.getformats().getConfigurationSection("TitleMessages").getKeys(false);
		  String format;
		  java.util.List<String> formatfile;
		  for(int i = 0; i < List.size(); i++) {
			  format = (String) List.toArray()[i];
			  String[] counts = format.split("-");
			  int first = Integer.parseInt(counts[0]);
			  int last = Integer.parseInt(counts[1]) + 1;
			  
			  for(int test = first; test < last; test++) {
				  if(Integer.parseInt(EditPlayerData.getData(Bukkit.getOfflinePlayer(p.getName()), "level")) == test) {
					  formatfile = main.getformats().getStringList("TitleMessages." + format + ".title");
					  if(main.getformats().getString("TitleMessages." + format + ".times.fadeIn") == null || main.getformats().getString("TitleMessages." + format + ".times.fadeOut") == null || main.getformats().getString("TitleMessages." + format + ".times.display") == null) return null;
					  int fadein = main.getformats().getInt("TitleMessages." + format + ".times.fadeIn");					  
					  int display = main.getformats().getInt("TitleMessages." + format + ".times.display");	
					  int fadeout = main.getformats().getInt("TitleMessages." + format + ".times.fadeOut");	
					  
					  List<String> title = new ArrayList<String>();
					  
					  if(formatfile.size() == 0) return null;
						  for(int is = 0; is < formatfile.size(); is++) {
							  title.add(PlaceHolders.Pholders(Bukkit.getOfflinePlayer(p.getName()), LevelSystem.getData(Bukkit.getOfflinePlayer(p.getName())), ToColors(formatfile.get(is))));
							  
							  if(title.size() == 1) {
								  p.sendTitle(title.get(0), "", fadein, display, fadeout);	  
							  } else if (title.size() == 2) {
								  p.sendTitle(title.get(0), title.get(1), fadein, display, fadeout);	  
							  } else {
								  return null;
							  }
							  
							  
						  }

				  }
			  }
		  }
		return "";
		
	}
	
	
    
	  private static String ToColors(String text)
	  {
	    return ChatColor.translateAlternateColorCodes('&', text);
	  }
}
