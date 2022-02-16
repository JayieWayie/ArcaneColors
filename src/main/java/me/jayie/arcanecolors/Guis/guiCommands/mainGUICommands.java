package me.jayie.arcanecolors.Guis.guiCommands;

import me.jayie.arcanecolors.ArcaneColors;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class mainGUICommands implements Listener {

    private final ArcaneColors plugin;
    public mainGUICommands(ArcaneColors plugin){
        this.plugin = plugin;
    }



    @EventHandler
    public void onClickEvent(InventoryClickEvent e){

        String guiTitle = plugin.getConfig().getString("GUI.Settings.Title");

        Player player = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase(Color(Hex(guiTitle)))){
            e.setCancelled(true);

            int slot = e.getRawSlot();
            if (slot == 11){
                player.sendMessage("open inventory *color*");
            }else if (slot == 13){
                player.sendMessage("open inventory *special*");
            }else if (slot == 15){
                player.sendMessage("reset color");
            }
        }

    }

    private String Color(String s){
        s = org.bukkit.ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }

    private static final Pattern HEX_PATTERN = Pattern.compile("&(#\\w{6})");

    public static String Hex(String message) {
        Matcher matcher = HEX_PATTERN.matcher(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', message));
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(buffer, net.md_5.bungee.api.ChatColor.of(matcher.group(1)).toString());
        }

        return matcher.appendTail(buffer).toString();
    }

}
