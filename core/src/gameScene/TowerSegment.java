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
	final static int startWidth = 300, height = 120, edgePadding = 30;
	
	public enum State { Floating, Falling, Dead };
	public State state;
	
	final static int floor = 40;
	public static boolean hasHitBottom;
	
	public boolean hasFirstCollision;
	
	public TowerSegment() {
		this(startWidth);
	}
	
	public TowerSegment(int width) {
		super(makeAnim(width), Game.WIDTH / 2 - width / 2, Game.camera.position.y + Game.HEIGHT / 2 - 200);
		state = State.Floating;
		vx = MathUtils.floor(MathUtils.random(1) * 2) - 1;
		vx *= 5 + (SceneManager.gameScene != null ? SceneManager.gameScene.getDifficulty() : 0);
		solid = true;
	}
	
	public void update() {
		
		if (SceneManager.gameScene.getNoOfTowers() == 1) {
			state = State.Falling;
			vx = 0;
		}
		
		switch (state) {
		case Floating:
			
			if (getX() < edgePadding || getX() + getW() > Game.WIDTH - edgePadding) vx *= -1;
			
			if (Input.isPressed()) {
				state = State.Falling;
				vx *= SceneManager.gameScene.getDifficulty();
			}
			
			break;
		case Falling:
			
			vy -= .5f;
			if (collisionY != CollisionState.None || getY() <= floor) {
				state = State.Dead;
				vy = 0;
				vx = 0;
				float d = getY()  - Game.cameraTargetY;
				System.out.println(d);
				if (d > 0) { 
					Game.cameraTargetY += d;
				}
				
				if (collidedObject != null && collidedObject instanceof TowerSegment) {
					TowerSegment col = ((TowerSegment)collidedObject);
					if (col.hasFirstCollision) {
						onGameOver();
						return;
					} else {
						col.hasFirstCollision = true;
						//SceneManager.gameScene.score++;
					}
				}
				
				
				if (getY() <= floor) {
					setY(floor);
					if (hasHitBottom) {
						onGameOver();
					} else {
						SceneManager.gameScene.add(new TowerSegment());
						SceneManager.gameScene.score += 100;
					}
					hasHitBottom = true;
				} else {
					int dw = Math.abs((int)getX() - (int)collidedObject.getX());
					int w = (int)getW() - dw;

					int score = 100 + (int) (Math.pow((getW() - dw), 3) / Math.pow(getW(), 3) * 100);
					SceneManager.gameScene.addScore(score);
					System.out.println("score: " + score);
					
					int tx = (int)(w / getW() * animation.sprite.getTexture().getWidth());
					int dtx = (int)(dw / getW() * animation.sprite.getTexture().getWidth());
					
					animation.sprite.setRegionWidth(tx);
					
					setW(w);
					if (getX() < collidedObject.getX()) { // was to the left
						SceneManager.getCurrentScene().add.add(new RestBlock(false, new Animation(animation.sprite.getTexture(), dw, getH()), 0, dtx, getX(), getY()));
						setX(getX() + dw);
						animation.sprite.setRegionX(dtx);
						animation.sprite.setRegionWidth(animation.sprite.getTexture().getWidth() - dtx);
						animation.sprite.setRegionX(dtx);
					} else {
						SceneManager.getCurrentScene().add.add(new RestBlock(true, new Animation(animation.sprite.getTexture(), dw, getH()), dtx, animation.sprite.getTexture().getWidth(), getX() + w, getY()));
					}
					
					
					
					if (w < 10) {
						onGameOver();
						return;
					} else {
						SceneManager.gameScene.add(new TowerSegment(w));
					}
				}
			}
			
			break;
		default:
			if (getY() > floor) vy -= .1f;
			break; //dead
		}
		
		super.update();
	}
	
	private void onGameOver() {
		System.out.println("Game over");
		SceneManager.gameScene.gameOver = true;
		SceneManager.gameScene.add(new EndBlock());		
	}
	
	private static Animation makeAnim(int width) {
		int texture = Assets.towerBlocks.length - (int)(((float)width / startWidth) * Assets.towerBlocks.length);
		if (texture < 0) texture = 0;
		if (texture >= Assets.towerBlocks.length) texture = Assets.towerBlocks.length - 1;
		Texture t = Assets.towerBlocks[texture];
		return new Animation(t, width, height, 1, t.getWidth(), t.getHeight(), 0);
	}
}
