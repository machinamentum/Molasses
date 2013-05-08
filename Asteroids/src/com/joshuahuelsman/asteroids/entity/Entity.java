/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.asteroids.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import com.joshuahuelsman.asteroids.entity.component.Component;
import com.joshuahuelsman.asteroids.entity.component.RenderComponent;
import com.joshuahuelsman.molasses.GameContainer;
import com.joshuahuelsman.molasses.GameInfo;
import com.joshuahuelsman.molasses.geom.Rectangle;
import com.joshuahuelsman.molasses.vector.Vector2f;

public abstract class Entity {
	
	protected Vector2f position;
	
	protected Vector2f velocity;
	
	protected ArrayList<Component> components;
	
	protected boolean remove = false;
	
	//Only really useful for rotating images
	//as our Rectangle colliders are static objects.
	protected double rotation = 0;
	
	public Entity() {
		position = new Vector2f();
		velocity = new Vector2f();
		components = new ArrayList<Component>();
	}
	
	public void update(GameContainer gc, int deltaMS) {
		for(Component c : components) {
			c.update(gc, deltaMS);
		}
		position.add(velocity);
	}
	
	public void render(GameContainer gc, Graphics g) {
		for(Component c : components) {
			if(RenderComponent.class.isInstance(c)) {
				((RenderComponent)c).render(gc, g);
			}
		}
		
		if(GameInfo.debug) {
			((Graphics2D)g).setTransform(new AffineTransform());
			g.setColor(Color.white);
			g.drawRect((int)getCollider().x, (int)getCollider().y, (int)getCollider().width, (int)getCollider().height);
		}
		
	}
	
	public abstract Rectangle getCollider();
	
	/**
	 * A hook for subclasses to implement and handle
	 * when this Entity collides with another Entity.
	 * @param other the Entity that collided with this
	 * Entity.
	 */
	public void onCollide(Entity other) {
		
	}
	
	public void setPosition(Vector2f vec) {
		position = new Vector2f(vec);
	}
	
	public Vector2f getPosition() {
		return position;
	}
	
	public void setVelocity(Vector2f vec) {
		velocity = new Vector2f(vec);
	}
	
	public Vector2f getVelocity() {
		return velocity;
	}
	
	public void addComponent(Component c) {
		c.setOwner(this);
		components.add(c);
	}
	
	public boolean readyForRemoval() {
		return remove;
	}
	
	public void onEntityRemoved(GameContainer gc) {
		
	}
	
	public double getRotation() {
		return rotation;
	}

}
