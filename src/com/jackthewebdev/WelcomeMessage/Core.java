package com.jackthewebdev.WelcomeMessage;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        this.saveDefaultConfig(); // create config file
        getLogger().info("Enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("setplayercolor")){
            String color = args[1];
            if(color.startsWith("ChatColor.")) {
                this.getConfig().set("color", color);
                ChatColor colorthing = new ChatColor(this.getConfig().getString("color"));
                sender.sendMessage("Set the player name color to:" + this.getConfig().get("color") + "this");
            }else{
                sender.sendMessage("Please include a valid color. The color blue would be: ChatColor.BLUE red would be ChatColor.RED");
            }
            return true;
        }
        return false;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        p.sendMessage("Welcome To The Server, "+this.getConfig().getString("color")+p.getName().toString());
    }
}
