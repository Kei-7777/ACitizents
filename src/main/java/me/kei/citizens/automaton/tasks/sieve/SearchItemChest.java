package me.kei.citizens.automaton.tasks.sieve;

import me.kei.citizens.automaton.tasks.Sieve;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_15_R1.BlockPosition;
import net.minecraft.server.v1_15_R1.Blocks;
import net.minecraft.server.v1_15_R1.PacketPlayOutBlockAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class SearchItemChest extends Sieve {
    @Override
    public void call(NPC npc, int state) {
        Location base = new Location(npc.getEntity().getWorld(), npc.data().get("base_x"), npc.data().get("base_y"), npc.data().get("base_z"));
        if(base.clone().add(1,0,0).getBlock().getType() == Material.TRAPPED_CHEST || base.clone().add(1,0,0).getBlock().getType() == Material.CHEST){
            npc.getNavigator().setTarget(base.clone().add(1,0,0));
            Location c = base.clone().add(1,0,0);
            npc.faceLocation(c);
            npc.data().set("state", 2);
            chestAnimation(true, c);
        } else if(base.clone().add(-1,0,0).getBlock().getType() == Material.TRAPPED_CHEST || base.clone().add(-1,0,0).getBlock().getType() == Material.CHEST){
            npc.getNavigator().setTarget(base.clone().add(-1,0,0));
            Location c = base.clone().add(-1,0,0);
            npc.faceLocation(c);
            npc.data().set("state", 2);
            chestAnimation(true, c);
        } else if(base.clone().add(0,0,1).getBlock().getType() == Material.TRAPPED_CHEST || base.clone().add(0,0,1).getBlock().getType() == Material.CHEST){
            npc.getNavigator().setTarget(base.clone().add(0,0,1));
            Location c = base.clone().add(0,0,1);
            npc.faceLocation(c);
            npc.data().set("state", 2);
            chestAnimation(true, c);
        } else if(base.clone().add(0,0,-1).getBlock().getType() == Material.TRAPPED_CHEST || base.clone().add(0,0,-1).getBlock().getType() == Material.CHEST){
            npc.getNavigator().setTarget(base.clone().add(0,0,-1));
            Location c = base.clone().add(0,0,-1);
            npc.faceLocation(c);
            npc.data().set("state", 2);
            chestAnimation(true, c);
        }
    }

    private static void chestAnimation(boolean open, Location loc) {
        BlockPosition pos = new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        PacketPlayOutBlockAction packet = new PacketPlayOutBlockAction(pos, Blocks.CHEST, (byte) 1, open ? (byte) 1 : 0);
        for (Player player : Bukkit.getOnlinePlayers())
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}
