package me.jayie.arcanecolors.Guis;

import me.jayie.arcanecolors.ArcaneColors;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class staffGUI {

    private final ArcaneColors plugin;
    public staffGUI(ArcaneColors plugin){
        this.plugin = plugin;
    }

    public Inventory mainMenu(Player player) throws SQLException {

        PreparedStatement ps1 = plugin.DB.getConnection().prepareStatement("SELECT color FROM chatcolor WHERE playerUUID=?");
        ps1.setString(1 , player.getUniqueId().toString());
        ResultSet rs1 = ps1.executeQuery();
        String color = null;
        if (rs1.next()){
            color = rs1.getString("color");
        }

        String fillerItem2 = plugin.getConfig().getString("GUI.Settings.Items.Filler");
        String guiTitle = "&dStaff Color Menu";

        Inventory StaffGUI = Bukkit.createInventory((InventoryHolder)player, 27, org.bukkit.ChatColor.translateAlternateColorCodes('&', guiTitle));

        ItemStack fillerItem = new ItemStack(Material.valueOf(fillerItem2), 1);
        ItemStack redItem = new ItemStack(Material.RED_CONCRETE_POWDER, 1);
        ItemStack darkRedItem = new ItemStack(Material.RED_CONCRETE, 1);
        ItemStack resetItem = new ItemStack(Material.BARRIER, 1);
        ItemStack backButton = new ItemStack(Material.STONE_BUTTON, 1);

        ItemMeta filleritem = fillerItem.getItemMeta();
        filleritem.setDisplayName(Color("&7"));
        filleritem.setUnbreakable(true);
        fillerItem.setItemMeta(filleritem);

        ItemMeta coloritem1 = redItem.getItemMeta();
        coloritem1.setDisplayName(Color(Hex("&cLight Red. (c)")));
        ArrayList<String> colorlore1 = new ArrayList<>();
        colorlore1.add(Color(Hex("&eLeft click to change your color to &cthis.")));
        coloritem1.setLore(colorlore1);
        redItem.setItemMeta(coloritem1);

        ItemMeta staffmeta1 = darkRedItem.getItemMeta();
        staffmeta1.setDisplayName(Color(Hex("&4Dark Red. &7(4)")));
        ArrayList<String> stafflore1 = new ArrayList<>();
        stafflore1.add(Color(Hex("&eLeft click to change your color to &4this.")));
        staffmeta1.setLore(stafflore1);
        darkRedItem.setItemMeta(staffmeta1);

        ItemMeta specialmeta1 = resetItem.getItemMeta();
        specialmeta1.setDisplayName(Color(Hex("&cReset colour.")));
        resetItem.setItemMeta(specialmeta1);

        ItemMeta backmeta = backButton.getItemMeta();
        backmeta.setDisplayName(Color(Hex("&8Back to menu.")));
        backButton.setItemMeta(backmeta);



        ItemStack[] menuItems = {backButton, fillerItem, fillerItem, fillerItem, fillerItem, fillerItem,fillerItem,fillerItem,fillerItem
                ,fillerItem,fillerItem,redItem,fillerItem,darkRedItem,fillerItem,resetItem,fillerItem,fillerItem,
                fillerItem,fillerItem,fillerItem,fillerItem,fillerItem,fillerItem,fillerItem,fillerItem,fillerItem};

        StaffGUI.setContents(menuItems);

        player.openInventory(StaffGUI);

        return StaffGUI;
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
