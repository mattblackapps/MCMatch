package com.mygdx.mcmatch.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * Created by MC on 8/11/16.
 */
public class FontGuy {
	private static FontGuy ourInstance = new FontGuy();
	private BitmapFont font;

	public static FontGuy getInstance() {
		return ourInstance;
	}
	public BitmapFont getFont() { return font; }

	private FontGuy() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Drifttype.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 64;
		font = generator.generateFont(parameter);
		generator.dispose();
	}
}
