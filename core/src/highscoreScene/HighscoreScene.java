package highscoreScene;


import gameScene.GameScene;
import ui.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.larsson.johannes.minaretBuilder.framework.Game;
import com.larsson.johannes.minaretBuilder.framework.Scene;
import com.larsson.johannes.minaretBuilder.game.Assets;
import com.larsson.johannes.minaretBuilder.game.SceneManager;

public class HighscoreScene extends Scene {
	
	private final String PREFERECE_NAME = "MinaretBuilderSettings", HIGHSCORE = "highscore";
	
	Button retry, quit;
	
	int highscore, score;
	
	public HighscoreScene() {
		super();
		retry = new Button(Assets.playButtonLight, Assets.playButtonDark, Game.UIWIDTH / 2 - 200, 1100, 400, 200);
		quit = new Button(Assets.quitButtonLight, Assets.quitButtonDark, Game.UIWIDTH / 2 - 200, 850, 400, 200);
		
		score = SceneManager.gameScene.score;
		highscore = getHighscore();
	}
	
	public void onResume() {
		Game.clearR = 3f / 255;
		Game.clearG = 43f / 255;
		Game.clearB = 46f / 255;
	}
	
	public void update() {
		System.out.println("h: " + highscore + ", s: " + score);
		
		if (retry.isPressed()) {
			SceneManager.setScene(SceneManager.gameScene = new GameScene());
		}
		else if (quit.isPressed()) {
			Gdx.app.exit();
		}
	}
	
	public void onPause() {
		if (score > highscore) {
			setHighscore(score);
			System.out.println("saving " + score);
		}
	}
	
	public void drawUi(SpriteBatch batch) {
		batch.draw(Assets.highscoreBackground, 0, 0);
		
		retry.draw(batch);
		quit.draw(batch);
		
		String s = "";
		if (score <= highscore) {
			s = "score: " + score + "\nhighscore: " + highscore;
		} else {
			s = "new highscore: " + score;
			if (highscore > 0) s += "\nprevious: " + highscore;
		}
		
		Assets.fontSmall.setColor(Color.BLACK);
		Assets.fontSmall.setScale(.8f);
		TextBounds b = Assets.fontSmall.getMultiLineBounds(s);
		Assets.fontSmall.drawMultiLine(batch, s, 430 - b.width / 2, 355 + b.height / 2);
		Assets.fontSmall.setColor(Color.WHITE);
		Assets.fontSmall.setScale(1);
	}
	
	public int getHighscore() {
		return readScore();
	}
	
	public void setHighscore(int score) {
		writeScore(score);
	}
	
	private int readScore() {
		Preferences prefs = Gdx.app.getPreferences(PREFERECE_NAME);
		if (prefs.contains(HIGHSCORE)) {
			System.out.println("highscore exists");
			return prefs.getInteger(HIGHSCORE);
		} else {
			System.out.println("no previous score");
			writeScore(0);
			return 0;
		}
	}
	
	private void writeScore(int score) {
		Preferences prefs = Gdx.app.getPreferences(PREFERECE_NAME);
		prefs.putInteger(HIGHSCORE, score);
		prefs.flush();
	}
}
