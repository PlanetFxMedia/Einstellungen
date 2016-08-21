package de.SebastianMikolai.PlanetFx.Einstellungen;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.SebastianMikolai.PlanetFx.Einstellungen.Datenbank.MySQL;
import de.SebastianMikolai.PlanetFx.Einstellungen.Datenbank.Partikel;
import de.SebastianMikolai.PlanetFx.Einstellungen.Datenbank.PlayerKonfiguration;
import de.SebastianMikolai.PlanetFx.Einstellungen.Utils.ItemStacks;

public class EventListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (p.hasPermission("pfx.tablist.leitung")) {
				player.getScoreboard().getTeam("000Leitung").addEntry(p.getName());
				p.setDisplayName(player.getScoreboard().getTeam("000Leitung").getPrefix() + p.getName());
			} else if (p.hasPermission("pfx.tablist.admin")) {
				player.getScoreboard().getTeam("001Admin").addEntry(p.getName());
				p.setDisplayName(player.getScoreboard().getTeam("001Admin").getPrefix() + p.getName());
			} else if (p.hasPermission("pfx.tablist.smod")) {
				player.getScoreboard().getTeam("002SMod").addEntry(p.getName());
				p.setDisplayName(player.getScoreboard().getTeam("002SMod").getPrefix() + p.getName());
			} else if (p.hasPermission("pfx.tablist.mod")) {
				player.getScoreboard().getTeam("003Mod").addEntry(p.getName());
				p.setDisplayName(player.getScoreboard().getTeam("003Mod").getPrefix() + p.getName());
			} else if (p.hasPermission("pfx.tablist.team")) {
				player.getScoreboard().getTeam("004Team").addEntry(p.getName());
				p.setDisplayName(player.getScoreboard().getTeam("004Team").getPrefix() + p.getName());
			} else if (p.hasPermission("pfx.tablist.vip")) {
				player.getScoreboard().getTeam("005Vip").addEntry(p.getName());
				p.setDisplayName(player.getScoreboard().getTeam("005Vip").getPrefix() + p.getName());
			} else {
				player.getScoreboard().getTeam("006Player").addEntry(p.getName());
				p.setDisplayName(player.getScoreboard().getTeam("006Player").getPrefix() + p.getName());
			}
		}
		p.getInventory().setItem(EinstellungenManager.getInstance().slot, ItemStacks.getEinstellungen());
		PlayerKonfiguration pk = MySQL.getPlayerConfig(p.getUniqueId());
		if (pk == null) {
			MySQL.addPlayerConfig(new PlayerKonfiguration(p.getUniqueId(), p.getName(), 1, Partikel.note.toString(), 1, 1));
			Bukkit.getScheduler().scheduleSyncDelayedTask(Einstellungen.getInstance(), new Runnable() {
				@Override
				public void run() {
					EinstellungenManager.getInstance().setPlayerKonfiguration(p, new PlayerKonfiguration(p.getUniqueId(), p.getName(), 1, Partikel.note.toString(), 1, 1));
					EinstellungenManager.getInstance().PlayerConfig.put(p, new PlayerKonfiguration(p.getUniqueId(), p.getName(), 1, Partikel.note.toString(), 1, 1));
				}
			}, 20L);
		} else {
			Bukkit.getScheduler().scheduleSyncDelayedTask(Einstellungen.getInstance(), new Runnable() {
				@Override
				public void run() {
					EinstellungenManager.getInstance().setPlayerKonfiguration(p, pk);
					EinstellungenManager.getInstance().PlayerConfig.put(p, pk);
				}
			}, 20L);
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		for (Player player : Bukkit.getOnlinePlayers()) {
    		p.showPlayer(player);
    	}
		EinstellungenManager.getInstance().savePlayerKonfiguration(p);
		EinstellungenManager.getInstance().PlayerConfig.remove(p);
	}
	
	@EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getItem() != null && e.getItem().isSimilar(ItemStacks.getEinstellungen())) {
			EinstellungenManager.getInstance().UseEinstellungen(p);
		}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			Player p = (Player)e.getWhoClicked();
			if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', EinstellungenManager.getInstance().title))) {
				e.setCancelled(true);
				if (e.getCurrentItem() != null) {
					if (e.getCurrentItem().isSimilar(ItemStacks.getParticelOption(true))) {
						EinstellungenManager.getInstance().UsePartikel(p, true, true);
					} else if (e.getCurrentItem().isSimilar(ItemStacks.getParticelOption(false))) {
						EinstellungenManager.getInstance().UsePartikel(p, false, true);
					} else if (e.getCurrentItem().isSimilar(ItemStacks.getParticel(EinstellungenManager.getInstance().getParticel(p)))) {
						EinstellungenManager.getInstance().UsePartikelAuswahl(p);
					} else if (e.getCurrentItem().isSimilar(ItemStacks.getScoreboardOption(true))) {
						EinstellungenManager.getInstance().UseScoreboard(p, true, true);
					} else if (e.getCurrentItem().isSimilar(ItemStacks.getScoreboardOption(false))) {
						EinstellungenManager.getInstance().UseScoreboard(p, false, true);
					} else if (e.getCurrentItem().isSimilar(ItemStacks.getHidePlayersOption(true))) {
						EinstellungenManager.getInstance().UseHidePlayers(p, false, true);
					} else if (e.getCurrentItem().isSimilar(ItemStacks.getHidePlayersOption(false))) {
						EinstellungenManager.getInstance().UseHidePlayers(p, true, true);
					}
				}
			} else if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', EinstellungenManager.getInstance().titlepartikel))) {
				e.setCancelled(true);
				if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains("Partikel: ")) {
					Partikel partikel = Partikel.valueOf(e.getCurrentItem().getItemMeta().getDisplayName().split(":")[1].replace(" ", "").toLowerCase());
					EinstellungenManager.getInstance().setParticel(p, partikel);
					EinstellungenManager.getInstance().UsePartikel(p, true, true);
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		if (e.getItemDrop().getItemStack().isSimilar(ItemStacks.getEinstellungen())) {
			e.setCancelled(true);
		}
	}
}