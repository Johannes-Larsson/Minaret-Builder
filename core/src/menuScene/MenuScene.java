package menuScene;

import gameScene.GameScene;
import ui.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.larsson.johannes.minaretBuilder.framework.Game;
import com.larsson.johannes.minaretBuilder.framework.Scene;
import com.larsson.johannes.minaretBuilder.game.Assets;
import com.larsson.johannes.minaretBuilder.game.SceneManager;

public class MenuScene extends Scene {
	
	private Button playButton, quitButton;
	
	public MenuScene() {
		super();
		playButton = new Button(Assets.playButtonLight, Assets.playButtonDark, 100, 1300, 400, 200);
		quitButton = new Button(Assets.quitButtonLight, Assets.quitButtonDark, 100, 1050, 400, 200);
		onResume();
	}
	
	public void onResume() {
		Game.clearR = 3f / 255;
		Game.clearG = 53f / 255;
		Game.clearB = 56f / 255;
	}
	
	public void update() {
		if (playButton.isPressed()) {
			SceneManager.gameScene = new GameScene();
			SceneManager.setScene(SceneManager.gameScene);
		} else if (quitButton.isPressed()) {
			Gdx.app.exit();
		}
	}
	
	public void drawUi(SpriteBatch uiBatch) {
		uiBatch.draw(Assets.menuBackground, 0, 0);
		playButton.draw(uiBatch);
		quitButton.draw(uiBatch);
	}
}
