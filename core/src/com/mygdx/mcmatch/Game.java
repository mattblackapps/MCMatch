package com.mygdx.mcmatch;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.mcmatch.handlers.GameStateManager;
//import com.mygdx.mcmatch.handlers.MyInputProcessor;

public class Game extends ApplicationAdapter {

	public static final String TITLE = "Block Bunny";
	public static final float STEP = 1 / 60f;
	private float accum;


	SpriteBatch sb;
	OrthographicCamera cam;
	OrthographicCamera hudCam;

	private GameStateManager gsm;
	
	@Override
	public void create () {

//		Gdx.input.setInputProcessor(new MyInputProcessor());

		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		gsm = new GameStateManager(this);

	}

	@Override
	public void render () {
		accum += Gdx.graphics.getDeltaTime();
		while(accum >= STEP) {
			accum -= STEP;
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			gsm.update(STEP);
			gsm.render();
//			Touch.update();
		}
	}
	
	@Override
	public void dispose () {

	}

	public SpriteBatch getSpriteBatch() { return sb; }
	public OrthographicCamera getCamera() { return cam; }
	public OrthographicCamera getHUDCamera() { return hudCam; }
}
