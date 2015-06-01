package ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.larsson.johannes.minaretBuilder.framework.Input;

public class Button {
	
	private Texture regular, hover;
	private float x, y, w, h;
	
	public Button(Texture regular, Texture hover, float x, float y, float w, float h) {
		this.regular = regular;
		this.hover = hover;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public boolean isPressed() {
		return Input.areaIsClicked(x, y, w, h) && Input.wasJustPressed();
	}
	
	public void draw(SpriteBatch uiBatch) {
		uiBatch.draw(Input.intersectingWith(x, y, w, h) ? hover : regular, x, y, w, h);
	}
}
