package me.kei.citizens.automaton.tasks.transport;

import me.kei.citizens.automaton.tasks.Transport;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChestNavigatorA extends Transport {

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
                if (b.getLocation().clone().add(1, 0, 0).getBlock().getType() == Material.AIR && b.getLocation().clone().add(1, 1, 0).getBlock().getType() == Material.AIR && !AirBlocks.contains(b.getLocation().clone().add(1, -1, 0).getBlock().getType())) {
                    target = b;
                    moveto = b.getLocation().clone().add(1.5, 0, 0.5);
                    break;
                } else if (b.getLocation().clone().add(-1, 0, 0).getBlock().getType() == Material.AIR && b.getLocation().clone().add(-1, 1, 0).getBlock().getType() == Material.AIR && !AirBlocks.contains(b.getLocation().clone().add(-1, -1, 0).getBlock().getType())) {
                    target = b;
                    moveto = b.getLocation().clone().add(-1 + .5, 0, 0.5);
                    break;
                } else if (b.getLocation().clone().add(0, 0, 1).getBlock().getType() == Material.AIR && b.getLocation().clone().add(0, 1, 1).getBlock().getType() == Material.AIR && !AirBlocks.contains(b.getLocation().clone().add(0, -1, 1).getBlock().getType())) {
                    target = b;
                    moveto = b.getLocation().clone().add(0.5, 0, 1.5);
                    break;
                } else if (b.getLocation().clone().add(0, 0, -1).getBlock().getType() == Material.AIR && b.getLocation().clone().add(0, 1, -1).getBlock().getType() == Material.AIR && !AirBlocks.contains(b.getLocation().clone().add(0, -1, -1).getBlock().getType())) {
                    target = b;
                    moveto = b.getLocation().clone().add(0.5, 0, -1 + .5);
                    break;
                }
            }

            if (target == null || moveto == null) {
                npc.getNavigator().setTarget(base);
                return;
            }
            npc.getNavigator().setTarget(target.getLocation());
            npc.data().set("state", 2);
        }
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
}
