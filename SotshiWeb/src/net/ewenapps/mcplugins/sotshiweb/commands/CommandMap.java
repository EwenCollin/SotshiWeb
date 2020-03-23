package net.ewenapps.mcplugins.sotshiweb.commands;

import net.ewenapps.mcplugins.sotshiweb.SotshiWeb;
import net.ewenapps.mcplugins.sotshiweb.core.TaskRenderImage;
import net.ewenapps.mcplugins.sotshiweb.core.TaskRenderWebpage;
import net.ewenapps.mcplugins.sotshiweb.core.TaskSaveWebpage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandMap implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("map")) {
            if (sender instanceof Player) {
                if(args.length == 2) {
                    if(args[0].equalsIgnoreCase("render")) {
                        final Player player = (Player)sender;
                        final String path = args[1];

                        sender.sendMessage(ChatColor.YELLOW + "Command received, starting execution");
                        new TaskRenderImage(player, path).runTask(SotshiWeb.INSTANCE);
                    }

                } else {
                    sender.sendMessage(ChatColor.RED + "Incorrect number of arguments");
                }
            }
            return true;
        }
        if(label.equalsIgnoreCase("web")) {
            if (sender instanceof Player) {
                if(args.length == 2) {
                    if(args[0].equalsIgnoreCase("render")) {
                        final Player player = (Player)sender;
                        final String path = args[1];

                        sender.sendMessage(ChatColor.YELLOW + "Command received, starting execution");

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                new TaskRenderWebpage(player, path).runTask(SotshiWeb.INSTANCE);
                                sender.sendMessage(ChatColor.YELLOW + "Thread finished");
                            }
                        }).start();
                        sender.sendMessage(ChatColor.YELLOW + "Continue server execution");
                    }

                } else if (args.length == 3) {
                    if(args[0].equalsIgnoreCase("save")) {
                        final Player player = (Player)sender;
                        final String path = args[2];
                        final String name = args[1];

                        sender.sendMessage(ChatColor.YELLOW + "Command received, starting execution");

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                new TaskSaveWebpage(player, path, name).runTask(SotshiWeb.INSTANCE);
                                sender.sendMessage(ChatColor.YELLOW + "Thread finished");
                            }
                        }).start();
                        sender.sendMessage(ChatColor.YELLOW + "Continue server execution");


                    }
                } else{
                    sender.sendMessage(ChatColor.RED + "Incorrect number of arguments");
                }
            }
            return true;
        }

        return false;
    }
}
