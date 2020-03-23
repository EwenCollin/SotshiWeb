package net.ewenapps.mcplugins.sotshiweb.helpers;

import net.ewenapps.mcplugins.sotshiweb.SotshiWeb;
import net.ewenapps.mcplugins.sotshiweb.core.ImageMap;
import net.ewenapps.mcplugins.sotshiweb.core.ImageMapYML;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.map.MapView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

public class ImageHelper {

    public static boolean isURL(String path) {
        return path.startsWith("http://") || path.startsWith("https://");
    }

    public static BufferedImage getImage(String path) throws IOException {
        if(isURL(path)) {
            final URL url = new URL(path);

            return ImageIO.read(url);
        } else{
            final File imageFile = new File(path);

            if(imageFile.exists()) {
                return ImageIO.read(imageFile);
            } else {
                throw new IOException("Specified image not found");
            }
        }
    }

    public static ImageMap createImageMap(BufferedImage image, String path, Player player) {
        short maxWidth = 128*16;
        short maxHeight = 128*9;

        final ArrayList<Short> mapsIds = new ArrayList<>();

        int height = image.getHeight();
        int width = image.getWidth();
        if(height > maxHeight) height = maxHeight;
        if(width > maxWidth) width = maxWidth;
        final int rows = 1 + height / 128;
        final int cols = 1 + width / 128;

        MapView map;
        int w = 0, h = 0;
        for(int y = 0; y < rows; y++) {
            for(int x = 0; x < cols; x++) {
                map = Bukkit.createMap(player.getWorld());

                map = RenderHelper.resetRenderers(map);

                map.setScale(MapView.Scale.FARTHEST);
                map.setUnlimitedTracking(false);

                if(x*128 + 128 > width) w = width - x*128;
                else w = 128;
                if(y*128 + 128 > height) h = height - y*128;
                else h = 128;
                if (h != 0 && w != 0) {
                    mapsIds.add((short) map.getId());
                }
            }
        }
        final ImageMap imageMap = new ImageMap(UUID.randomUUID(), path, mapsIds);
        final ImageMapYML imageMapYML = new ImageMapYML(imageMap.getUuid());

        imageMapYML.write(imageMap);

        SotshiWeb.IMAGE_MAP_MANAGER.addImageMap(imageMap);

        return imageMap;


    }
}
