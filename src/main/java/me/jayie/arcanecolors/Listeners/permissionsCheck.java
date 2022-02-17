package me.jayie.arcanecolors.Listeners;

import me.jayie.arcanecolors.ArcaneColors;
import org.bukkit.entity.Player;

public class permissionsCheck {

    private final ArcaneColors plugin;
    public permissionsCheck(ArcaneColors plugin){
        this.plugin = plugin;
    }


    public String permChecker(Player player){

        String resetColor = null;

        if (player.hasPermission("arcanecolors.defaults.red")){
            resetColor = "&c";
        }else if (player.hasPermission("arcanecolors.defaults.darkgreen")){
            resetColor = "&2";
        }else if (player.hasPermission("arcanecolors.defaults.cyan")){
            resetColor = "&3";
        }else if (player.hasPermission("arcanecolors.defaults.darkred")){
            resetColor = "&4";
        }else if (player.hasPermission("arcanecolors.defaults.purple")){
            resetColor = "&5";
        }else if (player.hasPermission("arcanecolors.defaults.gold")){
            resetColor = "&6";
        }else if (player.hasPermission("arcanecolors.defaults.gray")){
            resetColor = "&7";
        }else if (player.hasPermission("arcanecolors.defaults.darkgray")){
            resetColor = "&8";
        }else if (player.hasPermission("arcanecolors.defaults.blue")){
            resetColor = "&9";
        }else if (player.hasPermission("arcanecolors.defaults.black")){
            resetColor = "&0";
        }else if (player.hasPermission("arcanecolors.defaults.lime")){
            resetColor = "&a";
        }else if (player.hasPermission("arcanecolors.defaults.aqua")){
            resetColor = "&b";
        }else if (player.hasPermission("arcanecolors.defaults.darkblue")){
            resetColor = "&1";
        }else if (player.hasPermission("arcanecolors.defaults.pink")){
            resetColor = "&d";
        }else if (player.hasPermission("arcanecolors.defaults.yellow")){
            resetColor = "&e";
        }else if (player.hasPermission("arcanecolors.defaults.white")){
            resetColor = "&f";
        }else{
            resetColor = plugin.getConfig().getString("Plugin.DefaultColor");
        }

        return resetColor;

    }

}
