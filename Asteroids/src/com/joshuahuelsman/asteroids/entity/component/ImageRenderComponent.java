/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.asteroids.entity.component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import com.joshuahuelsman.molasses.GameContainer;

public class ImageRenderComponent extends RenderComponent {
	
	protected Image image;

	public ImageRenderComponent(Image image) {
		this.image = image;
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setTransform(new AffineTransform());
		g2.translate(owner.getPosition().x, owner.getPosition().y);
		g2.translate(owner.getCollider().width / 2, owner.getCollider().height / 2);
		g2.rotate(owner.getRotation());
		g2.translate(-owner.getCollider().width / 2, -owner.getCollider().height / 2);
		g2.translate(-owner.getPosition().x, -owner.getPosition().y);
		g2.drawImage(image, (int)owner.getPosition().x, (int)owner.getPosition().y, null);
	}

	@Override
	public void update(GameContainer gc, int deltaMS) {

	}

}
