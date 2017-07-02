package me.proxygames.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class EditPlayerData {

	
	public static String getData(OfflinePlayer p, String type){
    File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("LevelSystem").getDataFolder(), File.separator + "DataBase");
    File f = new File(userdata, File.separator + p.getUniqueId() + ".yml");
    FileConfiguration playerData = YamlConfiguration.loadConfiguration(f); 
    
    if(type.equalsIgnoreCase("xp"))
    	return playerData.getString(p.getUniqueId() + ".Xp");
    if(type.equalsIgnoreCase("level"))
    	return playerData.getString(p.getUniqueId() + ".Level");
        return null;
	}
	
	public static void setData(OfflinePlayer p, String type, int Amount) {
	    File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("LevelSystem").getDataFolder(), File.separator + "DataBase");
	    File f = new File(userdata, File.separator + p.getUniqueId() + ".yml");
	    FileConfiguration playerData = YamlConfiguration.loadConfiguration(f); 
	    
	    
	    if(type.equalsIgnoreCase("level")) {
            playerData.set(p.getUniqueId() + ".Level", Amount);
	    }
	    if(type.equalsIgnoreCase("xp")) {
            playerData.set(p.getUniqueId() + ".Xp", Amount);
	    }
	    
        try {
			playerData.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void addData(OfflinePlayer p, String type, int Amount) {
	    File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("LevelSystem").getDataFolder(), File.separator + "DataBase");
	    File f = new File(userdata, File.separator + p.getUniqueId() + ".yml");
	    FileConfiguration playerData = YamlConfiguration.loadConfiguration(f); 
	    
	    
	    if(type.equalsIgnoreCase("level")) {
	    	int level = playerData.getInt(p.getUniqueId() + ".Level") + Amount;
            playerData.set(p.getUniqueId() + ".Level", level);
	    }
	    if(type.equalsIgnoreCase("xp")) {
	    	int level = playerData.getInt(p.getUniqueId() + ".Xp") + Amount;
            playerData.set(p.getUniqueId() + ".Xp", level);
	    }
	    
        try {
			playerData.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void removeData(OfflinePlayer p, String type, int Amount) {
	    File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("LevelSystem").getDataFolder(), File.separator + "DataBase");
	    File f = new File(userdata, File.separator + p.getUniqueId() + ".yml");
	    FileConfiguration playerData = YamlConfiguration.loadConfiguration(f); 
	    

	    
	    if(type.equalsIgnoreCase("level")) {
	    	int level = playerData.getInt(p.getUniqueId() + ".Level") - Amount;
            playerData.set(p.getUniqueId() + ".Level", level);
	    }
	    if(type.equalsIgnoreCase("xp")) {
	    	int level = playerData.getInt(p.getUniqueId() + ".Xp") - Amount;
    	    if(level < 0 && Integer.parseInt(EditPlayerData.getData(p, "level")) != 0) {
                int newXp = Integer.parseInt(LevelSystem.getData(p)[0]) + level;
                Bukkit.broadcastMessage("" + newXp);
               
            	EditPlayerData.setData(p, "xp", newXp);
            	EditPlayerData.setData(p, "level", (Integer.parseInt(EditPlayerData.getData(p, "level")) - 1));
                return;
    	    }
	    	
            playerData.set(p.getUniqueId() + ".Xp", level);

	    }
	    
        try {
			playerData.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}
