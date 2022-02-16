package me.jayie.arcanecolors.Listeners;

import me.jayie.arcanecolors.ArcaneColors;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class chatlistener implements Listener {

    private final ArcaneColors plugin;
    public chatlistener(ArcaneColors plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onchat(AsyncPlayerChatEvent e) throws SQLException {
        Player player = e.getPlayer();

     String message = e.getMessage();
     String color = null;
     int bold = 0;
     int italic = 0;
     int magic = 0;
     int underline = 0;
     int strikethrough = 0;
    PreparedStatement ms1 = plugin.DB.getConnection().prepareStatement("SELECT * FROM chatcolor WHERE playerUUID=?");
    ms1.setString(1, player.getUniqueId().toString());
    ResultSet ms2 = ms1.executeQuery();
    if (ms2.next()){
        color = ms2.getString("color");
        bold = ms2.getInt("bold");
        italic = ms2.getInt("italic");
        magic = ms2.getInt("magic");
        underline = ms2.getInt("underline");
        strikethrough = ms2.getInt("strikethrough");
    }

    String colormessage = color;

    if (bold == 1){
        message = ChatColor.BOLD + message;
    }

    if (italic == 1){
        message = ChatColor.ITALIC + message;
    }

    if (strikethrough == 1){
        message = ChatColor.STRIKETHROUGH + message;
    }

    if (magic == 1){
        message = ChatColor.MAGIC + message;
    }

    if (underline == 1){
        message = ChatColor.UNDERLINE + message;
    }


     e.setMessage(Color(Hex(colormessage + message)));
    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&', s);
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
