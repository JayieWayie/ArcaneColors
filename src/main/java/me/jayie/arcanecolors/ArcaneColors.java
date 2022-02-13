package me.jayie.arcanecolors;

import me.jayie.arcanecolors.database.databaseConnection;
import me.jayie.arcanecolors.database.databaseQueries;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Objects;

public final class ArcaneColors extends JavaPlugin {

    public databaseConnection DB;
    public databaseQueries DBQ;

    @Override
    public void onEnable() {
        startup();
        try {
            database();
        } catch(SQLException e){
            getLogger().info("&8[&dArcaneColors&8] &cERROR.");
        }
        config();
        commands();
        listeners();
        this.DB = new databaseConnection();
        this.DBQ = new databaseQueries(this);
    }

    public void commands(){

    }

    public void listeners(){

    }

    public void database() throws SQLException {
        if (!DB.isConnected()){
            DB.connect();
            getLogger().severe("&8[&dArcaneColors&8] &cDatabase is not connected.");
            getLogger().severe("&8[&dArcaneColors&8] &cConnect the database in config.yml.");
            getLogger().severe("&8[&dArcaneColors&8] &cIf you believe this is an error report it to Jayie.");
        }else{
            DBQ.createTable();
        }

    }

    public void startup(){
        getLogger().info("&8[&dArcaneColors&8] &fPlugin started up.");
        getLogger().info("&8[&dArcaneColors&8] &fCreated by Jayie");
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
