package me.kei.citizens.automaton.tasks.cobbleminer;

import me.kei.citizens.automaton.tasks.CobbleMiner;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.Material;

public class FirstChestCheck extends CobbleMiner {
    @Override
    public void call(NPC npc, int state) {
        Location base = new Location(npc.getEntity().getWorld(), npc.data().get("base_x"), npc.data().get("base_y"), npc.data().get("base_z"));
        if(base.clone().add(1,0,0).getBlock().getType() == Material.COBBLESTONE){
            npc.getNavigator().setTarget(base.clone().add(1,0,0));
            Location c = base.clone().add(1,0,0);
            npc.faceLocation(c);
            npc.data().set("state", 2);
        } else if(base.clone().add(-1,0,0).getBlock().getType() == Material.COBBLESTONE){
            npc.getNavigator().setTarget(base.clone().add(-1,0,0));
            Location c = base.clone().add(-1,0,0);
            npc.faceLocation(c);
            npc.data().set("state", 2);
        } else if(base.clone().add(0,0,1).getBlock().getType() == Material.COBBLESTONE){
            npc.getNavigator().setTarget(base.clone().add(0,0,1));
            Location c = base.clone().add(0,0,1);
            npc.faceLocation(c);
            npc.data().set("state", 2);
        } else if(base.clone().add(0,0,-1).getBlock().getType() == Material.COBBLESTONE){
            npc.getNavigator().setTarget(base.clone().add(0,0,-1));
            Location c = base.clone().add(0,0,-1);
            npc.faceLocation(c);
            npc.data().set("state", 2);
        }
    }
}
