package me.kei.citizens.automaton.tasks;

import me.kei.citizens.automaton.AutomatonCitizens;
import me.kei.citizens.automaton.npc.AutomatonNPC;
import me.kei.citizens.automaton.tasks.cobbleminer.CheckEquipment;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;

public class TreeNPCRunningTask extends BukkitRunnable {
    public TreeNPCRunningTask(AutomatonCitizens automatonCitizens) {
        TreeGen.reset();
    }

    String dataString = AutomatonNPC.TREEGEN.id;
    String name = AutomatonNPC.TREEGEN.npcdisplay;

    @Override
    public void run() {
        for (Iterator i = CitizensAPI.getNPCRegistry().iterator(); i.hasNext(); ) {
            NPC npc = (NPC) i.next();
            if (npc.data().has("npctype")) {
                if (npc.data().get("npctype").equals(dataString)) {
                    int state = npc.data().get("state");
                    TreeGen.run(npc, state);
                }
            }
        }
    }
}
