package es.uvigo.esei.tfg.util;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

@SuppressWarnings({ "rawtypes", "serial" })
public class CountryComboBox extends JComboBox {
	private DefaultComboBoxModel model;

	@SuppressWarnings("unchecked")
	public CountryComboBox() {
		model = new DefaultComboBoxModel();
		setModel(model);
		setRenderer(new CountryItemRenderer());
		setEditor(new CountryItemEditor());
	}

	@SuppressWarnings("unchecked")
	public void addItems(List<String> items) {
		for (String anItem : items) {
			model.addElement(anItem);
		}
	}
}