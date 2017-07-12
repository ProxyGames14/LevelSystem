package me.proxygames.main.updatechecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import me.proxygames.main.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.UnknownDependencyException;

public class DownloadPlugin {

	
	static String adding;
	static Plugin getplugin = main.pl; 
	static int check = 0;
   private static String libbary = "https://raw.githubusercontent.com/proxygames14/LevelSystem/master/downloads";		 

		public static void checkDownloads(String[] a, CommandSender sender) {

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
        if(target.size() != 3) return;
        String version = target.get(0).replace("-", "");
        
         if(version.equalsIgnoreCase(getplugin.getDescription().getVersion())) {
 			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7You already installed that version!"));
        	 return;
         }
        String fileversion = target.get(1).replace("file:", "");
        String type = target.get(2).replace("type:", "").toUpperCase();
        download(sender, version, fileversion, type);

	    file.close();
	    count.close();
		} catch (IOException e) {
		}
		}


		  public static void download(CommandSender sender, String version, String fileversion, String type) {
			  
			  
			  String s = "https://dev.bukkit.org/projects/levelsystem/files/" + fileversion + "/download";
			  File dataFolder = new File("plugins/");
			  
		      if (!dataFolder.exists()) {
		        dataFolder.mkdir();
		      }
		      File dataFile = new File(dataFolder.getPath() + File.separator + fileName(s));
		      try
		      {
		        long startTime = System.currentTimeMillis();

		        System.out.println("Connecting to site...\n");
		        System.out.println("Saving to: " + dataFolder.getPath() + File.separator + fileName(s));

		        URL url = new URL(s);
		        url.openConnection();
		        InputStream reader = url.openStream();

		        FileOutputStream writer = new FileOutputStream(dataFile);
		        byte[] buffer = new byte[153600];
		        int totalBytesRead = 0;
		        int bytesRead = 0;

		        System.out.println("Reading ZIP file 150KB blocks at a time.\n");

		        while ((bytesRead = reader.read(buffer)) > 0)
		        {
		          writer.write(buffer, 0, bytesRead);
		          buffer = new byte[153600];
		          totalBytesRead += bytesRead;
		        }

		        long endTime = System.currentTimeMillis();

		        System.out.println("Done. " + new Integer(totalBytesRead).toString() + " bytes read (" + new Long(endTime - startTime).toString() + " millseconds).\n");
		        writer.close();
		        reader.close();

		      }
		      catch (MalformedURLException e)
		      {
		        e.printStackTrace();
		      }
		      catch (IOException e)
		      {
		        e.printStackTrace();
		      }
		      LoadPlugin();
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &aSuccesfully downloaded&b LevelSystem &ev" + version));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &aFile&f: &e" + fileversion + " &aType&f: &e" + type +
						"\n&cLevel > &c&fYou must Reload to sumbit"));
		  }
		  
public static void LoadPlugin() {
	File file = new File("plugins/LevelSystemAPI.jar");
	if (!file.exists()) return;
	if(file.exists()) {
	}
	Bukkit.getPluginManager().disablePlugin(main.pl);

	try {
		Bukkit.getPluginManager().loadPlugin(file);
	} catch (UnknownDependencyException e) {
		e.printStackTrace();
	} catch (InvalidPluginException e) {
		e.printStackTrace();
	} catch (InvalidDescriptionException e) {
		e.printStackTrace();
	}
	Bukkit.getPluginManager().enablePlugin(main.pl);
	

	
}

		  public static String fileName(String s)
		  {
		    return "LevelSystemAPI.jar";
		  }
	}
