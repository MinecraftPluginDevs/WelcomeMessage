package com.jackthewebdev.WelcomeMessage;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import static net.minecraft.server.v1_15_R1.Items.I;

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
            String color = args[0];
            this.getConfig().set("color", color);
            sender.sendMessage("Set the player name color to: " + ChatColor.valueOf(this.getConfig().get("color").toString()) + "this");
            return true;
        }
        if(command.getName().equalsIgnoreCase("setjoinmessage")){
            StringBuilder buffer = new StringBuilder();

            // change the starting i value to pick what argument to start from
            // 1 is the 2nd argument.
            for(int i = 0; i < args.length; i++)
            {
                buffer.append(' ').append(args[i]);
            }
            this.getConfig().set("message",buffer.toString());
            sender.sendMessage("Set the message to: "+this.getConfig().get("message").toString()+", "+ChatColor.valueOf(this.getConfig().get("color").toString())+"[player username]");
            return true;
        }
        return false;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        p.sendMessage( this.getConfig().get("message")+", "+ChatColor.valueOf(this.getConfig().get("color").toString())+p.getName().toString());
    }
}
