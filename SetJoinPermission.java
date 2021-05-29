package de.matrixkabel.mchat.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.matrixkabel.mchat.main.Main;

public class SetJoinPermission implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		FileConfiguration config = Main.getPlugin().getConfig();
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(!p.hasPermission("mchat.setjoinpermission")) {
				p.sendMessage("§7[§aM-Chat§7] >> §cYou dont have the Permission for that command!");
				return true;
			}
			if(args.length < 1 || args.length > 1) {
				p.sendMessage("§7[§aM-Chat§7] >> §cPlease use this command like this: \\n §d/setjoinpermission {permission}");
				return true;
			}
			config.set("Config.setJoinPermission.permission", args[0]);
			p.sendMessage("§7[§aM-Chat§7] >> §aThe Join Permission is now "+args[0]+"!");
			Main.getPlugin().saveConfig();
		}
		return false;
	}

}
