package net.ewenapps.mcplugins.sotshiweb.core;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.awt.image.BufferedImage;

public class ImageMapRenderer extends MapRenderer {
    private boolean shouldRender;
    private BufferedImage image;

    public ImageMapRenderer(BufferedImage image, boolean shouldRender) {
        this.image = image;
        this.shouldRender = shouldRender;
    }


    @Override
    public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
        if(shouldRender) {
            mapCanvas.drawImage(0, 0, image);
            shouldRender = false;
        }
    }

    public void setShouldRender(boolean shouldRender) {
        this.shouldRender = shouldRender;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }


}
