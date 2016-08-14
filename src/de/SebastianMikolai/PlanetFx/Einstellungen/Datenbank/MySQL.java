package de.SebastianMikolai.PlanetFx.Einstellungen.Datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import de.SebastianMikolai.PlanetFx.Einstellungen.Einstellungen;

public class MySQL {

	public static Connection c = null;
	public static Statement database;
	public static Connection c_uuids = null;
	public static Statement database_uuids;
	
	public static void Connect() {
		try {
			c = DriverManager.getConnection("jdbc:mysql://" + Einstellungen.getInstance().getConfig().getString("database.host") + ":" + 
					Einstellungen.getInstance().getConfig().getInt("database.port") + "/" + Einstellungen.getInstance().getConfig().getString("database.db") + 
					"?user=" + Einstellungen.getInstance().getConfig().getString("database.user") + "&password=" + Einstellungen.getInstance().getConfig().getString("database.password"));
			database = c.createStatement();
			Einstellungen.getInstance().getLogger().info("Die Verbindung zur Datenbank wurde hergestellt!");
		} catch (Exception e) {
			Einstellungen.getInstance().getPluginLoader().disablePlugin(Einstellungen.getInstance());
		}
	}
	
	public static void LadeTabellen() {
		try {
			Statement stmt = c.createStatement();
			ResultSet rss = stmt.executeQuery("SHOW TABLES LIKE 'EinstellungenPlayerConfig'");
			if (rss.next()) {
				Einstellungen.getInstance().getLogger().info("Die Tabelle EinstellungenPlayerConfig wurde geladen!");
			} else {
				int rs = stmt.executeUpdate("CREATE TABLE EinstellungenPlayerConfig (id INTEGER PRIMARY KEY AUTO_INCREMENT, uuid TEXT, name TEXT, partikel INTEGER)");
				Einstellungen.getInstance().getLogger().info("Die Tabelle EinstellungenPlayerConfig wurde erstellt! (" + rs + ")");
			}
		} catch (SQLException e) {
			Einstellungen.getInstance().getPluginLoader().disablePlugin(Einstellungen.getInstance());
		}
	}
	
	public static PlayerKonfiguration getPlayerConfig(UUID uuid) {
		PlayerKonfiguration pk = null;
		try {
			Statement stmt = c.createStatement();
			ResultSet rss = stmt.executeQuery("SELECT * FROM EinstellungenPlayerConfig WHERE uuid='" + uuid + "'");
			while (rss.next()) {
				pk = new PlayerKonfiguration(uuid, rss.getString("name"), rss.getInt("partikel"));
			}
		} catch (SQLException e) {}
		return pk;
	}
	
	public static void addPlayerConfig(PlayerKonfiguration pk) {
		try {
			Statement stmt = c.createStatement();
			stmt.execute("INSERT INTO EinstellungenPlayerConfig (uuid, name, hideplayer, scoreboard, partikel) VALUES ('" + pk.getUUID() + "', '" + pk.name + "', '" + pk.partikel + "')");
		} catch (SQLException e) {}
	}

	public static void updatePlayerConfig(PlayerKonfiguration pk) {
		try {
			Statement stmt = c.createStatement();
			stmt.executeUpdate("UPDATE EinstellungenPlayerConfig SET name='" + pk.getName() + "', partikel='" + pk.partikel + "' WHERE uuid='" + pk.getUUID() + "'");
		} catch (SQLException e) {}
	}
}