package de.SebastianMikolai.PlanetFx.Einstellungen.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
}