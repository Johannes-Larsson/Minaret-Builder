package menuScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.larsson.johannes.minaretBuilder.framework.Game;
import com.larsson.johannes.minaretBuilder.framework.Scene;
import com.larsson.johannes.minaretBuilder.game.Assets;
import com.larsson.johannes.minaretBuilder.game.SceneManager;

public class AboutScene extends Scene {
	
	private Scene prevScene;
	
	private boolean wasPressed;
	
	public AboutScene(Scene prevScene) {
		this.prevScene = prevScene;
		wasPressed = true;
	}
	
	public void update() {
		if (Gdx.input.isTouched() && !wasPressed) {
			SceneManager.setScene(prevScene);
		}
		wasPressed = Gdx.input.isTouched();
	}
	
	public void drawUi(SpriteBatch uiBatch) {
		uiBatch.draw(Assets.menuBackground, 0, 0);
		Assets.fontBig.drawMultiLine(uiBatch, "Press to drop block\nHit on center\nfor more points!\n\n\nGame by:\nJohannes Larsson \nSante Larsson\n\nMusic:\nNaraina \nby Kevin MacLeod\n\nBack", 10, Game.UIHEIGHT - 10);
	}
}
