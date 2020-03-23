package net.ewenapps.mcplugins.sotshiweb.core;

import net.ewenapps.mcplugins.sotshiweb.SotshiWeb;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class WebStore {
    private File configFile;
    private YamlConfiguration configuration;
    private ArrayList<ImageMap> imageMaps;

    public WebStore() {
        this.configFile = new File(SotshiWeb.IMAGES_MAP_DIR, "websites.yml");
        this.configuration = YamlConfiguration.loadConfiguration(configFile);
    }

    public ImageMap load(String name) {
        final ConfigurationSection config = this.configuration.getConfigurationSection(name);

        final String uuidStr = config.getString("uuid");
        final String path = config.getString("path");
        final ArrayList<Short> ids = (ArrayList<Short>) config.getShortList("ids");

        return new ImageMap(UUID.fromString(uuidStr), path, ids);
    }


    public void write(ImageMap imageMap, String name) {
        ConfigurationSection config;
        try{
            config = this.configuration.createSection(name);
        } catch (Exception e) {
            e.printStackTrace();
            config = this.configuration.getConfigurationSection(name);
        }

        config.set("uuid", imageMap.getUuid().toString());
        config.set("path", imageMap.getPath());
        config.set("ids", imageMap.getMapIds());

        save();
    }

    private void save() {
        try{
            configuration.save(configFile);
        } catch (IOException e) {
            SotshiWeb.INSTANCE.getLogger().severe("Cannot save image map website config file: websites.yml");
        }
    }
}
