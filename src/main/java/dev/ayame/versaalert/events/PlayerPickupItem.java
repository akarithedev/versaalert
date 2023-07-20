package dev.ayame.versaalert.events;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerPickupItemEvent;
import dev.ayame.versaalert.handlers.dataHandler;
import org.bukkit.inventory.ItemStack;

public class PlayerPickupItem implements Listener {
    @EventHandler
    @SuppressWarnings("deprecated")
    public void onPlayerPickupItem(final PlayerPickupItemEvent event) {
       final Player player = event.getPlayer();
        if (!canPickUpItems(player, event.getItem().getItemStack())) {
            dataHandler.INSTANCE.runTask(()-> player.sendTitle(dataHandler.INSTANCE.getConfig("versaalert.player.inventory.full.title", true).toString(), dataHandler.INSTANCE.getConfig("versaalert.player.inventory.full.subtitle", true).toString()));
        }
    }
    private boolean canPickUpItems(final Player player, final ItemStack item) {
        boolean rtn = false;

        for (final ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack == null) return true;

            if (itemStack.getType().equals(item.getType())) {
                if (itemStack.getAmount() == itemStack.getMaxStackSize() - item.getAmount()) {
                    rtn = false;
                    continue;
                }
                rtn = true;
            }
        }
        return rtn;
    }

}
