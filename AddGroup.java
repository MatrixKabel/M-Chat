package de.matrixkabel.mchat.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import de.matrixkabel.mchat.main.Main;

public class AddGroup implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		FileConfiguration config = Main.getPlugin().getConfig();
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(!p.hasPermission("mchat.addgroup")) {
				p.sendMessage("§7[§aM-Chat§7] >> §cYou dont have the Permission for that command!");
				return true;
			}
			if(args.length < 2) {
				p.sendMessage("§7[§aM-Chat§7] >> §cPlease use this command like this: \n §d/addgroup permission{example:chat.Player} format{example:[Player] {name} >> {message}}");
				return true;
			}
			String permission = args[0];
			String format = "";
			int num = (config.getInt("Config.chat.groups.groupnumbers") + 1);
			int i = 1;
			while(i < args.length) {
				if(i > 1) {
				format = format+" "+args[i];
				}else {
					format = args[i];
				}
				i++;
			}
			i = 0;
			while(i <= config.getInt("Config.chat.groups.groupnumbers")) {
				if(config.getString("Config.chat.groups.group"+i+".permission").equalsIgnoreCase(permission)) {
					p.sendMessage("§7[§aM-Chat§7] >> §cA group with the permission "+permission+" already exist! \n §dPlease remove the group with the same permission or take a another permission");
					return true;
				}
				i++;
			}
			config.set("Config.chat.groups.groupnumbers", num);
			config.set("Config.chat.groups.group"+num+".format", format);
			config.set("Config.chat.groups.group"+num+".permission", permission);
			Main.getPlugin().saveConfig();
			p.sendMessage("§7[§aM-Chat§7] >> §aYour new group has been created!");
		}
		
		return false;
	}

}
