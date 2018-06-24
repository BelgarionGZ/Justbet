package es.uvigo.esei.tfg.util;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JComponent;

import es.uvigo.esei.tfg.gui.FrameMain;

public class FrameResize {
	public static void componentListener(final FrameMain frame, final int lowWidth, final int highWidth,
			final int lowHeight, final int highHeight, final JComponent component, final String type) {
		frame.getFrame().addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent e) {
				resize(frame, lowWidth, highWidth, lowHeight, highHeight, component, type);
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				resize(frame, lowWidth, highWidth, lowHeight, highHeight, component, type);
			}

			@Override
			public void componentShown(ComponentEvent e) {
				resize(frame, lowWidth, highWidth, lowHeight, highHeight, component, type);
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				resize(frame, lowWidth, highWidth, lowHeight, highHeight, component, type);
			}
		});
	}

	public static void resize(FrameMain frame, int lowWidth, int highWidth, int lowHeight, int highHeight,
			JComponent component, String type) {
		Dimension frameSize = frame.getFrame().getSize();
		switch (type) {
		case "setMaximumSize":
			if (frameSize.getWidth() <= 1500) {
				component.setMaximumSize(new Dimension(lowWidth, lowHeight));
			} else {
				component.setMaximumSize(new Dimension(highWidth, highHeight));
			}
			break;
		case "setPreferredSize":
			if (frameSize.getWidth() <= 1500) {
				component.setPreferredSize(new Dimension(lowWidth, lowHeight));
			} else {
				component.setPreferredSize(new Dimension(highWidth, highHeight));
			}
			break;
		}

		component.revalidate();
		component.repaint();
	}
}