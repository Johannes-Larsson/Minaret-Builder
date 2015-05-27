package com.larsson.johannes.minaretBuilder.game;

import gameScene.GameScene;

import com.larsson.johannes.minaretBuilder.framework.Scene;

public class SceneManager {
	private static Scene currentScene;
	
	public static GameScene gameScene;
	
	public static void init() {
		gameScene = new GameScene();
		
		currentScene = gameScene;
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
