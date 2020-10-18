package me.kei.citizens.automaton;

import me.kei.citizens.automaton.item.AutomatonItemFactory;
import me.kei.citizens.automaton.listener.*;
import me.kei.citizens.automaton.tasks.*;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;

public class AutomatonCitizens extends JavaPlugin {

    @Override
    public void onEnable() {

        AutomatonItemFactory.init(this);

        saveDefaultConfig();


        getServer().getPluginManager().registerEvents(new NPCRemoveListener(this), this);

        getServer().getPluginManager().registerEvents(new TransportNPCListener(this), this);
        getServer().getPluginManager().registerEvents(new CobbleMinerNPCListener(this), this);
        getServer().getPluginManager().registerEvents(new GrinderNPCListener(this), this);
        getServer().getPluginManager().registerEvents(new SieveNPCListener(this), this);
        getServer().getPluginManager().registerEvents(new TurretNPCListener(this), this);
        getServer().getPluginManager().registerEvents(new TreeNPCListener(this), this);

        new BukkitRunnable(){
            @Override
            public void run() {
                File f = new File(JavaPlugin.getProvidingPlugin(AutomatonCitizens.class).getDataFolder() + File.separator + "data.yml");
                YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
                for (Iterator i = CitizensAPI.getNPCRegistry().iterator(); i.hasNext(); ) {
                    NPC npc = (NPC) i.next();
                    if (cfg.contains(String.valueOf(npc.getId()))) {
                        npc.data().set("owner", UUID.fromString((String) Objects.requireNonNull(cfg.get(npc.getId() + ".owner"))));
                        npc.data().set("state", 1);
                        npc.data().set("npctype", cfg.getString(npc.getId() + ".npctype"));
                        npc.data().set("base_x", cfg.getDouble(npc.getId() + ".base_x"));
                        npc.data().set("base_y", cfg.getDouble(npc.getId() + ".base_y"));
                        npc.data().set("base_z", cfg.getDouble(npc.getId() + ".base_z"));
                    }
                }
            }
        }.runTaskLater(this, 40);

        new TransportNPCRunningTask(this).runTaskTimer(this, 100, 20);
        new CobbleMinerNPCRunningTask(this).runTaskTimer(this, 100, 20);
        new GrinderNPCRunningTask(this).runTaskTimer(this, 100, 20);
        new SieveNPCRunningTask(this).runTaskTimer(this, 100, 20);
        new TurretNPCRunningTask(this).runTaskTimer(this, 100, 20);
        new TreeNPCRunningTask(this).runTaskTimer(this, 100, 20);

    }

    @Override
    public void onDisable() {
        File f = new File(this.getDataFolder() + File.separator + "data.yml");
        if(!f.exists()){
            try {
                f.mkdirs();
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
        for (Iterator i = CitizensAPI.getNPCRegistry().iterator(); i.hasNext(); ) {
            NPC npc = (NPC) i.next();
            if (npc.data().has("npctype")) {
                cfg.set(npc.getId() + ".owner", npc.data().get("owner").toString());
                cfg.set(npc.getId() + ".state", npc.data().get("state"));
                cfg.set(npc.getId() + ".npctype", npc.data().get("npctype").toString());
                cfg.set(npc.getId() + ".base_x", npc.data().get("base_x"));
                cfg.set(npc.getId() + ".base_y", npc.data().get("base_y"));
                cfg.set(npc.getId() + ".base_z", npc.data().get("base_z"));
            }
        }
        try {
            cfg.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
