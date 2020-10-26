package me.kei.citizens.automaton.tasks.treegen;

import me.kei.citizens.automaton.listener.TreeNPCListener;
import me.kei.citizens.automaton.tasks.TreeGen;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Dropper;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ResetDropperInventory extends TreeGen {

    @Override
    public void call(NPC npc, int state) {
        Location base = npc.getStoredLocation();

        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 10);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);

        if (base.clone().add(1, 0, 0).getBlock().getType() == Material.DROPPER) {
            Location c = base.clone().add(1, 0, 0);
            Dropper dropper = (Dropper) c.getBlock().getState();
            Inventory inv = dropper.getInventory();
            inv.clear();
            for (int i = 0; i < 9; i++) {
                inv.setItem(i, item);
            }
            npc.data().set("state", 2);
        } else if (base.clone().add(-1, 0, 0).getBlock().getType() == Material.DROPPER) {
            Location c = base.clone().add(-1, 0, 0);
            Dropper dropper = (Dropper) c.getBlock().getState();
            Inventory inv = dropper.getInventory();
            inv.clear();
            for (int i = 0; i < 9; i++) {
                inv.setItem(i, item);
            }
            npc.data().set("state", 2);
        } else if (base.clone().add(0, 0, 1).getBlock().getType() == Material.DROPPER) {
            Location c = base.clone().add(0, 0, 1);
            Dropper dropper = (Dropper) c.getBlock().getState();
            Inventory inv = dropper.getInventory();
            inv.clear();
            for (int i = 0; i < 9; i++) {
                inv.setItem(i, item);
            }
            npc.data().set("state", 2);
        } else if (base.clone().add(0, 0, -1).getBlock().getType() == Material.DROPPER) {
            Location c = base.clone().add(0, 0, -1);
            Dropper dropper = (Dropper) c.getBlock().getState();
            Inventory inv = dropper.getInventory();
            inv.clear();
            for (int i = 0; i < 9; i++) {
                inv.setItem(i, item);
            }
            npc.data().set("state", 2);
        }
    }
}
