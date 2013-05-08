/*
 * This file was written by Joshua Huelsman. Do whatever you want
 * with this stuff. If you think it's worth it, and we someday
 * meet, you can buy me an energy drink.
 */
package com.joshuahuelsman.molasses.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

public class InputProvider implements KeyListener {
	
	private HashMap<Integer, Command> map;
	
	private ArrayList<InputProviderListener> listeners;

	public InputProvider() {
		map = new HashMap<Integer, Command>();
		listeners = new ArrayList<InputProviderListener>();
	}
	
	/**
	 * Binds the specified key on the keyboard to a Command.
	 * @param keyCode the keycode of the key to bind.
	 * @param command the Command to bind the key to.
	 */
	public void bindCommand(int keyCode, Command command) {
		map.put(keyCode, command);
	}
	
	/**
	 * Adds an InputProviderListener to the listeners list.
	 * @param listener the listener to add.
	 */
	public void addListener(InputProviderListener listener) {
		if(!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}
	
	/**
	 * Remove an InputPoviderListener from the listeners list.
	 * @param listener the listener to remove.
	 */
	public void removeListener(InputProviderListener listener) {
		if(listeners.contains(listener)){
			listeners.remove(listener);
		}
	}
	
	/**
	 * Clears the list of InputProviderListener.
	 */
	public void removeAll() {
		listeners.clear();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Command command = map.get(e.getKeyCode());
		if(command != null) {
			for(InputProviderListener l : listeners) {
				l.controlPressed(command);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Command command = map.get(e.getKeyCode());
		if(command != null) {
			for(InputProviderListener l : listeners) {
				l.controlReleased(command);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
