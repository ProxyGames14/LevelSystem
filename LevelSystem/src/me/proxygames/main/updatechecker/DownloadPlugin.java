package me.proxygames.main.updatechecker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import me.proxygames.main.main;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.UnknownDependencyException;

public class DownloadPlugin {

	


		  public static void download(CommandSender sender) {
			  String s = "https://dev.bukkit.org/projects/levelsystem/files/2446250/download";
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
		Bukkit.broadcastMessage("error1");
		e.printStackTrace();
	} catch (InvalidPluginException e) {
		Bukkit.broadcastMessage("error2");
		e.printStackTrace();
	} catch (InvalidDescriptionException e) {
		Bukkit.broadcastMessage("error3");
		e.printStackTrace();
	}
	Bukkit.getPluginManager().enablePlugin(main.pl);
	

	
}

		  public static String fileName(String s)
		  {
		    return "LevelSystemAPI.jar";
		  }
	}
