package me.kei.citizens.automaton.listener;

import me.kei.citizens.automaton.AutomatonCitizens;
import me.kei.citizens.automaton.item.AutomatonItemFactory;
import me.kei.citizens.automaton.npc.AutomatonNPC;
import me.kei.citizens.automaton.tasks.CobbleMiner;
import me.kei.citizens.automaton.tasks.cobbleminer.CheckEquipment;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.Iterator;

public class NPCRemoveListener implements Listener {
    public NPCRemoveListener(AutomatonCitizens automatonCitizens) {
    }

    @EventHandler
    public void onClick(PlayerInteractAtEntityEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.STICK) {
            if(!CitizensAPI.getNPCRegistry().isNPC(e.getRightClicked())) return;
            for (Iterator i = CitizensAPI.getNPCRegistry().iterator(); i.hasNext(); ) {
                NPC npc = (NPC) i.next();
                if (npc.getEntity().getUniqueId().toString().equalsIgnoreCase(e.getRightClicked().getUniqueId().toString())) {
                    if (npc.data().has("npctype")) {
                        if (npc.data().get("npctype").equals(AutomatonNPC.COBBLEMINER.id)) {
                            npc.getStoredLocation().getWorld().dropItemNaturally(npc.getEntity().getLocation().clone().add(0,0,0), AutomatonItemFactory.cobbleminer_npc_egg);
                            npc.destroy();
                            return;
                        } else if (npc.data().get("npctype").equals(AutomatonNPC.TRANSPORT.id)) {
                            npc.getStoredLocation().getWorld().dropItemNaturally(npc.getEntity().getLocation().clone().add(0,0,0), AutomatonItemFactory.transport_npc_egg);
                            npc.destroy();
                            return;
                        } else if (npc.data().get("npctype").equals(AutomatonNPC.SIEVE.id)) {
                            npc.getStoredLocation().getWorld().dropItemNaturally(npc.getEntity().getLocation().clone().add(0,0,0), AutomatonItemFactory.sieve_npc_egg);
                            npc.destroy();
                            return;
                        } else if (npc.data().get("npctype").equals(AutomatonNPC.GRINDER.id)) {
                            npc.getStoredLocation().getWorld().dropItemNaturally(npc.getEntity().getLocation().clone().add(0,0,0), AutomatonItemFactory.grinder_npc_egg);
                            npc.destroy();
                            return;
                        } else if (npc.data().get("npctype").equals(AutomatonNPC.TURRET.id)) {
                            npc.getStoredLocation().getWorld().dropItemNaturally(npc.getEntity().getLocation().clone().add(0,0,0), AutomatonItemFactory.turret_npc_egg);
                            npc.destroy();
                            return;
                        }
                    }
                }
            }
        }
    }
}
