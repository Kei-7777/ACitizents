package me.kei.citizens.automaton.tasks;

import me.kei.citizens.automaton.tasks.transport.*;
import me.kei.citizens.automaton.tasks.turret.AttackEnemy;
import me.kei.citizens.automaton.tasks.turret.FindNearEnemy;
import net.citizensnpcs.api.npc.NPC;

import java.util.HashMap;
import java.util.Map;

public abstract class Turret {

    private static Map<Integer, Turret> methods = new HashMap<>();

    public static void reset() {
        register(1, new FindNearEnemy());
        register(2, new AttackEnemy());
    }

    public abstract void call(NPC npc, int state);

    public static void register(int state, Turret clazz) {
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
