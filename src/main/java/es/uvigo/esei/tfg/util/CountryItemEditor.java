package es.uvigo.esei.tfg.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxEditor;

public class CountryItemEditor extends BasicComboBoxEditor {
	private JPanel panel = new JPanel();
	private JLabel labelItem = new JLabel();
	private String selectedValue;

	public CountryItemEditor() {
		panel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		constraints.insets = new Insets(2, 5, 2, 2);

		labelItem.setForeground(Color.BLACK);
		labelItem.setHorizontalAlignment(JLabel.LEFT);
		labelItem.setOpaque(false);

		panel.add(labelItem, constraints);
		panel.setBackground(Color.WHITE);
	}

	public Component getEditorComponent() {
		return this.panel;
	}

	public Object getItem() {
		return this.selectedValue;
	}

	public void setItem(Object item) {
		if (item == null) {
			return;
		}
		String countryItem = (String) item;
		selectedValue = countryItem;
		labelItem.setText(selectedValue);
		labelItem.setIcon(new ImageIcon(getClass().getClassLoader().getResource("flags/png/" + countryItem + ".png")));
	}
}