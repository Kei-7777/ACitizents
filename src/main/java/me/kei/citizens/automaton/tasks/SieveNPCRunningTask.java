package me.kei.citizens.automaton.tasks;

import me.kei.citizens.automaton.AutomatonCitizens;
import me.kei.citizens.automaton.npc.AutomatonNPC;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;

public class SieveNPCRunningTask extends BukkitRunnable {

    String dataString = AutomatonNPC.SIEVE.id;
    String name = AutomatonNPC.SIEVE.npcdisplay;

    public SieveNPCRunningTask(AutomatonCitizens automatonCitizens) {
        Sieve.reset(automatonCitizens);
    }

    @Override
    public void run() {
        for (Iterator i = CitizensAPI.getNPCRegistry().iterator(); i.hasNext(); ) {
            NPC npc = (NPC) i.next();
            if (npc.data().has("npctype")) {
                if (npc.data().get("npctype").equals(dataString)) {
                    int state = npc.data().get("state");
                    Sieve.run(npc, state);
                }
            }
        }
    }
}
