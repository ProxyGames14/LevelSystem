package me.proxygames.main;




import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import me.proxygames.main.updatechecker.Updater;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;


public class main extends JavaPlugin implements Listener {
	public static String updatemsg = null;
	public static main plugin;
	  static FileConfiguration config;
	  public static File congiffile;
	  static FileConfiguration format;
	  public static File formatfile;
	  static FileConfiguration worldbar;
	  public static File worldbarfile;

	  private Updater updatechecker;
	public String version;

	public static main pl;
	   
	public main() {
	    pl = this;
	}
	
	public void onEnable() {


	getServer().getPluginManager().registerEvents(this, this);
	PluginDescriptionFile pdfile = getDescription();
	ConsoleCommandSender clogger = this.getServer().getConsoleSender();
    clogger.sendMessage(ChatColor.GOLD + "---------------------------------------");
    clogger.sendMessage(ChatColor.AQUA + pdfile.getName() + " Has Been Enabled");
    clogger.sendMessage(ChatColor.GREEN + "Plugin version: " + ChatColor.YELLOW + "v" +getDescription().getVersion());
    clogger.sendMessage(ChatColor.GOLD + "---------------------------------------");
    registerListeners();
    CreateConfig();
    GetFilesOnlinePlayers();
    
    CheckUpdates();
	}
	
	public void CheckUpdates() {
		  updatechecker = new Updater(this);
		  updatechecker.startUpdateCheck();	
		  Updater.UpdaterFiles();
		  CreateConfig();
	}
	
	public void onDisable() {
		PluginDescriptionFile pdfile = getDescription();
		ConsoleCommandSender clogger = this.getServer().getConsoleSender();
	    clogger.sendMessage(ChatColor.GOLD + "---------------------------------------");
	    clogger.sendMessage(ChatColor.DARK_RED + pdfile.getName() + " Has Been Disabled");
	    clogger.sendMessage(ChatColor.GREEN + "Plugin version: " + ChatColor.YELLOW + "v" +getDescription().getVersion());
	    clogger.sendMessage(ChatColor.GOLD + "---------------------------------------");
	    if(!Bukkit.getOnlinePlayers().isEmpty())
		RemoveTabList();
	}

	public void RemoveTabList() {
		  for(Player online : Bukkit.getOnlinePlayers()) {
	          LevelUpdater.UpdateTabList(online);
		  }
	}
	
	public void GetFilesOnlinePlayers() {
		  for(Player online : Bukkit.getOnlinePlayers()) {
	          OfflineFiles.CreateEveryone(online);
	          LevelUpdater.UpdateTabList(online);
	          LevelSystem.SetBar(online);
		  }
	}

	
    public static FileConfiguration getConfigFile()
    {
      return config;
    }
    public static FileConfiguration getformats()
    {
      return format;
    }
    public static FileConfiguration getWorldBars()
    {
      return worldbar;
    }
    
	  public void CreateConfig() {

		  
		    congiffile = new File(getDataFolder(), "config.yml");
		    formatfile = new File(getDataFolder(), "format.yml");
		    worldbarfile = new File(getDataFolder(), "worldbar.yml");
		    
		    if (!congiffile.exists()) {
		      congiffile.getParentFile().mkdirs();
		      saveResource("config.yml", false);
		    }
		    if (!worldbarfile.exists()) {
		    	worldbarfile.getParentFile().mkdirs();
			      saveResource("worldbar.yml", false);
			    }
		    if (!formatfile.exists()) {
		    	formatfile.getParentFile().mkdirs();
			      saveResource("format.yml", false);
			    }

		    
		    config = new YamlConfiguration();
		    format = new YamlConfiguration();
		    worldbar = new YamlConfiguration();
		    
		    
		    
		    try {
				worldbar.load(worldbarfile);
			} catch (FileNotFoundException e) {
				  getServer().getConsoleSender().sendMessage(ChatColor.RED + "ERROR 674: Coud not load worldbar.yml");
				e.printStackTrace();
			} catch (IOException e) {
				  getServer().getConsoleSender().sendMessage(ChatColor.RED + "ERROR 342: Coud not load worldbar.yml");
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				  getServer().getConsoleSender().sendMessage(ChatColor.RED + "ERROR 113: Coud not load worldbar.yml");
				e.printStackTrace();
			}
		    
		    try {
				config.load(congiffile);
			} catch (FileNotFoundException e) {
				  getServer().getConsoleSender().sendMessage(ChatColor.RED + "ERROR 126: Coud not load config.yml");
				e.printStackTrace();
			} catch (IOException e) {
				  getServer().getConsoleSender().sendMessage(ChatColor.RED + "ERROR 134: Coud not load config.yml");
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				  getServer().getConsoleSender().sendMessage(ChatColor.RED + "ERROR 197: Coud not load config.yml");
				e.printStackTrace();
			}
		    
    
		    
		    try {
				format.load(formatfile);
			} catch (FileNotFoundException e) {
				  getServer().getConsoleSender().sendMessage(ChatColor.RED + "ERROR 126: Coud not load format.yml");
				e.printStackTrace();
			} catch (IOException e) {
				  getServer().getConsoleSender().sendMessage(ChatColor.RED + "ERROR 134: Coud not load format.yml");
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				getServer().getConsoleSender().sendMessage(ChatColor.RED + "ERROR 197: Coud not load format.yml");
				e.printStackTrace();
			}
		    
		    
			try {
				getWorldBars().save(worldbarfile);
			} catch (IOException e) {
				  getServer().getConsoleSender().sendMessage(ChatColor.RED + "ERROR 543: Coud not save worldbar.yml");
				e.printStackTrace();
			}
			
		    
			try {
				getConfigFile().save(congiffile);
			} catch (IOException e) {
				  getServer().getConsoleSender().sendMessage(ChatColor.RED + "ERROR 453: Coud not save config.yml");
				e.printStackTrace();
			}

			try {
				getformats().save(formatfile);
			} catch (IOException e) {
				  getServer().getConsoleSender().sendMessage(ChatColor.RED + "ERROR 234: Coud not save format.yml");
				e.printStackTrace();
			}
		    
		    
		  }


	  
	  
public void registerListeners() {
    getServer().getPluginManager().registerEvents(new Events(this), this);
    getCommand("levelsystem").setExecutor(new Commands(this, this));

}
}