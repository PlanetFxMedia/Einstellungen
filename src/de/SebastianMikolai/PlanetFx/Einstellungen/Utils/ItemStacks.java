package de.SebastianMikolai.PlanetFx.Einstellungen.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.SebastianMikolai.PlanetFx.Einstellungen.Datenbank.Partikel;

public class ItemStacks {
	
	public static ItemStack getScoreboardOption(boolean bool) {
		if (bool) {
			ItemStack item = new ItemStack(Material.WOOL);
			item.setDurability((short)5);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&2Aktivieren"));
			item.setItemMeta(itemmeta);
			return item;
		} else {
			ItemStack item = new ItemStack(Material.WOOL);
			item.setDurability((short)14);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4Deaktivieren"));
			item.setItemMeta(itemmeta);
			return item;
		}
	}
	
	public static ItemStack getParticelOption(boolean bool) {
		if (bool) {
			ItemStack item = new ItemStack(Material.WOOL);
			item.setDurability((short)5);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&2An"));
			item.setItemMeta(itemmeta);
			return item;
		} else {
			ItemStack item = new ItemStack(Material.WOOL);
			item.setDurability((short)14);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4Aus"));
			item.setItemMeta(itemmeta);
			return item;
		}
	}
	
	public static ItemStack getParticel(Partikel partikel) {
		if (partikel == Partikel.cloud) {
			ItemStack item = new ItemStack(Material.WEB);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Partikel: Cloud"));
			itemmeta.addEnchant(Enchantment.KNOCKBACK, 10, false);
			item.setItemMeta(itemmeta);
			return item;
		} else if (partikel == Partikel.damageindicator) {
			ItemStack item = new ItemStack(Material.ANVIL);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Partikel: Damageindicator"));
			itemmeta.addEnchant(Enchantment.KNOCKBACK, 10, false);
			item.setItemMeta(itemmeta);
			return item;
		} else if (partikel == Partikel.dragonbreath) {
			ItemStack item = new ItemStack(Material.BLAZE_POWDER);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Partikel: Dragonbreath"));
			itemmeta.addEnchant(Enchantment.KNOCKBACK, 10, false);
			item.setItemMeta(itemmeta);
			return item;
		} else if (partikel == Partikel.enchantmenttable) {
			ItemStack item = new ItemStack(Material.ENCHANTMENT_TABLE);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Partikel: Enchantmenttable"));
			itemmeta.addEnchant(Enchantment.KNOCKBACK, 10, false);
			item.setItemMeta(itemmeta);
			return item;
		} else if (partikel == Partikel.endrod) {
			ItemStack item = new ItemStack(Material.ENDER_PORTAL_FRAME);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Partikel: Endrod"));
			itemmeta.addEnchant(Enchantment.KNOCKBACK, 10, false);
			item.setItemMeta(itemmeta);
			return item;
		} else if (partikel == Partikel.explode) {
			ItemStack item = new ItemStack(Material.TNT);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Partikel: Explode"));
			itemmeta.addEnchant(Enchantment.KNOCKBACK, 10, false);
			item.setItemMeta(itemmeta);
			return item;
		} else if (partikel == Partikel.fireworksspark) {
			ItemStack item = new ItemStack(Material.FIREWORK);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Partikel: Fireworksspark"));
			itemmeta.addEnchant(Enchantment.KNOCKBACK, 10, false);
			item.setItemMeta(itemmeta);
			return item;
		} else if (partikel == Partikel.flame) {
			ItemStack item = new ItemStack(Material.FIREBALL);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Partikel: Flame"));
			itemmeta.addEnchant(Enchantment.KNOCKBACK, 10, false);
			item.setItemMeta(itemmeta);
			return item;
		} else if (partikel == Partikel.happyvillager) {
			ItemStack item = new ItemStack(Material.EMERALD);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Partikel: Happyvillager"));
			itemmeta.addEnchant(Enchantment.KNOCKBACK, 10, false);
			item.setItemMeta(itemmeta);
			return item;
		} else if (partikel == Partikel.heart) {
			ItemStack item = new ItemStack(Material.APPLE);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Partikel: Heart"));
			itemmeta.addEnchant(Enchantment.KNOCKBACK, 10, false);
			item.setItemMeta(itemmeta);
			return item;
		} else if (partikel == Partikel.lava) {
			ItemStack item = new ItemStack(Material.LAVA_BUCKET);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Partikel: Lava"));
			itemmeta.addEnchant(Enchantment.KNOCKBACK, 10, false);
			item.setItemMeta(itemmeta);
			return item;
		} else if (partikel == Partikel.note) {
			ItemStack item = new ItemStack(Material.NOTE_BLOCK);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Partikel: Note"));
			itemmeta.addEnchant(Enchantment.KNOCKBACK, 10, false);
			item.setItemMeta(itemmeta);
			return item;
		} else if (partikel == Partikel.portal) {
			ItemStack item = new ItemStack(Material.ENDER_PEARL);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Partikel: Portal"));
			itemmeta.addEnchant(Enchantment.KNOCKBACK, 10, false);
			item.setItemMeta(itemmeta);
			return item;
		} else if (partikel == Partikel.rainbow) {
			ItemStack item = new ItemStack(Material.GOLDEN_APPLE);
			item.setDurability((short)1);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Partikel: Rainbow"));
			itemmeta.addEnchant(Enchantment.KNOCKBACK, 10, false);
			item.setItemMeta(itemmeta);
			return item;
		} else if (partikel == Partikel.reddust) {
			ItemStack item = new ItemStack(Material.REDSTONE);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Partikel: Reddust"));
			itemmeta.addEnchant(Enchantment.KNOCKBACK, 10, false);
			item.setItemMeta(itemmeta);
			return item;
		} else if (partikel == Partikel.smoke) {
			ItemStack item = new ItemStack(Material.FLINT);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Partikel: Smoke"));
			itemmeta.addEnchant(Enchantment.KNOCKBACK, 10, false);
			item.setItemMeta(itemmeta);
			return item;
		} else if (partikel == Partikel.snowshovel) {
			ItemStack item = new ItemStack(Material.SNOW_BALL);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Partikel: Snowshovel"));
			itemmeta.addEnchant(Enchantment.KNOCKBACK, 10, false);
			item.setItemMeta(itemmeta);
			return item;
		} else if (partikel == Partikel.witchmagic) {
			ItemStack item = new ItemStack(Material.BLAZE_ROD);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Partikel: Witchmagic"));
			itemmeta.addEnchant(Enchantment.KNOCKBACK, 10, false);
			item.setItemMeta(itemmeta);
			return item;
		} else {
			ItemStack item = new ItemStack(Material.NOTE_BLOCK);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Partikel: Note"));
			itemmeta.addEnchant(Enchantment.KNOCKBACK, 10, false);
			item.setItemMeta(itemmeta);
			return item;
		}
	}
	
	public static ItemStack getEinstellungen() {
		ItemStack item = new ItemStack(Material.GLOWSTONE_DUST);
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta.setDisplayName(ChatColor.GOLD + "Einstellungen");
		item.setItemMeta(itemmeta);
		return item;
	}
	
	public static ItemStack getSichtbarkeit() {
		ItemStack item = new ItemStack(Material.EYE_OF_ENDER);
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta.setDisplayName(ChatColor.GOLD + "Sichtbarkeit");
		item.setItemMeta(itemmeta);
		return item;
	}
	
	public static ItemStack getHidePlayersOption(boolean bool) {
		if (bool) {
			ItemStack item = new ItemStack(Material.WOOL);
			item.setDurability((short)5);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.DARK_GREEN + "Jeden anzeigen");
			item.setItemMeta(itemmeta);
			return item;
		} else {
			ItemStack item = new ItemStack(Material.WOOL);
			item.setDurability((short)14);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(ChatColor.DARK_RED + "Niemanden anzeigen");
			item.setItemMeta(itemmeta);
			return item;
		}
	}
	
	public static ItemStack getScoreboard() {
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta.setDisplayName(ChatColor.GOLD + "Scoreboard");
		item.setItemMeta(itemmeta);
		return item;
	}
	
	public static ItemStack getParticel() {
		ItemStack item = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta.setDisplayName(ChatColor.GOLD + "Partikeleffekte");
		item.setItemMeta(itemmeta);
		return item;
	}
	
	public static ItemStack getHidePlayers() {
		ItemStack item = new ItemStack(Material.EYE_OF_ENDER);
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta.setDisplayName(ChatColor.GOLD + "Sichtbarkeit");
		item.setItemMeta(itemmeta);
		return item;
	}
}