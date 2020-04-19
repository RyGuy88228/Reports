package us.ryguy.reports.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import us.ryguy.reports.Main;

public class Reports implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Main main = (Main) Bukkit.getServer().getPluginManager().getPlugin("Reports");
        if(args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("reports.reload") || sender.hasPermission("reports.op")) {
                    try {
                        main.reloadConfig();
                        main.checkConfig();
                        sender.sendMessage(ChatColor.DARK_AQUA + "[Reports]" + ChatColor.GRAY + " Config Reloaded!");
                    } catch (Exception e) {
                        sender.sendMessage(ChatColor.DARK_AQUA + "[Reports] " + ChatColor.GRAY + "There was a(n) " + e.getClass().getSimpleName() + " in attempting to reload configuration!");
                        sender.sendMessage(ChatColor.DARK_AQUA + "[Reports] " + ChatColor.GRAY + e.getMessage());
                        sender.sendMessage(ChatColor.DARK_AQUA + "[Reports] " + ChatColor.GRAY + e.getLocalizedMessage());
                        sender.sendMessage(ChatColor.DARK_AQUA + "[Reports] " + ChatColor.GRAY + "Try to use a rich text editor to edit your configuration, and if that shows nothing, and you believe it is a bug, report it!");
                    }
                } else {
                    sender.sendMessage(ChatColor.DARK_AQUA + "[Reports]" + ChatColor.GRAY + " No Permission!");
                }
            } else if (args[0].equalsIgnoreCase("getvalue")) {
                if (sender.hasPermission("reports.getvalue") || sender.hasPermission("reports.op")) {
                    sender.sendMessage(ChatColor.DARK_AQUA + "[Reports] " + ChatColor.GRAY + "ChatReports currently hold the previous " + ChatColor.GOLD + main.getConfig().getConfigurationSection("Options").getString("MaxMSGs") + ChatColor.GRAY + " messages in one report!");
                }
            } else {
                sender.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "---" + ChatColor.RESET.toString() + ChatColor.BOLD + "" + ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + " Reports Help " + ChatColor.GRAY + "]" + ChatColor.STRIKETHROUGH + "---");
                sender.sendMessage(ChatColor.DARK_AQUA + "/reports reload" + ChatColor.GRAY + " - Reload's Reports Config!");
                sender.sendMessage(ChatColor.DARK_AQUA + "/reports getvalue" + ChatColor.GRAY + " - Gets the amount of messages a ChatReport holds!");
                sender.sendMessage(ChatColor.DARK_AQUA + "/reports setvalue" + ChatColor.GRAY + " - Sets the amount of messages a ChatReport holds!");
            }
        }else if(args.length == 2) {
            if(args[0].equalsIgnoreCase("setvalue")) {
                if (sender.hasPermission("reports.setvalue") || sender.hasPermission("reports.op")) {
                    if (main.isInteger(args[1])) {
                        main.getConfig().getConfigurationSection("Options").set("MaxMSGs", Integer.valueOf(args[1]));
                        main.saveConfig();
                        sender.sendMessage(ChatColor.DARK_AQUA + "[Reports] " + ChatColor.GRAY + "ChatReports now hold the previous " + ChatColor.GOLD + main.getConfig().getConfigurationSection("Options").getString("MaxMSGs") + ChatColor.GRAY + " messages!");
                    } else {
                        sender.sendMessage(ChatColor.DARK_AQUA + "[Reports] " + ChatColor.RED + "You need to set this value to an integer!");
                    }
                } else {
                    sender.sendMessage(ChatColor.DARK_AQUA + "[Reports] " + ChatColor.RED + "No Permission!");
                }
            }else {
                sender.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "---" + ChatColor.RESET.toString() + ChatColor.BOLD + "" + ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + " Reports Help " + ChatColor.GRAY + "]" + ChatColor.STRIKETHROUGH + "---");
                sender.sendMessage(ChatColor.DARK_AQUA + "/reports reload" + ChatColor.GRAY + " - Reload's Reports Config!");
                sender.sendMessage(ChatColor.DARK_AQUA + "/reports getvalue" + ChatColor.GRAY + " - Gets the amount of messages a ChatReport holds!");
                sender.sendMessage(ChatColor.DARK_AQUA + "/reports setvalue" + ChatColor.GRAY + " - Sets the amount of messages a ChatReport holds!");
            }
        }else {
            sender.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "---" + ChatColor.RESET.toString() + ChatColor.BOLD + "" + ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + " Reports Help " + ChatColor.GRAY + "]" + ChatColor.STRIKETHROUGH + "---");
            sender.sendMessage(ChatColor.DARK_AQUA + "/reports reload" + ChatColor.GRAY + " - Reload's Reports Config!");
            sender.sendMessage(ChatColor.DARK_AQUA + "/reports getvalue" + ChatColor.GRAY + " - Gets the amount of messages a ChatReport holds!");
            sender.sendMessage(ChatColor.DARK_AQUA + "/reports setvalue" + ChatColor.GRAY + " - Sets the amount of messages a ChatReport holds!");
        }
        return true;
    }
}
