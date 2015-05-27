package gameScene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.larsson.johannes.minaretBuilder.framework.GameObject;
import com.larsson.johannes.minaretBuilder.framework.Scene;
import com.larsson.johannes.minaretBuilder.game.Assets;

public class GameScene extends Scene {
	
	public GameScene() {
		super();
		add(new TowerSegment());
	}
	
	public void update() {
		super.update();
	}
	
	public void draw(SpriteBatch batch) {
		super.draw(batch);
	}
	
	public float getDifficulty() {
		int noOfTowers = 0;
		
		for (GameObject g : objects) if (g instanceof TowerSegment) noOfTowers ++;
		
		return noOfTowers / 10f;
	}
}	
