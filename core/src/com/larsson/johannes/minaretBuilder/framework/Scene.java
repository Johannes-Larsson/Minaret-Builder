package com.larsson.johannes.minaretBuilder.framework;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Scene {
	public ArrayList<GameObject> objects, add, remove;
	
	public Scene() {
		objects = new ArrayList<GameObject>();
		add = new ArrayList<GameObject>();
		remove = new ArrayList<GameObject>();
	}
	
	public void update() {
		for (GameObject g : add) objects.add(g);
		for (GameObject g : remove) objects.remove(g);
		add.clear();
		remove.clear();
		
		for (GameObject g : objects) g.update();
	}
	
	public void draw(SpriteBatch batch) {
		for (GameObject g : objects) g.draw(batch);
	}
	
	public void drawUi(SpriteBatch uiBatch) { 
		for (GameObject g : objects) g.drawUi(uiBatch);
	}
	
	public void add(GameObject object) {
		add.add(object);
	}
	
	public void remove(GameObject object) {
		remove.add(object);
	}
	
	public void onPause() { }
	
	public void onResume() { }
}
