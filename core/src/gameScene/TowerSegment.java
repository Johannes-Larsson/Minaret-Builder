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
	final static int startWidth = 150, height = 60, edgePadding = 30;
	
	public enum State { Floating, Falling, Dead };
	public State state;
	
	public static boolean hasHitBottom;
	
	public boolean hasFirstCollision;
	
	public TowerSegment() {
		this(startWidth);
	}
	
	public TowerSegment(int width) {
		super(makeAnim(width), MathUtils.random() * (Game.WIDTH - 300) + 100, Game.camera.position.y + Game.HEIGHT / 2 - 120);
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
				
				if (collidedObject != null && collidedObject instanceof TowerSegment) {
					TowerSegment col = ((TowerSegment)collidedObject);
					if (col.hasFirstCollision) {
						onGameOver();
						return;
					}
					else col.hasFirstCollision = true;
				}
				
				
				if (getY() <= 0) {
					if (hasHitBottom) {
						onGameOver();
					} else {
						SceneManager.gameScene.add(new TowerSegment());
					}
					hasHitBottom = true;
				} else {
					int dw = Math.abs((int)getX() - (int)collidedObject.getX());
					int w = (int)getW() - dw;
					
					setW(w);
					if (getX() < collidedObject.getX()) setX(getX() + dw);
					//else setX(getX() + getW());
					
					if (w < 10) {
						onGameOver();
						return;
					}
					else SceneManager.gameScene.add(new TowerSegment(w));
				}
			}
			
			break;
		default: break;
		}
		
		super.update();
	}
	
	private void onGameOver() {
		System.out.println("Game over");
		SceneManager.gameScene.gameOver = true;
		SceneManager.gameScene.add(new EndBlock());		
	}
	
	private static Animation makeAnim(int width) {
		int texture = width / startWidth;
		if (texture < 0) texture = 0;
		if (texture >= Assets.towerBlocks.length) texture = Assets.towerBlocks.length - 1;
		Texture t = Assets.towerBlocks[texture];
		return new Animation(t, width, height, 1, t.getWidth(), t.getHeight(), 0);
	}
}
