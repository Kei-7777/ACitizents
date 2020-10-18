package me.kei.citizens.automaton.tasks.transport;

import me.kei.citizens.automaton.tasks.Transport;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.minecraft.server.v1_15_R1.BlockPosition;
import net.minecraft.server.v1_15_R1.Blocks;
import net.minecraft.server.v1_15_R1.PacketPlayOutBlockAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetItemFromChest extends Transport {
    @Override
    public void call(NPC npc, int state) {
        Location loc = npc.getEntity().getLocation();
        Location chestloc = null;
        if (loc.clone().add(1, 0, 0).getBlock().getType() == Material.CHEST) {
            chestloc = loc.clone().add(1, 0, 0).getBlock().getLocation();
        } else if (loc.clone().add(-1, 0, 0).getBlock().getType() == Material.CHEST) {
            chestloc = loc.clone().add(-1, 0, 0).getBlock().getLocation();
        } else if (loc.clone().add(0, 0, 1).getBlock().getType() == Material.CHEST) {
            chestloc = loc.clone().add(0, 0, 1).getBlock().getLocation();
        } else if (loc.clone().add(0, 0, -1).getBlock().getType() == Material.CHEST) {
            chestloc = loc.clone().add(0, 0, -1).getBlock().getLocation();
        }
        if (chestloc == null) return;
        Chest chest = (Chest) chestloc.getBlock().getState();
        for (int j = 0; j < chest.getInventory().getSize(); j++) {
            if (chest.getInventory().getItem(j) == null || chest.getInventory().getItem(j).getType() == Material.AIR) {
                continue;
            }
            ItemStack item = chest.getInventory().getItem(j);
            Equipment equipTrait = npc.getTrait(Equipment.class);
            equipTrait.set(Equipment.EquipmentSlot.HAND, item);
            chest.getInventory().setItem(j, new ItemStack(Material.AIR));
            chestAnimation(false, chestloc);
            npc.data().set("state", 4);
            j = 999;
            break;
        }
    }

    private static void chestAnimation(boolean open, Location loc) {
        BlockPosition pos = new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        PacketPlayOutBlockAction packet = new PacketPlayOutBlockAction(pos, Blocks.CHEST, (byte) 1, open ? (byte) 1 : 0);
        for (Player player : Bukkit.getOnlinePlayers())
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}
