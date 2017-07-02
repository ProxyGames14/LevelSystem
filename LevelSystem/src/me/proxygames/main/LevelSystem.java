package me.proxygames.main;


import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.Sound;


public class LevelSystem {

	private static main mains;
	public LevelSystem(main main) {
		LevelSystem.mains = main;
	}
	
	public static String[] getData(OfflinePlayer p){
		OfflineFiles offlineplayer = new OfflineFiles();
		offlineplayer.CreateFile(p);
		
		int level = Integer.parseInt(EditPlayerData.getData(p, "level"));
		int xp = Integer.parseInt(EditPlayerData.getData(p, "xp"));
		int StartXp = main.getConfigFile().getInt("StartXp");
		int UpPerLevel = Integer.parseInt(main.getConfigFile().getString("UpPerLevel").replace("%", ""));
		double product = UpPerLevel * 1.000 / 1000;
		double finalint = (((product*level)*StartXp)+StartXp) / 1.0;
		double until = xp/finalint;
		double procent = (((until * 100.0) * 1.00));
		int finalmax = (int) finalint;
		procent = Math.round(procent * 100.0) / 100.0;
		String[] List = {finalmax+"",procent+"",xp+"",level+""};
		return List;
	}
	
	public static void Datas(OfflinePlayer p) {
		
		String[] levelinfo = getData(p);	
		int xp = Integer.parseInt(levelinfo[2]);
		int maxxp = Integer.parseInt(levelinfo[0]);	
		if(xp >= maxxp) {
			if(Integer.parseInt(EditPlayerData.getData(p, "level")) >= Integer.parseInt(main.getConfigFile().getString("MaxLevel")) && Integer.parseInt(EditPlayerData.getData(p, "xp")) > maxxp) {
				EditPlayerData.setData(p, "xp", maxxp);
				return;
			}
			if(xp == maxxp) {
				EditPlayerData.setData(p, "xp", 0);
			} else {
				EditPlayerData.setData(p, "xp", 0);
				EditPlayerData.addData(p, "xp", (xp - maxxp));
			}
				xp = Integer.parseInt(getData(p)[2]);
				maxxp = Integer.parseInt(getData(p)[0]);

			if(!(Integer.parseInt(EditPlayerData.getData(p, "level")) >= Integer.parseInt(main.getConfigFile().getString("MaxLevel")))) {
				EditPlayerData.addData(p, "level", 1);
			}
			
			if(p.isOnline()) {
			Player player = (Player) p;
			LevelMessage(player, p);
			}
			
			if(xp >= maxxp) {
				Datas(p);
			}

			
		
		}
		}
	@SuppressWarnings("deprecation")
	public static void LevelMessage(Player player, OfflinePlayer p) {
		Runable run = new Runable(mains);
		run.Launch(player);		
		LevelSound(player);
		if(PlaceHolders.toInt(getData(Bukkit.getOfflinePlayer(p.getName()))[3]) == PlaceHolders.toInt(main.getConfigFile().getString("MaxLevel"))) {
			for(int i = 0 ; i < main.getformats().getStringList("LevelUpFormat").size(); i++) {
				player.sendMessage(FixColors(PlaceHolders.Pholders(player, getData(p), main.getformats().getStringList("MaxLevelFormat").get(i))));
			}
			return;
		}
		for(int i = 0 ; i < main.getformats().getStringList("LevelUpFormat").size(); i++) {
			player.sendMessage(FixColors(PlaceHolders.Pholders(player, getData(p), main.getformats().getStringList("LevelUpFormat").get(i))));
		}
	}
	@SuppressWarnings("deprecation")
	public static void LevelSound(Player p) {
		if(PlaceHolders.toInt(getData(Bukkit.getOfflinePlayer(p.getName()))[3]) == PlaceHolders.toInt(main.getConfigFile().getString("MaxLevel"))) {
			List<String> sound = main.getConfigFile().getStringList("LevelMaxSounds");
			if(sound.isEmpty()) return;
			for(int i = 0; 1 < sound.size(); i++) {
				if(sound.size() == i) {
					return;
				} else {
		        String[] data = sound.get(i).split(":");
		        String Sound1 = data[0];
		        String Pitch = data[1] + "F";
		        String Shift = data[2] + "F";
		        p.playSound(p.getLocation(), Sound.valueOf(Sound1), Float.valueOf(Pitch).floatValue(), Float.valueOf(Shift).floatValue());
				}
			}
			return;
		}
		
		
		List<String> sound = main.getConfigFile().getStringList("LevelUpSounds");
		if(sound.isEmpty()) return;
		for(int i = 0; 1 < sound.size(); i++) {
			if(sound.size() == i) {
				return;
			} else {
	        String[] data = sound.get(i).split(":");
	        String Sound1 = data[0];
	        String Pitch = data[1] + "F";
	        String Shift = data[2] + "F";
	        p.playSound(p.getLocation(), Sound.valueOf(Sound1), Float.valueOf(Pitch).floatValue(), Float.valueOf(Shift).floatValue());
			}
		}

	}
	public static String FixColors(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

}
