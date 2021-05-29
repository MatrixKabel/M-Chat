package de.matrixkabel.mchat.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.matrixkabel.mchat.main.Main;

public class RemoveBadword implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		FileConfiguration config = Main.getPlugin().getConfig();
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(!p.hasPermission("mchat.removebadword")) {
				p.sendMessage("§7[§aM-Chat§7] >> §cYou dont have the Permission for that command!");
				return true;
			}
			if(args.length < 1 || args.length > 1) {
				p.sendMessage("§7[§aM-Chat§7] >> §cPlease use this command like this: \n §d/removeBadword {word}");
				return true;
			}
			int i = 0;
			String badword = args[0];
			while(i <= config.getInt("Config.badwords.numbers")) {
				if(badword.equalsIgnoreCase(config.getString("Config.badwords."+i))) {
					config.set("Config.badwords.numbers", (config.getInt("Config.badwords.numbers") - 1));
					int i2 = (config.getInt("Config.badwords.numbers") + 1);
					i2 = i2 - i;
					int num = (config.getInt("Config.badwords.numbers") + 1);
					int oldnum = i;
					while(i2 >= 0) {
						config.set("Config.badwords."+oldnum, config.getString("Config.badwords."+(oldnum + 1)));
						if(num == oldnum) {
							config.set("Config.badwords."+oldnum, null);
							Main.getPlugin().saveConfig();
							p.sendMessage("§7[§aM-Chat§7] >> §aThe Badword is now delete!");
							return true;
						}
						oldnum++;
						Main.getPlugin().saveConfig();
						i2--;
					}
					return true;
				}
				i++;
			}
			p.sendMessage("§7[§aM-Chat§7] >> §cThe Badword was not found!");
		}
		return false;
	}

}
