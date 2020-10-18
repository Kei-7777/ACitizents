package me.kei.citizens.automaton.tasks;

import me.kei.citizens.automaton.tasks.cobbleminer.AddItemToChest;
import me.kei.citizens.automaton.tasks.cobbleminer.AnimationChest;
import me.kei.citizens.automaton.tasks.cobbleminer.BreakCobble;
import me.kei.citizens.automaton.tasks.cobbleminer.FirstChestCheck;
import me.kei.citizens.automaton.tasks.grinder.GrinderCobble;
import me.kei.citizens.automaton.tasks.grinder.PickupCobbleStone;
import me.kei.citizens.automaton.tasks.grinder.SearchGrinder;
import me.kei.citizens.automaton.tasks.grinder.SearchItemChest;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public abstract class Grinder {

    private static Map<Integer, Grinder> methods = new HashMap<>();

    public static void reset(){
        register(1, new SearchItemChest());
        register(2, new PickupCobbleStone());
        register(3, new SearchGrinder());
        register(4, new GrinderCobble());
    }

    public abstract void call(NPC npc, int state);
    public static void register(int state, Grinder clazz) {
        methods.put(state, clazz);
    }

    public static void run(NPC npc, int state){
        if(methods.containsKey(state)){
            methods.get(state).call(npc, state);
        } else {
            npc.data().set("state", 1);
        }
    }
}
