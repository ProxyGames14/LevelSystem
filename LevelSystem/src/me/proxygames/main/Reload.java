package me.proxygames.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Reload {
	int amount = 0;
  public void reloading() {
  
	    if(!new File(Bukkit.getServer().getPluginManager().getPlugin("LevelSystemAPI").getDataFolder(), "DataBase").exists()) {
		    File dir = new File(Bukkit.getServer().getPluginManager().getPlugin("LevelSystemAPI").getDataFolder(), "DataBase");
		    dir.mkdir(); 	
	    } 
	  
    File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("LevelSystemAPI").getDataFolder(), File.separator + "DataBase");
    File[] files = userdata.listFiles();

    for (File f:files)  
    {
         amount++;
    	
    	FileConfiguration playerData = YamlConfiguration.loadConfiguration(f); 
        try {
			playerData.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    

  }
  public int GetAmount() {
	  int last = amount;
	  amount = 0;
	  return last + 2;
  }


}
