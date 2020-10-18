package me.kei.citizens.automaton.tasks.grinder;

import me.kei.citizens.automaton.tasks.Grinder;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Hopper;
import org.bukkit.inventory.ItemStack;

public class GrinderCobble extends Grinder {
    @Override
    public void call(NPC npc, int state) {
        Location base = new Location(npc.getEntity().getWorld(), npc.data().get("base_x"), npc.data().get("base_y"), npc.data().get("base_z"));
        if (base.clone().add(1, 0, 0).getBlock().getType() == Material.HOPPER && base.clone().add(1, 1, 0).getBlock().getType() == Material.IRON_TRAPDOOR) {
            npc.getNavigator().setTarget(base.clone().add(1, 0, 0));
            Location c = base.clone().add(1, 0, 0);
            npc.faceLocation(c);

            Hopper hopper = (Hopper) base.clone().add(1, 0, 0).getBlock().getState();
            if(hopper.getInventory().addItem(new ItemStack(Material.GRAVEL)).size() == 0){
                npc.data().set("state", 1);
                c.getWorld().spawnParticle(Particle.BLOCK_CRACK, c.clone().add(0,1,0), 25, 0, 0, 0, Material.GRAVEL.createBlockData());
                c.getWorld().playSound(c.clone().add(0,1,0), Sound.BLOCK_GRAVEL_BREAK, 1f, 1f);
            }
        } else if (base.clone().add(-1, 0, 0).getBlock().getType() == Material.HOPPER && base.clone().add(-1, 1, 0).getBlock().getType() == Material.IRON_TRAPDOOR) {
            npc.getNavigator().setTarget(base.clone().add(-1, 0, 0));
            Location c = base.clone().add(-1, 0, 0);
            npc.faceLocation(c);
            Hopper hopper = (Hopper) base.clone().add(-1, 0, 0).getBlock().getState();
            if(hopper.getInventory().addItem(new ItemStack(Material.GRAVEL)).size() == 0){
                npc.data().set("state", 1);
                c.getWorld().spawnParticle(Particle.BLOCK_CRACK, c.clone().add(0,1,0), 25, 0, 0, 0, Material.GRAVEL.createBlockData());
                c.getWorld().playSound(c.clone().add(0,1,0), Sound.BLOCK_GRAVEL_BREAK, 1f, 1f);
            }
        } else if (base.clone().add(0, 0, 1).getBlock().getType() == Material.HOPPER && base.clone().add(0, 1, 1).getBlock().getType() == Material.IRON_TRAPDOOR) {
            npc.getNavigator().setTarget(base.clone().add(0, 0, 1));
            Location c = base.clone().add(0, 0, 1);
            npc.faceLocation(c);
            Hopper hopper = (Hopper) base.clone().add(0, 0, 1).getBlock().getState();
            if(hopper.getInventory().addItem(new ItemStack(Material.GRAVEL)).size() == 0){
                npc.data().set("state", 1);
                c.getWorld().spawnParticle(Particle.BLOCK_CRACK, c.clone().add(0,1,0), 25, 0, 0, 0, Material.GRAVEL.createBlockData());
                c.getWorld().playSound(c.clone().add(0,1,0), Sound.BLOCK_GRAVEL_BREAK, 1f, 1f);
            }
        } else if (base.clone().add(0, 0, -1).getBlock().getType() == Material.HOPPER && base.clone().add(0, 1, -1).getBlock().getType() == Material.IRON_TRAPDOOR) {
            npc.getNavigator().setTarget(base.clone().add(0, 0, -1));
            Location c = base.clone().add(0, 0, -1);
            npc.faceLocation(c);
            Hopper hopper = (Hopper) base.clone().add(0, 0, -1).getBlock().getState();
            if(hopper.getInventory().addItem(new ItemStack(Material.GRAVEL)).size() == 0){
                npc.data().set("state", 1);
                c.getWorld().spawnParticle(Particle.BLOCK_CRACK, c.clone().add(0,1,0), 25, 0, 0, 0, Material.GRAVEL.createBlockData());
                c.getWorld().playSound(c.clone().add(0,1,0), Sound.BLOCK_GRAVEL_BREAK, 1f, 1f);
            }
        }
    }
}
