package de.matrixkabel.mchat.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.matrixkabel.mchat.main.Main;

public class Enable_Disable  implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		FileConfiguration config = Main.getPlugin().getConfig();
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(!p.hasPermission("mchat.set")) {
				p.sendMessage("§7[§aM-Chat§7] >> §cYou dont have the Permission for that command!");
				return true;
			}
			boolean set = false;
			if(args.length < 1 || args.length > 2) {
				p.sendMessage("§7[§aM-Chat§7] >> §cPlease use this command like this: \\n §d/set {true/false} {sendprefix/savebadmessage/setjoinpermission}");
				return true;
			}
			if(!args[0].equalsIgnoreCase("true") && !args[0].equalsIgnoreCase("false")) {
				p.sendMessage("§7[§aM-Chat§7] >> §cPlease use this command like this: \\n §d/set {true/false} {sendprefix/savebadmessage/setjoinpermission}");
				return true;
			}
			if(args[0].equalsIgnoreCase("true")) {
				set = true;
			}
			if(args[0].equalsIgnoreCase("false")) {
				set = false;
			}
			if(!args[1].equalsIgnoreCase("sendprefix") && !args[1].equalsIgnoreCase("savebadmessage") && !args[1].equalsIgnoreCase("setjoinpermission")) {
				p.sendMessage("§7[§aM-Chat§7] >> §cPlease use this command like this: \\n §d/set {true/false} {sendprefix/savebadmessage/setjoinpermission}");
				return true;
			}
			if(args[1].equalsIgnoreCase("sendprefix")) {
				config.set("Config.chat.sendPrefix", set);
				p.sendMessage("§7[§aM-Chat§7] >> §a"+args[1]+" is now "+set+"!");
				Main.getPlugin().saveConfig();
				return true;
			}
			if(args[1].equalsIgnoreCase("savebadmessage")) {
				config.set("Config.chat.SaveBadMessage", set);
				p.sendMessage("§7[§aM-Chat§7] >> §a"+args[1]+" is now "+set+"!");
				Main.getPlugin().saveConfig();
				return true;
			}
			if(args[1].equalsIgnoreCase("setjoinpermission")) {
				config.set("Config.setJoinPermission.set", set);
				p.sendMessage("§7[§aM-Chat§7] >> §a"+args[1]+" is now "+set+"!");
				Main.getPlugin().saveConfig();
				return true;
			}
		}
		return false;
	}

}
