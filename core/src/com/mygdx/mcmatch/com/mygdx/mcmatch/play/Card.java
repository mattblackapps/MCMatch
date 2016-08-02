package com.mygdx.mcmatch.com.mygdx.mcmatch.play;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by matthew on 8/1/16.
 */
public class Card {
    private Rectangle rect;
    private Vector2 center;
    private ShapeRenderer shapeRenderer;
    private String word;
    private boolean flipped = false;
    private boolean matched = false;
    private BitmapFont font;
    private SpriteBatch batch;

    public Card (int x, int y, int width, int height, String word, SpriteBatch batch) {
        this.rect = new Rectangle(x, y, width, height);
        center = new Vector2(x + width / 2, y + height / 2);
        this.word = word;
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        this.batch = batch;

        flipped = true;

    }

    public void render() {
        if(flipped) {
            if(matched) {

            } else {
                renderFlipped();
            }
        } else {
            renderHidden();
        }
    }

    public void renderText() {
        if(flipped) {
            font.draw(batch, word, center.x, center.y);
        }
    }

    public void setMatched() {
        matched = true;
        font.setColor(Color.GREEN);
    }

    public void toggleFlipped() {
        flipped = !flipped;
    }

    public void setFlipped(boolean b) {
        flipped = b;
    }

    public boolean isFlipped() { return flipped; }

    private void renderFlipped() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.LIGHT_GRAY);
        shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        shapeRenderer.end();
    }

    private void renderHidden() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        shapeRenderer.end();
    }



}
