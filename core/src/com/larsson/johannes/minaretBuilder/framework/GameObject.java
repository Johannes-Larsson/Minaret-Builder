package com.larsson.johannes.minaretBuilder.framework;

import com.larsson.johannes.minaretBuilder.game.SceneManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameObject {
	
	public static final float GRAVITY = -1;
	
	public Animation animation;
	
	public int health;
	
	public final int maxHealth;
	public final boolean canDie;
	
	protected boolean gravitates, solid;
	protected float vx, vy;
	protected enum CollisionState { Pos, Neg, None };
	protected CollisionState collisionX, collisionY;
	protected GameObject collidedObject;
	
	
	public GameObject(Animation animation, float x, float y) {
		this(animation, x, y, 0);
	}
	
	public GameObject(Animation animation, float x, float y, int health) {
		this.health = health;
		this.animation = animation;
		setX(x);
		setY(y);
		canDie = health > 0;
		maxHealth = health;
	}
	
	public void update() {
		if (gravitates) vy += GRAVITY;
		
		if (canDie && health <= 0) SceneManager.getCurrentScene().remove(this);
		
		move();
	}
	
	protected void move() {
		collisionX = CollisionState.None;
		collisionY = CollisionState.None;
		
		final int steps = 5;
		final float x = vx / steps;
		final float y = vy / steps;
		
		for (int i = 0; i < steps; i++) {
			if (collisionX == CollisionState.None) {
				setX(getX() + x);
				if (collides()) {
					if (x > 0) collisionX = CollisionState.Pos;
					else collisionX = CollisionState.Neg;
					vx = 0;
					setX(getX() - x);
				}
			}
			
			if (collisionY == CollisionState.None) {
				setY(getY() + y);
				if (collides()) {
					if (y > 0) collisionY = CollisionState.Pos;
					else collisionY = CollisionState.Neg;
					vy = 0;
					setY(getY() - y);
				}
			}
		}
	}
	
	private boolean collides() {
		for (GameObject g : SceneManager.getCurrentScene().objects)
		{
			if (g.solid && g.intersects(this) && g != this) {
				collidedObject = g;
				return true;
			}
		}
		return false;
	}
	
	boolean oneD(float p1, float s1, float p2, float s2) {
	    return Math.abs(p1 - p2 + (s1 - s2) / 2) < (s1 + s2) / 2;
	}
	
	public boolean intersects(float x, float y, float w, float h) {
		return oneD(animation.sprite.getX(), animation.sprite.getWidth(), x, w) && oneD(animation.sprite.getY(), animation.sprite.getHeight(), y, h);
	}
	
	public boolean intersects(GameObject g) {
		return intersects(g.animation.sprite.getX(), g.animation.sprite.getY(), g.animation.sprite.getWidth(), g.animation.sprite.getHeight());
	}
	
	public float getX() {
		return animation.sprite.getX();
	}
	
	public float getY() {
		return animation.sprite.getY();
	}
	
	public float getW() {
		return animation.sprite.getWidth();
	}
	
	public float getH() {
		return animation.sprite.getHeight();
	}
	
	protected void setX(float x) {
		animation.sprite.setX(x);
	}
	
	protected void setY(float y) {
		animation.sprite.setY(y);
	}
	
	protected void setW(float w) {
		animation.sprite.setSize(w, getH());
	}
	
	protected void setH(float h) {
		animation.sprite.setSize(getW(), h);
	}
	
	public void draw(SpriteBatch batch) {
		animation.draw(batch);
	}
}
