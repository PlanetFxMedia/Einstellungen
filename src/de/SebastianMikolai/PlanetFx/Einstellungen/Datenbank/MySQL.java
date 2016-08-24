package de.SebastianMikolai.PlanetFx.Einstellungen.Datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import de.SebastianMikolai.PlanetFx.Einstellungen.Einstellungen;

public class MySQL {

	public static Connection con;
	
	public static Connection Connect() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://" + Einstellungen.getInstance().getConfig().getString("database.host") + ":" + 
					Einstellungen.getInstance().getConfig().getInt("database.port") + "/" + Einstellungen.getInstance().getConfig().getString("database.db") + "?autoReconnect=true", Einstellungen.getInstance().getConfig().getString("database.user"), Einstellungen.getInstance().getConfig().getString("database.password"));
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Connection getConnection() {
		try {
			if (con == null) {
				con = Connect();
			} else if (con.isClosed()) {
				con = Connect();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void LadeTabellen() {
		try {
			Connection c = getConnection();
			Statement stmt = c.createStatement();
			ResultSet rss = stmt.executeQuery("SHOW TABLES LIKE 'EinstellungenPlayerConfig'");
			if (rss.next()) {
				Einstellungen.getInstance().getLogger().info("Die Tabelle EinstellungenPlayerConfig wurde geladen!");
			} else {
				int rs = stmt.executeUpdate("CREATE TABLE EinstellungenPlayerConfig (id INTEGER PRIMARY KEY AUTO_INCREMENT, uuid TEXT, name TEXT, partikel INTEGER, partikeltype TEXT, showscoreboard INTEGER, hideplayers INTEGER)");
				Einstellungen.getInstance().getLogger().info("Die Tabelle EinstellungenPlayerConfig wurde erstellt! (" + rs + ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static PlayerKonfiguration getPlayerConfig(UUID uuid) {
		try {
			PlayerKonfiguration pk = null;
			Connection c = getConnection();
			Statement stmt = c.createStatement();
			ResultSet rss = stmt.executeQuery("SELECT * FROM EinstellungenPlayerConfig WHERE uuid='" + uuid + "'");
			while (rss.next()) {
				pk = new PlayerKonfiguration(uuid, rss.getString("name"), rss.getInt("partikel"), rss.getString("partikeltype"), rss.getInt("showscoreboard"), rss.getInt("hideplayers"));
			}
			return pk;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void addPlayerConfig(PlayerKonfiguration pk) {
		try {
			Connection c = getConnection();
			Statement stmt = c.createStatement();
			stmt.execute("INSERT INTO EinstellungenPlayerConfig (uuid, name, partikel, partikeltype, showscoreboard, hideplayers) VALUES ('" + pk.getUUID() + "', '" + pk.name + "', '" + pk.partikel + "', '" + pk.getPartikel() + "', '" + pk.showscoreboard + "', '" + pk.hideplayers + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updatePlayerConfig(PlayerKonfiguration pk) {
		try {
			Connection c = getConnection();
			Statement stmt = c.createStatement();
			stmt.executeUpdate("UPDATE EinstellungenPlayerConfig SET name='" + pk.getName() + "', partikel='" + pk.partikel + "', partikeltype='" + pk.getPartikel() + "', showscoreboard='" + pk.showscoreboard + "', hideplayers='" + pk.hideplayers + "' WHERE uuid='" + pk.getUUID() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}