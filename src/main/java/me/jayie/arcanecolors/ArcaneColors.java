package me.jayie.arcanecolors;

import me.jayie.arcanecolors.Commands.playerCommands;
import me.jayie.arcanecolors.Guis.colorsGUI;
import me.jayie.arcanecolors.Guis.guiCommands.mainGUICommands;
import me.jayie.arcanecolors.Guis.mainGUI;
import me.jayie.arcanecolors.Listeners.chatlistener;
import me.jayie.arcanecolors.database.databaseConnection;
import me.jayie.arcanecolors.database.databaseQueries;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Objects;

public final class ArcaneColors extends JavaPlugin implements Listener {

    public databaseConnection DB;
    public databaseQueries DBQ;
    public mainGUI mGUI;
    public colorsGUI cGUI;



    @Override
    public void onEnable() {
        config();
        commands();
        listeners();
        this.DB = new databaseConnection();
        this.DBQ = new databaseQueries(this);
        this.mGUI = new mainGUI(this);
        this.cGUI = new colorsGUI(this);
        startup();
        try {
            DB.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (DB.isConnected()) {
            try {
                DBQ.createTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void commands(){
        getCommand("chatcolor").setExecutor(new playerCommands(this));
    }

    public void listeners(){
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new chatlistener(this), this);
        getServer().getPluginManager().registerEvents(new mainGUICommands(this), this);
    }

    public void startup(){
        getLogger().info(Color("&8[&dArcaneColors&8] &fPlugin started up."));
        getLogger().info(Color("&8[&dArcaneColors&8] &fCreated by Jayie"));
    }

    public void config(){
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        DB.disconnect();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws SQLException{
        Player player = e.getPlayer();
        DBQ.createPlayer(player);
        PreparedStatement colorcheck = DB.getConnection().prepareStatement("SELECT color FROM chatcolor WHERE playerUUID=?");
        colorcheck.setString(1, player.getUniqueId().toString());
        ResultSet colorcheckrs = colorcheck.executeQuery();
        String color = null;
        if (colorcheckrs.next()){
            color = colorcheckrs.getString("color");
        }
        String message = Color("%prefix% &7Your current chatcolor is %color%this&7.");
        message.replace("%prefix%", (getConfig().getString("Plugin.Prefix")));
        message.replace("%color%", color);
        player.sendMessage(message);
    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }

}
