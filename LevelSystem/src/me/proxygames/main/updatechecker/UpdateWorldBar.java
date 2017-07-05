package me.proxygames.main.updatechecker;


import java.io.IOException;

import me.proxygames.main.main;

public class UpdateWorldBar {


public static void CheckUpdate() {



	if(main.getWorldBars().getString("worldbar") == null) {
		main.getWorldBars().set("worldbar", true);
		Updater.amount++;
	}
	if(main.getWorldBars().getString("worlds") == null) {
		String[] list = {"world","world_nether"};
		main.getWorldBars().set("worlds", list);
		Updater.amount++;
	}

	
   try {
	   main.getWorldBars().save(main.worldbarfile);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
   
	
	return; 
}
}
