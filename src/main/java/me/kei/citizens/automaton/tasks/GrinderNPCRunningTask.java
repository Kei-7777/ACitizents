package me.kei.citizens.automaton.tasks;

import me.kei.citizens.automaton.AutomatonCitizens;
import me.kei.citizens.automaton.npc.AutomatonNPC;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;

public class GrinderNPCRunningTask extends BukkitRunnable {
    String dataString = AutomatonNPC.GRINDER.id;
    String name = AutomatonNPC.GRINDER.npcdisplay;

    public GrinderNPCRunningTask(AutomatonCitizens automatonCitizens) {
        Grinder.reset();
    }

    @Override
    public void run() {
        for (Iterator i = CitizensAPI.getNPCRegistry().iterator(); i.hasNext(); ) {
            NPC npc = (NPC) i.next();
            if (npc.data().has("npctype")) {
                if (npc.data().get("npctype").equals(dataString)) {
                    int state = npc.data().get("state");
                    Grinder.run(npc, state);
                }
            }
        }
    }
}
