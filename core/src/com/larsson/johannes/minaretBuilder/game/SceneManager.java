package com.larsson.johannes.minaretBuilder.game;

import menuScene.MenuScene;
import gameScene.GameScene;

import com.larsson.johannes.minaretBuilder.framework.Scene;

public class SceneManager {
	private static Scene currentScene;
	
	public static GameScene gameScene;
	public static MenuScene menuScene;
	
	public static void init() {
		gameScene = new GameScene();
		menuScene = new MenuScene();
		
		currentScene = menuScene;
	}
	
	public static void setScene(Scene scene) {
		currentScene.onPause();
		currentScene = scene;
		currentScene.onResume();
	}
	
	public static Scene getCurrentScene() {
		return currentScene;
	}
}
