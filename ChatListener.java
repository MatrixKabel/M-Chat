package de.matrixkabel.mchat.events;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import de.matrixkabel.mchat.main.Main;

public class ChatListener implements Listener{
	
	 @EventHandler
    public boolean onPlayerChat(AsyncPlayerChatEvent event) {
		FileConfiguration config = Main.getPlugin().getConfig();
        Player p = event.getPlayer();
        String pn = p.getName();
        String pm = event.getMessage();
        String date = String.valueOf(LocalDate.now());
        String timeS = String.valueOf(LocalTime.now().getSecond());
        String timeM = String.valueOf(LocalTime.now().getMinute());
        String timeH = String.valueOf(LocalTime.now().getHour());
        String time = timeH + ":" + timeM + ":" + timeS;
        String txt = "[" + date + "/" + time + "] " + pn + ": " + pm;
        Bukkit.getConsoleSender().sendMessage(txt);
        Boolean badwords = false;
        String[] pmt = pm.split(" ");
        String[] pmt2 = pm.split("-");
        File f = new File("plugins/M-Chat/BadLog.txt");
        if(!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int i = 0;
        int i2 = config.getInt("Config.badwords.numbers");
        int i3 = 0;
        while((pmt.length -1) >= i){
        	while(i2 >= i3) {
        		if(pmt[i].equalsIgnoreCase(config.getString("Config.badwords." + i3))) {
        			badwords = true;
        		}
        		i3++;
        	};
        	i3 = 0;
        	i++;
        }
        i3 = 0;
        i = 0;
        while((pmt2.length - 1) >= i){
        	while(i2 >= i3) {
        		if(pmt2[i].equalsIgnoreCase(config.getString("Config.badwords." + i3))) {
        			badwords = true;
        		}
        		i3++;
        	}
        	i++;
        }
        i = 0;
        String pm2 = "";
        if(badwords) {
        	while(i <= pm.length()) {
        		pm2 = pm2 + "*";
        		i++;
        	}
        	pm = pm2;
            if(config.getBoolean("Config.chat.SaveBadMessage")) {
            try {
           	String old = Files.readString(Path.of("plugins/M-Chat/BadLog.txt"));
    			Files.writeString(Path.of("plugins/M-Chat/BadLog.txt"), old + "\n" + txt);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	    }
        }
        String np = "§f[§3Player§f] §b" + p.getName() + "§3>> §f" + pm;
        //String sp = "§f[§eSupporter§f] §b" + p.getName() + "§3>> §f" + pm;
        //String bp = "§f[§edBuilder§f] §b" + p.getName() + "§3>> §f" + pm;
        //String mp = "§f[§aModerator§f] §b" + p.getName() + "§3>> §f" + pm;
        //String dp = "§f[§2Developer§f] §b" + p.getName() + "§3>> §f" + pm;
        //String ap = "§f[§cAdmin§f] §b" + p.getName() + "§3>> §f" + pm;
        //String op = "§f[§4Owner§f] §b" + p.getName() + "§3>> §f" + pm;
        if(config.getBoolean("Config.chat.sendPrefix")) {
        	i = config.getInt("Config.chat.groups.groupnumbers");
            while(i >= 0) {
        	    if(p.hasPermission(config.getString("Config.chat.groups.group" + i + ".permission"))) {
        	    	String message = pm;
        	    	String Name = p.getName();
        	    	//int Healt = getHealth(p);
        	    	String[] format = config.getString("Config.chat.groups.group" + i + ".format").split(" ");
        	    	i3 = 0;
        	    	String endformate = "";
        	    	while(i3 < format.length){
        	    		String temp = format[i3];
        	    		if(i3 > 0) {
        	    		temp = " "+format[i3];
        	    		}
        	    		if(format[i3].equalsIgnoreCase("{name}")) {
        	    			temp = " "+Name;
        	    		}
                        if(format[i3].equalsIgnoreCase("{message}")) {
                        	temp = " "+message;
        	    		}
        	    		endformate = endformate + temp;
        	    		event.setFormat(endformate);
        	    		i3++;
        	    	}
        	    	
        	    	return true;
        	    }
            	i--;
            }
        if(p.hasPermission("chat.Player")) {
            event.setFormat(np);
            return true;
            }
        }
        if(!config.getBoolean("Config.chat.sendPrefix")) {
        	event.setFormat("<"+pn+"> "+pm);
        }
		return false;
  }
	public int getHealth(Player player){
	        return (int)StrictMath.ceil(damageable(player).getHealth());
	    }
	    
	    public Damageable damageable(Player player){
	        return (Damageable)player;
	    }
}
