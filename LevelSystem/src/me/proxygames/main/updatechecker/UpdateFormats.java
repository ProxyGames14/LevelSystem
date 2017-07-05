package me.proxygames.main.updatechecker;

import java.io.IOException;


import me.proxygames.main.main;

public class UpdateFormats {

	
	public static void CheckUpdate() {

		
		if(!main.getformats().getConfigurationSection("").getKeys(false).contains("Formats")) {
			
			String[] ListFirst = {
					"0-10"
					,"11-20"
					,"21-30"
					,"31-40"
					,"41-50"
					,"51-60"
					,"61-70"
					,"71-80"
					,"81-90"
					,"91-99"
					,"100-100"
					};
			String[] listSecond = {
					"&e%level% "
					,"&6%level% "
					,"&a%level% "
					,"&2%level% "
					,"&b%level% "
					,"&4%level% "
					,"&5%level% "
					,"&d%level% "
					,"&9%level% "
					,"&3%level% "
					,"&c%level% "
					};
			for(int i = 0; i < ListFirst.length; i++) {
				
				main.getformats().set("Formats." + ListFirst[i] + ".format", listSecond[i]);
			}

			Updater.amount++;
		}
		
		
		if(!main.getformats().getConfigurationSection("").getKeys(false).contains("BroadcastMessages")) {
			
			String[] ListFirst = {
					"0-99"
					,"100-100"
					};
			String[] ListSecond0 = {
					"&a%player% &ejust leveled to &bLevel %level%"
					};
			String[] ListSecond1 = {
					"&a%player% &6Maxed level"
					};
			for(int i = 0; i < ListFirst.length; i++) {
				if(i == 0)
				main.getformats().set("BroadcastMessages." + ListFirst[i] + ".message", ListSecond0);
				if(i == 1)
				main.getformats().set("BroadcastMessages." + ListFirst[i] + ".message", ListSecond1);
			}

			Updater.amount++;
		}
		
		if(main.getformats().getString("Type") == null) {
			main.getformats().set("Type", true);
			Updater.amount++;
		}
		
		if(!main.getformats().getConfigurationSection("").getKeys(false).contains("CheckStatsFormat")) {
			String[] List = {
					"&9&m-------------------------------------------"
					,"&a&l%player%''s &e&lStats"
					,""
					,"&bLeveling Stats&f: %ismax%"
					,"&bXp Amount&f: %xp%/%xplevelup% %procent%%"
					,"&bLevel Amount&f: %level%"
					,"&9&m-------------------------------------------"
					};
			main.getformats().set("CheckStatsFormat", List);
			Updater.amount++;	
		}
		if(!main.getformats().getConfigurationSection("").getKeys(false).contains("LevelUpFormat")) {	
			String[] List = {
					"&e&m----------------------------------------------------"
					,"&r                &e&k&&aCONGRATULATIONS YOU LEVELED UP&e&k&"
					,"&a                      You are now &blevel %level%&a!"
					,"&e&m----------------------------------------------------"
					};
			main.getformats().set("LevelUpFormat", List);
			Updater.amount++;	
		}
		if(!main.getformats().getConfigurationSection("").getKeys(false).contains("MaxLevelFormat")) {	
			String[] List = {
					"&e&m----------------------------------------------------"
					,"&r                &e&k&&6CONGRATULATIONS YOU LEVELED MAX&e&k&"
					,"&a                      You are now &blevel &cMAX&a!"
					,"&e&m----------------------------------------------------"
					};
			main.getformats().set("MaxLevelFormat", List);
			Updater.amount++;	
		}
		if(main.getformats().getString("NoPermissions") == null) {
			main.getformats().set("NoPermissions", "&cLevel > &7Sorry You dont have permissions to do that! &e%permissions%");
			Updater.amount++;
		}

		
	   try {
		   main.getformats().save(main.formatfile);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
		
		return;
	}
	
}
