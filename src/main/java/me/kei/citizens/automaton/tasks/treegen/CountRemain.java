package me.kei.citizens.automaton.tasks.treegen;

import me.kei.citizens.automaton.tasks.TreeGen;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Dropper;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CountRemain extends TreeGen {
    @Override
    public void call(NPC npc, int state) {
        Location base = npc.getStoredLocation();

        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 10);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);

        if (base.clone().add(1, 0, 0).getBlock().getType() == Material.DROPPER) {
            Location c = base.clone().add(1, 0, 0);
            if (c.getBlock().getType() != Material.DROPPER) {
                npc.data().set("state", 1);
                return;
            }
            Dropper dropper = (Dropper) c.getBlock().getState();
            Inventory inv = dropper.getInventory();
            if (inv.getItem(0) == null || inv.getItem(0).getType() == Material.AIR || inv.getItem(0).getType() != Material.GRAY_STAINED_GLASS_PANE) {
                npc.data().set("state", 1);
                return;
            }
            if (inv.getItem(0).getAmount() == 1) {
                inv.clear();
                npc.data().set("state", 3);
            } else {
                item.setAmount(inv.getItem(0).getAmount() - 1);
            }
            for (int i = 0; i < 9; i++) {
                inv.setItem(i, item);
            }
        } else if (base.clone().add(-1, 0, 0).getBlock().getType() == Material.DROPPER) {
            Location c = base.clone().add(-1, 0, 0);
            if (c.getBlock().getType() != Material.DROPPER) {
                npc.data().set("state", 1);
                return;
            }
            Dropper dropper = (Dropper) c.getBlock().getState();
            Inventory inv = dropper.getInventory();
            if (inv.getItem(0) == null || inv.getItem(0).getType() == Material.AIR || inv.getItem(0).getType() != Material.GRAY_STAINED_GLASS_PANE) {
                npc.data().set("state", 1);
                return;
            }
            if (inv.getItem(0).getAmount() == 1) {
                inv.clear();
                npc.data().set("state", 3);
            } else {
                item.setAmount(inv.getItem(0).getAmount() - 1);
            }
            for (int i = 0; i < 9; i++) {
                inv.setItem(i, item);
            }
        } else if (base.clone().add(0, 0, 1).getBlock().getType() == Material.DROPPER) {
            Location c = base.clone().add(0, 0, 1);
            if (c.getBlock().getType() != Material.DROPPER) {
                npc.data().set("state", 1);
                return;
            }
            Dropper dropper = (Dropper) c.getBlock().getState();
            Inventory inv = dropper.getInventory();
            if (inv.getItem(0) == null || inv.getItem(0).getType() == Material.AIR || inv.getItem(0).getType() != Material.GRAY_STAINED_GLASS_PANE) {
                npc.data().set("state", 1);
                return;
            }
            if (inv.getItem(0).getAmount() == 1) {
                inv.clear();
                npc.data().set("state", 3);
            } else {
                item.setAmount(inv.getItem(0).getAmount() - 1);
            }
            for (int i = 0; i < 9; i++) {
                inv.setItem(i, item);
            }
        } else if (base.clone().add(0, 0, -1).getBlock().getType() == Material.DROPPER) {
            Location c = base.clone().add(0, 0, -1);
            if (c.getBlock().getType() != Material.DROPPER) {
                npc.data().set("state", 1);
                return;
            }
            Dropper dropper = (Dropper) c.getBlock().getState();
            Inventory inv = dropper.getInventory();
            if (inv.getItem(0) == null || inv.getItem(0).getType() == Material.AIR || inv.getItem(0).getType() != Material.GRAY_STAINED_GLASS_PANE) {
                npc.data().set("state", 1);
                return;
            }
            if (inv.getItem(0).getAmount() == 1) {
                inv.clear();
                npc.data().set("state", 3);
            } else {
                item.setAmount(inv.getItem(0).getAmount() - 1);
            }
            for (int i = 0; i < 9; i++) {
                inv.setItem(i, item);
            }
        }
    }
}
