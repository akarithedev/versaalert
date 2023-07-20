package dev.ayame.versaalert.events;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerPickupItemEvent;
import dev.ayame.versaalert.handlers.dataHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import dev.ayame.versaalert.VersaAlert;
public class PlayerPickupItem implements Listener {
    @EventHandler
    public void onPlayerPickupItem(final PlayerPickupItemEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                final Player player = event.getPlayer();
                if (!canPickUpItems(player, event.getItem().getItemStack())) {
                    player.sendTitle(dataHandler.INSTANCE.getConfig("versaalert.player.inventory.full.title", true).toString(), dataHandler.INSTANCE.getConfig("versaalert.player.inventory.full.subtitle", true).toString());
                }
            }
        }.runTaskLater(VersaAlert.INSTANCE, 1);
    }
    private boolean canPickUpItems(final Player player, final ItemStack item) {
        for (final ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack == null) return true;

            if (itemStack.getType().equals(item.getType())) {
                player.sendMessage(itemStack.getAmount() + " - " + item.getMaxStackSize());

                if (itemStack.getAmount() < item.getMaxStackSize()) return true;
            }
        }
        return false;
    }

}
