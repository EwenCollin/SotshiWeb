package net.ewenapps.mcplugins.sotshiweb.core;

import net.ewenapps.mcplugins.sotshiweb.SotshiWeb;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class DataLoader {
    public static void loadMaps() throws IOException{
        final File imagesDir = SotshiWeb.IMAGES_DIR;
        final File imagesMapDir = SotshiWeb.IMAGES_MAP_DIR;

        if(!imagesDir.exists()) {
            if(!imagesDir.mkdirs()) {
                throw new IOException("Cannot create images directory");
            }
        }
        if(!imagesMapDir.exists()) {
            if(!imagesMapDir.mkdirs()) {
                throw new IOException("Cannot create image maps directory");
            }
        }

        final File[] files = imagesMapDir.listFiles();

        if (files != null) {
            ImageMap imageMap;
            ImageMapYML imageMapYML;

            for(File file : files) {
                if(file.getName().endsWith(".yml")) {
                    imageMapYML = new ImageMapYML(UUID.fromString(file.getName().replaceAll(".yml", "")));
                    imageMap = imageMapYML.read();

                    SotshiWeb.IMAGE_MAP_MANAGER.addImageMap(imageMap);
                    new TaskUpdateImage(imageMap).runTask(SotshiWeb.INSTANCE);
                }
            }
        }
    }
}
