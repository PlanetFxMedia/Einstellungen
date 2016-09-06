package de.SebastianMikolai.PlanetFx.Einstellungen;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.SebastianMikolai.PlanetFx.Einstellungen.Datenbank.MySQL;

public class Einstellungen extends JavaPlugin {
	
	public static Einstellungen instance;
	public String server;
	
	public static Einstellungen getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(new EventListener(),  this);
		server = Bukkit.getServerName();
		MySQL.Connect();
		MySQL.LadeTabellen();
	}
	
	@Override
	public void onDisable() {
		try {
			MySQL.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}