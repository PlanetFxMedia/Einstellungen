package de.SebastianMikolai.PlanetFx.Einstellungen.Datenbank;

import java.util.UUID;

public class PlayerKonfiguration {
	
	private final UUID uuid;
	public String name;
	public Integer partikel;
	
	public PlayerKonfiguration(UUID _uuid, String _name, Integer _partikel) {
		uuid = _uuid;
		name = _name;
		partikel = _partikel;
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
	
	public void setName(String _name) {
		name = _name;
	}

	public void setParticel(boolean bool) {
		if (bool == true) {
			partikel = 0;
		} else if (bool == false) {
			partikel = 1;
		}
	}
}