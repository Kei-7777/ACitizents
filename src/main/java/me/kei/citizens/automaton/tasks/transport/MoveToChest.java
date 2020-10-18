package me.kei.citizens.automaton.tasks.transport;

import me.kei.citizens.automaton.tasks.Transport;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_15_R1.BlockPosition;
import net.minecraft.server.v1_15_R1.Blocks;
import net.minecraft.server.v1_15_R1.PacketPlayOutBlockAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class MoveToChest extends Transport {

    List<Material> AirBlocks = Arrays.asList(
            Material.AIR,
            Material.LAVA,
            Material.WATER,
            Material.COBWEB,
            Material.STRING
    );

    @Override
    public void call(NPC npc, int state) {
        Location base = new Location(npc.getEntity().getWorld(), npc.data().get("base_x"), npc.data().get("base_y"), npc.data().get("base_z"));
        List<Block> chests = getNearChests(base, 10);
        Collections.shuffle(chests);
        if (chests.size() > 0) {
            Block target = null;
            Location moveto = null;
            for (Block b : chests) {
                if (b.getLocation().clone().add(.25, 0, 0).getBlock().getType() == Material.AIR && b.getLocation().clone().add(1, 1, 0).getBlock().getType() == Material.AIR && !AirBlocks.contains(b.getLocation().clone().add(1, -1, 0).getBlock().getType())) {
                    target = b;
                    npc.getNavigator().setTarget(target.getLocation());
                } else if (b.getLocation().clone().add(-.25, 0, 0).getBlock().getType() == Material.AIR && b.getLocation().clone().add(-1, 1, 0).getBlock().getType() == Material.AIR && !AirBlocks.contains(b.getLocation().clone().add(-1, -1, 0).getBlock().getType())) {
                    target = b;
                    npc.getNavigator().setTarget(target.getLocation());
                } else if (b.getLocation().clone().add(0, 0, .25).getBlock().getType() == Material.AIR && b.getLocation().clone().add(0, 1, 1).getBlock().getType() == Material.AIR && !AirBlocks.contains(b.getLocation().clone().add(0, -1, 1).getBlock().getType())) {
                    target = b;
                    npc.getNavigator().setTarget(target.getLocation());
                } else if (b.getLocation().clone().add(0, 0, -.25).getBlock().getType() == Material.AIR && b.getLocation().clone().add(0, 1, -1).getBlock().getType() == Material.AIR && !AirBlocks.contains(b.getLocation().clone().add(0, -1, -1).getBlock().getType())) {
                    target = b;
                    npc.getNavigator().setTarget(target.getLocation());
                }
            }
        }
        Location loc = npc.getEntity().getLocation();
        if (loc.clone().add(1, 0, 0).getBlock().getType() == Material.CHEST) {
            chestAnimation(true, loc.clone().add(1, 0, 0));
            npc.data().set("state", 3);
        } else if (loc.clone().add(-1, 0, 0).getBlock().getType() == Material.CHEST) {
            chestAnimation(true, loc.clone().add(-1, 0, 0));
            npc.data().set("state", 3);
        } else if (loc.clone().add(0, 0, 1).getBlock().getType() == Material.CHEST) {
            chestAnimation(true, loc.clone().add(0, 0, 1));
            npc.data().set("state", 3);
        } else if (loc.clone().add(0, 0, -1).getBlock().getType() == Material.CHEST) {
            chestAnimation(true, loc.clone().add(0, 0, -1));
            npc.data().set("state", 3);
        }
    }

    private boolean checkInventoryRemain(Chest chest, ItemStack itemStack) {
        Inventory inv = Bukkit.createInventory(null, chest.getInventory().getSize());
        for (int j = 0; j < chest.getInventory().getSize(); j++) {
            inv.setItem(j, chest.getInventory().getItem(j));
        }

        Map<Integer, ItemStack> remainingItems = inv.addItem(itemStack);
        return remainingItems.size() < 1;
    }

    public List<Block> getNearChests(Location loc, int radius) {
        int cx = loc.getBlockX();
        int cy = loc.getBlockY();
        int cz = loc.getBlockZ();
        List<Block> list = new ArrayList<>();

        for (int x = cx - radius; x <= cx + radius; x++) {
            for (int z = cz - radius; z <= cz + radius; z++) {
                for (int y = (cy - radius); y < (cy + radius); y++) {
                    double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + ((cy - y) * (cy - y));

                    if (dist < radius * radius) {
                        Location l = new Location(loc.getWorld(), x, y + 2, z);
                        if (l.getBlock().getType() == Material.CHEST) {
                            list.add(l.getBlock());
                        }
                    }
                }
            }
        }
        return list;
    }

    public List<Block> getNearTrapChests(Location loc, int radius) {
        int cx = loc.getBlockX();
        int cy = loc.getBlockY();
        int cz = loc.getBlockZ();
        List<Block> list = new ArrayList<>();

        for (int x = cx - radius; x <= cx + radius; x++) {
            for (int z = cz - radius; z <= cz + radius; z++) {
                for (int y = (cy - radius); y < (cy + radius); y++) {
                    double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + ((cy - y) * (cy - y));

                    if (dist < radius * radius) {
                        Location l = new Location(loc.getWorld(), x, y + 2, z);
                        if (l.getBlock().getType() == Material.TRAPPED_CHEST) {
                            list.add(l.getBlock());
                        }
                    }
                }
            }
        }
        return list;
    }

    private static void chestAnimation(boolean open, Location loc) {
        BlockPosition pos = new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        PacketPlayOutBlockAction packet = new PacketPlayOutBlockAction(pos, Blocks.CHEST, (byte) 1, open ? (byte) 1 : 0);
        for (Player player : Bukkit.getOnlinePlayers())
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}
