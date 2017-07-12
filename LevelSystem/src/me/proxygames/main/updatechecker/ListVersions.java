package me.proxygames.main.updatechecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import me.proxygames.main.main;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class ListVersions {
	
	static int page = 0;
	static int currentpage = 0;
	static int select = 0;
   
	static String adding;
	Plugin plugin = main.pl; 
	static int check = 0;
   private static String libbary = "https://raw.githubusercontent.com/proxygames14/LevelSystem/master/list";	

		public static void checkList(String[] a, CommandSender sender) {

		try {
			
		URL urls = new URL(libbary);
		
		BufferedReader file = new BufferedReader(new InputStreamReader(urls.openStream()));
		BufferedReader count = new BufferedReader(new InputStreamReader(urls.openStream()));
			
		
		int countlines = (int) count.lines().count();
		List<String> target = new ArrayList<String>();
        
        for(int line = 0; line < countlines; line++) {
        	target.add(file.readLine());
		}
        Pages(a, sender, target);
	    
	    file.close();
	    count.close();
		} catch (IOException e) {
		}
		}
		
		public static void Pages(String[] a, CommandSender sender, List<String> list) {
			
			List<String> send = new ArrayList<String>();
			
			if(a.length == 2) select = 1; else select = Integer.parseInt(a[2]);
			
			int size = list.size();
			int maxperpage = 5;
			double maxpages = Math.floor(size / maxperpage) + 1;
			int selectpage = select*maxperpage -5;
			if(size < (selectpage) + 1) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Page doesn't exist"));
				return;
			}
			
			for(int i = 0; i < maxperpage; i++) {
				if(size >= selectpage + (i+1)) send.add(list.get(selectpage + i));
			}
			senddata(a, sender, send, selectpage, maxpages);

			
		}
		public static void senddata(String[] a, CommandSender sender, List<String> send, int selectpage, double maxpages) {
			int page = 1;
			if(a.length == 3) {
				page = Integer.parseInt(a[2]); 	
			}
			String[] list;
            sender.sendMessage(color("&7-------------------------------------------------\n"));
            sender.sendMessage(color("&a&lVersions List &ePage " + page + "/" + ((int)maxpages) + "\n "));
			for(int i = 0; i < send.size(); i++) {
				list = send.get(i).split(" ");
				sender.sendMessage(color("&b➤ &ev" + list[0] + " &a[" + list[1] + "] &d" + list[2].toUpperCase()));
			}
            sender.sendMessage(color("&7-------------------------------------------------\n"));
		}
		public static String color(String s) {
			return ChatColor.translateAlternateColorCodes('&', s);
		}
		
		public static Boolean isvalid(String[] a) {
			if(a.length == 2 && a[1].matches("^[0-9]+$") == false && a[1].contains(".") && a[1].toString().length() == 5 
					&& a[1].toString().substring(1, 2).equalsIgnoreCase(".")
					&& a[1].toString().substring(3, 4).equalsIgnoreCase("."))
				return true; else return false;
		}
	
}
