package me.kei.citizens.automaton.tasks.grinder;

import me.kei.citizens.automaton.tasks.Grinder;
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
import org.bukkit.inventory.ItemStack;

public class PickupCobbleStone extends Grinder {
    @Override
    public void call(NPC npc, int state) {
        try {
            Location base = new Location(npc.getEntity().getWorld(), npc.data().get("base_x"), npc.data().get("base_y"), npc.data().get("base_z"));
            if (base.clone().add(1, 0, 0).getBlock().getType() == Material.TRAPPED_CHEST || base.clone().add(1, 0, 0).getBlock().getType() == Material.CHEST) {
                npc.getNavigator().setTarget(base.clone().add(1, 0, 0));
                Location c = base.clone().add(1, 0, 0);
                Chest chest = (Chest) c.getBlock().getState();
                if (check(chest)) {
                    for (int i = 0; i < chest.getInventory().getSize(); i++) {
                        ItemStack item = chest.getInventory().getItem(i);
                        if (item.getAmount() == 1) {
                            item.setType(Material.AIR);
                        } else {
                            item.setAmount(item.getAmount() - 1);
                            npc.faceLocation(c);
                            npc.data().set("state", 3);
                            chestAnimation(false, c);
                            return;
                        }
                    }
                } else {
                    return;
                }
            } else if (base.clone().add(-1, 0, 0).getBlock().getType() == Material.TRAPPED_CHEST || base.clone().add(-1, 0, 0).getBlock().getType() == Material.CHEST) {
                npc.getNavigator().setTarget(base.clone().add(-1, 0, 0));
                Location c = base.clone().add(-1, 0, 0);
                Chest chest = (Chest) c.getBlock().getState();
                if (check(chest)) {
                    for (int i = 0; i < chest.getInventory().getSize(); i++) {
                        ItemStack item = chest.getInventory().getItem(i);
                        if (item.getAmount() == 1) {
                            item.setType(Material.AIR);
                        } else {
                            item.setAmount(item.getAmount() - 1);
                            npc.faceLocation(c);
                            npc.data().set("state", 3);
                            chestAnimation(false, c);
                            return;
                        }
                    }
                } else {
                    return;
                }
            } else if (base.clone().add(0, 0, 1).getBlock().getType() == Material.TRAPPED_CHEST || base.clone().add(0, 0, 1).getBlock().getType() == Material.CHEST) {
                npc.getNavigator().setTarget(base.clone().add(0, 0, 1));
                Location c = base.clone().add(0, 0, 1);
                Chest chest = (Chest) c.getBlock().getState();
                if (check(chest)) {
                    for (int i = 0; i < chest.getInventory().getSize(); i++) {
                        ItemStack item = chest.getInventory().getItem(i);
                        if (item.getAmount() == 1) {
                            item.setType(Material.AIR);
                        } else {
                            item.setAmount(item.getAmount() - 1);
                            npc.faceLocation(c);
                            npc.data().set("state", 3);
                            chestAnimation(false, c);
                            return;
                        }
                    }
                } else {
                    return;
                }
            } else if (base.clone().add(0, 0, -1).getBlock().getType() == Material.TRAPPED_CHEST || base.clone().add(0, 0, -1).getBlock().getType() == Material.CHEST) {
                npc.getNavigator().setTarget(base.clone().add(0, 0, -1));
                Location c = base.clone().add(0, 0, -1);
                Chest chest = (Chest) c.getBlock().getState();
                if (check(chest)) {
                    for (int i = 0; i < chest.getInventory().getSize(); i++) {
                        ItemStack item = chest.getInventory().getItem(i);
                        if (item.getAmount() == 1) {
                            item.setType(Material.AIR);
                        } else {
                            item.setAmount(item.getAmount() - 1);
                            npc.faceLocation(c);
                            npc.data().set("state", 3);
                            chestAnimation(false, c);
                            return;
                        }
                    }

                } else {
                    return;
                }
            }
        } catch (NullPointerException ex) {

        }
    }

    boolean check(Chest chest) {
        for (ItemStack i : chest.getInventory().getContents()) {
            if (i == null) continue;
            if (i.getType() == Material.COBBLESTONE) {
                return true;
            }
        }
        return false;
    }

    private static void chestAnimation(boolean open, Location loc) {
        BlockPosition pos = new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        PacketPlayOutBlockAction packet = new PacketPlayOutBlockAction(pos, Blocks.CHEST, (byte) 1, open ? (byte) 1 : 0);
        for (Player player : Bukkit.getOnlinePlayers())
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}
