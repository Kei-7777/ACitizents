package me.kei.citizens.automaton.tasks.cobbleminer;

import me.kei.citizens.automaton.tasks.CobbleMiner;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;

public class BreakCobble extends CobbleMiner {
    @Override
    public void call(NPC npc, int state) {
        Location base = new Location(npc.getEntity().getWorld(), npc.data().get("base_x"), npc.data().get("base_y"), npc.data().get("base_z"));
        if(base.clone().add(1,0,0).getBlock().getType() == Material.COBBLESTONE){
            Location loc = base.clone().add(1,0,0);
            loc.getBlock().setType(Material.AIR);
            loc.getWorld().spawnParticle(Particle.BLOCK_CRACK, loc, 25, 0, 0, 0, Material.COBBLESTONE.createBlockData());
            loc.getWorld().playSound(loc, Sound.BLOCK_STONE_BREAK, 1f, 1f);
            npc.data().set("state", 3);
        } else if(base.clone().add(-1,0,0).getBlock().getType() == Material.COBBLESTONE){
            Location loc = base.clone().add(-1,0,0);
            loc.getBlock().setType(Material.AIR);
            loc.getWorld().spawnParticle(Particle.BLOCK_CRACK, loc, 25, 0, 0, 0, Material.COBBLESTONE.createBlockData());
            loc.getWorld().playSound(loc, Sound.BLOCK_STONE_BREAK, 1f, 1f);
            npc.data().set("state", 3);
        } else if(base.clone().add(0,0,1).getBlock().getType() == Material.COBBLESTONE){
            Location loc = base.clone().add(0,0,1);
            loc.getBlock().setType(Material.AIR);
            loc.getWorld().spawnParticle(Particle.BLOCK_CRACK, loc, 25, 0, 0, 0, Material.COBBLESTONE.createBlockData());
            loc.getWorld().playSound(loc, Sound.BLOCK_STONE_BREAK, 1f, 1f);
            npc.data().set("state", 3);
        } else if(base.clone().add(0,0,-1).getBlock().getType() == Material.COBBLESTONE){
            Location loc = base.clone().add(0,0,-1);
            loc.getBlock().setType(Material.AIR);
            loc.getWorld().spawnParticle(Particle.BLOCK_CRACK, loc, 25, 0, 0, 0, Material.COBBLESTONE.createBlockData());
            loc.getWorld().playSound(loc, Sound.BLOCK_STONE_BREAK, 1f, 1f);
            npc.data().set("state", 3);
        }
    }
}
