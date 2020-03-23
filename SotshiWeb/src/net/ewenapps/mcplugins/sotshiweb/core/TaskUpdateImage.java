package net.ewenapps.mcplugins.sotshiweb.core;

import net.ewenapps.mcplugins.sotshiweb.SotshiWeb;
import net.ewenapps.mcplugins.sotshiweb.helpers.ImageHelper;
import net.ewenapps.mcplugins.sotshiweb.helpers.RenderHelper;
import org.bukkit.Bukkit;
import org.bukkit.map.MapView;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class TaskUpdateImage extends BukkitRunnable {

    private ImageMap imageMap;
    short maxWidth = 128*16;
    short maxHeight = 128*9;

    public TaskUpdateImage(ImageMap imageMap) {
        this.imageMap = imageMap;
    }

    @Override
    public void run() {
        try {

            final BufferedImage image = ImageHelper.getImage(imageMap.getPath());
            int height = image.getHeight();
            int width = image.getWidth();
            if(height > maxHeight) height = maxHeight;
            if(width > maxWidth) width = maxWidth;
            final int rows = 1 + height / 128;
            final int cols = 1 + width / 128;

            MapView map;
            int index = 0;
            int w = 0, h = 0;
            for(int y = 0; y < rows; y++) {
                for(int x = 0; x < cols; x++) {
                    if (index < imageMap.getMapIds().size()) {
                        map = Bukkit.getMap(imageMap.getMapIds().get(index));

                        map = RenderHelper.resetRenderers(map);

                        map.setScale(MapView.Scale.FARTHEST);
                        map.setUnlimitedTracking(false);
                        if(x*128 + 128 > width) w = width - x*128;
                        else w = 128;
                        if(y*128 + 128 > height) h = height - y*128;
                        else h = 128;
                        if (h != 0 && w != 0) {
                            map.addRenderer(new ImageMapRenderer(image.getSubimage(x * 128, y * 128, 128, 128), true));
                            index++;
                        }
                    }
                }
            }


        } catch (IOException e) {
            SotshiWeb.INSTANCE.getLogger().warning("Cannot load image from " + imageMap.getPath());
            SotshiWeb.INSTANCE.getLogger().warning(e.getMessage());
        }
    }
}
