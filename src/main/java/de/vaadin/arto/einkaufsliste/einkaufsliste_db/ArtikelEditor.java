package de.vaadin.arto.einkaufsliste.einkaufsliste_db;

import java.util.List;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;

import de.vaadin.arto.einkaufsliste.einkaufsliste_db.backend.Artikel;
import de.vaadin.arto.einkaufsliste.einkaufsliste_db.backend.ArtikelServices;

public class ArtikelEditor extends ArtikelEditorDesign {

	private FieldGroup group;
	private Grid gridList;
	Item artikel;

	public ArtikelEditor() {

		// Update items
		buttonSave.addClickListener(e -> {
			if(!bezeichnung.isEmpty()) {
				try {
					group.commit();
					Notification.show("Einkaufsliste bearbeitet");
					gridList.deselectAll();
					bezeichnung.clear();
					menge.clear();
				} catch (CommitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else {
				Notification.show("Bitte einen Artikel aus der Einkaufsliste auswählen");
			}
				
		});

		// add items
		buttonAdd.addClickListener(e -> {
			String bez;
			String me;

			bez = bezeichnung.getValue();
			me = menge.getValue();

			if (bez.equalsIgnoreCase("") && me.equals("") || me == null || bez.isEmpty()) {
				Notification.show("Bitte Felder ausfüllen.");
			}else {
				
				Artikel newArtikel = new Artikel(bez, me);
				ArtikelServices.addArtikel(newArtikel);
				List<Artikel> einkListe = ArtikelServices.getUpdatedEinkaufsListe();
				BeanItemContainer<Artikel> container = MyUI.setOrUpdateList(einkListe, gridList);
				bezeichnung.clear();
				menge.clear();
				
				
			}

		});

		// delete item and refresh gridList
		buttonDel.addClickListener(e -> {
			
			String bez = bezeichnung.getValue();
			String anz = menge.getValue();
			
			if(bez.isEmpty() || anz.isEmpty()) {
				Notification.show("Bitte einen Artikel aus der Einkaufsliste auswählen.");
			}else{
				String artikel = group.getItemDataSource().toString();

				String[] output = artikel.split(" ");
				ArtikelServices.deleteArtikel(output[0]);
				List<Artikel> einkListe = ArtikelServices.getUpdatedEinkaufsListe();
				MyUI.setOrUpdateList(einkListe, gridList);
				bezeichnung.clear();
				menge.clear();				
			}
		});
		
		
		// cancel editing
		buttonCancel.addClickListener(e -> {
	
				bezeichnung.clear();
				menge.clear();				
				gridList.deselectAll();
		});

	}

	public void setArtikel(Item artikel) {
		group = new FieldGroup();
		group.setItemDataSource(artikel);
		group.buildAndBindMemberFields(this);
		this.artikel = artikel;

	}

	public void setGridList(Grid glist) {
		gridList = new Grid();
		this.gridList = glist;
	}

}
