package es.uvigo.esei.tfg.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

@SuppressWarnings({ "serial", "rawtypes" })
public class CountryItemRenderer extends JPanel implements ListCellRenderer {
	private JLabel labelItem = new JLabel();

	public CountryItemRenderer() {
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		constraints.insets = new Insets(2, 2, 2, 2);

		labelItem.setOpaque(true);
		labelItem.setHorizontalAlignment(JLabel.LEFT);

		add(labelItem, constraints);
		setBackground(Color.WHITE);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		String countryItem = (String) value;

		labelItem.setText(countryItem);

		labelItem.setIcon(new ImageIcon(getClass().getClassLoader().getResource("flags/png/" + countryItem + ".png")));

		if (isSelected) {
			labelItem.setBackground(Color.LIGHT_GRAY);
			labelItem.setForeground(Color.BLACK);
		} else {
			labelItem.setForeground(Color.BLACK);
			labelItem.setBackground(Color.WHITE);
		}

		return this;
	}

}