package me.kei.citizens.automaton.tasks;

import net.citizensnpcs.api.npc.NPC;

import java.util.HashMap;
import java.util.Map;

public abstract class TreeGen {

    private static Map<Integer, TreeGen> methods = new HashMap<>();

    public static void reset() {
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
