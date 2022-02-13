package me.jayie.arcanecolors;

import me.jayie.arcanecolors.database.databaseConnection;
import me.jayie.arcanecolors.database.databaseQueries;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArcaneColors extends JavaPlugin {

    public databaseConnection DB;
    public databaseQueries DBQ;

    @Override
    public void onEnable() {
        // Plugin startup logic
        startup();
        database();
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

    public void database(){

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
        // Plugin shutdown logic
    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }
}
