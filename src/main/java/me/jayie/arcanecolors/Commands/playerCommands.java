package me.jayie.arcanecolors.Commands;

import me.jayie.arcanecolors.ArcaneColors;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class playerCommands implements CommandExecutor {

    private final ArcaneColors plugin;
    public playerCommands(ArcaneColors plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String invalidArgument = plugin.getConfig().getString("Errors.InvalidArgument");
        String prefix = plugin.getConfig().getString("Plugin.Prefix");
        String allcolorperm = plugin.getConfig().getString("Plugin.Permissions.Color.Set");
        String certaincolorperm = plugin.getConfig().getString("Plugin.Permissions.Color.CertainColor");
        String everyperm = plugin.getConfig().getString("Plugin.Permissions.Admin.AllPerms");
        String hexperm = plugin.getConfig().getString("Plugin.Permissions.Color.Hex");
        String success = null;
        String noperm = plugin.getConfig().getString("Errors.NoPermission");
        String boldperm = plugin.getConfig().getString("Plugin.Permissions.Special.Bold");
        String italicperm = plugin.getConfig().getString("Plugin.Permissions.Special.Italic");
        String strikethroughperm = plugin.getConfig().getString("Plugin.Permissions.Special.Strikethrough");
        String underlineperm = plugin.getConfig().getString("Plugin.Permissions.Special.Underline");
        String magicperm = plugin.getConfig().getString("Plugin.Permissions.Special.Magic");


        if (sender instanceof Player){
            Player player = (Player) sender;




            if (command.getName().equalsIgnoreCase("chatcolor")) {

                try {

                    PreparedStatement colorset = null;
                    PreparedStatement boldset = null;
                    PreparedStatement italicset = null;
                    PreparedStatement magicset = null;
                    PreparedStatement underlineset = null;
                    PreparedStatement strikethroughset = null;
                    String colorChoice = null;
                    try {
                        colorset = plugin.DB.getConnection().prepareStatement("UPDATE chatcolor SET color=? WHERE playerUUID =?");
                        boldset = plugin.DB.getConnection().prepareStatement("UPDATE chatcolor SET bold=? WHERE playerUUID =?");
                        italicset = plugin.DB.getConnection().prepareStatement("UPDATE chatcolor SET italic=? WHERE playerUUID =?");
                        magicset = plugin.DB.getConnection().prepareStatement("UPDATE chatcolor SET magic=? WHERE playerUUID =?");
                        underlineset = plugin.DB.getConnection().prepareStatement("UPDATE chatcolor SET underline=? WHERE playerUUID =?");
                        strikethroughset = plugin.DB.getConnection().prepareStatement("UPDATE chatcolor SET strikethrough=? WHERE playerUUID =?");


                        colorset.setString(2, player.getUniqueId().toString());
                        boldset.setString(2, player.getUniqueId().toString());
                        italicset.setString(2, player.getUniqueId().toString());
                        magicset.setString(2, player.getUniqueId().toString());
                        underlineset.setString(2, player.getUniqueId().toString());
                        strikethroughset.setString(2, player.getUniqueId().toString());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                    if (args[0].equalsIgnoreCase("set")) {

                        if (args[1].equalsIgnoreCase("darkblue") || args[1].equalsIgnoreCase("1")) {
                            certaincolorperm = certaincolorperm.replace("%color-name%", "darkblue");
                            if (player.hasPermission(certaincolorperm) || player.hasPermission(allcolorperm) || player.hasPermission(everyperm)) {
                                try {
                                    colorset.setString(1, "&1");
                                    colorset.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                String success2 = plugin.getConfig().getString("Messages.Color.Success").replace("%color%", "&1");
                                player.sendMessage(Color(Hex(prefix + " " + success2)));
                            } else {
                                player.sendMessage(Color(Hex(prefix + " " + noperm)));
                            }
                        } else if (args[1].equalsIgnoreCase("green") || args[1].equalsIgnoreCase("2")) {
                            certaincolorperm = certaincolorperm.replace("%color-name%", "darkgreen");
                            if (player.hasPermission(certaincolorperm) || player.hasPermission(allcolorperm) || player.hasPermission(everyperm)) {
                                try {
                                    colorset.setString(1, "&2");
                                    colorset.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                String success2 = plugin.getConfig().getString("Messages.Color.Success").replace("%color%", "&2");
                                player.sendMessage(Color(Hex(prefix + " " + success2)));
                            } else {
                                player.sendMessage(Color(Hex(prefix + " " + noperm)));
                            }
                        } else if (args[1].equalsIgnoreCase("cyan") || args[1].equalsIgnoreCase("3")) {
                            certaincolorperm = certaincolorperm.replace("%color-name%", "cyan");
                            if (player.hasPermission(certaincolorperm) || player.hasPermission(allcolorperm) || player.hasPermission(everyperm)) {
                                try {
                                    colorset.setString(1, "&3");
                                    colorset.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                String success2 = plugin.getConfig().getString("Messages.Color.Success").replace("%color%", "&3");
                                player.sendMessage(Color(Hex(prefix + " " + success2)));
                            } else {
                                player.sendMessage(Color(Hex(prefix + " " + noperm)));
                            }

                        } else if (args[1].equalsIgnoreCase("darkred") || args[1].equalsIgnoreCase("4")) {
                            certaincolorperm = certaincolorperm.replace("%color-name%", "darkred");
                            if (player.hasPermission(certaincolorperm) || player.hasPermission(everyperm)) {
                                try {
                                    colorset.setString(1, "&4");
                                    colorset.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                String success2 = plugin.getConfig().getString("Messages.Color.Success").replace("%color%", "&4");
                                player.sendMessage(Color(Hex(prefix + " " + success2)));
                            } else {
                                player.sendMessage(Color(Hex(prefix + " " + noperm)));
                            }

                        } else if (args[1].equalsIgnoreCase("purple") || args[1].equalsIgnoreCase("5")) {
                            certaincolorperm = certaincolorperm.replace("%color-name%", "purple");
                            if (player.hasPermission(certaincolorperm) || player.hasPermission(allcolorperm) || player.hasPermission(everyperm)) {
                                try {
                                    colorset.setString(1, "&5");
                                    colorset.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                String success2 = plugin.getConfig().getString("Messages.Color.Success").replace("%color%", "&5");
                                player.sendMessage(Color(Hex(prefix + " " + success2)));
                            } else {
                                player.sendMessage(Color(Hex(prefix + " " + noperm)));
                            }

                        } else if (args[1].equalsIgnoreCase("gold") || args[1].equalsIgnoreCase("6")) {
                            certaincolorperm = certaincolorperm.replace("%color-name%", "gold");
                            if (player.hasPermission(certaincolorperm) || player.hasPermission(allcolorperm) || player.hasPermission(everyperm)) {
                                try {
                                    colorset.setString(1, "&6");
                                    colorset.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                String success2 = plugin.getConfig().getString("Messages.Color.Success").replace("%color%", "&6");
                                player.sendMessage(Color(Hex(prefix + " " + success2)));
                            } else {
                                player.sendMessage(Color(Hex(prefix + " " + noperm)));
                            }

                        } else if (args[1].equalsIgnoreCase("lightgray") || args[1].equalsIgnoreCase("7")) {
                            certaincolorperm = certaincolorperm.replace("%color-name%", "lightgray");
                            if (player.hasPermission(certaincolorperm) || player.hasPermission(allcolorperm) || player.hasPermission(everyperm)) {
                                try {
                                    colorset.setString(1, "&7");
                                    colorset.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                String success2 = plugin.getConfig().getString("Messages.Color.Success").replace("%color%", "&7");
                                player.sendMessage(Color(Hex(prefix + " " + success2)));
                            } else {
                                player.sendMessage(Color(Hex(prefix + " " + noperm)));
                            }

                        } else if (args[1].equalsIgnoreCase("gray") || args[1].equalsIgnoreCase("8")) {
                            certaincolorperm = certaincolorperm.replace("%color-name%", "gray");
                            if (player.hasPermission(certaincolorperm) || player.hasPermission(allcolorperm) || player.hasPermission(everyperm)) {
                                try {
                                    colorset.setString(1, "&8");
                                    colorset.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                String success2 = plugin.getConfig().getString("Messages.Color.Success").replace("%color%", "&8");
                                player.sendMessage(Color(Hex(prefix + " " + success2)));
                            } else {
                                player.sendMessage(Color(Hex(prefix + " " + noperm)));
                            }

                        } else if (args[1].equalsIgnoreCase("blue") || args[1].equalsIgnoreCase("9")) {
                            certaincolorperm = certaincolorperm.replace("%color-name%", "blue");
                            if (player.hasPermission(certaincolorperm) || player.hasPermission(allcolorperm) || player.hasPermission(everyperm)) {
                                try {
                                    colorset.setString(1, "&9");
                                    colorset.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                String success2 = plugin.getConfig().getString("Messages.Color.Success").replace("%color%", "&9");
                                player.sendMessage(Color(Hex(prefix + " " + success2)));
                            } else {
                                player.sendMessage(Color(Hex(prefix + " " + noperm)));
                            }

                        } else if (args[1].equalsIgnoreCase("black") || args[1].equalsIgnoreCase("0")) {
                            certaincolorperm = certaincolorperm.replace("%color-name%", "black");
                            if (player.hasPermission(certaincolorperm) || player.hasPermission(allcolorperm) || player.hasPermission(everyperm)) {
                                try {
                                    colorset.setString(1, "&0");
                                    colorset.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                String success2 = plugin.getConfig().getString("Messages.Color.Success").replace("%color%", "&0");
                                player.sendMessage(Color(Hex(prefix + " " + success2)));
                            } else {
                                player.sendMessage(Color(Hex(prefix + " " + noperm)));
                            }

                        } else if (args[1].equalsIgnoreCase("lime") || args[1].equalsIgnoreCase("a")) {
                            certaincolorperm = certaincolorperm.replace("%color-name%", "lime");
                            if (player.hasPermission(certaincolorperm) || player.hasPermission(allcolorperm) || player.hasPermission(everyperm)) {
                                try {
                                    colorset.setString(1, "&a");
                                    colorset.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                String success2 = plugin.getConfig().getString("Messages.Color.Success").replace("%color%", "&a");
                                player.sendMessage(Color(Hex(prefix + " " + success2)));
                            } else {
                                player.sendMessage(Color(Hex(prefix + " " + noperm)));
                            }

                        } else if (args[1].equalsIgnoreCase("aqua") || args[1].equalsIgnoreCase("b")) {
                            certaincolorperm = certaincolorperm.replace("%color-name%", "aqua");
                            if (player.hasPermission(certaincolorperm) || player.hasPermission(allcolorperm) || player.hasPermission(everyperm)) {
                                try {
                                    colorset.setString(1, "&b");
                                    colorset.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                String success2 = plugin.getConfig().getString("Messages.Color.Success").replace("%color%", "&b");
                                player.sendMessage(Color(Hex(prefix + " " + success2)));
                            } else {
                                player.sendMessage(Color(Hex(prefix + " " + noperm)));
                            }

                        } else if (args[1].equalsIgnoreCase("red") || args[1].equalsIgnoreCase("c")) {
                            certaincolorperm = certaincolorperm.replace("%color-name%", "red");
                            if (player.hasPermission(certaincolorperm) || player.hasPermission(everyperm)) {
                                try {
                                    colorset.setString(1, "&c");
                                    colorset.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                String success2 = plugin.getConfig().getString("Messages.Color.Success").replace("%color%", "&c");
                                player.sendMessage(Color(Hex(prefix + " " + success2)));
                            } else {
                                player.sendMessage(Color(Hex(prefix + " " + noperm)));
                            }

                        } else if (args[1].equalsIgnoreCase("pink") || args[1].equalsIgnoreCase("d")) {
                            certaincolorperm = certaincolorperm.replace("%color-name%", "pink");
                            if (player.hasPermission(certaincolorperm) || player.hasPermission(allcolorperm) || player.hasPermission(everyperm)) {
                                try {
                                    colorset.setString(1, "&d");
                                    colorset.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                String success2 = plugin.getConfig().getString("Messages.Color.Success").replace("%color%", "&d");
                                player.sendMessage(Color(Hex(prefix + " " + success2)));
                            } else {
                                player.sendMessage(Color(Hex(prefix + " " + noperm)));
                            }

                        } else if (args[1].equalsIgnoreCase("yellow") || args[1].equalsIgnoreCase("e")) {
                            certaincolorperm = certaincolorperm.replace("%color-name%", "yellow");
                            if (player.hasPermission(certaincolorperm) || player.hasPermission(allcolorperm) || player.hasPermission(everyperm)) {
                                try {
                                    colorset.setString(1, "&e");
                                    colorset.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                String success2 = plugin.getConfig().getString("Messages.Color.Success").replace("%color%", "&e");
                                player.sendMessage(Color(Hex(prefix + " " + success2)));
                            } else {
                                player.sendMessage(Color(Hex(prefix + " " + noperm)));
                            }

                        } else if (args[1].equalsIgnoreCase("white") || args[1].equalsIgnoreCase("f")) {
                            certaincolorperm = certaincolorperm.replace("%color-name%", "white");
                            if (player.hasPermission(certaincolorperm) || player.hasPermission(allcolorperm) || player.hasPermission(everyperm)) {
                                try {
                                    colorset.setString(1, "&f");
                                    colorset.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                String success2 = plugin.getConfig().getString("Messages.Color.Success").replace("%color%", "&f");
                                player.sendMessage(Color(Hex(prefix + " " + success2)));
                            } else {
                                player.sendMessage(Color(Hex(prefix + " " + noperm)));
                            }

                        } else if (args[1].equalsIgnoreCase("bold") || args[1].equalsIgnoreCase("l")) {
                            if (player.hasPermission(boldperm) || player.hasPermission(everyperm)) {
                                try {
                                    String boldtoggle = plugin.getConfig().getString("Messages.Bold.Success");
                                    PreparedStatement boldcheckps = plugin.DB.getConnection().prepareStatement("SELECT bold FROM chatcolor WHERE playerUUID=?");
                                    boldcheckps.setString(1, player.getUniqueId().toString());
                                    ResultSet boldcheckrs = boldcheckps.executeQuery();
                                    int boldcheck = 0;
                                    if (boldcheckrs.next()) {
                                        boldcheck = boldcheckrs.getInt("bold");
                                    }
                                    if (boldcheck == 1) {
                                        boldset.setInt(1, 0);
                                        boldtoggle = boldtoggle.replace("%status%", "off");
                                        player.sendMessage(Color(Hex(prefix + " " + boldtoggle)));
                                    } else {
                                        boldset.setInt(1, 1);
                                        boldtoggle = boldtoggle.replace("%status%", "on");
                                        player.sendMessage(Color(Hex(prefix + " " + boldtoggle)));
                                    }
                                    boldset.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }

                            } else {
                                player.sendMessage(Color(Hex(noperm)));
                            }

                        } else if (args[1].equalsIgnoreCase("italic") || args[1].equalsIgnoreCase("o")) {
                            if (player.hasPermission(italicperm) || player.hasPermission(everyperm)) {
                                try {
                                    String italictoggle = plugin.getConfig().getString("Messages.Italic.Success");
                                    PreparedStatement italiccheckps = plugin.DB.getConnection().prepareStatement("SELECT italic FROM chatcolor WHERE playerUUID=?");
                                    italiccheckps.setString(1, player.getUniqueId().toString());
                                    ResultSet italiccheckrs = italiccheckps.executeQuery();
                                    int italiccheck = 0;
                                    if (italiccheckrs.next()) {
                                        italiccheck = italiccheckrs.getInt("italic");
                                    }
                                    if (italiccheck == 1) {
                                        italicset.setInt(1, 0);
                                        italictoggle = italictoggle.replace("%status%", "off");
                                        player.sendMessage(Color(Hex(prefix + " " + italictoggle)));
                                    } else {
                                        italicset.setInt(1, 1);
                                        italictoggle = italictoggle.replace("%status%", "on");
                                        player.sendMessage(Color(Hex(prefix + " " + italictoggle)));
                                    }
                                    italicset.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                player.sendMessage(Color(Hex(noperm)));
                            }

                        } else if (args[1].equalsIgnoreCase("underline") || args[1].equalsIgnoreCase("n")) {
                            if (player.hasPermission(underlineperm) || player.hasPermission(everyperm)) {
                                try {
                                    String underlinetoggle = plugin.getConfig().getString("Messages.Underline.Success");
                                    PreparedStatement underlinecheckps = plugin.DB.getConnection().prepareStatement("SELECT underline FROM chatcolor WHERE playerUUID=?");
                                    underlinecheckps.setString(1, player.getUniqueId().toString());
                                    ResultSet underlinecheckrs = underlinecheckps.executeQuery();
                                    int underlinecheck = 0;
                                    if (underlinecheckrs.next()) {
                                        underlinecheck = underlinecheckrs.getInt("underline");
                                    }
                                    if (underlinecheck == 1) {
                                        underlineset.setInt(1, 0);
                                        underlinetoggle = underlinetoggle.replace("%status%", "off");
                                        player.sendMessage(Color(Hex(prefix + " " + underlinetoggle)));
                                    } else {
                                        underlineset.setInt(1, 1);
                                        underlinetoggle = underlinetoggle.replace("%status%", "on");
                                        player.sendMessage(Color(Hex(prefix + " " + underlinetoggle)));
                                    }
                                    underlineset.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }

                            } else {
                                player.sendMessage(Color(Hex(noperm)));
                            }

                        } else if (args[1].equalsIgnoreCase("strikethrough") || args[1].equalsIgnoreCase("m")) {
                            if (player.hasPermission(strikethroughperm) || player.hasPermission(everyperm)) {
                                try {
                                    String sttoggle = plugin.getConfig().getString("Messages.Strikethrough.Success");
                                    PreparedStatement strikethroughcheckps = plugin.DB.getConnection().prepareStatement("SELECT strikethrough FROM chatcolor WHERE playerUUID=?");
                                    strikethroughcheckps.setString(1, player.getUniqueId().toString());
                                    ResultSet strikethroughcheckrs = strikethroughcheckps.executeQuery();
                                    int strikethroughcheck = 0;
                                    if (strikethroughcheckrs.next()) {
                                        strikethroughcheck = strikethroughcheckrs.getInt("strikethrough");
                                    }
                                    if (strikethroughcheck == 1) {
                                        strikethroughset.setInt(1, 0);
                                        sttoggle = sttoggle.replaceAll("%status%", "off");
                                        player.sendMessage(Color(Hex(prefix + " " + sttoggle)));
                                    } else {
                                        strikethroughset.setInt(1, 1);
                                        sttoggle = sttoggle.replace("%status%", "on");
                                        player.sendMessage(Color(Hex(prefix + " " + sttoggle)));
                                    }
                                    strikethroughset.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }

                            } else {
                                player.sendMessage(Color(Hex(noperm)));
                            }

                        } else if (args[1].equalsIgnoreCase("magic") || args[1].equalsIgnoreCase("k")) {
                            if (player.hasPermission(magicperm) || player.hasPermission(everyperm)) {
                                try {
                                    String magictoggle = plugin.getConfig().getString("Messages.Magic.Success");
                                    PreparedStatement magiccheckps = plugin.DB.getConnection().prepareStatement("SELECT magic FROM chatcolor WHERE playerUUID=?");
                                    magiccheckps.setString(1, player.getUniqueId().toString());
                                    ResultSet magiccheckrs = magiccheckps.executeQuery();
                                    int magiccheck = 0;
                                    if (magiccheckrs.next()) {
                                        magiccheck = magiccheckrs.getInt("magic");
                                    }
                                    if (magiccheck == 1) {
                                        magicset.setInt(1, 0);
                                        magictoggle = magictoggle.replace("%status%", "off");
                                        player.sendMessage(Color(Hex(prefix + " " + magictoggle)));
                                    } else {
                                        magicset.setInt(1, 1);
                                        magictoggle = magictoggle.replace("%status%", "on");
                                        player.sendMessage(Color(Hex(prefix + " " + magictoggle)));
                                    }
                                    magicset.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }

                            } else {
                                player.sendMessage(Color(Hex(noperm)));
                            }

                        } else if (args[1].equalsIgnoreCase("hex")) {
                            if (player.hasPermission(hexperm) || player.hasPermission(everyperm)) {

                                Pattern HEX_PATTERN = Pattern.compile("&(#\\w{6})");
                                String hexChoice = args[2];
                                if (hexChoice.matches(HEX_PATTERN.pattern())) {
                                    Matcher matcher = HEX_PATTERN.matcher(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', hexChoice));
                                    StringBuffer buffer = new StringBuffer();
                                    while (matcher.find()) {
                                        matcher.appendReplacement(buffer, net.md_5.bungee.api.ChatColor.of(matcher.group(1)).toString());
                                    }
                                    matcher.appendTail(buffer).toString();
                                    try {
                                        colorset.setString(1, hexChoice);
                                        String success2 = plugin.getConfig().getString("Messages.Color.Success").replace("%color%", hexChoice);
                                        player.sendMessage(Color(Hex(prefix + " " + success2)));
                                        colorset.executeUpdate();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }

                                }
                            } else {
                                player.sendMessage(Color(Hex(prefix + " " + noperm)));
                            }
                        }


                    } else if (args[0].equalsIgnoreCase("help")) {

                        for (String help : plugin.getConfig().getStringList("Messages.HelpMenu")) {
                            player.sendMessage(Color(Hex(help)));
                        }

                    } else if (args[0].equalsIgnoreCase("gui")) {
                        String openmessage = plugin.getConfig().getString("GUI.Settings.OpeningMessage");
                        player.sendMessage(Color(Hex(prefix + " " + openmessage)));
                        player.openInventory(plugin.mGUI.mainMenu(player));

                    } else if (args[0].equalsIgnoreCase("reset")) {
                        try {
                            PreparedStatement resetCommand = plugin.DB.getConnection().prepareStatement("UPDATE chatcolor SET color=? WHERE playerUUID =?");
                            resetCommand.setString(1, plugin.pmc.permChecker(player));
                            resetCommand.setString(2, player.getUniqueId().toString());
                            resetCommand.executeUpdate();
                            player.sendMessage(Color(Hex(prefix + " " + plugin.getConfig().getString("Messages.Reset.Success"))));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else {

                    }

                }catch(ArrayIndexOutOfBoundsException e){
                    try {
                        player.openInventory(plugin.mGUI.mainMenu(player));
                        String openmessage = plugin.getConfig().getString("GUI.Settings.OpeningMessage");
                        player.sendMessage(Color(Hex(openmessage)));
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    player.sendMessage();
                } catch (SQLException e) {
                    e.printStackTrace();
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
