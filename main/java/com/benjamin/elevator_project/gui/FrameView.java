package com.benjamin.elevator_project.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

import com.benjamin.elevator_project.model.Model;
import com.benjamin.elevator_project.properties.PropertiesSettings;

/**
 * This is the frame view of the app that displays the GUI.
 * 
 * @author Team 3
 * @version 1.0.0
 * @since 2020-09-09
 *
 */
public class FrameView {
	private static final long serialVersionUID = 4590348855312450898L;
	private final int numLift = PropertiesSettings.getNumberOfElevators();
	private final int gHeight = 15;

	JFrame f;

	public FrameView(final int maxFloor) {

		f = new JFrame();

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Setting Frame width and height
		f.setSize(1080, 1050);

		// Setting the title of Frame
		f.setTitle("Team 3 - Elevator 1.0.0 - RELEASE");

		// Setting the layout for the Frame
		// setLayout(new FlowLayout());

		f.add(new JComponent() {
			{
				final Timer timer = new Timer(50, null);
				timer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						repaint();
					}
				});
				timer.start();
			}

			public void paintComponent(Graphics g) {
				int level = (getHeight() / (maxFloor + 1));
				for (int i = 1; i <= (maxFloor + 1); i++) {
					g.drawString(Integer.toString(i), 0, getHeight() - gHeight - level * i);
					g.drawLine(0, getHeight() - gHeight - level * i, f.getWidth(), getHeight() - gHeight - level * i);
				}

				g.setColor(Color.darkGray);
				g.fillRect(0, getHeight() - gHeight, getWidth(), gHeight);

				int lWidth = 50;
				int lHeight = 60;

				int sizeModifier = numLift / 10;

				if (sizeModifier > 1) {
					lWidth = lWidth / sizeModifier;
					lHeight = lHeight / sizeModifier;
				}

				for (int i = 0; i < numLift; i++) {

					if (Model.getElevator(i).isAvailable())
						g.setColor(Color.lightGray);
					else
						g.setColor(Color.darkGray);

					g.fillRect(getWidth() / 2 / numLift + (i * 2 * lWidth),
							(getHeight() - lHeight - gHeight) - Model.getElevator(i).getCurrentFloor() * level, lWidth,
							lHeight);
				}

			}
		});

		/*
		 * By default frame is not visible so we are setting the visibility to true to
		 * make it visible.
		 */
		f.setVisible(true);
	}
}
