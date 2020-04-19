package us.ryguy.reports.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import us.ryguy.reports.Main;
import us.ryguy.reports.util.ArrayUtils;
import us.ryguy.reports.util.FileMaker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatReport implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        ArrayUtils au = new ArrayUtils();
        Main main = (Main) Bukkit.getServer().getPluginManager().getPlugin("Reports");
        String timestamp = new SimpleDateFormat("MM.dd.yyyy hh_mm_ss").format(new Date());

        if (!main.crCancelled()) {
            if(args.length >= 1) {
                if(sender.hasPermission("reports.report") || sender.hasPermission("reports.op")) {
                    if(Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getServer().getPlayer(args[0]))) {
                        if(main.getMsgs().size() != 0) {
                            FileMaker fm = new FileMaker(main.getDF().getAbsolutePath() + File.separator + "ChatReports", "[" + timestamp + "] " + args[0] + " reported by " + sender.getName() + ".txt");
                            fm.create();
                            try {
                                FileWriter fw = new FileWriter(fm.getFolder() + File.separator + fm.getName());
                                fw.write("[-- Chat Report --] \n");
                                fw.write("Defendant: " + args[0] + "\n");
                                fw.write("Reported by: " + sender.getName() + "\n");
                                fw.write("Reason: " + au.join(au.removeFromArray(args, 0), " ") + "\n");
                                fw.write("Reported on: " + new SimpleDateFormat("MM / dd / yyyy").format(new Date()) + new SimpleDateFormat("hh:mm:ss").format(new Date()) + "\n \n");
                                for(String msg : main.getMsgs()) {
                                    fw.write(msg);
                                }
                                fw.close();
                                sender.sendMessage(ChatColor.DARK_AQUA + "[Reports] " + ChatColor.GRAY + "Thank you for your report!");
                                if(main.getConfig().getConfigurationSection("Options").getBoolean("ConsoleLogging")) {
                                    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[Report] New Chat Report from " + ChatColor.GOLD + sender.getName());
                                    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[Report] " + ChatColor.GOLD + args[0] + ChatColor.AQUA + " was chat reported for " + ChatColor.GOLD + au.join(au.removeFromArray(args, 0), " "));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else {
                            sender.sendMessage(ChatColor.DARK_AQUA + "[Reports] " + ChatColor.RED + "Error! No messages have been sent on the server yet!");
                        }
                    }else {
                        sender.sendMessage(ChatColor.DARK_AQUA + "[Report] " + ChatColor.GRAY + "Error: Player " + ChatColor.GOLD + args[0] + ChatColor.GRAY + " is not online!");
                    }
                }else {
                    sender.sendMessage(ChatColor.DARK_AQUA + "[Reports] " + ChatColor.RED + "Error! No permissions!");
                }
            }else {
                sender.sendMessage(ChatColor.DARK_AQUA + "[Reports] " + ChatColor.GRAY + "Usage: /chatreport [player] [optional reason]");
            }
        }else {
            sender.sendMessage(ChatColor.DARK_AQUA + "[Reports] " + ChatColor.RED + "Chat Reports have been disabled! Check your server logs!");
        }
        return true;
    }
}
