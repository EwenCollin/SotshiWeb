package net.ewenapps.mcplugins.sotshiweb.core;

import net.ewenapps.mcplugins.sotshiweb.SotshiWeb;
import net.ewenapps.mcplugins.sotshiweb.helpers.ImageHelper;
import net.ewenapps.mcplugins.sotshiweb.helpers.RenderHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.map.MapView;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class TaskRenderImage extends BukkitRunnable {


    private short maxWidth = 512 + 128;
    private short maxHeight = 512 + 128;
    private Player player;

    private String path;

    public TaskRenderImage(Player player, String path) {
        this.player = player;
        this.path = path;
    }

    @Override
    public void run() {
        try {

            final ArrayList<Short> mapsIds = new ArrayList<>();

            player.sendMessage(ChatColor.YELLOW + "Downloading image...");
            final BufferedImage image = ImageHelper.getImage(path);
            player.sendMessage(ChatColor.GREEN + "Image successfully downloaded");
            player.sendMessage(ChatColor.BLUE + "Converting image to maps...");
            player.sendMessage("Image ("+ path +") - (" + image.getWidth() +"x"+image.getHeight() + ") pixels");
            int height = image.getHeight();
            int width = image.getWidth();
            if(height > maxHeight) height = maxHeight;
            if(width > maxWidth) width = maxWidth;
            final int rows = 1 + height / 128;
            final int cols = 1 + width / 128;
            player.sendMessage("Needs " + rows*cols + " maps to render (" + cols + "x" + rows + ") maps");

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
                        map.addRenderer(new ImageMapRenderer(image.getSubimage(x*128, y*128, w, h), true));
                        player.sendMessage("Rendered map (" + x + "," + y + ")");
                        mapsIds.add((short) map.getId());
                    }
                }
            }
            player.sendMessage(ChatColor.GREEN + "Rendered all maps");
            /*
            player.sendMessage(ChatColor.YELLOW + "Giving you map items");
            for(short id : mapsIds) {
                ItemStack item = new ItemStack(Material.FILLED_MAP);
                MapMeta meta = (MapMeta) item.getItemMeta();
                meta.setMapId(id);
                item.setItemMeta(meta);

                player.getInventory().addItem(item);
            }*/

            final ImageMap imageMap = new ImageMap(UUID.randomUUID(), path, mapsIds);
            final ImageMapYML imageMapYML = new ImageMapYML(imageMap.getUuid());

            imageMapYML.write(imageMap);

            SotshiWeb.IMAGE_MAP_MANAGER.addImageMap(imageMap);


        } catch (IOException e) {
            player.sendMessage(ChatColor.RED + "Cannot load image from " + path);
            SotshiWeb.INSTANCE.getLogger().warning("Cannot load image from " + path);
            SotshiWeb.INSTANCE.getLogger().warning(e.getMessage());
        }
    }
}
