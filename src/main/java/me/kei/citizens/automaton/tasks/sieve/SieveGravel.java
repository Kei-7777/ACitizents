package me.kei.citizens.automaton.tasks.sieve;

import me.kei.citizens.automaton.AutomatonCitizens;
import me.kei.citizens.automaton.tasks.Sieve;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.*;
import org.bukkit.block.Hopper;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SieveGravel extends Sieve {

    List<Material> loot;

    public SieveGravel(AutomatonCitizens ac){
        loot = new ArrayList<>();
        for(String s : ac.getConfig().getConfigurationSection("sieve").getKeys(true)){
            for (int i = 0; i < ac.getConfig().getInt("sieve." + s); i++) {
                loot.add(Material.valueOf(s));
            }
        }
    }

    @Override
    public void call(NPC npc, int state) {
        Location base = new Location(npc.getEntity().getWorld(), npc.data().get("base_x"), npc.data().get("base_y"), npc.data().get("base_z"));
        if (base.clone().add(1, 0, 0).getBlock().getType() == Material.HOPPER && base.clone().add(1, 1, 0).getBlock().getType() == Material.RAIL) {
            npc.getNavigator().setTarget(base.clone().add(1, 0, 0));
            Location c = base.clone().add(1, 0, 0);
            npc.faceLocation(c);

            Hopper hopper = (Hopper) base.clone().add(1, 0, 0).getBlock().getState();
            Collections.shuffle(loot);
            if(hopper.getInventory().addItem(new ItemStack(loot.get(0))).size() == 0){
                npc.data().set("state", 1);
                c.getWorld().spawnParticle(Particle.BLOCK_CRACK, c.clone().add(0,1,0), 25, 0, 0, 0, Material.GRAVEL.createBlockData());
                c.getWorld().playSound(c.clone().add(0,1,0), Sound.BLOCK_GRAVEL_BREAK, 1f, 1f);
            }
        } else if (base.clone().add(-1, 0, 0).getBlock().getType() == Material.HOPPER && base.clone().add(-1, 1, 0).getBlock().getType() == Material.RAIL) {
            npc.getNavigator().setTarget(base.clone().add(-1, 0, 0));
            Location c = base.clone().add(-1, 0, 0);
            npc.faceLocation(c);
            Hopper hopper = (Hopper) base.clone().add(-1, 0, 0).getBlock().getState();
            Collections.shuffle(loot);
            if(hopper.getInventory().addItem(new ItemStack(loot.get(0))).size() == 0){
                npc.data().set("state", 1);
                c.getWorld().spawnParticle(Particle.BLOCK_CRACK, c.clone().add(0,1,0), 25, 0, 0, 0, Material.GRAVEL.createBlockData());
                c.getWorld().playSound(c.clone().add(0,1,0), Sound.BLOCK_GRAVEL_BREAK, 1f, 1f);
            }
        } else if (base.clone().add(0, 0, 1).getBlock().getType() == Material.HOPPER && base.clone().add(0, 1, 1).getBlock().getType() == Material.RAIL) {
            npc.getNavigator().setTarget(base.clone().add(0, 0, 1));
            Location c = base.clone().add(0, 0, 1);
            npc.faceLocation(c);
            Hopper hopper = (Hopper) base.clone().add(0, 0, 1).getBlock().getState();
            Collections.shuffle(loot);
            if(hopper.getInventory().addItem(new ItemStack(loot.get(0))).size() == 0){
                npc.data().set("state", 1);
                c.getWorld().spawnParticle(Particle.BLOCK_CRACK, c.clone().add(0,1,0), 25, 0, 0, 0, Material.GRAVEL.createBlockData());
                c.getWorld().playSound(c.clone().add(0,1,0), Sound.BLOCK_GRAVEL_BREAK, 1f, 1f);
            }
        } else if (base.clone().add(0, 0, -1).getBlock().getType() == Material.HOPPER && base.clone().add(0, 1, -1).getBlock().getType() == Material.RAIL) {
            npc.getNavigator().setTarget(base.clone().add(0, 0, -1));
            Location c = base.clone().add(0, 0, -1);
            npc.faceLocation(c);
            Hopper hopper = (Hopper) base.clone().add(0, 0, -1).getBlock().getState();
            Collections.shuffle(loot);
            if(hopper.getInventory().addItem(new ItemStack(loot.get(0))).size() == 0){
                npc.data().set("state", 1);
                c.getWorld().spawnParticle(Particle.BLOCK_CRACK, c.clone().add(0,1,0), 25, 0, 0, 0, Material.GRAVEL.createBlockData());
                c.getWorld().playSound(c.clone().add(0,1,0), Sound.BLOCK_GRAVEL_BREAK, 1f, 1f);
            }
        }
    }
}
