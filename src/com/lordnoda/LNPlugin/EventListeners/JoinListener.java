package com.lordnoda.LNPlugin.EventListeners;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
	
	public FileConfiguration Config;
	public ConfigurationSection JoinMessagesConfig;
	public ConfigurationSection JoinCommandConfig;
	
	public JoinListener(FileConfiguration config) {
		
		this.Config = config;
		this.JoinMessagesConfig = Config.getConfigurationSection("Join").getConfigurationSection("Join Messages");
		this.JoinCommandConfig = Config.getConfigurationSection("Join").getConfigurationSection("Join Commands");
		
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		
		if(JoinMessagesConfig.getBoolean("join-messages-enabled") == true) {
			List<String> joinMessages = JoinMessagesConfig.getStringList("join-messages");
			for(int x = 0 ; x < joinMessages.size(); x++) {
				String message = joinMessages.get(x).replace("%motd%", Bukkit.getMotd()).replace("%player%", event.getPlayer().getName());
				event.getPlayer().sendMessage(message);
			}
		}
		
		if(JoinCommandConfig.getBoolean("join-commands-enabled") == true) {
			List<String> joinCommands = JoinCommandConfig.getStringList("join-commands");
			for(int x = 0 ; x < joinCommands.size(); x++) {
				String command = joinCommands.get(x).replace("%motd%", Bukkit.getMotd()).replace("%player%", event.getPlayer().getName());
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
			}
		}
		
	}
}
