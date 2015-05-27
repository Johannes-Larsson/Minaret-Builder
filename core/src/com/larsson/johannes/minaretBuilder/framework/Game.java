package com.larsson.johannes.minaretBuilder.framework;

import com.larsson.johannes.minaretBuilder.game.Assets;
import com.larsson.johannes.minaretBuilder.game.SceneManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;

public class Game extends ApplicationAdapter {
	final static float aspectRatio = 16f/9f;
	public static final int WIDTH = 1024, HEIGHT = (int)(WIDTH * aspectRatio);
	SpriteBatch batch;
	public static OrthographicCamera camera;
	ShaderProgram shader;
	
	public static float cameraTargetX, cameraTargetY;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(false, WIDTH, HEIGHT);
		Assets.load();
		SceneManager.init();
		Input.init();
		
		//ShaderProgram.pedantic = false;
		//shader = new ShaderProgram(Gdx.files.internal("shaders/shader.vsh"), Gdx.files.internal("shaders/shader.fsh"));
		
		//batch.setShader(shader);
		cameraTargetX = WIDTH / 2;
		cameraTargetY = HEIGHT / 2;
	}
	
	void update() {
		Input.update();
		SceneManager.getCurrentScene().update();
		
		camera.position.x = MathUtils.lerp(camera.position.x, cameraTargetX, .05f);
		camera.position.y = MathUtils.lerp(camera.position.y, cameraTargetY, .05f);
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(8/255f, 128/255f, 138/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		SceneManager.getCurrentScene().draw(batch);
		batch.end();
	}
}
