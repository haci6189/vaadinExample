package de.vaadin.arto.einkaufsliste.einkaufsliste_db;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.vaadin.arto.einkaufsliste.einkaufsliste_db.backend.Artikel;
import de.vaadin.arto.einkaufsliste.einkaufsliste_db.backend.ArtikelServices;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	
	private FieldGroup group;

	// init methode 
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();
        
        final List<Artikel> einkaufsliste = ArtikelServices.getEinkaufsliste();
        
        final Grid gridList = new Grid("Meine Einkaufsliste");
        gridList.setSizeFull();
        
        final BeanItemContainer<Artikel> container = setOrUpdateList(einkaufsliste, gridList);
        
        final ArtikelEditor editor = new ArtikelEditor();
        editor.setWidth("100%");
       
        
        gridList.addSelectionListener(e -> {
        	Item artikel = container.getItem(gridList.getSelectedRow());
        	editor.setArtikel(artikel);
        });
        
        layout.addComponents(gridList, editor);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
        
        editor.setGridList(gridList);
    }

	public static BeanItemContainer<Artikel> setOrUpdateList(final List<Artikel> einkaufsliste, final Grid gridList) {
		final BeanItemContainer<Artikel> container = new BeanItemContainer<>(Artikel.class, einkaufsliste);
        gridList.setContainerDataSource(container);
		return container;
	}

    // Konfiguration
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
