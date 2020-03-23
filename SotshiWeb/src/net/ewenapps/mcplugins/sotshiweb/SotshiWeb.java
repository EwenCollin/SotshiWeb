package net.ewenapps.mcplugins.sotshiweb;

import net.ewenapps.mcplugins.sotshiweb.commands.CommandMap;
import net.ewenapps.mcplugins.sotshiweb.core.DataLoader;
import net.ewenapps.mcplugins.sotshiweb.core.ImageMapManager;
import net.ewenapps.mcplugins.sotshiweb.interactions.EventManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class SotshiWeb extends JavaPlugin {


    public static File IMAGES_DIR;

    public static File IMAGES_MAP_DIR;

    public static Plugin INSTANCE;

    public static ImageMapManager IMAGE_MAP_MANAGER;

    public static EventManager eventManager;



    @Override
    public void onEnable() {

        INSTANCE = this;

        IMAGES_DIR = new File(getDataFolder(), "images");

        IMAGES_MAP_DIR = new File(getDataFolder(), "imagemaps");

        IMAGE_MAP_MANAGER = new ImageMapManager();

        getLogger().info("Hello there");

        eventManager = new EventManager();
        getServer().getPluginManager().registerEvents(new EventManager(), this);

        getCommand("map").setExecutor(new CommandMap());
        getCommand("web").setExecutor(new CommandMap());


        try {
            getLogger().info("Loading image maps...");
            DataLoader.loadMaps();
        } catch (IOException e) {
            SotshiWeb.INSTANCE.getLogger().warning(e.getMessage());
        }

    }

    @Override
    public void onDisable(){
        getLogger().info("SotshiWeb was properly stopped");
    }
}
