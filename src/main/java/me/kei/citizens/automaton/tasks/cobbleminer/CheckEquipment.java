package me.kei.citizens.automaton.tasks.cobbleminer;

import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CheckEquipment {

    public static void check(NPC npc){
        if(npc.getTrait(Equipment.class).get(Equipment.EquipmentSlot.HAND) == null){
            npc.getTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, new ItemStack(Material.DIAMOND_PICKAXE));
        }
    }

}
