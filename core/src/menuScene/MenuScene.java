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
	
	private Button playButton, quitButton, aboutButton;
	
	public MenuScene() {
		super();
		playButton = new Button(Assets.playButtonLight, Assets.playButtonDark, Game.UIWIDTH / 2 - 200, 1100, 400, 200);
		quitButton = new Button(Assets.quitButtonLight, Assets.quitButtonDark, Game.UIWIDTH / 2 - 200, 600, 400, 200);
		aboutButton = new Button(Assets.aboutButton, Assets.aboutButton, Game.UIWIDTH / 2 - 200, 850, 400, 200);
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
		} else if (aboutButton.isPressed()) {
			SceneManager.setScene(new AboutScene(this));
		}
	}
	
	public void drawUi(SpriteBatch uiBatch) {
		uiBatch.draw(Assets.menuBackground, 0, 0);
		playButton.draw(uiBatch);
		aboutButton.draw(uiBatch);
		quitButton.draw(uiBatch);
	}
}
