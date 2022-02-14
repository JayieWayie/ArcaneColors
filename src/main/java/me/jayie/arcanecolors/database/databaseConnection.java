package me.jayie.arcanecolors.database;

import me.jayie.arcanecolors.ArcaneColors;
import org.bukkit.plugin.Plugin;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {

    Plugin plugin = ArcaneColors.getPlugin(ArcaneColors.class);


    private Connection connection;

    public boolean isConnected(){
        return(this.connection != null);
    }

    public void connect() throws SQLException {
        String host = plugin.getConfig().getString("Plugin.Database.MySQL.Address");
        String port = plugin.getConfig().getString("Plugin.Database.MySQL.Port");
        String user = plugin.getConfig().getString("Plugin.Database.MySQL.Username");
        String pass = plugin.getConfig().getString("Plugin.Database.MySQL.Password");
        String database = plugin.getConfig().getString("Plugin.Database.MySQL.Database");

             if (!isConnected()) {
                 try{
                     this.connection = DriverManager.getConnection("jdbc:mysql://" + URLEncoder.encode(user, StandardCharsets.UTF_8) + ":" + URLEncoder.encode(pass, StandardCharsets.UTF_8) + "@" + host + ":" + port + "/" + database + "?autoReconnect=true");
                 } catch (SQLException e) {
                     plugin.getLogger().severe("! DATABASE NEEDS TO BE CONNECTED !");
                 }
             }
         }

    public void disconnect(){
        if (isConnected()){
            try{
                this.connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection(){
        return this.connection;
    }

}

