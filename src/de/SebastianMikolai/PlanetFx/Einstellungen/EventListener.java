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

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;

import de.SebastianMikolai.PlanetFx.Einstellungen.Datenbank.MySQL;
import de.SebastianMikolai.PlanetFx.Einstellungen.Datenbank.PlayerKonfiguration;
import de.SebastianMikolai.PlanetFx.Einstellungen.Utils.ItemStacks;

public class EventListener implements Listener {
	
	private ProtocolManager protocolManager;
	
	public EventListener(ProtocolManager _protocolManager) {
		protocolManager = _protocolManager;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("pfx.tablist.leitung")) {
			Einstellungen.getInstance().sb.getTeam("000Leitung").addEntry(p.getName());
			p.setDisplayName(Einstellungen.getInstance().sb.getTeam("000Leitung").getPrefix() + p.getName());
		} else if (p.hasPermission("pfx.tablist.admin")) {
			Einstellungen.getInstance().sb.getTeam("001Admin").addEntry(p.getName());
			p.setDisplayName(Einstellungen.getInstance().sb.getTeam("001Admin").getPrefix() + p.getName());
		} else if (p.hasPermission("pfx.tablist.smod")) {
			Einstellungen.getInstance().sb.getTeam("002SMod").addEntry(p.getName());
			p.setDisplayName(Einstellungen.getInstance().sb.getTeam("002SMod").getPrefix() + p.getName());
		} else if (p.hasPermission("pfx.tablist.mod")) {
			Einstellungen.getInstance().sb.getTeam("003Mod").addEntry(p.getName());
			p.setDisplayName(Einstellungen.getInstance().sb.getTeam("003Mod").getPrefix() + p.getName());
		} else if (p.hasPermission("pfx.tablist.team")) {
			Einstellungen.getInstance().sb.getTeam("004Team").addEntry(p.getName());
			p.setDisplayName(Einstellungen.getInstance().sb.getTeam("004Team").getPrefix() + p.getName());
		} else if (p.hasPermission("pfx.tablist.vip")) {
			Einstellungen.getInstance().sb.getTeam("005Vip").addEntry(p.getName());
			p.setDisplayName(Einstellungen.getInstance().sb.getTeam("005Vip").getPrefix() + p.getName());
		} else {
			Einstellungen.getInstance().sb.getTeam("006Player").addEntry(p.getName());
			p.setDisplayName(Einstellungen.getInstance().sb.getTeam("006Player").getPrefix() + p.getName());
		}
		PacketContainer pc = this.protocolManager.createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
		pc.getChatComponents().write(0, WrappedChatComponent.fromText(ChatColor.translateAlternateColorCodes('&', "&2Akonia Gameserver\n&e" + Bukkit.getServerName())))
		.write(1, WrappedChatComponent.fromText(ChatColor.translateAlternateColorCodes('&', "&a»»»»» &6mc.akonia.de &a«««««")));
		try {
			this.protocolManager.sendServerPacket(p, pc);
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		p.getInventory().setItem(EinstellungenManager.getInstance().slot, ItemStacks.getEinstellungen());
		PlayerKonfiguration pk = MySQL.getPlayerConfig(p.getUniqueId());
		if (pk == null) {
			MySQL.addPlayerConfig(new PlayerKonfiguration(p.getUniqueId(), p.getName(), 1));
			Bukkit.getScheduler().scheduleSyncDelayedTask(Einstellungen.getInstance(), new Runnable() {
				@Override
				public void run() {
					EinstellungenManager.getInstance().setPlayerKonfiguration(p, new PlayerKonfiguration(p.getUniqueId(), p.getName(), 1));
					EinstellungenManager.getInstance().PlayerConfig.put(p, new PlayerKonfiguration(p.getUniqueId(), p.getName(), 1));
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
					}
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