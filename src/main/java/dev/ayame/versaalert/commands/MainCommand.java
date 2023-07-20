package dev.ayame.versaalert.commands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import dev.ayame.versaalert.handlers.dataHandler;
import dev.ayame.versaalert.VersaAlert;
public class MainCommand implements CommandExecutor {
    @SuppressWarnings("deprecated")
    @Override
    public boolean onCommand(CommandSender sender, @org.jetbrains.annotations.NotNull Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("versaalert")) {
            if(args.length > 0) {
                if(args[0].equals("reload")) {
                    if(sender instanceof Player) {
                        Player player = (Player) sender;
                        if(player.hasPermission("versaalert.admin")) {
                            dataHandler.INSTANCE.updateConfig();
                            player.sendMessage(dataHandler.INSTANCE.getConfig("versaalert.messages.reload", true).toString());
                        } else {
                            player.sendMessage(dataHandler.INSTANCE.getConfig("versaalert.messages.no-permission", true).toString());
                        }
                    } else {
                        dataHandler.INSTANCE.updateConfig();
                        sender.sendMessage(dataHandler.INSTANCE.getConfig("versaalert.messages.reload", true).toString());
                    }
                } else if(args[0].equals("version")) {
                    if(sender instanceof Player) {
                        Player player = (Player) sender;
                        player.sendMessage("§fYou are running version §b" + VersaAlert.INSTANCE.getDescription().getVersion() + "§fof versaalert");
                    } else {
                        sender.sendMessage("§fYou are running version §b" + VersaAlert.INSTANCE.getDescription().getVersion() + "§fof versaalert");

                    }
                }
            } else {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage("§fAvailable commands:\n§b/versaalert - §fShows this message\n§b/versaalert reload - §fReloads the plugin\n§b/versaalert version - §fGet the version of the plugin");
                } else {
                    sender.sendMessage("§fAvailable commands:\n§b/versaalert - §fShows this message\n§b/versaalert reload - §fReloads the plugin\n§b/versaalert version - §fGet the version of the plugin");

                }
            }
        }
        return true;
    }
}

