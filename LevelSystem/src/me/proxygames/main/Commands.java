package me.proxygames.main;



import java.io.File;



import me.proxygames.main.format.MainForm;
import me.proxygames.main.updatechecker.DownloadPlugin;
import me.proxygames.main.updatechecker.InfoServer;
import me.proxygames.main.updatechecker.ListVersions;
import me.proxygames.main.updatechecker.Updater;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
	main plugin;
	private main main;
	public Commands(main main, main plugin) {
		this.main = main;
		this.plugin = plugin;
	}	
	private Updater updatechecker;
	public String version;

	
	@SuppressWarnings({ "deprecation", "static-access" })
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {	
	    if(!sender.hasPermission("levelsystem.command")) {
	    	NoPermissions.Send(sender, "levelsystem.command");
	    	return false;
		}
		    
			if(a.length == 0) {
				ErrorMessage(sender, label);
				return false;
			}
			
            if (!(a[0].equalsIgnoreCase("help"))
                &&!(a[0].equalsIgnoreCase("reload"))
               &&!(a[0].equalsIgnoreCase("set"))
               &&!(a[0].equalsIgnoreCase("add"))
               &&!(a[0].equalsIgnoreCase("check"))       
               &&!(a[0].equalsIgnoreCase("remove"))
               &&!(a[0].equalsIgnoreCase("delete")) 
               &&!(a[0].equalsIgnoreCase("format")) 
               &&!(a[0].equalsIgnoreCase("checkupdates")) 
               &&!(a[0].equalsIgnoreCase("update")) 
               &&!(a[0].equalsIgnoreCase("download")) 
               &&!(a[0].equalsIgnoreCase("version")) 
               &&!(a[0].equalsIgnoreCase("test")) 
                ) 
            {	
            	ErrorMessage(sender, label);
            	return false;	
            }
            
			if(a[0].equalsIgnoreCase("test")) {
				Bukkit.broadcastMessage("testing");
				InfoServer.checkLibbary(a, sender);
			}
            //Help menu starts here
			if(a[0].equalsIgnoreCase("help")) {
			    if(!sender.hasPermission("levelsystem.command.help")) {
			    	NoPermissions.Send(sender, "levelsystem.command.help");
			    	return false;
				
			}
				if(a.length != 1 && a.length != 2) {
	            	ErrorMessage(sender, label);
	            	return false;	
				}
				int page = 1;
				if(a.length != 1) {
				}
				
				int maxpages = 3;
				
				if(a.length == 1 || a[1].equalsIgnoreCase("1") ) {
					
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', 
			    "\n&7----------------------------------------\n"
			    + "&d&l&oLevel System &ev" + plugin.getDescription().getVersion() + " &7&oPage: " + page +"/" + maxpages +">\n"
				+"\n \n&61. &a/" + label +" help &f(For the info page)"
				+"\n&62. &a/" + label +" reload &f(To reload the plugin)"
				+"\n&63. &a/" + label +" set &6[PLAYER] &aXP/LEVEL &6[AMOUNT]"
				+"\n&64. &a/" + label +" add &6[PLAYER] &aXP/LEVEL &6[AMOUNT]"
				+"\n&65. &c&m/" + label +" remove [PLAYER] XP/LEVEL [AMOUNT]"
				+"\n&7----------------------------------------"
						));
				return false;
				} else {
					if(a[1].matches("^[0-9]+$") == false) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Page must be a number!"));
						return false;	
					}
					if(Integer.parseInt(a[1]) > maxpages) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Page does not exists, Max " + maxpages));
						return false;
					}
					if(a[1].equalsIgnoreCase("2")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', 
							    "\n&7----------------------------------------\n"
								+ "&d&l&oLevel System &ev" + plugin.getDescription().getVersion() + " &7&oPage: " + page +"/" + maxpages +">\n"
								+"\n \n&66. &a/" + label +" check &6[PLAYER] &f(To check the level)"
								+"\n&67. &a/" + label +" delete &6[PLAYER] &f(To delete the player file)"
								+"\n&68. &a/" + label +" checkupdates &f(To check updates on the site)"
								+"\n&69. &a/" + label +" update &f(To check updates and fix file data)"
								+"\n&610. &a/" + label +" download &f(To download newest version)"
								+"\n&7----------------------------------------"
								));
					}
					if(a[1].equalsIgnoreCase("3")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', 
							    "\n&7----------------------------------------\n"
								+ "&d&l&oLevel System &ev" + plugin.getDescription().getVersion() + " &7&oPage: " + page +"/" + maxpages +">\n"
								+"\n \n&611. &a/" + label +" version &f(To check the plugin version)"
								+"\n&7----------------------------------------"
								));
					}
					
				}
			}
			
			
			
			if(a[0].equalsIgnoreCase("checkupdates")) {
			    if(!sender.hasPermission("levelsystem.checkupdates")) {
			    	NoPermissions.Send(sender, "levelsystem.checkupdates");
			    	return false;
				
			}
				updatechecker = new Updater(main);
		        if(updatechecker.SendUpdates() == "none") {
		        	
		        	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &fNo updates found! Running version &av" + plugin.getDescription().getVersion()));
		        	return false;
		        }
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', updatechecker.SendUpdates()));
			}
			if(a[0].equalsIgnoreCase("set")) {
			    if(!sender.hasPermission("levelsystem.command.set")) {
			    	NoPermissions.Send(sender, "levelsystem.command.set");
			    	return false;
			    }
				if(a.length != 4) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Type &a/" + label +" set &6[PLAYER] &aXP/LEVEL &6[AMOUNT]"));
					return false;	
				}
				if(!a[2].equalsIgnoreCase("xp") && !a[2].equalsIgnoreCase("level")) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Type &a/" + label +" set &6[PLAYER] &aXP/LEVEL &6[AMOUNT]"));
					return false;		
				}
				if(a[3].matches("^[0-9]+$") == false) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Amount must be a number!"));
					return false;	
				}

				
				OfflinePlayer p = Bukkit.getOfflinePlayer(a[1]);
				OfflineFiles offlineplayer = new OfflineFiles();
				offlineplayer.CreateFile(p);
				
				if(a[2].equalsIgnoreCase("xp")) {
				    if(!sender.hasPermission("levelsystem.command.set.xp")) {
				    	NoPermissions.Send(sender, "levelsystem.command.set.xp");
				    	return false;
				    }
				EditPlayerData data = new EditPlayerData();
				data.setData(p, "xp", Integer.parseInt(a[3]));
				} 				
				if(a[2].equalsIgnoreCase("level")) {
				    if(!sender.hasPermission("levelsystem.command.set.level")) {
				    	NoPermissions.Send(sender, "levelsystem.command.level");
				    	return false;
				    }
					if(Integer.parseInt(a[3]) > main.getConfigFile().getInt("MaxLevel")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Amount cannot be higher than maximum!"));
						return false;	
					}
					
					if(Integer.parseInt(a[3]) < Integer.parseInt(EditPlayerData.getData(p, "level"))) {
						EditPlayerData.setData(p, "xp", 0);
					}
					
				EditPlayerData data = new EditPlayerData();
				data.setData(p, "level", Integer.parseInt(a[3]));
				}
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &aSuccesfully setted &6" + a[1] + "'s &2" + a[2].toLowerCase() + "&a To &e" + a[3]));
				
	            LevelSystem systemlevel = new LevelSystem(main);
	            systemlevel.Datas(p);
	        	LevelUpdater.CheckTabOnlinePlayer(p);
				return false;
				
			}
			if(a[0].equalsIgnoreCase("download")) {
			    if(!sender.hasPermission("levelsystem.command.download")) {
			    	NoPermissions.Send(sender, "levelsystem.command.download");
			    	return false;
			    }
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &eTrying to download from Bukkit!"));
				DownloadPlugin.download(sender);
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &aSuccesfully downloaded&b LevelSystem &ev" + Updater.GetNewestVersion() + " \n&cLevel > &cTIP &fReload to sumbit"));
				return false;
			}
			
			if(a[0].equalsIgnoreCase("update")) {
			    if(!sender.hasPermission("levelsystem.command.update")) {
			    	NoPermissions.Send(sender, "levelsystem.command.update");
			    	return false;
			    }
				if(a.length != 1) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Type &a/" + label +" update"));
					return false;	
				}
					
				main.CreateConfig();
				int amount = Updater.UpdaterFiles();

				if(amount == 0) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7No FileUpdates found, You are up to date!"));
				} else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &aSuccesfully updated &e" + amount + " &aThing(s)!"));
				}

				return false;
			}
			
			if(a[0].equalsIgnoreCase("add")) {
			    if(!sender.hasPermission("levelsystem.command.add")) {
			    	NoPermissions.Send(sender, "levelsystem.command.add");
			    	return false;
			    }
				if(a.length != 4) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Type &a/" + label +" add &6[PLAYER] &aXP/LEVEL &6[AMOUNT]"));
					return false;	
				}
				if(!a[2].equalsIgnoreCase("xp") && !a[2].equalsIgnoreCase("level")) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Type &a/" + label +" add &6[PLAYER] &aXP/LEVEL &6[AMOUNT]"));
					return false;		
				}
				if(a[3].matches("^[0-9]+$") == false) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Amount must be a number!"));
					return false;	
				}

				
				OfflinePlayer p = Bukkit.getOfflinePlayer(a[1]);
				OfflineFiles offlineplayer = new OfflineFiles();
				offlineplayer.CreateFile(p);
				
				
				
				if(a[2].equalsIgnoreCase("xp")) {
				    if(!sender.hasPermission("levelsystem.command.add.xp")) {
				    	NoPermissions.Send(sender, "levelsystem.command.add.xp");
				    	return false;
				    }
				EditPlayerData data = new EditPlayerData();
				data.addData(p, "xp", Integer.parseInt(a[3]));
				} 				
				if(a[2].equalsIgnoreCase("level")) {
				    if(!sender.hasPermission("levelsystem.command.add.level")) {
				    	NoPermissions.Send(sender, "levelsystem.command.add.level");
				    	return false;
				    }
					if(Integer.parseInt(a[3]) > main.getConfigFile().getInt("MaxLevel")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Amount cannot be higher than maximum!"));
						return false;	
					}
				EditPlayerData data = new EditPlayerData();
				data.addData(p, "level", Integer.parseInt(a[3]));
				}
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &aSuccesfully added &e" + a[3] + " &2"+ a[2].toLowerCase() + "'s" + "&a to&6 " + a[1]));
				
	            LevelSystem systemlevel = new LevelSystem(main);
	            systemlevel.Datas(p);
	        	LevelUpdater.CheckTabOnlinePlayer(p);
	            
				return false;
				
			}
			
			
			if(a[0].equalsIgnoreCase("version")) {
			    if(!sender.hasPermission("levelsystem.command.version")) {
			    	NoPermissions.Send(sender, "levelsystem.command.version");
			    	return false;
			    }
				if(a.length == 1) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', 
					    "&7----------------------------------------\n"
						+"\n&b&lCurrent Version&f: v" + main.pl.getDescription().getVersion()
						+"\n&e&lNewest Version&f: v" + Updater.GetNewestVersion()
						+"\n&7----------------------------------------"
						));
				return false;
				}
				if(a.length > 3) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Type &a/" + label +" version [VERSION] | list (page)"));
					return false;
				}
				
				if(a[1].equalsIgnoreCase("list")) {
					if(a[2].matches("^[0-9]+$") == false) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7We cannot find page! only put numbers!"));
						return false;
					}
					ListVersions.checkList(a, sender);
					return false;
				}
				if(a.length == 2 && a[1].matches("^[0-9]+$") == false && a[1].contains(".") && a[1].toString().length() == 5 
						&& a[1].toString().substring(1, 2).equalsIgnoreCase(".")
						&& a[1].toString().substring(3, 4).equalsIgnoreCase(".")
						) {
                    if(Integer.parseInt(a[1].replace(".", "")) > Integer.parseInt(Updater.GetNewestVersion().replace(".", ""))) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7That version is from the future! Have patience"));
                    	return false;
                    }
					InfoServer.checkLibbary(a, sender);
					return false;
				} else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Wrong input! Example: 1.5.3"));
					return false;
				}

			}
			
			
			if(a[0].equalsIgnoreCase("remove")) {
  
				sender.sendMessage("comming soon");
				int i = 1;
				if (i == 1)
				return false;
				
			    if(!sender.hasPermission("levelsystem.command.remove")) {
			    	NoPermissions.Send(sender, "levelsystem.command.remove");
			    	return false;
			    }
				if(a.length != 4) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Type &a/" + label +" remove &6[PLAYER] &aXP/LEVEL &6[AMOUNT]"));
					return false;	
				}
				if(!a[2].equalsIgnoreCase("xp") && !a[2].equalsIgnoreCase("level")) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Type &a/" + label +" remove &6[PLAYER] &aXP/LEVEL &6[AMOUNT]"));
					return false;		
				}
				if(a[3].matches("^[0-9]+$") == false) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Amount must be a number!"));
					return false;	
				}

				
				OfflinePlayer p = Bukkit.getOfflinePlayer(a[1]);
				OfflineFiles offlineplayer = new OfflineFiles();
				offlineplayer.CreateFile(p);
				
				
				
				if(a[2].equalsIgnoreCase("xp")) {
				    if(!sender.hasPermission("levelsystem.command.remove.xp")) {
				    	NoPermissions.Send(sender, "levelsystem.command.remove.xp");
				    	return false;
				    }
				EditPlayerData data = new EditPlayerData();
				if(Integer.parseInt(EditPlayerData.getData(p, "level")) == 0 && Integer.parseInt(EditPlayerData.getData(p, "xp")) < Integer.parseInt(a[3])) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Amount cannot be lower than xp at level 0"));
					return false;
				}
				data.removeData(p, "xp", Integer.parseInt(a[3]));
				} 				
				if(a[2].equalsIgnoreCase("level")) {
				    if(!sender.hasPermission("levelsystem.command.remove.level")) {
				    	NoPermissions.Send(sender, "levelsystem.command.remove.level");
				    	return false;
				    }
					if(Integer.parseInt(a[3]) > Integer.parseInt(EditPlayerData.getData(p, "level"))) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Amount cannot be lower than 0"));
						return false;	
					}
				EditPlayerData data = new EditPlayerData();
				data.removeData(p, "level", Integer.parseInt(a[3]));
				}
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &aSuccesfully removed &e" + a[3] + " &2"+ a[2].toLowerCase() + "'s" + "&a to&6 " + a[1]));
				
	            LevelSystem systemlevel = new LevelSystem(main);
	            systemlevel.Datas(p);
	        	LevelUpdater.CheckTabOnlinePlayer(p);
	            
				return false;
				
			}
			
			
		
			
			if(a[0].equalsIgnoreCase("delete")) {
			    if(!sender.hasPermission("levelsystem.command.delete")) {
			    	NoPermissions.Send(sender, "levelsystem.command.delete");
			    	return false;
			    }
				if(a.length != 2) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Type &a/" + label +" delete &6[PLAYER]"));
					return false;	
				}
				OfflinePlayer p = Bukkit.getOfflinePlayer(a[1]);
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', DeleteFiles.DeletePlayerFile(p)));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &4TIP&f: To avoid errors. Type &e/" + label + " reload"));
				
			}
			
			
			if(a[0].equalsIgnoreCase("check")) {
			    if(!sender.hasPermission("levelsystem.command.check")) {
			    	NoPermissions.Send(sender, "levelsystem.command.check");
			    	return false;
			    }
				if(a.length != 1 && a.length != 2) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Type &a/" + label +" check &6[PLAYER]"));
					return false;	
				}
				OfflinePlayer p;
				if(a.length == 1) {
				    if(!sender.hasPermission("levelsystem.command.check.self")) {
				    	NoPermissions.Send(sender, "levelsystem.command.check.self");
				    	return false;
				    }
					p = Bukkit.getOfflinePlayer(sender.getName());					
					
				} else {
				    if(!sender.hasPermission("levelsystem.command.check.other")) {
				    	NoPermissions.Send(sender, "levelsystem.command.check.other");
				    	return false;
				    }
				   p = Bukkit.getOfflinePlayer(a[1]) ;
				}
				
			    File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("LevelSystemAPI").getDataFolder(), File.separator + "DataBase");
		        File f = new File(userdata, File.separator + p.getUniqueId() + ".yml");
		        
		        if(!f.exists()) {
					OfflineFiles offlineplayer = new OfflineFiles();
					offlineplayer.CreateFile(p);  	
		        }

				
				for(int i = 0 ; i < main.getformats().getStringList("CheckStatsFormat").size(); i++) {
					sender.sendMessage(LevelSystem.FixColors(PlaceHolders.Pholders(p, LevelSystem.getData(p), main.getformats().getStringList("CheckStatsFormat").get(i))));
				}
				return false;
				
			}
			
			
			if(a[0].equalsIgnoreCase("reload")) {
			    if(!sender.hasPermission("levelsystem.command.reload")) {
			    	NoPermissions.Send(sender, "levelsystem.command.reload");
			    	return false;
			    }
				if(a.length != 1) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Type &a/" + label +" reload"));
					return false;	
				}
				main.CreateConfig();
				Updater.UpdaterFiles();
				  for(Player online : Bukkit.getOnlinePlayers()) {
			          OfflineFiles.CreateEveryone(online);
			          LevelUpdater.UpdateTabList(online);
			          LevelSystem.SetBar(online);
				  }				


				Reload reload = new Reload();
				reload.reloading();
				
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Reloaded Succesfully!" + " &f" + reload.GetAmount() + " Files"));
				
			}
			
			
			if(a[0].equalsIgnoreCase("format")) {
			    if(!sender.hasPermission("levelsystem.command.format")) {
			    	NoPermissions.Send(sender, "levelsystem.command.format");
			    	return false;
			    }
				if(a.length == 1) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', 
						    "\n&7----------------------------------------\n"
						    + "&d&l&oLevel System &7&oPage: 1/1>\n"
							+"\n&61. &b/" + label +" format add [FROM]-[TO] '[FORMAT]'"
							+"\n&62. &b/" + label +" format edit [FROM]-[TO] '[NEWFORMAT]'"
							+"\n&63. &b/" + label +" format remove [FROM]-[TO]"
							+"\n&64. &b/" + label +" format check [FROM]-[TO]"							
							+"\n&7----------------------------------------"
									));
							return false;
				}
	            if (!(a[1].equalsIgnoreCase("add"))
	            &&!(a[1].equalsIgnoreCase("check"))
	            &&!(a[1].equalsIgnoreCase("remove"))            
	            &&!(a[1].equalsIgnoreCase("edit"))) {
	            	ErrorMessage(sender, label);
	            }
				
				if(a[1].equalsIgnoreCase("add")) {
				    if(!sender.hasPermission("levelsystem.command.format.add")) {
				    	NoPermissions.Send(sender, "levelsystem.command.format.add");
				    	return false;
				    }
					if(a.length == 2 || a.length == 3) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cLevel > &7Type &a/" + label +" format add [FROM]-[TO] '[FORMAT]'"));
						return false;
					}
					  MainForm.addform(sender, a);				
				}
				if(a[1].equalsIgnoreCase("check")) {
				    if(!sender.hasPermission("levelsystem.command.format.check")) {
				    	NoPermissions.Send(sender, "levelsystem.command.format.check");
				    	return false;
				    }
					if(a.length != 3) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cLevel > &7Type &a/" + label +" format check [FROM]-[TO]"));
						return false;
					}
					  MainForm.checkform(sender, a);				
				}
				else 
				 if(a[1].equalsIgnoreCase("edit")) {
					    if(!sender.hasPermission("levelsystem.command.format.edit")) {
					    	NoPermissions.Send(sender, "levelsystem.command.format.edit");
					    	return false;
					    }
					if(a.length == 2 || a.length == 3) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cLevel > &7Type &a/" + label +" format edit [FROM]-[TO] '[FORMAT]'"));
						return false;
					}
					  MainForm.editform(sender, a);
				} else 
				if(a[1].equalsIgnoreCase("remove")) {
				    if(!sender.hasPermission("levelsystem.command.format.remove")) {
				    	NoPermissions.Send(sender, "levelsystem.command.format.remove");
				    	return false;
				    }
					if(a.length != 3) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cLevel > &7Type &a/" + label +" format remove [FROM]-[TO]"));
						return false;
					}
					  MainForm.removeform(sender, a);				
				}
					return false;	
			}
		
		return false;
	}
	
public void ErrorMessage(CommandSender sender, String label) {
	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel > &7Wrong usage. try &f/" + label +" help&7."));
}
}
