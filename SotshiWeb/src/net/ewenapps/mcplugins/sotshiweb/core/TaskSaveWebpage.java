package net.ewenapps.mcplugins.sotshiweb.core;

import net.ewenapps.mcplugins.sotshiweb.helpers.ImageHelper;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;

public class TaskSaveWebpage extends BukkitRunnable {
    private Player player;

    private String path;
    private String name;

    public TaskSaveWebpage(Player player, String path, String name) {
        this.player = player;
        this.path = path;
        this.name = name;
    }


    @Override
    public void run() {
        String uriWebscreenshot = "image.png";
        if (path.startsWith("https:")) {
            uriWebscreenshot = path.replaceAll("://", "_")
                    .replaceFirst("/", "_443_")
                    .replaceAll("/", "_")
                    .replaceAll("%", "_")
                    .replaceAll(":", "_")
                    .replaceAll("\\?", "_")
                    .replaceAll("=", "_")
                    .replaceAll("\\+", "_")
                    .replaceAll("@", "_")
                    .replaceAll(",", "_")
                    .replaceAll("&", "_");
            uriWebscreenshot += ".png";
        } else {
            uriWebscreenshot = path.replaceAll("://", "_")
                    .replaceFirst("/", "_80_")
                    .replaceAll("/", "_")
                    .replaceAll("%", "_")
                    .replaceAll(":", "_")
                    .replaceAll("\\?", "_")
                    .replaceAll("=", "_")
                    .replaceAll("\\+", "_")
                    .replaceAll("@", "_")
                    .replaceAll(",", "_")
                    .replaceAll("&", "_");
            uriWebscreenshot += ".png";
        }

        try {

            player.sendMessage(ChatColor.YELLOW + "Getting screenshot of page " + path + ", server may lag a lot");

            int width = 128*16, height = 128*9;



            Process pr = Runtime.getRuntime().exec("webscreenshot.bat \"" + path + "\" \"" + width + "," + height + "\"");
            pr.waitFor();

            player.sendMessage(ChatColor.GREEN + "Screenshot saved");
            player.sendMessage(ChatColor.YELLOW + "Loading screenshot at screenshots/" + uriWebscreenshot);

            WebStore webStore = new WebStore();
            webStore.write(ImageHelper.createImageMap(ImageHelper.getImage("screenshots/" + uriWebscreenshot), "screenshots/" + uriWebscreenshot, player), name);
            player.sendMessage(ChatColor.GREEN + "Screenshot loaded");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
