package me.kei.citizens.automaton.tasks.turret;

import me.kei.citizens.automaton.tasks.Turret;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class AttackEnemy extends Turret {

    double range = 10;
    double damage = 3.5;
    List<EntityType> enemy =
            Arrays.asList(
                    EntityType.ZOMBIE,
                    EntityType.ZOMBIE_HORSE,
                    EntityType.PIG_ZOMBIE,
                    EntityType.SKELETON,
                    EntityType.WITHER_SKELETON,
                    EntityType.SPIDER,
                    EntityType.CAVE_SPIDER,
                    EntityType.PHANTOM,
                    EntityType.MAGMA_CUBE,
                    EntityType.SLIME,
                    EntityType.SILVERFISH,
                    EntityType.ENDERMAN,
                    EntityType.ENDERMITE,
                    EntityType.BLAZE
            );
    
    @Override
    public void call(NPC npc, int state) {
        npc.getTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, new ItemStack(Material.ENCHANTED_BOOK));
        Location base = new Location(npc.getEntity().getWorld(), npc.data().get("base_x"), npc.data().get("base_y"), npc.data().get("base_z"));
        LivingEntity target = null;
        for(LivingEntity livingEntity : base.getNearbyLivingEntities(range)){
            if(enemy.contains(livingEntity.getType())) {
                if (target == null) target = livingEntity;
                if (base.distance(livingEntity.getLocation()) < base.distance(target.getLocation())) {
                    target = livingEntity;
                }
            }
        }

        if(target == null) return;
        else {
            target.getLocation().getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, target.getLocation(), 10);
            target.getLocation().getWorld().playSound(target.getLocation(), Sound.ENTITY_ITEM_BREAK, 1f, 1f);
            target.damage(damage);
            npc.data().set("state", 1);
        }

    }
}
