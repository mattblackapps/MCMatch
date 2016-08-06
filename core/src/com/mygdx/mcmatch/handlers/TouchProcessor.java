package com.mygdx.mcmatch.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MC on 7/24/16.
 */
public class TouchProcessor extends InputAdapter {
	private int h = Gdx.graphics.getHeight();
	private List<Touch> touches = new ArrayList<Touch>();
	private List<Touchable> touchables = new ArrayList<Touchable>();

	public void register(Touchable t) {
		touchables.add(t);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touches.add(pointer, new Touch(screenX, h - screenY, pointer, Touch.TouchState.BEGAN));
		touched(touches.get(pointer));
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Touch t = touches.get(pointer);
		t.x = screenX;
		t.y = h - screenY;
		t.state = Touch.TouchState.ENDED;
		touched(t);
		touches.remove(pointer);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Touch t = touches.get(pointer);
		t.x = screenX;
		t.y = h - screenY;
		t.state = Touch.TouchState.MOVING;
		touched(t);
		touches.add(pointer, t);
		return false;
	}

	private void touched(Touch touch) {
		for(Touchable t : touchables) {
			t.touched(touch);
		}
	}
}
