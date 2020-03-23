package net.ewenapps.mcplugins.sotshiweb.core;

import net.ewenapps.mcplugins.sotshiweb.SotshiWeb;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;

import java.util.List;

public class MapDisplay {
    private int screenWidth, screenHeight;
    private ImageMap imageMap;

    public MapDisplay(ImageMap imageMap) {
        this.imageMap = imageMap;
    }

    public void display(BlockFace directionFacing, Location coordinates) {
        World world = coordinates.getWorld();
        world.getBlockAt(coordinates).breakNaturally();
        ItemFrame itemFrame;
        int index = 0;
        for(int y = 0; y < 9; y++) {
            for(int x = 15; x >= 0; x--) {
                Location transfer = new Location(world, coordinates.getX(), coordinates.getY(), coordinates.getZ());
                transfer.add(x, 9 - y, 0);

                ItemStack item = new ItemStack(Material.FILLED_MAP);
                MapMeta meta = (MapMeta) item.getItemMeta();
                meta.setMapId(imageMap.getMapIds().get(index));
                item.setItemMeta(meta);

                //itemFrame = (ItemFrame) world.spawn(transfer, ItemFrame.class);
                //itemFrame.setItem(item);
                SotshiWeb.INSTANCE.getLogger().info("Getting entities to place map at " + x + "," + y);
                List<Entity> nearbyEntities = (List<Entity>) transfer.getWorld().getEntities();
                for (Entity entity : nearbyEntities) {
                    if(entity.getLocation().distance(transfer) <= 1.5) {
                        SotshiWeb.INSTANCE.getLogger().info("Got entity near location");
                        if(entity instanceof ItemFrame) {
                            SotshiWeb.INSTANCE.getLogger().info("Got item frame");
                            ((ItemFrame) entity).setItem(item);
                        }
                    }
                }



                index++;
            }
        }

    }
}
