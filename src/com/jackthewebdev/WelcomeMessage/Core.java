package com.jackthewebdev.WelcomeMessage;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

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
        if(command.getName().equalsIgnoreCase("setjoincolor")){
            String color = args[0];
            this.getConfig().set("color", color.toUpperCase());
            sender.sendMessage("Set the player name color to: " + ChatColor.valueOf(this.getConfig().get("color").toString()) + "this");
            return true;
        }
        if(command.getName().equalsIgnoreCase("setjoinmessage")){
            StringBuilder buffer = new StringBuilder();
            // change the starting i value to pick what argument to start from
            // 1 is the 2nd argument.
            for(int i = 0; i < args.length; i++)
            {
                if(!args[i].equals(" ")){
                    buffer.append(' ').append(args[i]);
                }
            }
            String message = buffer.toString();
            String trimmed = message.trim();

            this.getConfig().set("message",trimmed);
            sender.sendMessage("The welcome message will look like this: "+this.getConfig().get("message")+", "+ChatColor.valueOf(this.getConfig().get("color").toString())+"[player name here]");
            return true;
        }
        return false;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        p.sendMessage( this.getConfig().get("message")+", "+ChatColor.valueOf(Objects.requireNonNull(this.getConfig().get("color")).toString())+p.getName().toString());
    }
}
