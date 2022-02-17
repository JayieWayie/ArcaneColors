package me.jayie.arcanecolors.Commands;

import me.jayie.arcanecolors.ArcaneColors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class adminCommands implements CommandExecutor {


    private final ArcaneColors plugin;
    public adminCommands(ArcaneColors plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String nullplayer = plugin.getConfig().getString("Errors.NullPlayer");
        String noperm = plugin.getConfig().getString("Errors.NoPermission");
        String adminperm = plugin.getConfig().getString("Plugin.Permissions.Admin.AllPerms");
        String forceresetperm = plugin.getConfig().getString("Plugin.Permissions.Reset.Force");
        String staffmessage = plugin.getConfig().getString("Messages.Reset.ForceReset.Staff");
        String playermessage = plugin.getConfig().getString("Messages.Reset.ForceReset.Player");
        String allperms = plugin.getConfig().getString("Plugin.Permissions.Admin.Reload");

        if (sender instanceof Player){
            Player player = (Player) sender;

            if (command.getName().equalsIgnoreCase("achatcolor")){

                if (args.length < 1){
                    player.sendMessage("error404: this isn't a command.");
                }else if (args[0].equalsIgnoreCase("reset")){
                    if (player.hasPermission(forceresetperm) || player.hasPermission(adminperm)){
                        try {
                            Player target = Bukkit.getPlayer(args[1]);

                            PreparedStatement resetCommand = plugin.DB.getConnection().prepareStatement("UPDATE chatcolor SET color=? WHERE playerUUID =?");
                            resetCommand.setString(1, plugin.pmc.permChecker(target));
                            resetCommand.setString(2, target.getUniqueId().toString());
                            resetCommand.executeUpdate();
                            staffmessage = staffmessage.replace("%target%", target.getName());
                            player.sendMessage(Color(Hex(staffmessage)));
                            playermessage = playermessage.replace("%player%", player.getName());
                            target.sendMessage(Color(Hex(playermessage)));

                        } catch (NullPointerException ex){
                            player.sendMessage(Color(Hex(nullplayer)));
                        }catch(SQLException e) {
                            e.printStackTrace();
                        }

                    }
                }else if (args[0].equalsIgnoreCase("reload")){
                    if (player.hasPermission(adminperm) || player.hasPermission(allperms)){
                        player.sendMessage(Color(Hex("&8[&cArcaneColors&8] &aPlugin reloaded.")));
                        plugin.reloadConfig();
                    }
                }
            }


        }


        return true;
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
