package com.larsson.johannes.minaretBuilder.framework;

import com.badlogic.gdx.Gdx;

public class Input {
	final static int points = 3; //how many multitouch points are checked
	
	public static float[] x;
	public static float[] y;
	public static boolean[] isPressed;
	
	public static float[] oldX;
	public static float[] oldY;
	public static boolean[] wasPressed;
	
	private static float scaleX;
	private static float scaleY;
	
	
	public static void init() {
		x = new float[points];
		y = new float[points];
		isPressed = new boolean[points];
		oldX = new float[points];
		oldY = new float[points];
		wasPressed = new boolean[points];
		
		scaleX = (float)Game.WIDTH / Gdx.graphics.getWidth();
		scaleY = (float)Game.HEIGHT / Gdx.graphics.getHeight();
		getAllInput();
		update();
	}
	
	public static boolean wasJustPressed(int pointerIndex) {
		return isPressed[pointerIndex] && !wasPressed[pointerIndex];
	}
	
	private static void getAllInput() {
		for (int i = 0; i < points; i++) getInput(i);
	}
	
	private static void getInput(int pointer) {
		x[pointer] = Gdx.input.getX(pointer) * scaleX;
		y[pointer] = (Game.HEIGHT) - (Gdx.input.getY(pointer) * scaleY);
		wasPressed[pointer] = isPressed[pointer];
		isPressed[pointer] = Gdx.input.isTouched(pointer);		
	}
	
	public static void update() {
		oldX = x;
		oldY = y;
		getAllInput();
	}
	
	public static boolean isPressed() {
		for (boolean b : isPressed) if (b) return true;
		return false;
	}
	
	public static boolean wasPressed() {
		for (boolean b : wasPressed) if (b) return true;
		return false;
	}
	
	public static boolean wasJustPressed() {
		return isPressed() && !wasPressed();
	}
	
	public static boolean intersectingWith(float _x, float _y, float width, float height) {
		for (int i = 0; i < points; i++) if (x[i] >= _x && y[i] >= _y && x[i] <= _x + width && y[i] <= _y + height) return true;
		return false;
	}
	
	public static boolean areaIsClicked(float _x, float _y, float width, float height) {
		for (int i = 0; i < points; i++) if (x[i] >= _x && y[i] >= _y && x[i] <= _x + width && y[i] <= _y + height && isPressed[i]) return true;
		return false;
	}
	
	public static boolean areaWasJustClicked(float _x, float _y, float width, float height) {
		for (int i = 0; i < points; i++) if (x[i] >= _x && y[i] >= _y && x[i] <= _x + width && y[i] <= _y + height && isPressed[i] && !wasPressed[i]) return true;
		return false;
	}
}