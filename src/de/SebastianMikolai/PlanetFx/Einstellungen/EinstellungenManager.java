package de.SebastianMikolai.PlanetFx.Einstellungen;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.SebastianMikolai.PlanetFx.Einstellungen.Datenbank.MySQL;
import de.SebastianMikolai.PlanetFx.Einstellungen.Datenbank.PlayerKonfiguration;
import de.SebastianMikolai.PlanetFx.Einstellungen.Utils.ChatUtils;
import de.SebastianMikolai.PlanetFx.Einstellungen.Utils.ItemStacks;

public class EinstellungenManager {
	
	private static EinstellungenManager instance;
	public Integer slot = 8;
	public Integer size = 9;
	public String title = ChatColor.DARK_GREEN + "Einstellungen";
	public Map<Player, PlayerKonfiguration> PlayerConfig = new HashMap<Player, PlayerKonfiguration>();
	
	public static EinstellungenManager getInstance() {
		if (instance == null) {
			instance = new EinstellungenManager();
		}
		return instance;
	}
	
	public void setPlayerKonfiguration(Player p, PlayerKonfiguration pk) {
		PlayerConfig.put(p, pk);
		p.setScoreboard(Einstellungen.getInstance().sb);
		UsePartikel(p, pk.getShowParticel(), false);
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
	
	public void setShowParticel(Player p, boolean bool) {
		PlayerKonfiguration pk = PlayerConfig.get(p);
		if (pk != null) {
			pk.setParticel(bool);
			PlayerConfig.replace(p, pk);
		}
	}
	
	public void UseEinstellungen(Player p) {
		Inventory inv = Bukkit.createInventory(null, size, ChatColor.translateAlternateColorCodes('&', title));
		inv.setItem(0, ItemStacks.getParticel());
		inv.setItem(1, ItemStacks.getParticelOption(true));
		inv.setItem(2, ItemStacks.getParticelOption(false));
		inv.setItem(8, ItemStacks.getParticelOption(getShowParticel(p)));
		p.openInventory(inv);
	}
	
	public void UsePartikel(Player p, boolean pt, Boolean bool) {
		if (bool == true) {
			if (pt == true) {
				if (p.hasPermission("playerparticles.happyvillager")) {
					p.performCommand("pp happyvillager");
				} else if (p.hasPermission("playerparticles.reddust")) {
					p.performCommand("pp reddust");
				} else if (p.hasPermission("playerparticles.witchmagic")) {
					p.performCommand("pp witchmagic");
				} else if (p.hasPermission("playerparticles.flame")) {
					p.performCommand("pp flame");
				} else if (p.hasPermission("playerparticles.rainbow")) {
					p.performCommand("pp rainbow");
				} else if (p.hasPermission("playerparticles.enchantmenttable")) {
					p.performCommand("pp enchantmenttable");
				} else {
					p.closeInventory();
					ChatUtils.sendMessageConfig(p, "BuyVIP");
				}
			} else if (pt == false) {
				if (p.hasPermission("playerparticles.happyvillager")) {
					p.performCommand("pp clear");
				} else if (p.hasPermission("playerparticles.reddust")) {
					p.performCommand("pp clear");
				} else if (p.hasPermission("playerparticles.witchmagic")) {
					p.performCommand("pp clear");
				} else if (p.hasPermission("playerparticles.flame")) {
					p.performCommand("pp clear");
				} else if (p.hasPermission("playerparticles.rainbow")) {
					p.performCommand("pp clear");
				} else if (p.hasPermission("playerparticles.enchantmenttable")) {
					p.performCommand("pp clear");
				} else {
					p.closeInventory();
					ChatUtils.sendMessageConfig(p, "BuyVIP");
				}
			}
			if (p.hasPermission("playerparticles.happyvillager")) {
				setShowParticel(p, pt);
				UseEinstellungen(p);
			} else if (p.hasPermission("playerparticles.reddust")) {
				setShowParticel(p, pt);
				UseEinstellungen(p);
			} else if (p.hasPermission("playerparticles.witchmagic")) {
				setShowParticel(p, pt);
				UseEinstellungen(p);
			} else if (p.hasPermission("playerparticles.flame")) {
				setShowParticel(p, pt);
				UseEinstellungen(p);
			} else if (p.hasPermission("playerparticles.rainbow")) {
				setShowParticel(p, pt);
				UseEinstellungen(p);
			} else if (p.hasPermission("playerparticles.enchantmenttable")) {
				setShowParticel(p, pt);
				UseEinstellungen(p);
			}
		} else {
			if (pt == true) {
				if (p.hasPermission("playerparticles.happyvillager")) {
					p.performCommand("pp happyvillager");
				} else if (p.hasPermission("playerparticles.reddust")) {
					p.performCommand("pp reddust");
				} else if (p.hasPermission("playerparticles.witchmagic")) {
					p.performCommand("pp witchmagic");
				} else if (p.hasPermission("playerparticles.flame")) {
					p.performCommand("pp flame");
				} else if (p.hasPermission("playerparticles.rainbow")) {
					p.performCommand("pp rainbow");
				} else if (p.hasPermission("playerparticles.enchantmenttable")) {
					p.performCommand("pp enchantmenttable");
				}
			} else if (pt == false) {
				if (p.hasPermission("playerparticles.happyvillager")) {
					p.performCommand("pp clear");
				} else if (p.hasPermission("playerparticles.reddust")) {
					p.performCommand("pp clear");
				} else if (p.hasPermission("playerparticles.witchmagic")) {
					p.performCommand("pp clear");
				} else if (p.hasPermission("playerparticles.flame")) {
					p.performCommand("pp clear");
				} else if (p.hasPermission("playerparticles.rainbow")) {
					p.performCommand("pp clear");
				} else if (p.hasPermission("playerparticles.enchantmenttable")) {
					p.performCommand("pp clear");
				}
			}
		}
	}
}