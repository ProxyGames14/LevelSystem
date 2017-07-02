package me.proxygames.main;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class DeleteFiles {

	public static String DeletePlayerFile(OfflinePlayer p) {
		String playeruuid = p.getUniqueId().toString();
		
	    File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("LevelSystem").getDataFolder(), File.separator + "DataBase");
        File f = new File(userdata, File.separator + playeruuid + ".yml");
        
        if(!f.exists()) {
			return "&cLevel > &7File does not exist in the database";
        } else {
        	f.delete();
			return "&cLevel > &aDeleted the file!";
        }
	}
}
