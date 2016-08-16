package de.SebastianMikolai.PlanetFx.Einstellungen.Datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import de.SebastianMikolai.PlanetFx.Einstellungen.Einstellungen;

public class MySQL {
	
	public static Connection Connect() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://" + Einstellungen.getInstance().getConfig().getString("database.host") + ":" + 
					Einstellungen.getInstance().getConfig().getInt("database.port") + "/" + Einstellungen.getInstance().getConfig().getString("database.db") + 
					"?user=" + Einstellungen.getInstance().getConfig().getString("database.user") + "&password=" + Einstellungen.getInstance().getConfig().getString("database.password"));
			Einstellungen.getInstance().getLogger().info("Die Verbindung zur Datenbank wurde hergestellt!");
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void Close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void LadeTabellen() {
		try {
			Connection con = Connect();
			Statement stmt = con.createStatement();
			ResultSet rss = stmt.executeQuery("SHOW TABLES LIKE 'EinstellungenPlayerConfig'");
			if (rss.next()) {
				Einstellungen.getInstance().getLogger().info("Die Tabelle EinstellungenPlayerConfig wurde geladen!");
			} else {
				int rs = stmt.executeUpdate("CREATE TABLE EinstellungenPlayerConfig (id INTEGER PRIMARY KEY AUTO_INCREMENT, uuid TEXT, name TEXT, partikel INTEGER)");
				Einstellungen.getInstance().getLogger().info("Die Tabelle EinstellungenPlayerConfig wurde erstellt! (" + rs + ")");
			}
			Close(con);
		} catch (SQLException e) {
			e.printStackTrace();
			Einstellungen.getInstance().getPluginLoader().disablePlugin(Einstellungen.getInstance());
		}
	}
	
	public static PlayerKonfiguration getPlayerConfig(UUID uuid) {
		try {
			PlayerKonfiguration pk = null;
			Connection con = Connect();
			if (con != null) {
				Statement stmt = con.createStatement();
				ResultSet rss = stmt.executeQuery("SELECT * FROM EinstellungenPlayerConfig WHERE uuid='" + uuid + "'");
				while (rss.next()) {
					pk = new PlayerKonfiguration(uuid, rss.getString("name"), rss.getInt("partikel"));
				}
			}
			Close(con);
			return pk;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void addPlayerConfig(PlayerKonfiguration pk) {
		try {
			Connection con = Connect();
			if (con != null) {
				Statement stmt = con.createStatement();
				stmt.execute("INSERT INTO EinstellungenPlayerConfig (uuid, name, partikel) VALUES ('" + pk.getUUID() + "', '" + pk.name + "', '" + pk.partikel + "')");
			}
			Close(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updatePlayerConfig(PlayerKonfiguration pk) {
		try {
			Connection con = Connect();
			if (con != null) {
				Statement stmt = con.createStatement();
				stmt.executeUpdate("UPDATE EinstellungenPlayerConfig SET name='" + pk.getName() + "', partikel='" + pk.partikel + "' WHERE uuid='" + pk.getUUID() + "'");
			}
			Close(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}