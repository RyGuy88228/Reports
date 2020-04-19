package us.ryguy.reports;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import us.ryguy.reports.cmds.*;
import us.ryguy.reports.listeners.AsyncPlayerChat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {
    Boolean chatReportsCancelled = false;
    List<String> msgs = new ArrayList<>();
    File datafolder;
    public void onEnable() {
        this.datafolder = getDataFolder();
        getCommand("report").setExecutor(new Report());
        getCommand("report").setTabCompleter(new ReportTabComplete());
        getCommand("reports").setExecutor(new Reports());
        getCommand("reports").setTabCompleter(new ReportsCompleter());
        getCommand("chatreport").setExecutor(new ChatReport());
        getCommand("chatreport").setTabCompleter(new CReportTabComplete());

        getServer().getPluginManager().registerEvents(new AsyncPlayerChat(), this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Reports] Events and Commands initialized!");

        if(getConfig().getConfigurationSection("Options") == null) {
            initConfig();
        }
    }
    public void onDisable() {

    }
    public void initConfig() {
        getConfig().createSection("Options");
        getConfig().getConfigurationSection("Options").addDefault("ConsoleLogging", true);
        getConfig().getConfigurationSection("Options").addDefault("MaxMSGs", 20);

        getConfig().options().copyDefaults(true);
        saveConfig();
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[Reports] Config Created!");

        File cr = new File(this.getDataFolder() + File.separator + "ChatReports");
        if(cr.mkdir()) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Reports] Chat Reports directory created!");
        }else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[Reports] Error: Could not create ChatReports directory! Disabling Chat Report functionality, please investigate!");
            chatReportsCancelled = true;
        }
    }
    public void checkConfig() {
        if(!isInteger(getConfig().getConfigurationSection("Options").getString("MaxMSGS"))) {
            getConfig().getConfigurationSection("Options").set("MaxMSGs", Integer.valueOf(getConfig().getConfigurationSection("Options").getString("MaxMSGS")));
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Reports] Config Checked!");
        }
    }
    public File getDF() {
        return datafolder;
    }
    public List<String> getMsgs() {
        return msgs;
    }
    public Boolean crCancelled() {
        return this.chatReportsCancelled;
    }
    public boolean isInteger(String s) {
        boolean isValidInteger = false;
        try {
            Integer.parseInt(s);
            isValidInteger = true;
        } catch (NumberFormatException e) {

        }
        return isValidInteger;
    }
}
