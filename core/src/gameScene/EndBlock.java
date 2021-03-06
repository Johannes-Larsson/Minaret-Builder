package gameScene;

import com.larsson.johannes.minaretBuilder.framework.Animation;
import com.larsson.johannes.minaretBuilder.framework.Game;
import com.larsson.johannes.minaretBuilder.framework.GameObject;
import com.larsson.johannes.minaretBuilder.game.Assets;
import com.larsson.johannes.minaretBuilder.game.SceneManager;

public class EndBlock extends GameObject {
	
	public static boolean dead;
	
	int deathCounter;
	
	public EndBlock() {
		super(new Animation(Assets.endBlock, 140, 240, 1, Assets.endBlock.getWidth(), Assets.endBlock.getHeight(), 0), getXPos(), Game.camera.position.y + Game.HEIGHT / 2 + 100);
		dead = false;
		setX(getX() - getW() / 2);
		solid = true;
	}
	
	public void update() {
		
		vy += -.5f;
		if (collisionY != CollisionState.None) {
			dead = true;
			vy = 0;
		}
		
		if (dead) deathCounter++;
		if (deathCounter > 30) {
			Game.cameraTargetY = Game.HEIGHT / 2;
		}
		
		super.update();
	}
	
	private static int getXPos() {
		GameObject highest = null;
		for (GameObject g : SceneManager.gameScene.objects) if (g instanceof TowerSegment && (highest == null || g.getY() > highest.getY())) highest = g;
		return (int)(highest.getX() + highest.getW() / 2);
	}
}
