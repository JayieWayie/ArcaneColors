package me.jayie.arcanecolors.Guis;

import me.jayie.arcanecolors.ArcaneColors;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class colorsGUI {

    private final ArcaneColors plugin;
    public colorsGUI(ArcaneColors plugin){
        this.plugin = plugin;
    }

    public Inventory colorGUI(Player player){

        String fillerItem2 = plugin.getConfig().getString("GUI.Settings.Items.Filler");
        String guiTitle = "&eColors Menu";
        String guiType = plugin.getConfig().getString("GUI.Settings.Items.Colors.Type");

        Inventory colorsGUI = Bukkit.createInventory((InventoryHolder)player, 54, org.bukkit.ChatColor.translateAlternateColorCodes('&', guiTitle));

        ItemStack fillerItem = new ItemStack(Material.valueOf(fillerItem2), 1);
        ItemStack backButton = new ItemStack(Material.STONE_BUTTON, 1);
        ItemStack darkblue = null;
        ItemStack darkgreen = null;
        ItemStack cyan = null;
        ItemStack purple = null;
        ItemStack gold = null;
        ItemStack gray = null;
        ItemStack darkgray = null;
        ItemStack blue = null;
        ItemStack black = null;
        ItemStack lime = null;
        ItemStack aqua = null;
        ItemStack pink = null;
        ItemStack yellow = null;
        ItemStack white = null;

        if (guiType.equalsIgnoreCase("Dye")){
            darkblue = new ItemStack(Material.LAPIS_LAZULI, 1);
            darkgreen = new ItemStack(Material.GREEN_DYE, 1);
            cyan = new ItemStack(Material.CYAN_DYE, 1);
            purple = new ItemStack(Material.PURPLE_DYE, 1);
            gold = new ItemStack(Material.ORANGE_DYE, 1);
            gray = new ItemStack(Material.LIGHT_GRAY_DYE, 1);
            darkgray = new ItemStack(Material.GRAY_DYE, 1);
            blue = new ItemStack(Material.BLUE_DYE, 1);
            black = new ItemStack(Material.BLACK_DYE, 1);
            lime = new ItemStack(Material.LIME_DYE, 1);
            aqua = new ItemStack(Material.LIGHT_BLUE_DYE);
            pink = new ItemStack(Material.MAGENTA_DYE, 1);
            yellow = new ItemStack(Material.YELLOW_DYE, 1);
            white = new ItemStack(Material.WHITE_DYE, 1);

        }else if (guiType.equalsIgnoreCase("Concrete")){

            darkblue = new ItemStack(Material.BLUE_CONCRETE_POWDER, 1);
            darkgreen = new ItemStack(Material.GREEN_CONCRETE, 1);
            cyan = new ItemStack(Material.CYAN_CONCRETE, 1);
            purple = new ItemStack(Material.PURPLE_CONCRETE, 1);
            gold = new ItemStack(Material.ORANGE_CONCRETE, 1);
            gray = new ItemStack(Material.LIGHT_GRAY_CONCRETE, 1);
            darkgray = new ItemStack(Material.GRAY_CONCRETE, 1);
            blue = new ItemStack(Material.BLUE_CONCRETE, 1);
            black = new ItemStack(Material.BLACK_CONCRETE, 1);
            lime = new ItemStack(Material.LIME_CONCRETE, 1);
            aqua = new ItemStack(Material.LIGHT_BLUE_CONCRETE);
            pink = new ItemStack(Material.MAGENTA_CONCRETE, 1);
            yellow = new ItemStack(Material.YELLOW_CONCRETE, 1);
            white = new ItemStack(Material.WHITE_CONCRETE, 1);
        }else if (guiType.equalsIgnoreCase("Wool")){

            darkblue = new ItemStack(Material.BLUE_BED, 1);
            darkgreen = new ItemStack(Material.GREEN_WOOL, 1);
            cyan = new ItemStack(Material.CYAN_WOOL, 1);
            purple = new ItemStack(Material.PURPLE_WOOL, 1);
            gold = new ItemStack(Material.ORANGE_WOOL, 1);
            gray = new ItemStack(Material.LIGHT_GRAY_WOOL, 1);
            darkgray = new ItemStack(Material.GRAY_WOOL, 1);
            blue = new ItemStack(Material.BLUE_WOOL, 1);
            black = new ItemStack(Material.BLACK_WOOL, 1);
            lime = new ItemStack(Material.LIME_WOOL, 1);
            aqua = new ItemStack(Material.LIGHT_BLUE_WOOL);
            pink = new ItemStack(Material.MAGENTA_WOOL, 1);
            yellow = new ItemStack(Material.YELLOW_WOOL, 1);
            white = new ItemStack(Material.WHITE_WOOL, 1);
        }



        ItemMeta filleritem = fillerItem.getItemMeta();
        filleritem.setDisplayName(Color("&7"));
        filleritem.setUnbreakable(true);
        fillerItem.setItemMeta(filleritem);

        ItemMeta backmeta = backButton.getItemMeta();
        backmeta.setDisplayName(Color(Hex("&7Back to menu.")));
        backButton.setItemMeta(backmeta);


        ItemMeta dbm = darkblue.getItemMeta();
        dbm.setDisplayName(Color(Hex("&1Dark Blue. &7(1)")));
        ArrayList<String> dbl = new ArrayList<>();
        dbl.add(Color(Hex("&eset your color to &1this.")));
        dbm.setLore(dbl);
        darkblue.setItemMeta(dbm);

        ItemMeta dgm = darkgreen.getItemMeta();
        dgm.setDisplayName(Color(Hex("&2Dark Green. &7(2)")));
        ArrayList<String> dgl = new ArrayList<>();
        dgl.add(Color(Hex("&eset your color to &2this.")));
        dgm.setLore(dgl);
        darkgreen.setItemMeta(dgm);

        ItemMeta cm = cyan.getItemMeta();
        cm.setDisplayName(Color(Hex("&3Cyan. &7(3)")));
        ArrayList<String> cl = new ArrayList<>();
        cl.add(Hex(Color("&eset your color to &3this.")));
        cm.setLore(cl);
        cyan.setItemMeta(cm);

        ItemMeta pm = purple.getItemMeta();
        pm.setDisplayName(Color(Hex("&5Purple. &7(5)")));
        ArrayList<String> pl = new ArrayList<>();
        pl.add(Color(Hex("&eset your color to &5this.")));
        pm.setLore(pl);
        purple.setItemMeta(pm);

        ItemMeta gm = gold.getItemMeta();
        gm.setDisplayName(Color(Hex("&6Gold. &7(6)")));
        ArrayList<String> gl = new ArrayList<>();
        gl.add(Color(Hex("&eset your color to &6this.")));
        gm.setLore(gl);
        gold.setItemMeta(gm);

        ItemMeta lgm = gray.getItemMeta();
        lgm.setDisplayName(Color(Hex("&7Gray. &7(7)")));
        ArrayList<String> lgl = new ArrayList<>();
        lgl.add(Color(Hex("&eset your color to &7this.")));
        lgm.setLore(lgl);
        gray.setItemMeta(lgm);

        ItemMeta ddgm = darkgray.getItemMeta();
        ddgm.setDisplayName(Color(Hex("&8Dark Gray. &7(8)")));
        ArrayList<String> dggl = new ArrayList<>();
        dggl.add(Color(Hex("&eset your color to &7this.")));
        ddgm.setLore(dggl);
        darkgray.setItemMeta(ddgm);

        ItemMeta bm = blue.getItemMeta();
        bm.setDisplayName(Color(Hex("&9Blue. &7(9)")));
        ArrayList<String> bl = new ArrayList<>();
        bl.add(Color(Hex("&eset your color to &9this.")));
        bm.setLore(bl);
        blue.setItemMeta(bm);

        ItemMeta blackm = black.getItemMeta();
        blackm.setDisplayName(Color(Hex("&0Black. &7(0)")));
        ArrayList<String> blackl = new ArrayList<>();
        blackl.add(Color(Hex("&eset your color to &0this.")));
        blackm.setLore(blackl);
        black.setItemMeta(blackm);

        ItemMeta lm = lime.getItemMeta();
        lm.setDisplayName(Color(Hex("&aLime. &7(a)")));
        ArrayList<String> ll = new ArrayList<>();
        ll.add(Hex(Color("&eset your color to &athis.")));
        lm.setLore(ll);
        lime.setItemMeta(lm);

        ItemMeta am = aqua.getItemMeta();
        am.setDisplayName(Color(Hex("&bAqua. &7(b)")));
        ArrayList<String> al = new ArrayList<>();
        al.add(Hex(Color("&eset your color to &bthis.")));
        am.setLore(al);
        aqua.setItemMeta(am);

        ItemMeta lpm = pink.getItemMeta();
        lpm.setDisplayName(Color(Hex("&dPink. &7(d)")));
        ArrayList<String> pll = new ArrayList<>();
        pll.add(Color(Hex("&eset your color to &dthis.")));
        lpm.setLore(pll);
        pink.setItemMeta(lpm);

        ItemMeta ym = yellow.getItemMeta();
        ym.setDisplayName(Color(Hex("&eYellow. &7(e)")));
        ArrayList<String> yl = new ArrayList<>();
        yl.add(Color(Hex("&eset your color to this.")));
        ym.setLore(yl);
        yellow.setItemMeta(ym);

        ItemMeta wm = white.getItemMeta();
        wm.setDisplayName(Color(Hex("&fWhite. &7(f)")));
        ArrayList<String> wl = new ArrayList<>();
        wl.add(Color(Hex("&eset your color to &fthis.")));
        wm.setLore(wl);
        white.setItemMeta(wm);


        ItemStack[] menuItems = {backButton, fillerItem, fillerItem, fillerItem, fillerItem, fillerItem,fillerItem,fillerItem,fillerItem
                                ,fillerItem,darkblue,fillerItem,darkgreen,fillerItem,cyan,fillerItem,purple,fillerItem,
                                fillerItem,fillerItem,gold,fillerItem,gray,fillerItem,darkgray,fillerItem,fillerItem,
                                fillerItem,blue,fillerItem,black,fillerItem,lime,fillerItem,aqua,fillerItem,
                                fillerItem,fillerItem,pink,fillerItem,yellow,fillerItem,white,fillerItem,fillerItem,
                                fillerItem,fillerItem,fillerItem,fillerItem,fillerItem,fillerItem,fillerItem,fillerItem,fillerItem};

        colorsGUI.setContents(menuItems);

        player.openInventory(colorsGUI);


        return colorsGUI;

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
