package us.ryguy.reports.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import us.ryguy.reports.Main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AsyncPlayerChat implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Main main = (Main) Bukkit.getServer().getPluginManager().getPlugin("Reports");
        String timestamp = new SimpleDateFormat("hh:mm:ss").format(new Date());
        if(main.getMsgs().size() == main.getConfig().getConfigurationSection("Options").getInt("MaxMSGs")) {
            main.getMsgs().remove(main.getMsgs().size() - 1);
        }
        main.getMsgs().add(0, "[" + timestamp + "] " + e.getPlayer().getName() + ": " + e.getMessage() + "\n");
    }
}
