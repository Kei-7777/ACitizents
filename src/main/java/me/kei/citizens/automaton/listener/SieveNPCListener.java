package me.kei.citizens.automaton.listener;

import me.kei.citizens.automaton.AutomatonCitizens;
import me.kei.citizens.automaton.item.AutomatonItemFactory;
import me.kei.citizens.automaton.npc.AutomatonNPC;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SieveNPCListener implements Listener {
    Plugin plugin;
    public SieveNPCListener(AutomatonCitizens automatonCitizens) {
        this.plugin = automatonCitizens;
    }

    @EventHandler
    public void onEggClicked(PlayerInteractEvent e) {
        try {
            if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (e.getItem().isSimilar(AutomatonItemFactory.sieve_npc_egg)) {
                    Player p = e.getPlayer();
                    NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, AutomatonNPC.SIEVE.npcdisplay);
                    npc.data().set("owner", p.getUniqueId());
                    npc.data().set("npctype", AutomatonNPC.SIEVE.id);
                    npc.data().set("state", 1);
                    npc.data().set("base_x", e.getClickedBlock().getLocation().getX() + .5);
                    npc.data().set("base_y", e.getClickedBlock().getLocation().getY() + 1);
                    npc.data().set("base_z", e.getClickedBlock().getLocation().getZ() + .5);
                    skin(npc, p.getName());
                    setHP(npc);
                    npc.spawn(e.getClickedBlock().getLocation().add(0.5, 1.5, 0.5));
                }
            }
        } catch (NullPointerException ex){
            // threw
        }
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent e){
        if(e.getEntityType() == EntityType.PUFFERFISH){
            if(e.getEntity().getCustomName().equals(AutomatonItemFactory.sieve_npc_egg.getItemMeta().getDisplayName())) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        e.getEntity().remove();
                    }
                }.runTaskLater(this.plugin, 1);
            }
        }
    }

    public void skin(NPC npc, String name) {
        String skinName = name;
        npc.data().set(NPC.PLAYER_SKIN_UUID_METADATA, name);
        npc.data().set(NPC.PLAYER_SKIN_USE_LATEST, false);
    }

    public void setHP(NPC npc){
        npc.setProtected(false);
    }
}
