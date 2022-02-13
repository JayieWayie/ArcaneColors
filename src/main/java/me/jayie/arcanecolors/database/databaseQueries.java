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
        PreparedStatement table = plugin.DB.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS SCPlayer(playerUUID varchar(255), Frozen int(1), Vanished int(1), PRIMARY KEY(playerUUID))");
        table.executeUpdate();
        plugin.getLogger().info(Color("&8[&6Marriage&8] &6Database table created."));
    }


    public void createPlayer(Player player) throws SQLException {
        UUID uuid = player.getUniqueId();
        if (!doesPlayerExist(player)){
            PreparedStatement createPlayer = plugin.DB.getConnection().prepareStatement("INSERT IGNORE INTO SCPlayer(playerUUID, Frozen, Vanished) VALUES (?,?,?)");
            createPlayer.setString(1, uuid.toString());
            createPlayer.setInt(2, 0);
            createPlayer.setInt(3, 0);
            createPlayer.executeUpdate();
        }
    }


    public boolean doesPlayerExist(Player player) throws SQLException{
        UUID uuid = player.getUniqueId();

        PreparedStatement ps1 = plugin.DB.getConnection().prepareStatement("SELECT * FROM SCPlayer WHERE playerUUID=?");
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
