package rurik.noshearwool;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;

public class BlockDamageListener implements Listener {
    private final Noshearwool nsw;

    public BlockDamageListener(Noshearwool nsw) {
        this.nsw = nsw;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockDamage(BlockDamageEvent event) {
        if (!this.nsw.isActive())
            return;
        ItemStack currentItem = event.getItemInHand();
        if (currentItem == null)
            return;
        if (currentItem.getType() == Material.SHEARS){
            Block block = event.getBlock();
            if (nsw.getWoolTypes().contains(block.getBlockData().getMaterial())) {
                event.setCancelled(true);
                return;
            }
        }
    }
}
