package me.proxygames.main;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

public class Database {

	public void CreateFile(PlayerJoinEvent event) {
	 Player player = event.getPlayer();
	    String playeruuid = player.getUniqueId().toString();	 
	    if(!new File(Bukkit.getServer().getPluginManager().getPlugin("LevelSystemAPI").getDataFolder(), "DataBase").exists()) {
		    File dir = new File(Bukkit.getServer().getPluginManager().getPlugin("LevelSystemAPI").getDataFolder(), "DataBase");
		    dir.mkdir(); 	
	    }

	    
	    File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("LevelSystemAPI").getDataFolder(), File.separator + "DataBase");
        File f = new File(userdata, File.separator + playeruuid + ".yml");
	    FileConfiguration playerData = YamlConfiguration.loadConfiguration(f); 
	    

	    if(!f.exists()){
	        try {
	            f.createNewFile();
	            } catch (IOException e) {
	                e.printStackTrace();
	                    }
	 
	                        try {
								playerData.load(f);
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (InvalidConfigurationException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	// Here you can set what you want to be in the player file by default
	                        playerData.set(playeruuid + ".Name", player.getName());
	                        playerData.set(playeruuid + ".UUID", playeruuid);
	                        playerData.set(playeruuid + ".Level", 0);
	                        playerData.set(playeruuid + ".Xp", 0);
	                        
	                        try {
								playerData.save(f);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
	    } else {
	    	String playername = event.getPlayer().getName();
	    	String filename = playerData.getString(playeruuid + ".Name");
	    	if(!(filename.equals(playername))) {
	    		playerData.set(playeruuid + ".Name", playername);
	    	}
	    	
	        try {
				playerData.save(f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	    }
	}
	
	
	
}
