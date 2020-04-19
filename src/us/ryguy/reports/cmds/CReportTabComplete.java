package us.ryguy.reports.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CReportTabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        ArrayList<String> complete = new ArrayList<>();
        if(args.length == 1) {
            for(Player p : Bukkit.getServer().getOnlinePlayers()) {
                complete.add(p.getName());
            }
            Collections.sort(complete);
            return complete;
        }
        return null;
    }
}
