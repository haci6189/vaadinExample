package de.vaadin.arto.einkaufsliste.einkaufsliste_db.backend;

public class Artikel {
	
	private String bezeichnung;
	
	private String menge;
	
	public Artikel(String bezeichnung, String menge) {
		this.bezeichnung = bezeichnung;
		this.menge = menge;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getMenge() {
		return menge;
	}

	public void setMenge(String menge) {
		this.menge = menge;
	}
	
	

}
