package highscoreScene;


import gameScene.GameScene;
import ui.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.larsson.johannes.minaretBuilder.framework.Scene;
import com.larsson.johannes.minaretBuilder.game.Assets;
import com.larsson.johannes.minaretBuilder.game.SceneManager;

public class HighscoreScene extends Scene {
	
	private final String PREFERECE_NAME = "MinaretBuilderSettings";
	
	Button retry, quit;
	
	int highscore, score;
	
	public HighscoreScene() {
		super();
		retry = new Button(Assets.playButtonLight, Assets.playButtonDark, 100, 1300, 400, 200);
		quit = new Button(Assets.quitButtonLight, Assets.quitButtonDark, 100, 1050, 400, 200);
		
		score = SceneManager.gameScene.score;
		highscore = getHighscore();
	}
	
	public void update() {
		if (retry.isPressed()) {
			SceneManager.setScene(SceneManager.gameScene = new GameScene());
		}
		else if (quit.isPressed()) {
			Gdx.app.exit();
		}
	}
	
	public void drawUi(SpriteBatch batch) {
		retry.draw(batch);
		quit.draw(batch);
		
		String s = "";
		if (score <= highscore) {
			s = "score: " + score + "\nhighscore: " + highscore;
		} else {
			s = "new highscore: " + score + "\nprevious: " + highscore;
		}
		
		Assets.fontBig.drawMultiLine(batch, s, 100, 100);
	}
	
	public int getHighscore() {
		return readScore();
	}
	
	public void setHighscore(int score) {
		writeScore(score);
	}
	
	private int readScore() {
		Preferences prefs = Gdx.app.getPreferences(PREFERECE_NAME);
		if (prefs.contains("highscore")) {
			return prefs.getInteger("highscore");
		} else {
			writeScore(0);
			return 0;
		}
	}
	
	private void writeScore(int score) {
		Preferences prefs = Gdx.app.getPreferences(PREFERECE_NAME);
		prefs.putInteger("highscore", score);
	}
}
