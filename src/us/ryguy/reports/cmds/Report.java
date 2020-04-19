package us.ryguy.reports.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.ryguy.reports.util.ArrayUtils;
import us.ryguy.reports.Main;

public class Report implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        ArrayUtils au = new ArrayUtils();
        Main main = (Main) Bukkit.getServer().getPluginManager().getPlugin("Reports");
        if(args.length >= 1) {
            if(sender.hasPermission("reports.report") || sender.hasPermission("reports.op")) {
                if(Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getServer().getPlayer(args[0]))) {
                    for(Player p : Bukkit.getOnlinePlayers()) {
                        if(p.hasPermission("reports.read") || sender.hasPermission("reports.op")) {
                            p.sendMessage(ChatColor.DARK_AQUA + "[Report] " + ChatColor.GRAY + "New Report from " + ChatColor.GOLD + sender.getName());
                            p.sendMessage(ChatColor.DARK_AQUA + "[Report] " + ChatColor.GOLD + args[0] + ChatColor.GRAY + " was reported for " + ChatColor.GOLD + au.join(au.removeFromArray(args, 0), " "));
                        }
                    }
                    if(main.getConfig().getConfigurationSection("Options").getBoolean("ConsoleLogging")) {
                        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[Report] New Report from " + ChatColor.GOLD + sender.getName());
                        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[Report] " + ChatColor.GOLD + args[0] + ChatColor.AQUA + " was reported for " + ChatColor.GOLD + au.join(au.removeFromArray(args, 0), " "));
                    }
                    sender.sendMessage(ChatColor.DARK_AQUA + "[Report] " + ChatColor.GRAY + "Thank you so much for your report!");
                }else {
                    sender.sendMessage(ChatColor.DARK_AQUA + "[Report] " + ChatColor.GRAY + "Error: Player " + ChatColor.GOLD + args[0] + ChatColor.GRAY + " is not online!");
                }
            }else {
                sender.sendMessage(ChatColor.DARK_AQUA + "[Report] " + ChatColor.RED + "No permission!");
            }
        }else {
            sender.sendMessage(ChatColor.DARK_AQUA + "[Report] " + ChatColor.GRAY + "Usage: /report [player] [Optional Reason]");
        }
        return true;
    }
}
