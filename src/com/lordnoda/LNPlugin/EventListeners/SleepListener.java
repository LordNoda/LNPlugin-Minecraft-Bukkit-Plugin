package com.lordnoda.LNPlugin.EventListeners;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedEnterEvent.BedEnterResult;

public class SleepListener implements Listener {
	
	public FileConfiguration Config;
	
	public SleepListener(FileConfiguration config) {	
		this.Config = config;		
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerBedEnterEvent event) {
		
		Player player = event.getPlayer();		
		
		if(event.getBedEnterResult() == BedEnterResult.OK) {
			Bukkit.broadcastMessage(player.getName() + " is sleeping - Moving to day time");
			Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "time set day");
		}

	}
}
