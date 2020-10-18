package me.kei.citizens.automaton.item;

import me.kei.citizens.automaton.AutomatonCitizens;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class AutomatonItemFactory {

    public static ItemStack transport_npc_egg;
    public static ItemStack cobbleminer_npc_egg;
    public static ItemStack grinder_npc_egg;
    public static ItemStack sieve_npc_egg;
    public static ItemStack turret_npc_egg;
    public static ItemStack tree_npc_egg;

    public static void init(AutomatonCitizens plugin){

        transport_npc_egg = new ItemStack(Material.PUFFERFISH_SPAWN_EGG);
        ItemMeta meta = transport_npc_egg.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + "" + ChatColor.AQUA + "運搬奴隷の卵");
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        transport_npc_egg.setItemMeta(meta);
        Bukkit.resetRecipes();
        NamespacedKey key = new NamespacedKey(plugin, "transport_npc_spawn_egg");
        ShapedRecipe recipe = new ShapedRecipe(key, transport_npc_egg);
        recipe.shape("DID", "IGI", "DID");
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('I', Material.IRON_BLOCK);
        recipe.setIngredient('G', Material.GOLD_BLOCK);
        Bukkit.addRecipe(recipe);

        cobbleminer_npc_egg = new ItemStack(Material.PUFFERFISH_SPAWN_EGG);
        meta = cobbleminer_npc_egg.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + "" + ChatColor.AQUA + "採掘奴隷の卵");
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        cobbleminer_npc_egg.setItemMeta(meta);
        key = new NamespacedKey(plugin, "cobbleminer_npc_spawn_egg");
        recipe = new ShapedRecipe(key, cobbleminer_npc_egg);
        recipe.shape("DID", "IGI", "DID");
        recipe.setIngredient('D', Material.IRON_PICKAXE);
        recipe.setIngredient('I', Material.IRON_BLOCK);
        recipe.setIngredient('G', Material.DIAMOND_PICKAXE);
        Bukkit.addRecipe(recipe);

        grinder_npc_egg = new ItemStack(Material.PUFFERFISH_SPAWN_EGG);
        meta = grinder_npc_egg.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + "" + ChatColor.AQUA + "粉砕奴隷の卵");
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        grinder_npc_egg.setItemMeta(meta);
        key = new NamespacedKey(plugin, "grinder_npc_spawn_egg");
        recipe = new ShapedRecipe(key, grinder_npc_egg);
        recipe.shape("DID", "IGI", "DID");
        recipe.setIngredient('D', Material.IRON_SHOVEL);
        recipe.setIngredient('I', Material.IRON_BLOCK);
        recipe.setIngredient('G', Material.DIAMOND_SHOVEL);
        Bukkit.addRecipe(recipe);

        sieve_npc_egg = new ItemStack(Material.PUFFERFISH_SPAWN_EGG);
        meta = sieve_npc_egg.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + "" + ChatColor.AQUA + "篩い奴隷の卵");
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        sieve_npc_egg.setItemMeta(meta);
        key = new NamespacedKey(plugin, "sieve_npc_spawn_egg");
        recipe = new ShapedRecipe(key, sieve_npc_egg);
        recipe.shape("DID", "IGI", "DID");
        recipe.setIngredient('D', Material.IRON_PICKAXE);
        recipe.setIngredient('I', Material.IRON_BLOCK);
        recipe.setIngredient('G', Material.STRING);
        Bukkit.addRecipe(recipe);

        turret_npc_egg = new ItemStack(Material.PUFFERFISH_SPAWN_EGG);
        meta = turret_npc_egg.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + "" + ChatColor.AQUA + "タレット奴隷の卵");
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        turret_npc_egg.setItemMeta(meta);
        key = new NamespacedKey(plugin, "turret_npc_spawn_egg");
        recipe = new ShapedRecipe(key, turret_npc_egg);
        recipe.shape("DID", "IGI", "DID");
        recipe.setIngredient('D', Material.BOW);
        recipe.setIngredient('I', Material.IRON_BLOCK);
        recipe.setIngredient('G', Material.ARROW);
        Bukkit.addRecipe(recipe);

        tree_npc_egg = new ItemStack(Material.PUFFERFISH_SPAWN_EGG);
        meta = tree_npc_egg.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + "" + ChatColor.AQUA + "木材奴隷の卵");
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        tree_npc_egg.setItemMeta(meta);
        key = new NamespacedKey(plugin, "tree_npc_spawn_egg");
        recipe = new ShapedRecipe(key, tree_npc_egg);
        recipe.shape("DID", "IGI", "DID");
        recipe.setIngredient('D', Material.DIAMOND_AXE);
        recipe.setIngredient('I', Material.IRON_BLOCK);
        recipe.setIngredient('G', Material.IRON_AXE);
        Bukkit.addRecipe(recipe);
    }

}
