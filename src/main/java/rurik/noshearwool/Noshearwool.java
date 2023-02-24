package rurik.noshearwool;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class Noshearwool extends JavaPlugin {
    private static Noshearwool instance;
    private Boolean active = false;
    private HashSet<Material> woolTypes = new HashSet<>();

    public void onLoad() {
        instance = this;
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        initCommands();
        initListeners();
        initMaterials();
    }

    private void initMaterials() {
        woolTypes.add(Material.WHITE_WOOL);
        woolTypes.add(Material.BLACK_WOOL);
        woolTypes.add(Material.BLUE_WOOL);
        woolTypes.add(Material.LIGHT_BLUE_WOOL);
        woolTypes.add(Material.BROWN_WOOL);
        woolTypes.add(Material.CYAN_WOOL);
        woolTypes.add(Material.GRAY_WOOL);
        woolTypes.add(Material.GREEN_WOOL);
        woolTypes.add(Material.LIGHT_GRAY_WOOL);
        woolTypes.add(Material.LIME_WOOL);
        woolTypes.add(Material.MAGENTA_WOOL);
        woolTypes.add(Material.ORANGE_WOOL);
        woolTypes.add(Material.PINK_WOOL);
        woolTypes.add(Material.PURPLE_WOOL);
        woolTypes.add(Material.RED_WOOL);
        woolTypes.add(Material.YELLOW_WOOL);
    }

    HashSet<Material> getWoolTypes(){return this.woolTypes;}

    private void initListeners() {
        Bukkit.getPluginManager().registerEvents(new BlockDamageListener(this), (Plugin)this);
    }
    public String getStatus() {
        return ChatColor.DARK_GREEN + "NoShearWool is now " + (this.active ? (ChatColor.YELLOW + "enabled") : (ChatColor.RED + "disabled"));
    }
    private void initCommands() {
        CommandNSW cmdNSW = new CommandNSW();
        ((PluginCommand) Objects.<PluginCommand>requireNonNull(getCommand("noshearwool"))).setExecutor(cmdNSW);
        ((PluginCommand)Objects.<PluginCommand>requireNonNull(getCommand("noshearwool"))).setTabCompleter(cmdNSW);
    }
    public void toggleEnabled() {
        this.active = !this.active;
    }

    public void enable(boolean setEnabled) {
        this.active = setEnabled;
    }

    public static Noshearwool getInstance() {
        return instance;
    }

    public boolean isActive() {
        return this.active;
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
