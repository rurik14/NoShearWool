package rurik.noshearwool;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CommandNSW implements CommandExecutor, TabCompleter {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("noshearwool.toggle")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission!");
            return true;
        }
        Noshearwool nsw = Noshearwool.getInstance();
        if (args.length == 0) {
            nsw.toggleEnabled();
            sender.sendMessage(nsw.getStatus());
            return true;
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("info")) {
                sender.sendMessage(nsw.getStatus());
            } else if (args[0].equalsIgnoreCase("on")) {
                nsw.enable(true);
                sender.sendMessage(nsw.getStatus());
            } else if (args[0].equalsIgnoreCase("off")) {
                nsw.enable(false);
                sender.sendMessage(nsw.getStatus());
            } else {
                sender.sendMessage(ChatColor.RED + "unrecognized command: " + args[0] + "\nallowed values are on/off/info");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "at most one argument is permitted, you gave " + args.length);
        }
        return true;
    }

    @Nullable
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("noshearwool.toggle"))
            return new ArrayList<>();
        if (args.length == 1)
            return (List<String>)Stream.<String>of(new String[] { "on", "off", "info" }).filter(s -> s.startsWith(args[0]))
                    .collect(Collectors.toList());
        return new ArrayList<>();
    }
}

