package me.proxygames.main.api;


import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

import me.proxygames.main.DeleteFiles;
import me.proxygames.main.EditPlayerData;
import me.proxygames.main.LevelSystem;
import me.proxygames.main.LevelUpdater;
import me.proxygames.main.PlaceHolders;
import me.proxygames.main.main;

public class LevelSystemAPI extends JavaPlugin {
	
	
public static void SetPlayerXp(OfflinePlayer p, int Xp) {
	EditPlayerData.setData(p, "xp", Xp);
	LevelUpdater.CheckTabOnlinePlayer(p);
	LevelSystem.Datas(p);
}
public static void SetPlayerLevel(OfflinePlayer p, int Level) {
	
	if(Level > main.getConfigFile().getInt("MaxLevel")) {
		return;
	}
	
	if(Level < Integer.parseInt(EditPlayerData.getData(p, "level"))) {
		EditPlayerData.setData(p, "xp", 0);
	}
	
	
	EditPlayerData.setData(p, "level", Level);
	LevelUpdater.CheckTabOnlinePlayer(p);
	LevelSystem.Datas(p);
}
public static void AddPlayerLevel(OfflinePlayer p, int Level) {
	EditPlayerData.addData(p, "level", Level);
	LevelUpdater.CheckTabOnlinePlayer(p);
	LevelSystem.Datas(p);
}
public static void AddPlayerXp(OfflinePlayer p, int Xp) {
	EditPlayerData.addData(p, "xp", Xp);
	LevelUpdater.CheckTabOnlinePlayer(p);
	LevelSystem.Datas(p);
}
public static int GetPlayerXp(OfflinePlayer p) {
	return Integer.parseInt(EditPlayerData.getData(p, "xp"));	
}
public static int GetPlayerLevel(OfflinePlayer p) {
	return Integer.parseInt(EditPlayerData.getData(p, "level"));	
}
public static double GetPlayerAchievementsProcent(OfflinePlayer p) {
	return Double.parseDouble(LevelSystem.getData(p)[1]);		
}
public static int GetPlayerTotalXpNextLevel(OfflinePlayer p) {
	return Integer.parseInt(LevelSystem.getData(p)[0]);		
}
public static int GetConfigStartXp() {
	return main.getConfigFile().getInt("StartXp");
}
public static int GetConfigUpPerLevel() {
	return main.getConfigFile().getInt("UpPerLevel");
	
}
public static boolean PlayerIsMaxed(OfflinePlayer p) {
	if(PlaceHolders.toInt(LevelSystem.getData(p)[3]) == PlaceHolders.toInt(main.getConfigFile().getString("MaxLevel"))) {
		return true;
	} else {
		return false;
	}
}

public static void DeletePlayerfile(OfflinePlayer p) {
	DeleteFiles.DeletePlayerFile(p);
}

public static void SetConfigUpPerLevel(int UpPerLevel) {
	main.getConfigFile().set("UpPerLevel", UpPerLevel + "%");
	
    File configdata = new File(Bukkit.getServer().getPluginManager().getPlugin("LevelSystem").getDataFolder(), "config.yml");

	try {
		main.getConfigFile().save(configdata);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static Boolean PlayerFileExist(OfflinePlayer p) {
    File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("LevelSystem").getDataFolder(), File.separator + "DataBase");
    File f = new File(userdata, File.separator + p.getUniqueId().toString() + ".yml");
    if(f.exists()) {
        return true;	
    }
    else {
    	return false;
    }

}

public static void SetConfigStartXp(int StartXp) {
	main.getConfigFile().set("StartXp", StartXp);
	
    File configdata = new File(Bukkit.getServer().getPluginManager().getPlugin("LevelSystem").getDataFolder(), "config.yml");

	try {
		main.getConfigFile().save(configdata);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static void SetConfigMaxLevel(int MaxLevel) {
	main.getConfigFile().set("MaxLevel", MaxLevel);
	
    File configdata = new File(Bukkit.getServer().getPluginManager().getPlugin("LevelSystem").getDataFolder(), "config.yml");

	try {
		main.getConfigFile().save(configdata);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
