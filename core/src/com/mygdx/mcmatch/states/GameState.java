package com.mygdx.mcmatch.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.mcmatch.Game;
import com.mygdx.mcmatch.handlers.GameStateManager;

/**
 * Created by MC on 7/20/16.
 */
public abstract class GameState {

	protected GameStateManager gsm;
	protected Game game;

	protected SpriteBatch sb;

	protected GameState(GameStateManager gsm) {
		this.gsm = gsm;
		game = gsm.game();
		sb = game.getSpriteBatch();
	}

	public abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render();
	public abstract void dispose();
}
