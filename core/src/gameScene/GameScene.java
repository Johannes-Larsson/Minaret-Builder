package gameScene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.larsson.johannes.minaretBuilder.framework.GameObject;
import com.larsson.johannes.minaretBuilder.framework.Input;
import com.larsson.johannes.minaretBuilder.framework.Scene;
import com.larsson.johannes.minaretBuilder.game.Assets;
import com.larsson.johannes.minaretBuilder.game.SceneManager;

public class GameScene extends Scene {
	
	public static boolean gameOver;
	
	public GameScene() {
		super();
		TowerSegment.hasHitBottom = false;
		gameOver = false;
		add(new TowerSegment());
	}
	
	public void update() {
		if (gameOver && EndBlock.dead && Input.isPressed()) {
			SceneManager.gameScene = new GameScene();
			SceneManager.setScene(SceneManager.gameScene);
		}
		
		super.update();
	}
	
	public void draw(SpriteBatch batch) {
		super.draw(batch);
	}
	
	public float getDifficulty() {
		int noOfTowers = 0;
		
		for (GameObject g : objects) if (g instanceof TowerSegment) noOfTowers ++;
		
		return noOfTowers / 20f;
	}
}	
