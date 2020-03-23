package net.ewenapps.mcplugins.sotshiweb.core;

import net.ewenapps.mcplugins.sotshiweb.SotshiWeb;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class ImageMapYML {

    private UUID imageMapUUID;
    private File configFile;
    private YamlConfiguration yamlConfiguration;

    public ImageMapYML(UUID imageMapUUID) {
        this.imageMapUUID = imageMapUUID;
        this.configFile = new File(SotshiWeb.IMAGES_MAP_DIR, imageMapUUID.toString() + ".yml");
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    }

    public void write(ImageMap imageMap) {
        final ConfigurationSection config = this.yamlConfiguration.createSection("image");

        config.set("uuid", imageMap.getUuid().toString());
        config.set("path", imageMap.getPath());
        config.set("ids", imageMap.getMapIds());

        save();
    }

    public ImageMap read() {
        final ConfigurationSection config = this.yamlConfiguration.getConfigurationSection("image");

        final String uuidStr = config.getString("uuid");
        final String path = config.getString("path");
        final ArrayList<Short> ids = (ArrayList<Short>) config.getShortList("ids");

        return new ImageMap(UUID.fromString(uuidStr), path, ids);

    }

    private void save() {
        try{
            yamlConfiguration.save(configFile);
        } catch (IOException e) {
            SotshiWeb.INSTANCE.getLogger().severe("Cannot save image map config file: " + imageMapUUID.toString() + ".yml");
        }
    }
}
