package de.SebastianMikolai.PlanetFx.Einstellungen;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.comphenix.protocol.ProtocolLibrary;

import de.SebastianMikolai.PlanetFx.Einstellungen.Datenbank.MySQL;

public class Einstellungen extends JavaPlugin {
	
	public static Einstellungen instance;
	public Scoreboard sb;
	public String server;
	
	public static Einstellungen getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(new EventListener(ProtocolLibrary.getProtocolManager()),  this);
		server = Bukkit.getServerName();
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
		sb = b;
		MySQL.Connect();
		MySQL.LadeTabellen();
	}
}