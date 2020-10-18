package me.kei.citizens.automaton.npc;

public enum AutomatonNPC {
    TRANSPORT("transport", "運搬奴隷"),
    COBBLEMINER("miner", "採掘奴隷"),
    GRINDER("grinder", "粉砕奴隷"),
    SIEVE("sieve", "篩い奴隷"),
    TURRET("turret", "タレット奴隷"),
    TREEGEN("tree", "木材奴隷");

    public String npcdisplay;
    public String id;
    AutomatonNPC(String name, String npcdisplay) {
        this.id = name;
        this.npcdisplay = npcdisplay;
    }
}
