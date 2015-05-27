package gameScene;

import com.larsson.johannes.minaretBuilder.framework.Animation;
import com.larsson.johannes.minaretBuilder.framework.Game;
import com.larsson.johannes.minaretBuilder.framework.GameObject;
import com.larsson.johannes.minaretBuilder.game.Assets;

public class Background extends GameObject {

	public Background() {
		super(new Animation(Assets.background, Game.WIDTH, 2100, 1, 1024, 2100, 0), 0, 0);
		// TODO Auto-generated constructor stub
	}
	
	public void update() {
		setY((Game.camera.position.y - Game.HEIGHT / 2) / 3);
	}
}
