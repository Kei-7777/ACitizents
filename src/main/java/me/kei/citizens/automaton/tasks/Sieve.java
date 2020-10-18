package me.kei.citizens.automaton.tasks;

import me.kei.citizens.automaton.AutomatonCitizens;
import me.kei.citizens.automaton.tasks.sieve.PickupGravel;
import me.kei.citizens.automaton.tasks.sieve.SearchItemChest;
import me.kei.citizens.automaton.tasks.sieve.SearchSieve;
import me.kei.citizens.automaton.tasks.sieve.SieveGravel;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public abstract class Sieve {

    private static Map<Integer, Sieve> methods = new HashMap<>();

    public static void reset(AutomatonCitizens ac){
        register(1, new SearchItemChest());
        register(2, new PickupGravel());
        register(3, new SearchSieve());
        register(4, new SieveGravel(ac));
    }

    public abstract void call(NPC npc, int state);
    public static void register(int state, Sieve clazz) {
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
