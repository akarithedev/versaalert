package dev.ayame.versaalert.events;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerPickupItemEvent;
import dev.ayame.versaalert.handlers.dataHandler;
import org.bukkit.inventory.ItemStack;
import dev.ayame.versaalert.VersaAlert;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerPickupItem implements Listener {
    private final VersaAlert main;

    public PlayerPickupItem(final VersaAlert main) {
        this.main = main;
    }
    @EventHandler
    public void onPlayerPickupItem(final PlayerPickupItemEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                final Player player = event.getPlayer();
                if (canNotPickUpItems(player)) {
                    player.sendTitle(dataHandler.INSTANCE.getConfig("versaalert.player.inventory.full.title", true).toString(),
                            dataHandler.INSTANCE.getConfig("versaalert.player.inventory.full.subtitle", true).toString(),
                            10, 10, 10
                    );
                }
            }
        }.runTaskLater(main, 1);
    }
    private boolean canNotPickUpItems(final Player player) {
        for (final ItemStack itemStack : player.getInventory().getStorageContents()) {
            if (itemStack == null || itemStack.getAmount() < itemStack.getMaxStackSize()) return false;
        }
        return true;
    }

}
