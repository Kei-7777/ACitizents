package me.kei.citizens.automaton.listener;

import me.kei.citizens.automaton.AutomatonCitizens;
import me.kei.citizens.automaton.item.AutomatonItemFactory;
import me.kei.citizens.automaton.npc.AutomatonNPC;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Dropper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class TreeNPCListener implements Listener {
    AutomatonCitizens plugin;
    public TreeNPCListener(AutomatonCitizens automatonCitizens) {
        this.plugin = automatonCitizens;
    }

    public static String title = ChatColor.RESET + "木材生産機 ";
    public static String oak = "オーク";
    public static String spruce = "松";
    public static String birch = "白樺";
    public static String jungle = "ジャングル";
    public static String acacia = "アカシア";
    public static String darkoak = "ダークオーク";

    @EventHandler
    public void onEggClicked(PlayerInteractEvent e) {
        try {
            if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (e.getItem().isSimilar(AutomatonItemFactory.tree_npc_egg)) {
                    Player p = e.getPlayer();
                    NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, AutomatonNPC.TREEGEN.npcdisplay);
                    npc.data().set("owner", p.getUniqueId());
                    npc.data().set("npctype", AutomatonNPC.TREEGEN.id);
                    npc.data().set("state", 1);
                    npc.data().set("base_x", e.getClickedBlock().getLocation().getX() + .5);
                    npc.data().set("base_y", e.getClickedBlock().getLocation().getY() + 1);
                    npc.data().set("base_z", e.getClickedBlock().getLocation().getZ() + .5);
                    skin(npc, p.getName());
                    setHP(npc);
                    npc.spawn(e.getClickedBlock().getLocation().add(0.5, 1.5, 0.5));
                } else if(e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.DROPPER){
                    if(e.getItem() == null) return;
                    ItemStack item = e.getItem();
                    if(item.getType() == Material.OAK_SAPLING) {
                        Dropper dropper = (Dropper) e.getClickedBlock().getState();
                        if(dropper.getCustomName() != null && dropper.getCustomName().startsWith(title)){
                            dropper.setCustomName(title + oak);
                            dropper.update();
                        }
                    } else if(item.getType() == Material.SPRUCE_SAPLING) {
                        Dropper dropper = (Dropper) e.getClickedBlock().getState();
                        if(dropper.getCustomName() != null && dropper.getCustomName().startsWith(title)){
                            dropper.setCustomName(title + spruce);
                            dropper.update();
                        }
                    } else if(item.getType() == Material.ACACIA_SAPLING) {
                        Dropper dropper = (Dropper) e.getClickedBlock().getState();
                        if(dropper.getCustomName() != null && dropper.getCustomName().startsWith(title)){
                            dropper.setCustomName(title + acacia);
                            dropper.update();
                        }
                    } else if(item.getType() == Material.BIRCH_SAPLING) {
                        Dropper dropper = (Dropper) e.getClickedBlock().getState();
                        if(dropper.getCustomName() != null && dropper.getCustomName().startsWith(title)){
                            dropper.setCustomName(title + birch);
                            dropper.update();
                        }
                    } else if(item.getType() == Material.JUNGLE_SAPLING) {
                        Dropper dropper = (Dropper) e.getClickedBlock().getState();
                        if(dropper.getCustomName() != null && dropper.getCustomName().startsWith(title)){
                            dropper.setCustomName(title + jungle);
                            dropper.update();
                        }
                    } else if(item.getType() == Material.DARK_OAK_SAPLING) {
                        Dropper dropper = (Dropper) e.getClickedBlock().getState();
                        if(dropper.getCustomName() != null && dropper.getCustomName().startsWith(title)){
                            dropper.setCustomName(title + darkoak);
                            dropper.update();
                        }
                    }
                }
            }
        } catch (NullPointerException ex){
            // threw
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getClickedInventory() == null) return;
        if(e.getView().getTitle().startsWith(title)){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if(e.getBlock().getType() == Material.DROPPER){
            Dropper dropper = (Dropper) e.getBlock().getState();
            try {
                if (dropper.getCustomName().startsWith(title)) {
                    dropper.getInventory().clear();
                    e.setDropItems(false);
                    e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DROPPER));
                }
            } catch (NullPointerException ex){
                //threw
            }
        }
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent e){
        if(e.getEntityType() == EntityType.PUFFERFISH){
            if(e.getEntity().getCustomName().equals(AutomatonItemFactory.tree_npc_egg.getItemMeta().getDisplayName())) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        e.getEntity().remove();
                    }
                }.runTaskLater(this.plugin, 1);
            }
        }
    }

    @EventHandler
    public void onDispense(BlockDispenseEvent e){
        if(e.getBlock().getType() == Material.DROPPER){
            Dropper dropper = (Dropper) e.getBlock().getState();
            if(dropper.getCustomName() != null && dropper.getCustomName().startsWith(title)){
                e.setCancelled(true);
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
