package me.kei.citizens.automaton.tasks.turret;

import me.kei.citizens.automaton.tasks.Turret;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.Arrays;
import java.util.List;

public class FindNearEnemy extends Turret {

    double range = 10;
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
        Location base = new Location(npc.getEntity().getWorld(), npc.data().get("base_x"), npc.data().get("base_y"), npc.data().get("base_z"));
        LivingEntity target = null;
        for (LivingEntity livingEntity : base.getNearbyLivingEntities(range)) {
            if (enemy.contains(livingEntity.getType())) {
                if (target == null) target = livingEntity;
                if (base.distance(livingEntity.getLocation()) < base.distance(target.getLocation())) {
                    target = livingEntity;
                    npc.getNavigator().setPaused(false);
                    npc.getNavigator().setTarget(target.getLocation());
                    npc.getNavigator().setPaused(true);
                }
            }
        }
        if (target != null) {
            npc.data().set("state", 2);
        }
    }
}
