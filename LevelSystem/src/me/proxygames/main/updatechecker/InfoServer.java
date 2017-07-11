package me.proxygames.main.updatechecker;
import me.proxygames.main.main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class InfoServer {
	static String adding;
	Plugin plugin = main.pl; 
	static int check = 0;
   private static String libbary = "https://raw.githubusercontent.com/proxygames14/LevelSystem/master/Libary";		 

		public static void checkLibbary(String[] a, CommandSender sender) {

		try {
			
		URL urls = new URL(libbary);
		
		BufferedReader file = new BufferedReader(new InputStreamReader(urls.openStream()));
		BufferedReader count = new BufferedReader(new InputStreamReader(urls.openStream()));
				
		
		int countlines = (int) count.lines().count();
		List<String> target = new ArrayList<String>();
        
        for(int line = 0; line < countlines; line++) {
        	adding = file.readLine();
        	
        	if(adding.equalsIgnoreCase("-"+a[1])) {
        		check = 1;
        	}
        	if(adding.equalsIgnoreCase("-end")) {
                  check = 0;
        	}
        	
        	if(check == 1) {
        		target.add(adding);	
        	}
		}
        if(target.size() == 0) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7We cannot find that version! Try /level version list (page)"));
        	return;
        }
        target.remove("");
        
            sender.sendMessage(color("&7-------------------------------------------------\n"));
            sender.sendMessage(color("&b&lVersion&f: &ev" + a[1] + "\n&d&lType&f: &e" + target.get(1) + "\n \n"));
        for(int i = 2; i < target.size(); i++) {
            sender.sendMessage(color("&a■ &f"+target.get(i)));	
        }
            sender.sendMessage(color("&7-------------------------------------------------"));
	    
	    file.close();
	    count.close();
		} catch (IOException e) {
		}
		}
		public static String color(String s) {
			return ChatColor.translateAlternateColorCodes('&', s);
		}
}
