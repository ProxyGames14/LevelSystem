package me.proxygames.main;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class NoPermissions {

	public static void Send(CommandSender sender, String perm) {
		sender.sendMessage(colors(main.getformats().getString("NoPermissions").replace("%permission%", perm)));
		return;
	}
	public static String colors(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
}
