package me.proxygames.main;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class PlaceHolders {

	
	@SuppressWarnings("deprecation")
	public static String Pholders(OfflinePlayer p, String[] List, String s) {
		return s.replace("%level%", List[3])
				.replace("%player%", p.getName())
				.replace("%xp%", List[2])
				.replace("%procent%", List[1])
				.replace("%xplevelup%", List[0])
				.replace("%maxlevel%", main.getConfigFile().getString("MaxLevel"))
				.replace("%ismax%", (PlaceHolders.toInt(LevelSystem.getData(Bukkit.getOfflinePlayer(p.getName()))[3]) == PlaceHolders.toInt(main.getConfigFile().getString("MaxLevel")) ? "&cMAXED" : "&7Not Maxed") + "")
				;
	}
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
	
}
