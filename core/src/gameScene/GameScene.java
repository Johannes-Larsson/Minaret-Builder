package gameScene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.larsson.johannes.minaretBuilder.framework.Scene;
import com.larsson.johannes.minaretBuilder.game.Assets;

public class GameScene extends Scene {
	
	public GameScene() {
		super();
	}
	
	public void draw(SpriteBatch batch) {
		Assets.fontBig.draw(batch, "its working!", 100, 100);
	}
}	
