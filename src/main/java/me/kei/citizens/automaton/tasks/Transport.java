package me.kei.citizens.automaton.tasks;

import me.kei.citizens.automaton.tasks.cobbleminer.AddItemToChest;
import me.kei.citizens.automaton.tasks.cobbleminer.AnimationChest;
import me.kei.citizens.automaton.tasks.cobbleminer.BreakCobble;
import me.kei.citizens.automaton.tasks.cobbleminer.FirstChestCheck;
import me.kei.citizens.automaton.tasks.transport.*;
import net.citizensnpcs.api.npc.NPC;

import java.util.HashMap;
import java.util.Map;

public abstract class Transport {

    private static Map<Integer, Transport> methods = new HashMap<>();

    public static void reset() {
        register(1, new ChestNavigatorA());
        register(2, new MoveToChest());
        register(3, new GetItemFromChest());
        register(4, new MoveToTargetChest());
        register(5, new CheckTargetChest());
        register(6, new ItemAddTrapChest());
    }

    public abstract void call(NPC npc, int state);

    public static void register(int state, Transport clazz) {
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