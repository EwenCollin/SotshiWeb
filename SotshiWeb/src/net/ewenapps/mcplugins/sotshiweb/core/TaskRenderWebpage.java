package net.ewenapps.mcplugins.sotshiweb.core;

import net.ewenapps.mcplugins.sotshiweb.SotshiWeb;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;

public class TaskRenderWebpage extends BukkitRunnable {
    private Player player;

    private String path;

    public TaskRenderWebpage(Player player, String path) {
        this.player = player;
        this.path = path;
    }


    @Override
    public void run() {
        String url = path.replaceAll(" ", "%20")
                .replaceAll("\\?", "%3F")
                .replaceAll(",", "%2C")
                .replaceAll("\\+", "%2B")
                .replaceAll("#", "%23")
                .replaceAll("\\$", "%24")
                .replaceAll("&", "%26")
                .replaceAll("=", "%3D")
                .replaceAll(">", "%3E")
                .replaceAll("<", "%3C")
                .replaceAll("@", "%40")
                .replaceAll(";", "%3B")
                .replaceAll("/", "%2F")
                .replaceAll(":", "%3A");
        String uriWebscreenshot = "image.png";
        if (path.startsWith("https:")) {
            uriWebscreenshot = path.replaceAll("://", "_")
                    .replaceFirst("/", "_443_")
                    .replaceAll("/", "_")
                    .replaceAll("%", "_")
                    .replaceAll(":", "_");
            uriWebscreenshot += ".png";
        } else {
            uriWebscreenshot = path.replaceAll("://", "_")
                    .replaceFirst("/", "_80_")
                    .replaceAll("/", "_")
                    .replaceAll("%", "_")
                    .replaceAll(":", "_");
            uriWebscreenshot += ".png";
        }

        try {

            player.sendMessage(ChatColor.YELLOW + "Getting screenshot of page " + path + ", server may lag a lot");

            int width = 640, height = 640;



            Process pr = Runtime.getRuntime().exec("webscreenshot.bat " + path + " \"" + width + "," + height + "\"");
            pr.waitFor();
            player.sendMessage(ChatColor.GREEN + "Screenshot saved");
            player.sendMessage(ChatColor.YELLOW + "Loading screenshot");
            new TaskRenderImage(player, "screenshots/" + uriWebscreenshot).runTask(SotshiWeb.INSTANCE);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
