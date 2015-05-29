package gameScene;

import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.larsson.johannes.minaretBuilder.framework.Game;
import com.larsson.johannes.minaretBuilder.framework.GameObject;
import com.larsson.johannes.minaretBuilder.framework.Input;
import com.larsson.johannes.minaretBuilder.framework.Scene;
import com.larsson.johannes.minaretBuilder.game.Assets;
import com.larsson.johannes.minaretBuilder.game.SceneManager;

public class GameScene extends Scene {
	
	public static boolean gameOver;
	
	public int score;
	
	public GameScene() {
		super();
		TowerSegment.hasHitBottom = false;
		gameOver = false;
		add(new Background());
		for (int i = 0; i < 10; i++) add(new Cloud(Game.HEIGHT / 2 + i * 300));
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
	
	public void drawUi(SpriteBatch uiBatch) {
		String s = "Score: " + score;
		TextBounds b = Assets.fontBig.getBounds(s);
		Assets.fontBig.draw(uiBatch, s, Game.UIWIDTH / 2 - b.width / 2, Game.UIHEIGHT - b.height * 1.2f); //todo: actuall score
	}
	
	public int getNoOfTowers() {
		int noOfTowers = 0;
		
		for (GameObject g : objects) if (g instanceof TowerSegment) noOfTowers ++;
		
		return noOfTowers;
	}
	
	public float getDifficulty() {
		return getNoOfTowers() / 20f;
	}
}	
