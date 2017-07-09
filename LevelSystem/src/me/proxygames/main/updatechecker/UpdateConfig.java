package me.proxygames.main.updatechecker;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;

import me.proxygames.main.main;

public class UpdateConfig {

	
	public static void CheckUpdate() {

	    if(!new File(Bukkit.getServer().getPluginManager().getPlugin("LevelSystemAPI").getDataFolder(), "DataBase").exists()) {
		    File dir = new File(Bukkit.getServer().getPluginManager().getPlugin("LevelSystemAPI").getDataFolder(), "DataBase");
		    dir.mkdir();
	    }

		if(main.getConfigFile().getString("update-checker") == null) {
			main.getConfigFile().set("update-checker", true);
			Updater.amount++;
		}
		
		if(main.getConfigFile().getString("StartXp") == null) {
			main.getConfigFile().set("StartXp", 1000);
			Updater.amount++;
		}
		if(main.getConfigFile().getString("UpPerLevel") == null) {
			main.getConfigFile().set("UpPerLevel", "125%");
			Updater.amount++;
		}
		if(main.getConfigFile().getString("MaxLevel") == null) {
			main.getConfigFile().set("MaxLevel", 100);
			Updater.amount++;
		}
		if(main.getConfigFile().getString("ChatFormat") == null) {
			main.getConfigFile().set("ChatFormat", true);
			Updater.amount++;
		}
		if(main.getConfigFile().getString("TabListBelowName") == null) {
			main.getConfigFile().set("TabListBelowName", true);
			Updater.amount++;
		}
		if(main.getConfigFile().getString("LevelUpSounds") == null) {
			String[] list = {"BLOCK_NOTE_PLING:1:2","ENTITY_FIREWORK_LAUNCH:1:1", "ENTITY_FIREWORK_BLAST:1:1", "ENTITY_FIREWORK_TWINKLE:1:1"};
			main.getConfigFile().set("LevelUpSounds", list);
			Updater.amount++;
		}
		if(main.getConfigFile().getString("LevelMaxSounds") == null) {
			String[] list = {"ENTITY_ENDERDRAGON_AMBIENT:1:1","ENTITY_WITHER_SPAWN:1:2", "ENTITY_FIREWORK_LAUNCH:1:1", "ENTITY_FIREWORK_BLAST:1:1", "ENTITY_FIREWORK_TWINKLE:1:1"};
			main.getConfigFile().set("LevelUpSounds", list);
			Updater.amount++;
		}


		
	   try {
		   main.getConfigFile().save(main.congiffile);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
		
		return; 
	}
	
}
