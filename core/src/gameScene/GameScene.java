package gameScene;

import highscoreScene.HighscoreScene;

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
		onResume();
	}
	
	public void addScore(int score) {
		this.score += score;
		
		String[] messages = new String[] {
				"perfect!",
				"not bad",
				"ok",
				"not good",
				"terrible!"
		};
		float rel = (score - 100) / 100f;
		int msg = (int)((1 - rel) * messages.length);
		System.out.println(rel);
		
		SceneManager.getCurrentScene().add(new FloatingMessage(Game.WIDTH / 2, Game.HEIGHT - 200, "+" + score + "\n" + (msg < messages.length ? messages[msg] : "game over!"), 60));
	}
	
	public void onResume() {
		Game.clearR = 8/255f;
		Game.clearG=  128/255f;
		Game.clearB = 138/255f;
	}
	
	public void update() {
		if (gameOver && EndBlock.dead && Input.isPressed()) {
			SceneManager.setScene(SceneManager.highscoreScene = new HighscoreScene());
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
		
		super.drawUi(uiBatch);
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
