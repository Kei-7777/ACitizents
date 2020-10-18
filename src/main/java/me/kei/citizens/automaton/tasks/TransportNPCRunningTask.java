package me.kei.citizens.automaton.tasks;

import me.kei.citizens.automaton.AutomatonCitizens;
import me.kei.citizens.automaton.npc.AutomatonNPC;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class TransportNPCRunningTask extends BukkitRunnable {

    String dataString = AutomatonNPC.TRANSPORT.id;
    String name = AutomatonNPC.TRANSPORT.npcdisplay;

    Map<NPC, Location> npcs;

    public TransportNPCRunningTask(AutomatonCitizens automatonCitizens) {
        this.npcs = new HashMap<>();
        Transport.reset();
    }

    @Override
    public void run() {
        for (Iterator i = CitizensAPI.getNPCRegistry().iterator(); i.hasNext(); ) {
            NPC npc = (NPC) i.next();
            if (npc.data().has("npctype")) {
                if (npc.data().get("npctype").equals(dataString)) {
                    int state = npc.data().get("state");
                    if(!npcs.containsKey(npc)){
                        npcs.put(npc, npc.getEntity().getLocation());
                    } else {
                        Location base = new Location(npc.getEntity().getWorld(), npc.data().get("base_x"), npc.data().get("base_y"), npc.data().get("base_z"));
                        if(npcs.get(npc).getX() == npc.getEntity().getLocation().getX() && npcs.get(npc).getY() == npc.getEntity().getLocation().getY() && npcs.get(npc).getZ() == npc.getEntity().getLocation().getZ()){
                            npc.getNavigator().setTarget(base);
                        }
                        npcs.replace(npc, npc.getEntity().getLocation());
                    }
                    Transport.run(npc, state);
                }
            }
        }
    }
}
