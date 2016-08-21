package de.SebastianMikolai.PlanetFx.Einstellungen;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;

import de.SebastianMikolai.PlanetFx.Einstellungen.Datenbank.MySQL;
import de.SebastianMikolai.PlanetFx.Einstellungen.Datenbank.Partikel;
import de.SebastianMikolai.PlanetFx.Einstellungen.Datenbank.PlayerKonfiguration;
import de.SebastianMikolai.PlanetFx.Einstellungen.Utils.ChatUtils;
import de.SebastianMikolai.PlanetFx.Einstellungen.Utils.ItemStacks;

public class EinstellungenManager {
	
	private static EinstellungenManager instance;
	public Integer slot = 8;
	public Integer size = 27;
	public String title = ChatColor.DARK_GREEN + "Einstellungen";
	public String titlepartikel = ChatColor.DARK_GREEN + "Einstellungen: Partikel";
	public Map<Player, PlayerKonfiguration> PlayerConfig = new HashMap<Player, PlayerKonfiguration>();
	
	public static EinstellungenManager getInstance() {
		if (instance == null) {
			instance = new EinstellungenManager();
		}
		return instance;
	}
	
	public void setPlayerKonfiguration(Player p, PlayerKonfiguration pk) {
		PlayerConfig.put(p, pk);
		UsePartikel(p, pk.getShowParticel(), false);
		UseScoreboard(p, pk.getShowScoreboard(), false);
	}

	public void savePlayerKonfiguration(Player p) {
		PlayerKonfiguration pk = PlayerConfig.get(p);
		if (pk != null) {
			MySQL.updatePlayerConfig(pk);
		}
	}
	
	public boolean getShowParticel(Player p) {
		boolean bool = true;
		PlayerKonfiguration pk = PlayerConfig.get(p);
		if (pk != null) {
			bool = pk.getShowParticel();
		}
		return bool;
	}
	
	public Partikel getParticel(Player p) {
		Partikel partikel = Partikel.note;
		PlayerKonfiguration pk = PlayerConfig.get(p);
		if (pk != null) {
			partikel = pk.getPartikel();
		}
		return partikel;
	}
	
	public void setParticel(Player p, Partikel partikel) {
		PlayerKonfiguration pk = PlayerConfig.get(p);
		if (pk != null) {
			pk.setPartikel(partikel.toString());
		}
	}
	
	public void setShowParticel(Player p, boolean bool) {
		PlayerKonfiguration pk = PlayerConfig.get(p);
		if (pk != null) {
			pk.setParticel(bool);
		}
	}
	
	public boolean getShowScoreboard(Player p) {
		boolean bool = true;
		PlayerKonfiguration pk = PlayerConfig.get(p);
		if (pk != null) {
			bool = pk.getShowScoreboard();
		}
		return bool;
	}
	
	public void setShowScoreboard(Player p, boolean bool) {
		PlayerKonfiguration pk = PlayerConfig.get(p);
		if (pk != null) {
			pk.setShowScoreboard(bool);
		}
	}
	
	public boolean getHidePlayers(Player p) {
		boolean bool = true;
		PlayerKonfiguration pk = PlayerConfig.get(p);
		if (pk != null) {
			bool = pk.getHidePlayers();
		}
		return bool;
	}
	
	public void setHidePlayers(Player p, boolean bool) {
		PlayerKonfiguration pk = PlayerConfig.get(p);
		if (pk != null) {
			pk.setHidePlayers(bool);
		}
	}
	
	public void UseEinstellungen(Player p) {
		Inventory inv = Bukkit.createInventory(null, size, ChatColor.translateAlternateColorCodes('&', title));
		inv.setItem(0, ItemStacks.getParticel());
		inv.setItem(1, ItemStacks.getParticelOption(true));
		inv.setItem(2, ItemStacks.getParticelOption(false));
		inv.setItem(3, ItemStacks.getParticel(getParticel(p)));
		inv.setItem(8, ItemStacks.getParticelOption(getShowParticel(p)));
		
		inv.setItem(9, ItemStacks.getScoreboard());
		inv.setItem(10, ItemStacks.getScoreboardOption(true));
		inv.setItem(11, ItemStacks.getScoreboardOption(false));
		inv.setItem(17, ItemStacks.getScoreboardOption(getShowScoreboard(p)));
		
		inv.setItem(18, ItemStacks.getHidePlayers());
		inv.setItem(19, ItemStacks.getHidePlayersOption(true));
		inv.setItem(20, ItemStacks.getHidePlayersOption(false));
		inv.setItem(26, ItemStacks.getHidePlayersOption(getHidePlayers(p)));
		p.openInventory(inv);
	}
	
	public void UsePartikelAuswahl(Player p) {
		Inventory inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', titlepartikel));
		inv.setItem(0, ItemStacks.getParticel(Partikel.cloud));
		inv.setItem(1, ItemStacks.getParticel(Partikel.damageindicator));
		inv.setItem(2, ItemStacks.getParticel(Partikel.dragonbreath));
		inv.setItem(3, ItemStacks.getParticel(Partikel.enchantmenttable));
		inv.setItem(4, ItemStacks.getParticel(Partikel.endrod));
		inv.setItem(5, ItemStacks.getParticel(Partikel.explode));
		inv.setItem(6, ItemStacks.getParticel(Partikel.fireworksspark));
		inv.setItem(7, ItemStacks.getParticel(Partikel.flame));
		inv.setItem(8, ItemStacks.getParticel(Partikel.happyvillager));
		inv.setItem(9, ItemStacks.getParticel(Partikel.heart));
		inv.setItem(10, ItemStacks.getParticel(Partikel.lava));
		inv.setItem(11, ItemStacks.getParticel(Partikel.note));
		inv.setItem(12, ItemStacks.getParticel(Partikel.portal));
		inv.setItem(13, ItemStacks.getParticel(Partikel.rainbow));
		inv.setItem(14, ItemStacks.getParticel(Partikel.reddust));
		inv.setItem(15, ItemStacks.getParticel(Partikel.smoke));
		inv.setItem(16, ItemStacks.getParticel(Partikel.snowshovel));
		inv.setItem(17, ItemStacks.getParticel(Partikel.witchmagic));
		p.openInventory(inv);
	}
	
	public void UsePartikel(Player p, boolean show, Boolean bool) {
		if (show) {
			if (p.hasPermission("pfx.cs.vip")) {
				Partikel partikel = getParticel(p);
				String type = partikel.toString();
				p.performCommand("pp " + type);
			} else {
				p.closeInventory();
				ChatUtils.sendMessageConfig(p, "BuyVIP");
			}
		} else {
			if (p.hasPermission("pfx.cs.vip")) {
				p.performCommand("pp clear");					
			} else {
				p.closeInventory();
				ChatUtils.sendMessageConfig(p, "BuyVIP");
			}
		}
		if (bool && p.hasPermission("pfx.cs.vip")) {
			setShowParticel(p, show);
			UseEinstellungen(p);
		}
	}

	public void UseScoreboard(Player p, Boolean show, Boolean bool) {
		if (show) {
			ScoreboardManager m = Bukkit.getScoreboardManager();
			Scoreboard b = m.getNewScoreboard();
			b.registerNewTeam("000Leitung");
			b.registerNewTeam("001Admin");
			b.registerNewTeam("002SMod");
			b.registerNewTeam("003Mod");
			b.registerNewTeam("004Team");
			b.registerNewTeam("005Vip");
			b.registerNewTeam("006Player");
			b.getTeam("000Leitung").setPrefix(ChatColor.DARK_RED + "Leitung" + ChatColor.DARK_GRAY + " | " + ChatColor.DARK_RED);
			b.getTeam("001Admin").setPrefix(ChatColor.RED + "Admin" + ChatColor.DARK_GRAY + " | " + ChatColor.RED);
			b.getTeam("002SMod").setPrefix(ChatColor.BLUE + "SMod" + ChatColor.DARK_GRAY + " | " + ChatColor.BLUE);
			b.getTeam("003Mod").setPrefix(ChatColor.BLUE + "Mod" + ChatColor.DARK_GRAY + " | " + ChatColor.BLUE);
			b.getTeam("004Team").setPrefix(ChatColor.DARK_GREEN + "Team" + ChatColor.DARK_GRAY + " | " + ChatColor.DARK_GREEN);
			b.getTeam("005Vip").setPrefix(ChatColor.AQUA + "Vip" + ChatColor.DARK_GRAY + " | " + ChatColor.WHITE);
			b.getTeam("006Player").setPrefix(ChatColor.DARK_GRAY + "");
			Objective o = b.registerNewObjective("Einstellungen", "dummy");	
			int i = 6;
			o.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&2&lMinigames"));
			o.setDisplaySlot(DisplaySlot.SIDEBAR);
			o.getScore(" ").setScore(i--);
			o.getScore(ChatColor.translateAlternateColorCodes('&', "&6TeamSpeak")).setScore(i--);
			o.getScore(ChatColor.translateAlternateColorCodes('&', "&fts3.akonia.de")).setScore(i--);
			o.getScore("  ").setScore(i--);
			o.getScore(ChatColor.translateAlternateColorCodes('&', "&6Website")).setScore(i--);
			o.getScore(ChatColor.translateAlternateColorCodes('&', "&fwww.akonia.de")).setScore(i--);
			p.setScoreboard(b);
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (player.hasPermission("pfx.tablist.leitung")) {
					p.getScoreboard().getTeam("000Leitung").addEntry(player.getName());
					player.setDisplayName(p.getScoreboard().getTeam("000Leitung").getPrefix() + player.getName());
				} else if (player.hasPermission("pfx.tablist.admin")) {
					p.getScoreboard().getTeam("001Admin").addEntry(player.getName());
					player.setDisplayName(p.getScoreboard().getTeam("001Admin").getPrefix() + player.getName());
				} else if (player.hasPermission("pfx.tablist.smod")) {
					p.getScoreboard().getTeam("002SMod").addEntry(player.getName());
					player.setDisplayName(p.getScoreboard().getTeam("002SMod").getPrefix() + player.getName());
				} else if (player.hasPermission("pfx.tablist.mod")) {
					p.getScoreboard().getTeam("003Mod").addEntry(player.getName());
					player.setDisplayName(p.getScoreboard().getTeam("003Mod").getPrefix() + player.getName());
				} else if (player.hasPermission("pfx.tablist.team")) {
					p.getScoreboard().getTeam("004Team").addEntry(player.getName());
					player.setDisplayName(p.getScoreboard().getTeam("004Team").getPrefix() + player.getName());
				} else if (player.hasPermission("pfx.tablist.vip")) {
					p.getScoreboard().getTeam("005Vip").addEntry(player.getName());
					player.setDisplayName(p.getScoreboard().getTeam("005Vip").getPrefix() + player.getName());
				} else {
					p.getScoreboard().getTeam("006Player").addEntry(player.getName());
					player.setDisplayName(p.getScoreboard().getTeam("006Player").getPrefix() + player.getName());
				}
			}
			PacketContainer pc = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
			pc.getChatComponents().write(0, WrappedChatComponent.fromText(ChatColor.translateAlternateColorCodes('&', "&2Akonia Gameserver\n&e" + Bukkit.getServerName())))
			.write(1, WrappedChatComponent.fromText(ChatColor.translateAlternateColorCodes('&', "&a»»»»» &6mc.akonia.de &a«««««")));
			try {
				ProtocolLibrary.getProtocolManager().sendServerPacket(p, pc);
			} catch (Exception ex) {
				ex.printStackTrace();
			}	
		} else {
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			ScoreboardManager m = Bukkit.getScoreboardManager();
			Scoreboard b = m.getNewScoreboard();
			b.registerNewTeam("000Leitung");
			b.registerNewTeam("001Admin");
			b.registerNewTeam("002SMod");
			b.registerNewTeam("003Mod");
			b.registerNewTeam("004Team");
			b.registerNewTeam("005Vip");
			b.registerNewTeam("006Player");
			b.getTeam("000Leitung").setPrefix(ChatColor.DARK_RED + "Leitung" + ChatColor.DARK_GRAY + " | " + ChatColor.DARK_RED);
			b.getTeam("001Admin").setPrefix(ChatColor.RED + "Admin" + ChatColor.DARK_GRAY + " | " + ChatColor.RED);
			b.getTeam("002SMod").setPrefix(ChatColor.BLUE + "SMod" + ChatColor.DARK_GRAY + " | " + ChatColor.BLUE);
			b.getTeam("003Mod").setPrefix(ChatColor.BLUE + "Mod" + ChatColor.DARK_GRAY + " | " + ChatColor.BLUE);
			b.getTeam("004Team").setPrefix(ChatColor.DARK_GREEN + "Team" + ChatColor.DARK_GRAY + " | " + ChatColor.DARK_GREEN);
			b.getTeam("005Vip").setPrefix(ChatColor.AQUA + "Vip" + ChatColor.DARK_GRAY + " | " + ChatColor.WHITE);
			b.getTeam("006Player").setPrefix(ChatColor.DARK_GRAY + "");
			p.setScoreboard(b);
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (player.hasPermission("pfx.tablist.leitung")) {
					p.getScoreboard().getTeam("000Leitung").addEntry(player.getName());
					player.setDisplayName(p.getScoreboard().getTeam("000Leitung").getPrefix() + player.getName());
				} else if (player.hasPermission("pfx.tablist.admin")) {
					p.getScoreboard().getTeam("001Admin").addEntry(player.getName());
					player.setDisplayName(p.getScoreboard().getTeam("001Admin").getPrefix() + player.getName());
				} else if (player.hasPermission("pfx.tablist.smod")) {
					p.getScoreboard().getTeam("002SMod").addEntry(player.getName());
					player.setDisplayName(p.getScoreboard().getTeam("002SMod").getPrefix() + player.getName());
				} else if (player.hasPermission("pfx.tablist.mod")) {
					p.getScoreboard().getTeam("003Mod").addEntry(player.getName());
					player.setDisplayName(p.getScoreboard().getTeam("003Mod").getPrefix() + player.getName());
				} else if (player.hasPermission("pfx.tablist.team")) {
					p.getScoreboard().getTeam("004Team").addEntry(player.getName());
					player.setDisplayName(p.getScoreboard().getTeam("004Team").getPrefix() + player.getName());
				} else if (player.hasPermission("pfx.tablist.vip")) {
					p.getScoreboard().getTeam("005Vip").addEntry(player.getName());
					player.setDisplayName(p.getScoreboard().getTeam("005Vip").getPrefix() + player.getName());
				} else {
					p.getScoreboard().getTeam("006Player").addEntry(player.getName());
					player.setDisplayName(p.getScoreboard().getTeam("006Player").getPrefix() + player.getName());
				}
			}
			PacketContainer pc = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
			pc.getChatComponents().write(0, WrappedChatComponent.fromText(ChatColor.translateAlternateColorCodes('&', "&2Akonia Gameserver\n&e" + Bukkit.getServerName())))
			.write(1, WrappedChatComponent.fromText(ChatColor.translateAlternateColorCodes('&', "&a»»»»» &6mc.akonia.de &a«««««")));
			try {
				ProtocolLibrary.getProtocolManager().sendServerPacket(p, pc);
			} catch (Exception ex) {
				ex.printStackTrace();
			}	
		}
		if (bool) {
			setShowScoreboard(p, show);
			UseEinstellungen(p);
		}
	}
	
	public void UseHidePlayers(Player p, boolean hide, Boolean bool) {
		if (hide) {
			if (bool) {
				ChatUtils.sendMessage(p, ChatColor.GOLD + "&6Alle Spieler sind jetzt unsichtbar.");
			}
	    	for (Player player : Bukkit.getOnlinePlayers()) {
	    		if (!player.hasPermission("pfx.hideplayers.nohide")) {
	    			p.hidePlayer(player);
	    		} else {
	    			p.showPlayer(player);
	    		}
	    	}
		} else {
			if (bool) {
				ChatUtils.sendMessage(p, ChatColor.GOLD + "&6Alle Spieler sind jetzt sichtbar.");
			}
	    	for (Player player : Bukkit.getOnlinePlayers()) {
	    		p.showPlayer(player);
	    	}
		}
		if (bool) {
			setHidePlayers(p, hide);
			UseEinstellungen(p);
		}
	}
}