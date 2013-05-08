/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.asteroids;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.joshuahuelsman.asteroids.entity.AsteroidMediumEntity;
import com.joshuahuelsman.asteroids.entity.AsteroidSmallEntity;
import com.joshuahuelsman.asteroids.entity.BulletEntity;
import com.joshuahuelsman.asteroids.entity.Entity;
import com.joshuahuelsman.asteroids.entity.PlayerEntity;
import com.joshuahuelsman.asteroids.entity.あや;
import com.joshuahuelsman.molasses.BasicGame;
import com.joshuahuelsman.molasses.GameContainer;
import com.joshuahuelsman.molasses.GameInfo;
import com.joshuahuelsman.molasses.display.Display;
import com.joshuahuelsman.molasses.input.BasicCommand;
import com.joshuahuelsman.molasses.input.Command;
import com.joshuahuelsman.molasses.input.Mouse;
import com.joshuahuelsman.molasses.vector.Vector2f;
import com.joshuahuelsman.nbt.CompoundTag;
import com.joshuahuelsman.nbt.IntTag;
import com.joshuahuelsman.nbt.StringTag;
import com.joshuahuelsman.nbt.stream.NBTInputStream;
import com.joshuahuelsman.nbt.stream.NBTOutputStream;

public class AsteroidsGame extends BasicGame {

	private ArrayList<Entity> entities;

	private ArrayList<Entity> addReq;

	private PlayerEntity player;

	// Mysterious flag is mysterious...
	private boolean hasAya = false;

	private Random random = new Random();

	// Number of times the game was updated
	private long ticks = 0;

	// How long before a new asteroid is added to the game.
	private long addAsteroidDelay = 1000;

	private long addAsteroidCount = 0;

	// Total time alloted in MS
	private long time = 1000 * 60 * 1;

	private long timePassed = 0;

	private boolean done = false;

	public int score = 0;

	public boolean glitch = false;

	private boolean gameover = false;
	
	private boolean s = false;
	
	private String playerName = "P1";
	
	private String highScoreHolder = "";
	
	private int highScore = 0;

	public AsteroidsGame() {
		super("Asteroids");
	}

	public int getMinutesLeft() {
		return (int) ((time - timePassed) / 60000);
	}

	public int getSecondsLeft() {
		return (int) ((time - timePassed) % 60000) / 1000;
	}

	public ArrayList<Entity> getEntityList() {
		return entities;
	}

	public void addEntity(Entity e) {
		addReq.add(e);
	}

	@Override
	public void controlPressed(Command command) {
		String s = ((BasicCommand) command).getName();
		if (s.equals("f")) {
			Display.setFullscreen(!Display.isFullscreen());
		} else if (s.equals("debug")) {
			GameInfo.debug = !GameInfo.debug;
		}
	}

	@Override
	public void controlReleased(Command command) {
	}

	@Override
	public void update(GameContainer gc, int deltaMS) {
		ticks++;

		if (glitch) {
			if(!s) {
				saveHighScore();
				s = true;
			}
			return;
		}

		if (done) {
			if (Mouse.isButtonDown(MouseEvent.BUTTON1)) {
				
				reset();
			}
			return;
		}

		timePassed += deltaMS;
		if (timePassed >= time) {
			done = true;
			if(score > highScore) {
				saveHighScore();
				highScoreHolder = playerName;
				highScore = score;
			}
		}

		if (player.getHealth() <= 0) {
			gameover = true;
			done = true;
			timePassed = time;
		}

		hasAya = false;
		for (Entity e : entities) {
			if (あや.class.isInstance(e)) {
				hasAya = true;
			}
		}

		for (Entity e : entities) {
			if (hasAya
					&& (あや.class.isInstance(e)
							|| PlayerEntity.class.isInstance(e) || BulletEntity.class
								.isInstance(e))) {
				e.update(gc, deltaMS);
			} else if (!hasAya) {
				e.update(gc, deltaMS);
			}
		}

		for (Entity e : entities) {
			for (Entity f : entities) {
				if (e != f) {
					if (e.getCollider().intersects(f.getCollider())) {
						e.onCollide(f);
					}
				}
			}
		}

		ArrayList<Entity> remove = new ArrayList<Entity>();
		for (Entity e : entities) {
			if (e.readyForRemoval()) {
				e.onEntityRemoved(gc);
				remove.add(e);
			}
		}
		if (!remove.isEmpty()) {
			entities.removeAll(remove);
			remove.clear();
		}

		int num = random.nextInt(1000000000);

		if (num == 4182013) {
			あや a = new あや();
			a.setPosition(new Vector2f(random.nextInt((int) (gc.getWidth() - a
					.getCollider().width)), -a.getCollider().height));
			addReq.add(a);
		}

		addAsteroidCount += deltaMS;
		if (addAsteroidCount > addAsteroidDelay) {
			addAsteroidCount -= addAsteroidDelay;
			Random random = new Random();
			int i = random.nextInt(24);
			if (i < 8) {
				addReq.add(new AsteroidSmallEntity(gc));
			} else if (i < 16) {
				addReq.add(new AsteroidMediumEntity(gc));
			}
		}

		if (!addReq.isEmpty()) {
			entities.addAll(addReq);
			addReq.clear();
		}

	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, gc.getWidth(), gc.getHeight());

		if (glitch) {
			for (int i = 0; i < gc.getWidth(); i++) {
				for (int k = 0; k < gc.getHeight(); k++) {
					g.setColor(new Color(random.nextInt(0xffffff)));
					g.fillRect(i, k, 1, 1);
				}
			}
			return;
		}

		for (Entity e : entities) {
			e.render(gc, g);
		}
		String timeString = getMinutesLeft()
				+ ":"
				+ (getSecondsLeft() < 10 ? "0" + getSecondsLeft()
						: getSecondsLeft());
		int width = g.getFontMetrics().stringWidth(timeString);
		((Graphics2D) g).setTransform(new AffineTransform());
		g.setColor(Color.white);
		g.drawString(timeString, gc.getWidth() / 2 - width / 2, 20);

		if (!done) {
			String scoreString = "Score: " + score;
			width = g.getFontMetrics().stringWidth(scoreString);
			g.drawString(scoreString, 20, 20);
		}

		if (done) {
			if (gameover) {
				score = 0;
				Font orig = g.getFont();
				g.setFont(orig.deriveFont(32f));
				String gameOver = "Game Over!";
				width = g.getFontMetrics().stringWidth(gameOver);
				g.drawString(gameOver, gc.getWidth() / 2 - width / 2,
						gc.getHeight() / 2 - 16);
				g.setFont(orig);
			}
			Font orig = g.getFont();
			g.setFont(orig.deriveFont(20f));
			String scoreString = "Score:" + score;
			width = g.getFontMetrics().stringWidth(scoreString);
			g.drawString(scoreString, gc.getWidth() / 2 - width / 2,
					gc.getHeight() / 2 + 20);
			
			// This isnt as accurate as using a delay
			// timer, but this generally gets the job done.
			if (ticks % 1000 < 500) {
				String clickString = "Click anywhere to continue.";
				width = g.getFontMetrics().stringWidth(clickString);
				g.drawString(clickString, gc.getWidth() / 2 - width / 2,
						gc.getHeight() / 2 + 150);
			}
			g.setFont(orig);
			
			if(highScoreHolder != null && !highScoreHolder.equals("") && highScore != 0) {
				String highscoreString = "HighScore:" + highScore + ", " + highScoreHolder;
				g.drawString(highscoreString, 20,
						gc.getHeight()  - 20);
			}
		}

		if (GameInfo.debug) {
			g.setColor(Color.white);
			g.drawString("FPS: " + gc.getFPS(), 20, 20);
			g.drawString(
					"Memory Usage: " + gc.getMemoryUsageMB() + "/"
							+ gc.getTotalMemoryMB(), 20, 40);
			g.drawString("Entities:" + entities.size(), 20, 60);
			g.drawString("あや ? " + hasAya, 20, 80);
			g.drawString("Ticks: " + ticks, 20, 100);
		}

	}

	@Override
	public void init(GameContainer gc) {
		gc.getInputProvider().addListener(this);
		gc.getInputProvider().bindCommand(KeyEvent.VK_S,
				new BasicCommand("down"));
		gc.getInputProvider().bindCommand(KeyEvent.VK_O,
				new BasicCommand("down"));
		gc.getInputProvider()
				.bindCommand(KeyEvent.VK_W, new BasicCommand("up"));
		gc.getInputProvider().bindCommand(KeyEvent.VK_A,
				new BasicCommand("left"));
		gc.getInputProvider().bindCommand(KeyEvent.VK_D,
				new BasicCommand("right"));
		gc.getInputProvider().bindCommand(KeyEvent.VK_E,
				new BasicCommand("right"));
		gc.getInputProvider().bindCommand(KeyEvent.VK_F, new BasicCommand("f"));
		gc.getInputProvider().bindCommand(KeyEvent.VK_B,
				new BasicCommand("debug"));
		gc.getInputProvider().bindCommand(KeyEvent.VK_SPACE,
				new BasicCommand("shoot"));
		reset();
	}

	/**
	 * Resets the game to its initial state.
	 */
	public void reset() {
		entities = new ArrayList<Entity>();
		addReq = new ArrayList<Entity>();
		player = new PlayerEntity();
		entities.add(player);
		timePassed = 0;
		done = false;
		score = 0;
		gameover = false;
		highScoreHolder = getHighScoreHolder();
		highScore = getHighScore();
		glitch = s = wasGlitched();
	}
	
	public void saveHighScore() {
			NBTOutputStream stream;
			try {
				File f = new File(Platform.getAppDir(), "save.dat");
				if(f.exists()) {
					f.delete();
				}
				f.createNewFile();
				stream = new NBTOutputStream(new FileOutputStream(f));
				CompoundTag rootTag = new CompoundTag("");
				IntTag highScoreTag = new IntTag("HighScore");
				highScoreTag.setInt(score);
				StringTag playerNameTag = new StringTag("HighScorePlayerName");
				playerNameTag.setString(playerName);
				StringTag g = new StringTag("g");
				g.setString(Boolean.toString(glitch));
				
				rootTag.put(highScoreTag);
				rootTag.put(playerNameTag);
				rootTag.put(g);
				stream.writeTag(rootTag);
				stream.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
			
	}
	
	public int getHighScore() {
		int score = 0;
		try {
			NBTInputStream stream = new NBTInputStream(new FileInputStream(new File(Platform.getAppDir(), "save.dat")));
			CompoundTag rootTag = (CompoundTag) stream.readTag();
			stream.close();
			score = ((IntTag)rootTag.getMap().get("HighScore")).getInt();
		} catch (FileNotFoundException e) {
			return 0;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return score;
	}
	
	public boolean wasGlitched() {
		boolean g = false;
		try {
			NBTInputStream stream = new NBTInputStream(new FileInputStream(new File(Platform.getAppDir(), "save.dat")));
			CompoundTag rootTag = (CompoundTag) stream.readTag();
			stream.close();
			g = Boolean.parseBoolean(((StringTag)rootTag.getMap().get("g")).getString());
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			return false;
		}
		return g;
	}
	
	public String getHighScoreHolder() {
		String name = "";
		try {
			NBTInputStream stream = new NBTInputStream(new FileInputStream(new File(Platform.getAppDir(), "save.dat")));
			CompoundTag tag = (CompoundTag) stream.readTag();
			stream.close();
			name = ((StringTag)tag.getMap().get("HighScorePlayerName")).getString();
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return name;
	}
	
	public void setPlayerName(String name) {
		this.playerName = new String(name);
	}

}
