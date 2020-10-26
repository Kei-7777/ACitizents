package me.kei.citizens.automaton.tasks;

import me.kei.citizens.automaton.tasks.treegen.CountRemain;
import me.kei.citizens.automaton.tasks.treegen.DropToChest;
import me.kei.citizens.automaton.tasks.treegen.ResetDropperInventory;
import net.citizensnpcs.api.npc.NPC;

import java.util.HashMap;
import java.util.Map;

public abstract class TreeGen {

    private static Map<Integer, TreeGen> methods = new HashMap<>();

    public static void reset() {
        register(1, new ResetDropperInventory());
        register(2, new CountRemain());
        register(3, new DropToChest());
    }

    public abstract void call(NPC npc, int state);

    public static void register(int state, TreeGen clazz) {
        methods.put(state, clazz);
    }

    public static void run(NPC npc, int state) {
        if (methods.containsKey(state)) {
            methods.get(state).call(npc, state);
        } else {
            npc.data().set("state", 1);
        }
    }
}
