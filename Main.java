package de.matrixkabel.mchat.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.matrixkabel.mchat.command.AddBadword;
import de.matrixkabel.mchat.command.AddGroup;
import de.matrixkabel.mchat.command.Enable_Disable;
import de.matrixkabel.mchat.command.RemoveBadword;
import de.matrixkabel.mchat.command.RemoveGroup;
import de.matrixkabel.mchat.command.SetJoinPermission;
import de.matrixkabel.mchat.events.ChatListener;
import de.matrixkabel.mchat.events.JoinListener;

public class Main extends JavaPlugin{
	
    private static Main plugin;
	
	public void onEnable() {
		plugin = this;
		System.out.println("[M-Chat] >> Aktiv!");
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new ChatListener(), this);
		pluginManager.registerEvents(new JoinListener(this), this);
		getCommand("addgroup").setExecutor(new AddGroup());
		getCommand("removegroup").setExecutor(new RemoveGroup());
		getCommand("addbadword").setExecutor(new AddBadword());
		getCommand("removebadword").setExecutor(new RemoveBadword());
		getCommand("ag").setExecutor(new AddGroup());
		getCommand("rg").setExecutor(new RemoveGroup());
		getCommand("ab").setExecutor(new AddBadword());
		getCommand("rb").setExecutor(new RemoveBadword());
		getCommand("setjoinpermission").setExecutor(new SetJoinPermission());
		getCommand("set").setExecutor(new Enable_Disable());
		Main.getPlugin().saveDefaultConfig();
	}
	
	public static Main getPlugin() {
		return plugin;
	}

}
