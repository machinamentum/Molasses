/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.asteroids;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.joshuahuelsman.molasses.AppGameContainer;

public class PlayFrame extends JFrame {

	private static final long serialVersionUID = 6601934618963373986L;
	
	private AppGameContainer gc;
	
	private AsteroidsGame game;
	
	private JTextField textField;
	
	public PlayFrame() {
		JLabel nameLabel = new JLabel("Username:");
		textField = new JTextField();
		textField.setText("P1");
		
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.LINE_AXIS));
		textPanel.add(nameLabel);
		textPanel.add(textField);
		
		JButton button = new JButton("Play!");
		button.setActionCommand("play");
		button.addActionListener(new ButtonHandler());
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(button);
		
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		add(textPanel);
		add(buttonPanel);
		pack();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	
	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("play")) {
				PlayFrame.this.setVisible(false);
				game = new AsteroidsGame();
				game.setPlayerName((!textField.getText().equals("") ? textField.getText() : "P1"));
				gc = new AppGameContainer(game);
				gc.start();
			}
		}
		
	}
}
