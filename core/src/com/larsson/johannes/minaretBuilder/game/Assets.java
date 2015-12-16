package com.larsson.johannes.minaretBuilder.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Assets {
	public static BitmapFont fontBig, fontSmall;
	
	public static Texture[] towerBlocks, clouds;
	
	public static Texture 
	endBlock,
	gameBackground,
	
	menuBackground,
	playButtonLight,
	playButtonDark,
	quitButtonLight,
	quitButtonDark,
	
	highscoreBackground;
	
	public static void load() {
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("ArabDances.ttf"));
		FreeTypeFontParameter par = new FreeTypeFontParameter();
		
		par.magFilter = TextureFilter.MipMapLinearLinear;
		
		par.size = 90;
		fontSmall = gen.generateFont(par);
		
		par.size = 100;
		fontBig = gen.generateFont(par);
		
		gen.dispose();
		
		
		towerBlocks = new Texture[8];
		for (int i = 0; i < 8; i++) towerBlocks[i] = new Texture("Lada_" + (i + 1) + ".png");
		
		clouds = new Texture[4];
		for (int i = 0; i < clouds.length; i++) clouds[i] = new Texture("Moln_" + (i + 1) + ".png");
		
		endBlock = new Texture("towerTop.png");
		gameBackground = new Texture("background.png");
		
		menuBackground = new Texture("menuBackground.png");
		playButtonLight = new Texture("playButtonLight.png");
		playButtonDark = new Texture("playButtonDark.png");
		quitButtonLight = new Texture("quitButtonLight.png");
		quitButtonDark = new Texture("quitButtonDark.png");
		highscoreBackground = new Texture("highscoreScreen.png");
	}
}
