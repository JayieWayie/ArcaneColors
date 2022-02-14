package me.jayie.arcanecolors.database;

import me.jayie.arcanecolors.ArcaneColors;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class databaseQueries {

    private final ArcaneColors plugin;
    public databaseQueries(ArcaneColors plugin){
        this.plugin = plugin;
    }

    public void createTable() throws SQLException {
        PreparedStatement table = plugin.DB.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS chatcolor(playerUUID varchar(255),color varchar(50),bold int(1),italic int(1),underline int(1),strikethrough int(1),magic int(1), PRIMARY KEY(playerUUID))");
        table.executeUpdate();
        plugin.getLogger().info(Color("&8[&dArcaneColors&8] &6Database table created."));
    }


    public void createPlayer(Player player) throws SQLException {
        UUID uuid = player.getUniqueId();
        if (!doesPlayerExist(player)){
            PreparedStatement createPlayer = plugin.DB.getConnection().prepareStatement("INSERT IGNORE INTO chatcolor(playerUUID, color, bold, italic, underline, strikethrough, magic) VALUES (?,?,?,?,?,?,?)");
            createPlayer.setString(1, uuid.toString());
            createPlayer.setString(2, plugin.getConfig().getString("DefaultColor"));
            createPlayer.setInt(3, 0);
            createPlayer.setInt(4, 0);
            createPlayer.setInt(5, 0);
            createPlayer.setInt(6, 0);
            createPlayer.setInt(7, 0);
            createPlayer.executeUpdate();
        }
    }


    public boolean doesPlayerExist(Player player) throws SQLException{
        UUID uuid = player.getUniqueId();
        PreparedStatement ps1 = plugin.DB.getConnection().prepareStatement("SELECT * FROM chatcolor WHERE playerUUID=?");
        ps1.setString(1, String.valueOf(uuid));
        ResultSet rs1 = ps1.executeQuery();
        if (rs1.next()){
            return true;
        }else{
            return false;
        }
    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&',s);
        return s;
    }
}
