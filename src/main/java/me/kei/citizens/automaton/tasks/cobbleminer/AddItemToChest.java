package me.kei.citizens.automaton.tasks.cobbleminer;

import me.kei.citizens.automaton.tasks.CobbleMiner;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_15_R1.BlockPosition;
import net.minecraft.server.v1_15_R1.Blocks;
import net.minecraft.server.v1_15_R1.PacketPlayOutBlockAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class AddItemToChest extends CobbleMiner {
    @Override
    public void call(NPC npc, int state) {
        Location base = new Location(npc.getEntity().getWorld(), npc.data().get("base_x"), npc.data().get("base_y"), npc.data().get("base_z"));
        Location loc = npc.getEntity().getLocation();
        Location chestloc = null;
        if (loc.clone().add(1, 0, 0).getBlock().getType() == Material.TRAPPED_CHEST || loc.clone().add(1, 0, 0).getBlock().getType() == Material.CHEST) {
            chestloc = loc.clone().add(1, 0, 0).getBlock().getLocation();
        } else if (loc.clone().add(-1, 0, 0).getBlock().getType() == Material.TRAPPED_CHEST || loc.clone().add(-1, 0, 0).getBlock().getType() == Material.CHEST) {
            chestloc = loc.clone().add(-1, 0, 0).getBlock().getLocation();
        } else if (loc.clone().add(0, 0, 1).getBlock().getType() == Material.TRAPPED_CHEST || loc.clone().add(0, 0, 1).getBlock().getType() == Material.CHEST) {
            chestloc = loc.clone().add(0, 0, 1).getBlock().getLocation();
        } else if (loc.clone().add(0, 0, -1).getBlock().getType() == Material.TRAPPED_CHEST || loc.clone().add(0, 0, -1).getBlock().getType() == Material.CHEST) {
            chestloc = loc.clone().add(0, 0, -1).getBlock().getLocation();
        }
        if (chestloc == null) return;
        Chest chest = (Chest) chestloc.getBlock().getState();
        if(checkInventoryRemain(chest, new ItemStack(Material.COBBLESTONE))) {
            chest.getInventory().addItem(new ItemStack(Material.COBBLESTONE));
            chestAnimation(false, chestloc);
            npc.data().set("state", 1);
            npc.getNavigator().setTarget(base);
        }
    }

    private static void chestAnimation(boolean open, Location loc) {
        BlockPosition pos = new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        PacketPlayOutBlockAction packet = new PacketPlayOutBlockAction(pos, Blocks.CHEST, (byte) 1, open ? (byte) 1 : 0);
        for (Player player : Bukkit.getOnlinePlayers())
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    private boolean checkInventoryRemain(Chest chest, ItemStack itemStack) {
        Inventory inv = Bukkit.createInventory(null, chest.getInventory().getSize());
        for (int j = 0; j < chest.getInventory().getSize(); j++) {
            inv.setItem(j, chest.getInventory().getItem(j));
        }

        Map<Integer, ItemStack> remainingItems = inv.addItem(itemStack);
        return remainingItems.size() < 1;
    }
}
