package me.jayie.arcanecolors.Guis.guiCommands;

import com.sun.tools.javac.Main;
import me.jayie.arcanecolors.ArcaneColors;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class colorGUICommands implements Listener {

    private final ArcaneColors plugin;

    public colorGUICommands(ArcaneColors plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onClickEvent(InventoryClickEvent e) throws SQLException {

        String ColorsGuiTitle = "&eColors Menu";
        String MainGuiTitle = "&cMain Color Menu";
        String StaffGuiTitle = "&dStaff Color Menu";

        Player player = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', ColorsGuiTitle))) {
            e.setCancelled(true);

            int slot = e.getRawSlot();
            if (slot == 0) {
                player.openInventory(plugin.mGUI.mainMenu(player));
            } else if (slot == 10) {
                player.performCommand("color set 1");
            } else if (slot == 12) {
                player.performCommand("color set 2");
            } else if (slot == 14) {
                player.performCommand("color set 3");
            } else if (slot == 16) {
                player.performCommand("color set 5");
            } else if (slot == 20) {
                player.performCommand("color set 6");
            } else if (slot == 22) {
                player.performCommand("color set 7");
            } else if (slot == 24) {
                player.performCommand("color set 8");
            } else if (slot == 28) {
                player.performCommand("color set 9");
            } else if (slot == 30) {
                player.performCommand("color set 0");
            } else if (slot == 32) {
                player.performCommand("color set a");
            } else if (slot == 34) {
                player.performCommand("color set b");
            } else if (slot == 38) {
                player.performCommand("color set d");
            } else if (slot == 40) {
                player.performCommand("color set e");
            } else if (slot == 42) {
                player.performCommand("color set f");
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', StaffGuiTitle))) {
            e.setCancelled(true);
            int slot = e.getRawSlot();
            if (slot == 0) {
                player.openInventory(plugin.mGUI.mainMenu(player));
            } else if (slot == 11) {
                player.performCommand("color set c");
            } else if (slot == 13) {
                player.performCommand("color set 4");
            } else if (slot == 15) {
                player.performCommand("color reset");
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', MainGuiTitle))) {
            e.setCancelled(true);

            int slot = e.getRawSlot();
            if (slot == 0) {
                player.performCommand("color reset");
            } else if (slot == 11) {
                player.openInventory(plugin.cGUI.colorGUI(player));
            } else if (slot == 13) {
                player.sendMessage("open inventory *special*");
            } else if (slot == 15) {
                player.openInventory(plugin.sGUI.mainMenu(player));
            }
        }

    }

    private String Color(String s) {
        s = ChatColor.translateAlternateColorCodes('&',s);
        return s;
    }
}
