/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.asteroids.entity.component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import com.joshuahuelsman.molasses.GameContainer;

public class AnimatedImageRenderComponent extends ImageRenderComponent {
	
	protected long delay = 250;
	
	protected long count = 0;

	protected int curx = 0;
	
	protected int cury = 0;
	
	protected int tw = 0;
	
	protected int th = 0;
	
	public AnimatedImageRenderComponent(Image image, int tw, int th) {
		super(image);
		this.tw = tw;
		this.th = th;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		int x = (int)owner.getPosition().x;
		int y = (int)owner.getPosition().y;
		Graphics2D g2 = (Graphics2D)g;
		g2.setTransform(new AffineTransform());
		g2.translate(owner.getPosition().x, owner.getPosition().y);
		g2.translate(owner.getCollider().width / 2, owner.getCollider().height / 2);
		g2.rotate(owner.getRotation());
		g2.translate(-owner.getCollider().width / 2, -owner.getCollider().height / 2);
		g2.translate(-owner.getPosition().x, -owner.getPosition().y);
		g2.drawImage(image, x, y, x + tw, y + th, curx * tw, cury * th, (curx * tw) + tw, (cury * th) + th, null);
	}
	
	@Override
	public void update(GameContainer gc, int deltaMS) {
		count += deltaMS;
		if(count > delay) {
			count = count - delay;
			curx++;
			if(curx >= image.getWidth(null) / tw) {
				curx = 0;
			}
		}
	}
	

}
