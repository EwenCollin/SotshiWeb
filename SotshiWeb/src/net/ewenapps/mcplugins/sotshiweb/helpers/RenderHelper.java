package net.ewenapps.mcplugins.sotshiweb.helpers;

import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.util.Iterator;

public class RenderHelper {
    public static MapView resetRenderers(MapView map) {
        final Iterator<MapRenderer> iterator = map.getRenderers().iterator();

        while(iterator.hasNext()) {
            map.removeRenderer(iterator.next());
        }

        return map;
    }
}
