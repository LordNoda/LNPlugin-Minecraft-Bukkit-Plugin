package com.lordnoda.LNPlugin.Commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class LNHelp implements CommandExecutor {	
	
	public FileConfiguration Config;
	public ConfigurationSection HelpConfig;
	
	public LNHelp(FileConfiguration config) {
		
		this.Config = config;
		this.HelpConfig = Config.getConfigurationSection("Help");
		
	}
	
    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {		
		
		List<String> helpMessages = HelpConfig.getStringList("help-messages");
		for(int x = 0 ; x < helpMessages.size(); x++) {
			String message = helpMessages.get(x);
			((Player)sender).sendMessage(message);
		}
	    	
		return true;
    }
}
