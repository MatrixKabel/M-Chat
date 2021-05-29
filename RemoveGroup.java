package de.matrixkabel.mchat.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.matrixkabel.mchat.main.Main;

public class RemoveGroup implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		FileConfiguration config = Main.getPlugin().getConfig();
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(!p.hasPermission("mchat.removegroup")) {
				p.sendMessage("§7[§aM-Chat§7] >> §cYou dont have the Permission for that command!");
				return true;
			}
			if(args.length < 1 || args.length > 1) {
				p.sendMessage("§7[§aM-Chat§7] >> §cPlease use this command like this: \n §d/removegroup permission{example:chat.Player}");
				return true;
			}
			String perm = args[0];
			int num = (config.getInt("Config.chat.groups.groupnumbers") - 1);
			int i = 0;
			int i2 = (num + 1);
			while(i <= config.getInt("Config.chat.groups.groupnumbers")) {
				if(config.getString("Config.chat.groups.group"+i+".permission").equalsIgnoreCase(perm)) {
					int oldnum = i;
					config.set("Config.chat.groups.groupnumbers", num);
					p.sendMessage("§7[§aM-Chat§7] >> §aThe Group with the permission " +perm+" is now deleted!");
					i2 = (i2 - i);
					while(i2 >= 0) {
						config.set("Config.chat.groups.group"+oldnum+".format", config.getString("Config.chat.groups.group"+(oldnum + 1)+".format"));
						config.set("Config.chat.groups.group"+oldnum+".permission", config.getString("Config.chat.groups.group"+(oldnum + 1)+".permission"));
						if((num + 1) == oldnum) {
							config.set("Config.chat.groups.group"+oldnum, null);
						}
						oldnum++;
						Main.getPlugin().saveConfig();
						i2--;
					}
					Main.getPlugin().saveConfig();
					return true;
				}
				i++;
			}
			p.sendMessage("§7[§aM-Chat§7] >> §cThe Group with the permission " +perm+" was not found!");
		}
		
		return false;
	}

}
