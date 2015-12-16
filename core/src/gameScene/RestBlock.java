package gameScene;

import com.larsson.johannes.minaretBuilder.framework.Animation;
import com.larsson.johannes.minaretBuilder.framework.GameObject;

public class RestBlock extends GameObject {

	public RestBlock(boolean isRight, Animation animation, int textureStart, int textureEnd, float x, float y) {
		super(animation, x, y);
		animation.sprite.setRegion(textureStart, 0, textureEnd - textureStart, animation.sprite.getTexture().getHeight());
		System.out.println("new restblock, x = " + x + ", y = " + y + ", " + "w = " + animation.sprite.getWidth() + " h = " + animation.sprite.getHeight());
		
		if (isRight) vx = 3;
		else vx = -3;
	}
	
	public void update() {
		super.vy -= .5f;
		super.update();
	}

}
