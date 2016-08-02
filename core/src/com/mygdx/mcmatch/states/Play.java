package com.mygdx.mcmatch.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.mcmatch.com.mygdx.mcmatch.play.Card;
import com.mygdx.mcmatch.handlers.GameStateManager;

/**
 * Created by MC on 7/20/16.
 */
public class Play extends GameState {

	private SpriteBatch batch;

	public static Logger logger = new Logger("Dev", Logger.INFO);

	private int numCards = 4;
	private Card[] cards;
	private Card cardA;
	private Card cardB;

	private int score = 0;

	public Play(GameStateManager gsm) {
		super(gsm);
		batch = gsm.game().getSpriteBatch();

		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		height = MathUtils.ceil( height * .85f);



	}

	@Override
	public void handleInput() {



	}

	@Override
	public void update(float dt) {
		handleInput();

//		world.step(dt, 6, 2);

	}

	@Override
	public void render() {
//		card1.render();
		batch.begin();
//		card1.renderText();
		batch.end();
	}

	@Override
	public void dispose() {

	}
}
