package me.kei.citizens.automaton.tasks.transport;

import me.kei.citizens.automaton.tasks.Transport;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_15_R1.BlockPosition;
import net.minecraft.server.v1_15_R1.Blocks;
import net.minecraft.server.v1_15_R1.PacketPlayOutBlockAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CheckTargetChest extends Transport {
    @Override
    public void call(NPC npc, int state) {
        Location loc = npc.getEntity().getLocation();
        if (loc.clone().add(1, 0, 0).getBlock().getType() == Material.TRAPPED_CHEST) {
            chestAnimation(true, loc.clone().add(1, 0, 0));
            npc.data().set("state", 6);
        } else if (loc.clone().add(-1, 0, 0).getBlock().getType() == Material.TRAPPED_CHEST) {
            chestAnimation(true, loc.clone().add(-1, 0, 0));
            npc.data().set("state", 6);
        } else if (loc.clone().add(0, 0, 1).getBlock().getType() == Material.TRAPPED_CHEST) {
            chestAnimation(true, loc.clone().add(0, 0, 1));
            npc.data().set("state", 6);
        } else if (loc.clone().add(0, 0, -1).getBlock().getType() == Material.TRAPPED_CHEST) {
            chestAnimation(true, loc.clone().add(0, 0, -1));
            npc.data().set("state", 6);
        }
    }

    private static void chestAnimation(boolean open, Location loc) {
        BlockPosition pos = new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        PacketPlayOutBlockAction packet = new PacketPlayOutBlockAction(pos, Blocks.CHEST, (byte) 1, open ? (byte) 1 : 0);
        for (Player player : Bukkit.getOnlinePlayers())
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}
