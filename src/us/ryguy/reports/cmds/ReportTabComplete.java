package us.ryguy.reports.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReportTabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        ArrayList<String> complete = new ArrayList<>();
        if (args.length == 1) {
            complete.add("reload");
            complete.add("getvalue");
            complete.add("setvalue");
            Collections.sort(complete);
            return complete;
        }
        return null;
    }
}
