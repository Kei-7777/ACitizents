package me.kei.citizens.automaton.tasks;

import me.kei.citizens.automaton.AutomatonCitizens;
import me.kei.citizens.automaton.listener.TreeNPCListener;
import me.kei.citizens.automaton.npc.AutomatonNPC;
import me.kei.citizens.automaton.tasks.cobbleminer.CheckEquipment;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Dropper;
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

                    Location base = npc.getStoredLocation();
                    if (base.clone().add(1, 0, 0).getBlock().getType() == Material.DROPPER) {
                        Location c = base.clone().add(1, 0, 0);
                        Dropper dropper = (Dropper) c.getBlock().getState();
                        dropper.update();
                        if (dropper.getCustomName() == null || !dropper.getCustomName().startsWith(TreeNPCListener.title))
                            dropper.setCustomName(TreeNPCListener.title + TreeNPCListener.oak);
                        dropper.update();
                    } else if (base.clone().add(-1, 0, 0).getBlock().getType() == Material.DROPPER) {
                        Location c = base.clone().add(-1, 0, 0);
                        Dropper dropper = (Dropper) c.getBlock().getState();
                        if (dropper.getCustomName() == null || !dropper.getCustomName().startsWith(TreeNPCListener.title))
                            dropper.setCustomName(TreeNPCListener.title + TreeNPCListener.oak);
                        dropper.update();
                    } else if (base.clone().add(0, 0, 1).getBlock().getType() == Material.DROPPER) {
                        Location c = base.clone().add(0, 0, 1);
                        Dropper dropper = (Dropper) c.getBlock().getState();
                        if (dropper.getCustomName() == null || !dropper.getCustomName().startsWith(TreeNPCListener.title))
                            dropper.setCustomName(TreeNPCListener.title + TreeNPCListener.oak);
                        dropper.update();
                    } else if (base.clone().add(0, 0, -1).getBlock().getType() == Material.DROPPER) {
                        Location c = base.clone().add(0, 0, -1);
                        Dropper dropper = (Dropper) c.getBlock().getState();
                        if (dropper.getCustomName() == null || !dropper.getCustomName().startsWith(TreeNPCListener.title))
                            dropper.setCustomName(TreeNPCListener.title + TreeNPCListener.oak);
                        dropper.update();
                    }

                    TreeGen.run(npc, state);
                }
            }
        }
    }
}
