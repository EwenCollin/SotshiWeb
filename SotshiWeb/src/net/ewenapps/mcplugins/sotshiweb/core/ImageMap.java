package net.ewenapps.mcplugins.sotshiweb.core;

import java.util.ArrayList;
import java.util.UUID;

public class ImageMap {

    private UUID uuid;

    private String path;

    private ArrayList<Short> mapIds;

    public ImageMap(UUID uuid, String path, ArrayList<Short> mapIds) {
        this.uuid = uuid;
        this.path = path;
        this.mapIds = mapIds;
    }


    public UUID getUuid() {
        return uuid;
    }

    public String getPath() {
        return path;
    }

    public ArrayList<Short> getMapIds() {
        return mapIds;
    }
}
