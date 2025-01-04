package lordostrov.myEarth;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CheckInventory {
    public static boolean hasEmeraldStack(Player player) {
        Inventory inventory = player.getInventory();
        int emeraldCount = 0;

        for (ItemStack item : inventory.getContents()) {
            if (item != null && item.getType() == Material.EMERALD) {
                emeraldCount += item.getAmount();
            }
        }

        return emeraldCount >= 64;
    }


    public static void removeEmeraldStack(Player player) {
        Inventory inventory = player.getInventory();
        int removed = 0;

        for (int i = 0; i < inventory.getSize(); i++){
            ItemStack item = inventory.getItem(i);
            if (item != null && item.getType() == Material.EMERALD){
                if (removed + item.getAmount() <= 64){
                    removed += item.getAmount();
                    inventory.clear(i);
                } else {
                    int amountToRemove = 64 - removed;
                    item.setAmount(item.getAmount() - amountToRemove);
                    removed = 64;
                    break;
                }
            }
        }
    }
}

