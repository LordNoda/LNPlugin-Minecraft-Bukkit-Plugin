package com.lordnoda.LNPlugin.EventListeners;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.World;
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
			
			World world = player.getWorld();
			long Time_Required_To_Next_Day = 24000 - world.getTime();				
			Bukkit.broadcastMessage(player.getName() + " is sleeping - Moving to day time");
			world.setFullTime(world.getFullTime() + Time_Required_To_Next_Day);
			
			Thread ResetTimeReset = new ResetTimeReset();
			ResetTimeReset.start();
			
		}

	}
	
	public class ResetTimeReset extends Thread {
		
		public void run() {
			
			List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
			
			for(int x = 0; x < players.size(); x++ ) {				
				players.get(x).setStatistic(Statistic.TIME_SINCE_REST, 0);
			}			
		}
	}
	
}
