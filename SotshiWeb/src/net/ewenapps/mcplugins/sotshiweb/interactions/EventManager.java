package net.ewenapps.mcplugins.sotshiweb.interactions;

import net.ewenapps.mcplugins.sotshiweb.SotshiWeb;
import net.ewenapps.mcplugins.sotshiweb.core.ImageMap;
import net.ewenapps.mcplugins.sotshiweb.core.MapDisplay;
import net.ewenapps.mcplugins.sotshiweb.core.TaskUpdateImage;
import net.ewenapps.mcplugins.sotshiweb.core.WebStore;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.material.Sign;

public class EventManager implements Listener {
    @EventHandler
    public void onSignChange(SignChangeEvent event){
        String message = event.getLine(0);
        SotshiWeb.INSTANCE.getLogger().info("Sign message is " + message);

        if (message.startsWith(".")) {
            String savedSite = message.substring(1, message.length());
            Sign sign = (Sign) event.getBlock().getState().getData();
            BlockFace directionFacing = sign.getAttachedFace();
            Location location = event.getBlock().getLocation();
            WebStore webStore = new WebStore();
            try {
                ImageMap imageMap = webStore.load(savedSite);
                new TaskUpdateImage(imageMap).runTask(SotshiWeb.INSTANCE);
                MapDisplay mapDisplay = new MapDisplay(imageMap);
                mapDisplay.display(directionFacing, location);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
