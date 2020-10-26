package me.kei.citizens.automaton.tasks.treegen;

import me.kei.citizens.automaton.listener.TreeNPCListener;
import me.kei.citizens.automaton.tasks.TreeGen;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_15_R1.BlockPosition;
import net.minecraft.server.v1_15_R1.Blocks;
import net.minecraft.server.v1_15_R1.PacketPlayOutBlockAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.Dropper;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DropToChest extends TreeGen {
    @Override
    public void call(NPC npc, int state) {
        Location base = new Location(npc.getEntity().getWorld(), npc.data().get("base_x"), npc.data().get("base_y"), npc.data().get("base_z"));
        Location loc = npc.getEntity().getLocation();
        Location chestloc = null;
        int type = 0;

        if (base.clone().add(1, 0, 0).getBlock().getType() == Material.DROPPER) {
            Location c = base.clone().add(1, 0, 0);
            if (c.getBlock().getType() != Material.DROPPER) {
                npc.data().set("state", 1);
                return;
            }
            Dropper dropper = (Dropper) c.getBlock().getState();
            Inventory inv = dropper.getInventory();
            if(dropper.getCustomName() == null){
                npc.data().set("state", 1);
                return;
            }

            if(dropper.getCustomName().startsWith(TreeNPCListener.title)){
                String str = dropper.getCustomName().replaceAll(TreeNPCListener.title, "");
                if(TreeNPCListener.oak.equals(str)){
                    type = 0;
                } else if(TreeNPCListener.spruce.equals(str)) {
                    type = 1;
                } else if(TreeNPCListener.birch.equals(str)) {
                    type = 2;
                } else if(TreeNPCListener.acacia.equals(str)) {
                    type = 3;
                } else if(TreeNPCListener.jungle.equals(str)) {
                    type = 4;
                } else if(TreeNPCListener.darkoak.equals(str)) {
                    type = 5;
                } else {
                    type = 0;
                }
            }
        } else if (base.clone().add(-1, 0, 0).getBlock().getType() == Material.DROPPER) {
            Location c = base.clone().add(-1, 0, 0);
            if (c.getBlock().getType() != Material.DROPPER) {
                npc.data().set("state", 1);
                return;
            }
            Dropper dropper = (Dropper) c.getBlock().getState();
            Inventory inv = dropper.getInventory();
            if(dropper.getCustomName() == null){
                npc.data().set("state", 1);
                return;
            }

            if(dropper.getCustomName().startsWith(TreeNPCListener.title)){
                String str = dropper.getCustomName().replaceAll(TreeNPCListener.title, "");
                if(TreeNPCListener.oak.equals(str)){
                    type = 0;
                } else if(TreeNPCListener.spruce.equals(str)) {
                    type = 1;
                } else if(TreeNPCListener.birch.equals(str)) {
                    type = 2;
                } else if(TreeNPCListener.acacia.equals(str)) {
                    type = 3;
                } else if(TreeNPCListener.jungle.equals(str)) {
                    type = 4;
                } else if(TreeNPCListener.darkoak.equals(str)) {
                    type = 5;
                } else {
                    type = 0;
                }
            }
        } else if (base.clone().add(0, 0, 1).getBlock().getType() == Material.DROPPER) {
            Location c = base.clone().add(0, 0, 1);
            if (c.getBlock().getType() != Material.DROPPER) {
                npc.data().set("state", 1);
                return;
            }
            Dropper dropper = (Dropper) c.getBlock().getState();
            Inventory inv = dropper.getInventory();
            if(dropper.getCustomName() == null){
                npc.data().set("state", 1);
                return;
            }

            if(dropper.getCustomName().startsWith(TreeNPCListener.title)){
                String str = dropper.getCustomName().replaceAll(TreeNPCListener.title, "");
                if(TreeNPCListener.oak.equals(str)){
                    type = 0;
                } else if(TreeNPCListener.spruce.equals(str)) {
                    type = 1;
                } else if(TreeNPCListener.birch.equals(str)) {
                    type = 2;
                } else if(TreeNPCListener.acacia.equals(str)) {
                    type = 3;
                } else if(TreeNPCListener.jungle.equals(str)) {
                    type = 4;
                } else if(TreeNPCListener.darkoak.equals(str)) {
                    type = 5;
                } else {
                    type = 0;
                }
            }
        } else if (base.clone().add(0, 0, -1).getBlock().getType() == Material.DROPPER) {
            Location c = base.clone().add(0, 0, -1);
            if (c.getBlock().getType() != Material.DROPPER) {
                npc.data().set("state", 1);
                return;
            }
            Dropper dropper = (Dropper) c.getBlock().getState();
            Inventory inv = dropper.getInventory();
            if(dropper.getCustomName() == null){
                npc.data().set("state", 1);
                return;
            }

            if(dropper.getCustomName().startsWith(TreeNPCListener.title)){
                String str = dropper.getCustomName().replaceAll(TreeNPCListener.title, "");
                if(TreeNPCListener.oak.equals(str)){
                    type = 0;
                } else if(TreeNPCListener.spruce.equals(str)) {
                    type = 1;
                } else if(TreeNPCListener.birch.equals(str)) {
                    type = 2;
                } else if(TreeNPCListener.acacia.equals(str)) {
                    type = 3;
                } else if(TreeNPCListener.jungle.equals(str)) {
                    type = 4;
                } else if(TreeNPCListener.darkoak.equals(str)) {
                    type = 5;
                } else {
                    type = 0;
                }
            }
        }

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
        npc.faceLocation(chestloc);
        List<ItemStack> items = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(9) + 1; i++) {
            Random r = new Random();
            int num = r.nextInt(100)+1;
            if(num > 90 && num <= 98){
                if(type == 0) items.add(new ItemStack(Material.OAK_SAPLING));
                if(type == 1) items.add(new ItemStack(Material.SPRUCE_SAPLING));
                if(type == 2) items.add(new ItemStack(Material.BIRCH_SAPLING));
                if(type == 3) items.add(new ItemStack(Material.ACACIA_SAPLING));
                if(type == 4) items.add(new ItemStack(Material.JUNGLE_SAPLING));
                if(type == 5) items.add(new ItemStack(Material.DARK_OAK_SAPLING));
            } else if(num == 99){
                if(type == 0) items.add(new ItemStack(Material.APPLE));
                if(type == 1) items.add(new ItemStack(Material.APPLE));
                if(type == 2) items.add(new ItemStack(Material.APPLE));
                if(type == 3) items.add(new ItemStack(Material.APPLE));
                if(type == 4) items.add(new ItemStack(Material.APPLE));
                if(type == 5) items.add(new ItemStack(Material.APPLE));
            } else {
                if(type == 0) items.add(new ItemStack(Material.OAK_LOG));
                if(type == 1) items.add(new ItemStack(Material.SPRUCE_LOG));
                if(type == 2) items.add(new ItemStack(Material.BIRCH_LOG));
                if(type == 3) items.add(new ItemStack(Material.ACACIA_LOG));
                if(type == 4) items.add(new ItemStack(Material.JUNGLE_LOG));
                if(type == 5) items.add(new ItemStack(Material.DARK_OAK_LOG));
            }
        }
        for(ItemStack item : items) {
            chest.getInventory().addItem(item);
            npc.data().set("state", 1);
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
