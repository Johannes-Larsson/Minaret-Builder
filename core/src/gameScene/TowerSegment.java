package gameScene;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.larsson.johannes.minaretBuilder.framework.Animation;
import com.larsson.johannes.minaretBuilder.framework.Game;
import com.larsson.johannes.minaretBuilder.framework.GameObject;
import com.larsson.johannes.minaretBuilder.framework.Input;
import com.larsson.johannes.minaretBuilder.game.Assets;
import com.larsson.johannes.minaretBuilder.game.SceneManager;

public class TowerSegment extends GameObject {
	final static int startWidth = 100, height = 30, edgePadding = 30;
	
	public enum State { Floating, Falling, Dead };
	public State state;
	
	public TowerSegment() {
		this(startWidth);
	}
	
	public TowerSegment(int width) {
		super(makeAnim(width), MathUtils.random() * (Game.WIDTH - 300) + 100, Game.camera.position.y);
		state = State.Floating;
		vx = MathUtils.random(2, 3) - 1;
		vx *= 1;
		solid = true;
	}
	
	public void update() {
		
		switch (state) {
		case Floating:
			
			if (getX() < edgePadding || getX() + getW() > Game.WIDTH - edgePadding) vx *= -1;
			
			if (Input.isPressed()) {
				state = State.Falling;
				vx *= SceneManager.gameScene.getDifficulty();
			}
			
			break;
		case Falling:
			
			vy -= .1f;
			if (collisionY != CollisionState.None || getY() <= 0) {
				state = State.Dead;
				vy = 0;
				vx = 0;
				
				if (getY() <= 0) {
					SceneManager.gameScene.add(new TowerSegment());
				} else {
					int dw = Math.abs((int)getX() - (int)collidedObject.getX());
					int w = (int)getW() - dw;
					if (w < 10) {
						System.out.println("Game over");
						SceneManager.gameScene.add(new EndBlock());
						return;
					}
					SceneManager.gameScene.add(new TowerSegment(w));
				}
			}
			
			break;
		default: break;
		}
		
		super.update();
	}
	
	private static Animation makeAnim(int width) {
		int texture = width / startWidth;
		if (texture < 0) texture = 0;
		if (texture >= Assets.towerBlocks.length) texture = Assets.towerBlocks.length - 1;
		Texture t = Assets.towerBlocks[texture];
		return new Animation(t, width, height, 1, t.getWidth(), t.getHeight(), 0);
	}
}
