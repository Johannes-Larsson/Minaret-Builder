package com.larsson.johannes.minaretBuilder.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.larsson.johannes.minaretBuilder.framework.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 400;
		config.height = (int)(config.width * (16f / 9f));
		new LwjglApplication(new Game(), config);
	}
}
