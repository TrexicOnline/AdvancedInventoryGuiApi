package de.trexic.inventoryguiapi;

import de.trexic.inventoryguiapi.internal.listener.inventoryGui.InventoryClick;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class InventoryGuiApi extends JavaPlugin {
    private static InventoryGuiApi instance;

    @Override
    public void onEnable() {
        instance = this;

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new InventoryClick(), this);
    }

    public static InventoryGuiApi getInstance(){
        return instance;
    }
}