package gameScene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.larsson.johannes.minaretBuilder.framework.Animation;
import com.larsson.johannes.minaretBuilder.framework.GameObject;
import com.larsson.johannes.minaretBuilder.game.Assets;
import com.larsson.johannes.minaretBuilder.game.SceneManager;

public class FloatingMessage extends GameObject {
	
	int lifeTime, timer;
	String message;
	
	public FloatingMessage(float x, float y, String message, int lifeTime) {
		super(new Animation(Assets.quitButtonDark, 1, 1, 1, 1, 1, 0), x, y); //the animation is never drawn but is still needed
		
		this.message = message;
		this.lifeTime = lifeTime;
		
		vy = -1;
	}
	
	public void update() {
		timer++;
		if (timer >= lifeTime) SceneManager.getCurrentScene().remove(this);
		
		super.update();
	}
	
	public void drawUi(SpriteBatch batch) {
		Color o = Assets.fontBig.getColor();
		Assets.fontBig.setColor(o.r, o.g, o.b, 1 - (float)timer / lifeTime);
		Assets.fontBig.drawMultiLine(batch, message, getX(), getY());
		Assets.fontBig.setColor(o);
	}
}
