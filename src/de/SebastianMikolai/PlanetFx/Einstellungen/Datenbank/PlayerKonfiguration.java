package de.SebastianMikolai.PlanetFx.Einstellungen.Datenbank;

import java.util.UUID;

public class PlayerKonfiguration {
	
	private final UUID uuid;
	public String name;
	public Integer partikel;
	public String partikeltype;
	public Integer showscoreboard;
	public Integer hideplayers;
	
	public PlayerKonfiguration(UUID _uuid, String _name, Integer _partikel, String _partikeltype, Integer _showscoreboard, Integer _hideplayers) {
		uuid = _uuid;
		name = _name;
		partikel = _partikel;
		partikeltype = _partikeltype;
		showscoreboard = _showscoreboard;
		hideplayers = _hideplayers;
	}
	
	public UUID getUUID() {
		return uuid;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean getShowParticel() {
		boolean bool = true;
		if (partikel == 1) {
			bool = false;
		}
		return bool;
	}
	
	public Partikel getPartikel() {
		return Partikel.valueOf(partikeltype);
	}
	
	public void setName(String _name) {
		name = _name;
	}

	public void setParticel(boolean bool) {
		if (bool) {
			partikel = 0;
		} else if (!bool) {
			partikel = 1;
		}
	}
	
	public void setShowScoreboard(Boolean bool) {
		if (bool) {
			showscoreboard = 1;
		} else if (!bool) {
			showscoreboard = 0;
		}
	}
	
	public boolean getShowScoreboard() {
		boolean bool = true;
		if (showscoreboard == 0) {
			bool = false;
		}
		return bool;
	}
	
	public void setHidePlayers(Boolean bool) {
		if (bool) {
			hideplayers = 0;
		} else if (!bool) {
			hideplayers = 1;
		}
	}
	
	public boolean getHidePlayers() {
		boolean bool = true;
		if (hideplayers == 0) {
			bool = false;
		}
		return bool;
	}
	
	public void setPartikel(String _partikeltype) {
		partikeltype = _partikeltype;
	}
}