package com.mygdx.mcmatch.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by MC on 7/24/16.
 */
public class TouchProcessor extends InputAdapter {

	private Rectangle touchRect;

	public void setTouchArea(int x, int y, int w, int h) {
		this.touchRect = new Rectangle(x, y, w, h);
	}

	public TouchProcessor(int x, int y, int w, int h) {
		setTouchArea(x, y, w, h);
	}



	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		if(touchRect.contains(screenX, screenY)) { return true; }

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		if(touchRect.contains(screenX, screenY)) { return true; }

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		if(touchRect.contains(screenX, screenY)) { return true; }

		return false;
	}
}
