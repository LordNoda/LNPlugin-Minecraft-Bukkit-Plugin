package com.lordnoda.LNPlugin;
import java.io.File;
import java.util.ArrayList;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.lordnoda.LNPlugin.Commands.LNHelp;
import com.lordnoda.LNPlugin.EventListeners.JoinListener;
import com.lordnoda.LNPlugin.EventListeners.SleepListener;


public class LNPlugin extends JavaPlugin{	
	
	FileConfiguration config = getConfig();
    // Fired when plugin is first enabled
	
    @Override
    public void onEnable() {
		
    	saveDefaultConfig();
    	getConfig().options().copyDefaults(true);
    	
        //Register event listeners 
    	getServer().getPluginManager().registerEvents(new JoinListener(config), this);
    	getServer().getPluginManager().registerEvents(new SleepListener(config), this);
    	
    	//Register commands
    	this.getCommand("LNHelp").setExecutor(new LNHelp(config));    
    	
    }
    
    // Fired when plugin is disabled
    @Override
    public void onDisable() {

    }
}
