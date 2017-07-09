package me.proxygames.main.updatechecker;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.bukkit.ChatColor;

import me.proxygames.main.main;
 
public class Updater {
main plugin;
public Updater(main plugin) {
this.plugin = plugin;
currentVersion = plugin.getDescription().getVersion();
}
 
private String currentVersion;
private String readurl = "https://raw.githubusercontent.com/proxygames14/LevelSystem/master/version.txt";
 
static int amount = 0;
public static int UpdaterFiles() {
	UpdateWorldBar.CheckUpdate();
	UpdateConfig.CheckUpdate();
	UpdateFormats.CheckUpdate();
	int current = amount;
	amount = 0;
	return current;
}

public void startUpdateCheck() {

if (main.getConfigFile().getBoolean("update-checker")) {

try {
URL urls = new URL(readurl);
BufferedReader brs = new BufferedReader(new InputStreamReader(urls.openStream()));
String line = brs.readLine();
String newvervionstring = line.substring(12);
double newversion = Double.parseDouble(newvervionstring.replace(".", ""));
double version = Double.parseDouble(currentVersion.replace(".", ""));
if(newversion > version) {
	plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', 
			"&dUPDATE > &bLevelSystem &ev" + line.substring(13) + " &bHas been released! Get it now on BukkitDev!"
			));
} else {
	plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', 
			"&aLevelSystem &ev" + currentVersion + " &ais up to date"
			));
}
brs.close();
} catch (IOException e) {
	
}
}
}

public String SendUpdates() {

if (main.getConfigFile().getBoolean("update-checker")) {

try {
URL urls = new URL(readurl);
BufferedReader brs = new BufferedReader(new InputStreamReader(urls.openStream()));
String line = brs.readLine();
String newvervionstring = line.substring(12);
double newversion = Double.parseDouble(newvervionstring.replace(".", ""));
double version = Double.parseDouble(currentVersion.replace(".", ""));
brs.close();
if(newversion > version) {
	return "&cLevelSystem > &bLevelSystem &ev" + line.substring(13) + " &bHas been released!\nGet it now on BukkitDev! &fhttps://dev.bukkit.org/projects/levelsystem";
} else {
	return "none";
}

} catch (IOException e) {
	
}
}

return "ERROR";
}


public static String GetNewestVersion() {

if (main.getConfigFile().getBoolean("update-checker")) {
 String readurl1 = "https://raw.githubusercontent.com/proxygames14/LevelSystem/master/version.txt";
try {
URL urls = new URL(readurl1);
BufferedReader brs = new BufferedReader(new InputStreamReader(urls.openStream()));
String line = brs.readLine();
brs.close();

return line.substring(13);

} catch (IOException e) {
	
}
}

return "ERROR";
}

}
