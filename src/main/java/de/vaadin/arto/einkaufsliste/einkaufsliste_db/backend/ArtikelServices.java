package de.vaadin.arto.einkaufsliste.einkaufsliste_db.backend;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.Item;

public class ArtikelServices {
	
	private static List<Artikel> einkaufsliste = new ArrayList<Artikel>();
	
	public ArtikelServices(){
		
	}
	
	public static List<Artikel> getEinkaufsliste(){
		
		if(einkaufsliste != null) {
			einkaufsliste.clear();
		}
		
		einkaufsliste.add(new Artikel("Hose","2 Stück"));
		einkaufsliste.add(new Artikel("Toilettenpapier", "2 Packungen"));
		einkaufsliste.add(new Artikel("Waschmittel", "2 kl. Beutel"));
		einkaufsliste.add(new Artikel("Äpfel", "1 Kg"));
		einkaufsliste.add(new Artikel("Orangen", "6 Stück"));
		einkaufsliste.add(new Artikel("Tafel Schokolade", "3 Tafel"));
		einkaufsliste.add(new Artikel("Küchenrolle","2 Packungen"));
		einkaufsliste.add(new Artikel("Kiwi", "5 Stück"));
		
		return einkaufsliste;
	}
	
	
	public static void deleteArtikel(String artikelBez){
		if(einkaufsliste != null)
			for(int i = 0; i< einkaufsliste.size(); i++) {
				if(einkaufsliste.get(i).getBezeichnung().contains(artikelBez)) {
					einkaufsliste.remove(i);
				}
			}
	}
	
	public static List<Artikel> getUpdatedEinkaufsListe(){
		return einkaufsliste;
		
	}
	
	public static void addArtikel(Artikel artikel) {
		einkaufsliste.add(artikel);
	}

}
