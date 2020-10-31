package me.kei.citizens.automaton.tasks;

import me.kei.citizens.automaton.AutomatonCitizens;
import me.kei.citizens.automaton.npc.AutomatonNPC;
import me.kei.citizens.automaton.tasks.cobbleminer.CheckEquipment;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;

public class CobbleMinerNPCRunningTask extends BukkitRunnable {

    String dataString = AutomatonNPC.COBBLEMINER.id;
    String name = AutomatonNPC.COBBLEMINER.npcdisplay;

    public CobbleMinerNPCRunningTask(AutomatonCitizens automatonCitizens) {
        CobbleMiner.reset();
    }

    @Override
    public void run() {
        for (Iterator i = CitizensAPI.getNPCRegistry().iterator(); i.hasNext(); ) {
            NPC npc = (NPC) i.next();
            if (npc.data().has("npctype")) {
                if (npc.data().get("npctype").equals(dataString)) {
                    int state = npc.data().get("state");
                    CobbleMiner.run(npc, state);
                    CheckEquipment.check(npc);
                }
            }
        }
    }
}
