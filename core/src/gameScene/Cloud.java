package gameScene;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.larsson.johannes.minaretBuilder.framework.Animation;
import com.larsson.johannes.minaretBuilder.framework.Game;
import com.larsson.johannes.minaretBuilder.framework.GameObject;
import com.larsson.johannes.minaretBuilder.game.Assets;

public class Cloud extends GameObject {
	
	public Cloud(int height) {
		super(getAnim(), MathUtils.random(0, Game.WIDTH), height);
		vx = MathUtils.random(.1f, .3f) * (MathUtils.randomBoolean() ? -1 : 1);
	}
	
	public void update() {
		if (getX() + getW() < 0 || getX() > Game.WIDTH) vx *= -1;
		
		super.update();
	}
	
	private static Animation getAnim() {
		Texture t = Assets.clouds[MathUtils.random(Assets.clouds.length - 1)];
		return new Animation(t, t.getWidth(), t.getHeight(), 1, t.getWidth(), t.getHeight(), 0);
	}
}
