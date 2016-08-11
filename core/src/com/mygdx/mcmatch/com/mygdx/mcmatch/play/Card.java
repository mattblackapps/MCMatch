package com.mygdx.mcmatch.com.mygdx.mcmatch.play;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.mcmatch.handlers.Touch;
import com.mygdx.mcmatch.handlers.Touchable;

/**
 * Created by matthew on 8/1/16.
 */
public class Card implements Touchable {

    private CardState state;

    private Rectangle rect;
    private Vector2 center;
    private ShapeRenderer shapeRenderer;
    private String word;
    private BitmapFont font;
    private SpriteBatch batch;

    public Card (int x, int y, int width, int height, String word, SpriteBatch batch) {
        this.rect = new Rectangle(x, y, width, height);
        this.word = word;
        this.batch = batch;

        center = new Vector2(x + width / 2, y + height / 2);
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();

        state = CardState.IDLE;
    }

    public enum CardState {
        IDLE,FLIPPED,MATCHED
    }

    public void render() {
        switch (state) {
            case IDLE:
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                shapeRenderer.setColor(Color.DARK_GRAY);
                shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
                shapeRenderer.end();
                break;
            case FLIPPED:
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(Color.DARK_GRAY);
                shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
                shapeRenderer.end();
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                shapeRenderer.setColor(Color.LIGHT_GRAY);
                shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
                shapeRenderer.end();
                batch.begin();
                font.setColor(Color.DARK_GRAY);
                font.draw(batch, word, center.x, center.y);
                batch.end();
                break;
            case MATCHED:
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(Color.GREEN);
                shapeRenderer.rect(rect.x +10, rect.y +10, rect.width -10, rect.height -10);
                shapeRenderer.end();
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                shapeRenderer.setColor(Color.GOLD);
                shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
                shapeRenderer.end();
                batch.begin();
                font.setColor(Color.GOLD);
                font.draw(batch, word, center.x, center.y);
                batch.end();
            default:
                break;
        }
    }

    public void setMatched() {
        state = CardState.MATCHED;
    }

    public void toggleFlipped() {
        if(state == CardState.FLIPPED) { state = CardState.IDLE; }
        else if (state == CardState.IDLE) { state = CardState.FLIPPED; }
    }

    public CardState getState() { return state; }
    public String getWord() { return word; }

    @Override
    public boolean touched(Touch touch) {
        if(rect.contains(touch.x, touch.y)) {
            return true;
        }
        return false;
    }
}
