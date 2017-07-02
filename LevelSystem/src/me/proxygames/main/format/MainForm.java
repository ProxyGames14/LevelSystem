package me.proxygames.main.format;

import java.io.IOException;

import me.proxygames.main.main;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MainForm {

	
	public static void addform(CommandSender sender, String[] a) {
		String message = "";
		StringBuilder s = new StringBuilder();
		for(int i = 3; i < a.length; i++) {
			if(i > 3) s.append(" ");
				s.append(a[i]); 
		}
		if(!s.toString().endsWith('"' + "") && !s.toString().startsWith('"' + "") || s.toString().length() < 2) return;
		message = s.substring(s.toString().indexOf('"') + 1);
		message = message.substring(0, message.indexOf('"')).replace("_", " ");
		
		if(a[2].contains("-")) {
		String[] picker = a[2].split("-");
		if(picker[0].matches("^[0-9]+$") == true && picker[1].matches("^[0-9]+$") == true) {
			
		int pickfirst = Integer.parseInt(picker[0]);
		int picklast = Integer.parseInt(picker[1]);
		
		if(pickfirst > picklast) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7That first value cannot be higher than the second"));
			return;	
		}
		
		if(main.getformats().getString("Formats." + pickfirst + "-" + picklast) != null) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7That value already exists, Try &e/level format edit"));
			return;
		}
		main.getformats().set("Formats." + pickfirst + "-" + picklast + ".format", message);
		try {
			main.getformats().save(main.formatfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &aSuccesfully added value &e" + pickfirst + "-" + picklast + " &aformat&f " + '"' + message+'"'));
		
		}
		} else return;

	}
	
	
	
	public static void editform(CommandSender sender, String[] a) {
		String message = "";
		StringBuilder s = new StringBuilder();
		for(int i = 3; i < a.length; i++) {
			if(i > 3) s.append(" ");
				s.append(a[i]); 
		}
		if(!s.toString().endsWith('"' + "") && !s.toString().startsWith('"' + "") || s.toString().length() < 2) return;
		message = s.substring(s.toString().indexOf('"') + 1);
		message = message.substring(0, message.indexOf('"')).replace("_", " ");
		
		if(a[2].contains("-")) {
		String[] picker = a[2].split("-");
		if(picker[0].matches("^[0-9]+$") == true && picker[1].matches("^[0-9]+$") == true) {
			
		int pickfirst = Integer.parseInt(picker[0]);
		int picklast = Integer.parseInt(picker[1]);
		
		if(pickfirst > picklast) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7That first value cannot be higher than the second"));
			return;	
		}
		
		if(!main.getformats().getConfigurationSection("Formats").getKeys(false).contains(pickfirst + "-" + picklast)) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7That value doesnt exists, Try &e/level format add"));
			return;
		}
		
		main.getformats().set("Formats." + pickfirst + "-" + picklast + ".format", message);
		try {
			main.getformats().save(main.formatfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &aSuccesfully edited value &e" + pickfirst + "-" + picklast + " &ato format&f " + '"' + message+'"'));
		
		}
		} else return;

	}
	
	
	public static void removeform(CommandSender sender, String[] a) {	
		if(a[2].contains("-")) {
		String[] picker = a[2].split("-");
		if(picker[0].matches("^[0-9]+$") == true && picker[1].matches("^[0-9]+$") == true) {
			
		int pickfirst = Integer.parseInt(picker[0]);
		int picklast = Integer.parseInt(picker[1]);
		
		if(pickfirst > picklast) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7That first value cannot be higher than the second"));
			return;	
		}
		
		if(!main.getformats().getConfigurationSection("Formats").getKeys(false).contains(pickfirst + "-" + picklast)) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7That value doesnt exists, Try &e/level format add"));
			return;
		}
		main.getformats().set("Formats." + pickfirst + "-" + picklast, null);
		try {
			main.getformats().save(main.formatfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &aSuccesfully remove format value &e" + pickfirst + "-" + picklast));
		
		}
		} else return;

	}
	
	
	public static void checkform(CommandSender sender, String[] a) {
		if(a[2].contains("-")) {
		String[] picker = a[2].split("-");
		if(picker[0].matches("^[0-9]+$") == true && picker[1].matches("^[0-9]+$") == true) {
			
		int pickfirst = Integer.parseInt(picker[0]);
		int picklast = Integer.parseInt(picker[1]);
		
		if(pickfirst > picklast) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7That first value cannot be higher than the second"));
			return;	
		}
		
		if(!main.getformats().getConfigurationSection("Formats").getKeys(false).contains(pickfirst + "-" + picklast)) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7That value doesnt exists, Try &e/level format add"));
			return;
		}
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', 
				"&9&m-------------------------------------------&r\n"
				+ "&a&lFormat Help"
				+ " \n \n"
				+ "&bValue&f: " + pickfirst + "-" + picklast + "\n"
				+ "&bFormat&f: " + '"' + main.getformats().getString("Formats." + pickfirst + "-" + picklast + ".format") + '"' 
				+ "\n&9&m-------------------------------------------"
				));
		
		}
		} else return;

	}
	
	
	
	
}
