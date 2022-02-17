package me.jayie.arcanecolors.Guis;

import me.jayie.arcanecolors.ArcaneColors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class specialGUI {

    private final ArcaneColors plugin;
    public specialGUI(ArcaneColors plugin){
        this.plugin = plugin;
    }

    public Inventory specialGUI(Player player) throws SQLException {


        String fillerItem2 = plugin.getConfig().getString("GUI.Settings.Items.Filler");
        String guiTitle = "&6Special Color Menu";

        Inventory specialGUI = Bukkit.createInventory((InventoryHolder)player, 27, org.bukkit.ChatColor.translateAlternateColorCodes('&', guiTitle));

        ItemStack fillerItem = new ItemStack(Material.valueOf(fillerItem2), 1);
        ItemStack boldItem = new ItemStack(Material.GREEN_DYE, 1);
        ItemStack italicItem = new ItemStack(Material.RED_DYE, 1);
        ItemStack underlineItem = new ItemStack(Material.YELLOW_DYE, 1);
        ItemStack strikethroughItem = new ItemStack(Material.PURPLE_DYE, 1);
        ItemStack magicItem = new ItemStack(Material.WHITE_DYE, 1);
        ItemStack backButton = new ItemStack(Material.STONE_BUTTON, 1);

        ItemMeta filleritem = fillerItem.getItemMeta();
        filleritem.setDisplayName(Color("&7"));
        filleritem.setUnbreakable(true);
        fillerItem.setItemMeta(filleritem);

        ItemMeta boldmeta = boldItem.getItemMeta();
        boldmeta.setDisplayName(Color(Hex("&f&lBold. (l)")));
        ArrayList<String> boldlore = new ArrayList<>();
        boldlore.add(Color(Hex("&eClick to toggle bold.")));
        boldmeta.setLore(boldlore);
        boldItem.setItemMeta(boldmeta);

        ItemMeta italicmeta = italicItem.getItemMeta();
        italicmeta.setDisplayName(Color(Hex("&f&oItalic. (o)")));
        ArrayList<String> italiclore = new ArrayList<>();
        italiclore.add(Color(Hex("&eClick to toggle italic.")));
        italicmeta.setLore(italiclore);
        italicItem.setItemMeta(italicmeta);

        ItemMeta underlinemeta = underlineItem.getItemMeta();
        underlinemeta.setDisplayName(Color(Hex("&f&nUnderline. (n)")));
        ArrayList<String> underlinelore = new ArrayList<>();
        underlinelore.add(Color(Hex("&eClick to toggle underline.")));
        underlinemeta.setLore(underlinelore);
        underlineItem.setItemMeta(underlinemeta);

        ItemMeta strikethroughmeta = strikethroughItem.getItemMeta();
        strikethroughmeta.setDisplayName(Color(Hex("&f&mStrikethrough. (m)")));
        ArrayList<String> strikethroughlore = new ArrayList<>();
        strikethroughlore.add(Color(Hex("&eClick to toggle strikethrough.")));
        strikethroughmeta.setLore(strikethroughlore);
        strikethroughItem.setItemMeta(strikethroughmeta);

        ItemMeta magicmeta = magicItem.getItemMeta();
        magicmeta.setDisplayName(Color(Hex("&fMagic. (k)")));
        ArrayList<String> magiclore = new ArrayList<>();
        magiclore.add(Color(Hex("&eClick to toggle magic.")));
        magicmeta.setLore(magiclore);
        magicItem.setItemMeta(magicmeta);


        ItemMeta backmeta = backButton.getItemMeta();
        backmeta.setDisplayName(Color(Hex("&8Back to menu.")));
        backButton.setItemMeta(backmeta);



        ItemStack[] menuItems = {backButton, fillerItem, fillerItem, fillerItem, fillerItem, fillerItem,fillerItem,fillerItem,fillerItem
                ,fillerItem,fillerItem,boldItem,italicItem,underlineItem,strikethroughItem,magicItem,fillerItem,fillerItem,
                fillerItem,fillerItem,fillerItem,fillerItem,fillerItem,fillerItem,fillerItem,fillerItem,fillerItem};

        specialGUI.setContents(menuItems);

        player.openInventory(specialGUI);

        return specialGUI;
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
