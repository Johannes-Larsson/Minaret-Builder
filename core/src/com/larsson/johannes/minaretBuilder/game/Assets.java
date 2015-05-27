package com.larsson.johannes.minaretBuilder.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Assets {
	public static BitmapFont fontBig, fontSmall;
	
	public static Texture[] towerBlocks, clouds;
	
	public static Texture endBlock, background;
	
	public static void load() {
		/*FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("ARIAL.TTF"));
		FreeTypeFontParameter par = new FreeTypeFontParameter();
		par.size = 12;
		fontSmall = gen.generateFont(par);
		
		par.size = 24;
		fontBig = gen.generateFont(par);
		
		gen.dispose();
		*/
		
		towerBlocks = new Texture[8];
		for (int i = 0; i < 8; i++) towerBlocks[i] = new Texture("Lada_" + (i + 1) + ".png");
		
		clouds = new Texture[4];
		for (int i = 0; i < clouds.length; i++) clouds[i] = new Texture("Moln_" + (i + 1) + ".png");
		
		endBlock = new Texture("towerTop.png");
		background = new Texture("background.png");
		
		fontBig = new BitmapFont();
	}
}
