package de.matrixkabel.mchat.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.matrixkabel.mchat.main.Main;

public class AddBadword implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		FileConfiguration config = Main.getPlugin().getConfig();
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(!p.hasPermission("mchat.addbadword")) {
				p.sendMessage("§7[§aM-Chat§7] >> §cYou dont have the Permission for that command!");
				return true;
			}
			if(args.length < 1 || args.length > 1) {
				p.sendMessage("§7[§aM-Chat§7] >> §cPlease use this command like this: \n §d/AddBadword {word}");
				return true;
			}
			String word = args[0];
			int num = (config.getInt("Config.badwords.numbers") + 1);
			int i = 0;
			while(i <= config.getInt("Config.badwords.numbers")) {
				if(config.getString("Config.badwords."+i).equalsIgnoreCase(word)) {
					p.sendMessage("§7[§aM-Chat§7] >> §cThis badword already exist! \n §dPlease choose a another!");
					return true;
				}
				i++;
			}
			config.set("Config.badwords."+num, word);
			config.set("Config.badwords.numbers", num);
			Main.getPlugin().saveConfig();
			p.sendMessage("§7[§aM-Chat§7] >> §aYour badword was added to the badword list!");
		}
		return false;
	}

}
