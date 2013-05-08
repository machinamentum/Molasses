/*
 * This file was written by Joshua Huelsman. Do whatever you want
 * with this stuff. If you think it's worth it, and we someday
 * meet, you can buy me an energy drink.
 */
package com.joshuahuelsman.molasses.display;

import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.joshuahuelsman.molasses.input.Mouse;

public final class Display extends Canvas {

	private static final long serialVersionUID = -5793808493880915990L;

	private Display() {
		setSize(new Dimension(displayMode.getWidth(),
				displayMode.getHeight()));
	}
	
	
	@Override
	public void addNotify() {
		super.addNotify();
		createBufferStrategy(3);
	}

	public static void update() {
		if (created) {
			
			if(!frame.isVisible()) {
				return;
			}
			
			Mouse.update();
			if (Mouse.isGrabbed()) {
				robot.mouseMove(frame.getX() + (displayMode.getWidth() / 2),
						frame.getY() + (displayMode.getHeight() / 2));
			}
			BufferStrategy bs = display.getBufferStrategy();
			if (bs == null) {
				display.createBufferStrategy(3);
				return;
			} else {
				RenderingContext.getInstance().paint(bs.getDrawGraphics());
				bs.getDrawGraphics().dispose();
				bs.show();
			}
		}

	}

	private static DisplayMode displayMode;

	private static Display display;

	private static JFrame frame;

	private static boolean created = false;

	private static boolean closeRequested = false;

	private static Robot robot;

	public static void create() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					if (!created) {
						if (displayMode == null) {
							displayMode = new DisplayMode(640, 480);
						}
						display = new Display();
						RenderingContext.instance = new RenderingContext(
								displayMode);
						display.addMouseMotionListener(new Mouse());
						display.addMouseListener(new Mouse());
						frame = new JFrame();
						frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
						frame.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosing(WindowEvent e) {
								closeRequested = true;
							}
						});
						frame.add(display);
						frame.pack();
						frame.setLocationRelativeTo(null);
						frame.setResizable(true);
						frame.setVisible(true);
						created = true;
						try {
							robot = new Robot();
						} catch (AWTException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void setDisplayMode(DisplayMode mode) {
		displayMode = mode;
	}

	public static DisplayMode getDisplayMode() {
		return displayMode;
	}

	public static void setTitle(String title) {
		if (frame != null) {
			frame.setTitle(title);
		}
	}

	public static void registerKeyListener(KeyListener l) {
		display.addKeyListener(l);
	}

	private static java.awt.DisplayMode old = null;

	public static void setFullscreen(boolean full) {
		GraphicsDevice d = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		if (full && created) {
			frame.dispose();
			frame.setUndecorated(true);
			frame.setResizable(false);

			d.setFullScreenWindow(frame);
			java.awt.DisplayMode[] dma = d.getDisplayModes();
			
			for(java.awt.DisplayMode mode : dma) {
				if(mode.getWidth() == displayMode.getWidth() && mode.getHeight() == displayMode.getHeight()) {
					d.setDisplayMode(mode);
					break;
				}
			}

		} else {
			if (old != null) {
				d.setDisplayMode(old);
			}
			d.setFullScreenWindow(null);
			
			if (frame != null) {
				frame.dispose();
				frame.setResizable(true);
				frame.setUndecorated(false);
				frame.setVisible(true);
			}
		}
	}

	public static boolean isFullscreen() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice().getFullScreenWindow() == frame;
	}

	public static boolean closeRequested() {

		if (closeRequested) {
			closeRequested = false;
			return true;
		}

		return false;
	}

}
