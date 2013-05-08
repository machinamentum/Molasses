/*
 * This file was written by Joshua Huelsman. Do whatever you want
 * with this stuff. If you think it's worth it, and we someday
 * meet, you can buy me an energy drink.
 */
package com.joshuahuelsman.molasses;

import java.awt.Graphics;

import com.joshuahuelsman.molasses.display.Display;
import com.joshuahuelsman.molasses.input.InputProvider;

/**
 * A container for a game instance that manages the game's updates
 * and updating the {@link Display}.
 * @author joshuahuelsman
 *
 */
public abstract class GameContainer extends Thread {

	private Game theGame;
	
	private InputProvider inputProvider;
	
	private boolean running = true;
	
	public GameContainer(Game game) {
		lastFPS = getTime();
		//Initializes the timer, otherwise the first dt
		//is massive.
		getDelta();
		inputProvider = new InputProvider();
		theGame = game;
		
	}
	
	private long currentFPS = 0;
	
	private long lastFrame;
	
	public long lastFPS;

	public long fps;

	
	protected void update() {
		updateFPS();
		theGame.update(this, getDelta());
		theGame.render(this, getGraphics());
		Display.update();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	private void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			//Display.setTitle("FPS: " + getFPS());
			currentFPS = fps;
			fps = 0; // reset the FPS counter
			lastFPS += 1000; // add one second
		}
		fps++;
	}

	/**
	 * @return the current Unix time in milliseconds.
	 */
	public long getTime() {
		return System.nanoTime() / 1000000;
	}

	/**
	 * @return the change in time since the last time
	 * this method was called.
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}
	
	/**
	 * @return the frames per second that the
	 * game is updating.
	 */
	public long getFPS() {
		return currentFPS;
	}
	
	/**
	 * @return the width of the drawing area.
	 */
	public int getWidth() {
		return Display.getDisplayMode().getWidth();
	}
	
	/**
	 * @return the height of the drawing area.
	 */
	public int getHeight() {
		return Display.getDisplayMode().getHeight();
	}
	
	public InputProvider getInputProvider() {
		return inputProvider;
	}
	
	/**
	 * @return the Graphics implementation of this
	 * GameContainer.
	 */
	public abstract Graphics getGraphics();
	
	public Game getGame() {
		return theGame;
	}
	
	/**
	 * @return the amount of memory currently being consumed
	 * by this game, in megabytes.
	 */
	public long getMemoryUsageMB() {
		return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1014;
	}
	
	/**
	 * @return the total memory alloted by the JVM to this game,
	 * in megabytes.
	 */
	public long getTotalMemoryMB() {
		return Runtime.getRuntime().totalMemory() / 1024 / 1024;
	}

	@Override
	public void run() {
		
		Display.create();
		Display.registerKeyListener(inputProvider);
		SoundSystem.init();
		while(running) {
			if(Display.closeRequested()) {
				if(theGame.closeRequested()) {
					System.exit(0);
				}
			}
			update();
		}
	}
}
