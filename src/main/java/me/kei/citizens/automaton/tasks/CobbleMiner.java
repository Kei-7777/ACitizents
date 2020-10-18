package me.kei.citizens.automaton.tasks;

import me.kei.citizens.automaton.tasks.cobbleminer.AddItemToChest;
import me.kei.citizens.automaton.tasks.cobbleminer.AnimationChest;
import me.kei.citizens.automaton.tasks.cobbleminer.BreakCobble;
import me.kei.citizens.automaton.tasks.cobbleminer.FirstChestCheck;
import net.citizensnpcs.api.npc.NPC;

import java.util.HashMap;
import java.util.Map;

public abstract class CobbleMiner {

    private static Map<Integer, CobbleMiner> methods = new HashMap<>();

    public static void reset(){
        register(1, new FirstChestCheck());
        register(2, new BreakCobble());
        register(3, new AnimationChest());
        register(4, new AddItemToChest());
    }

    public abstract void call(NPC npc, int state);
    public static void register(int state, CobbleMiner clazz) {
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
