/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.asteroids.entity;

import com.joshuahuelsman.molasses.SoundSystem;


public abstract class HealthEntity extends Entity {
	
	protected int health;
	
	protected int maxHealth;
	
	protected boolean killable;
	
	public HealthEntity() {
		super();
		maxHealth = 1;
		health = maxHealth;
		killable = true;
	}
	
	public boolean damage(Entity attacker) {
		
		if(killable) {
			setHealth(health - 1);
			if(health == 0) {
				onEntityKilled(attacker);
			}
			return true;
		}
		return false;
	}
	
	public void onEntityKilled(Entity attacker) {
		remove = true;
		SoundSystem.play("explode.wav");
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		if(health < this.health) {
			SoundSystem.play("hit.wav");
		}
		this.health = health;
		if(this.health < 0) {
			this.health = 0;
		}
		if(this.health > maxHealth) {
			this.health = maxHealth;
		}
	}
	

}
