package com.mygdx.mcmatch.com.mygdx.mcmatch.play;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.mcmatch.Game;
import com.mygdx.mcmatch.handlers.FontGuy;
import com.mygdx.mcmatch.handlers.Touch;
import com.mygdx.mcmatch.handlers.Touchable;

/**
 * Created by matthew on 8/1/16.
 */
public class Card implements Touchable {

    private static final Color fontColor1 = Color.LIGHT_GRAY;
    private static final Color fontColor2 = Color.GOLD;

    private CardState state;

    private Rectangle rect;
    private Vector2 center;
    private ShapeRenderer shapeRenderer;
    private String word;
    private BitmapFont font;
    private GlyphLayout layout;
    private SpriteBatch batch;

    public Card (int x, int y, int width, int height, String word, SpriteBatch batch) {
        this.rect = new Rectangle(x, y, width, height);
        this.word = word;
        this.batch = batch;

        center = new Vector2(x + width / 2, y + height / 2);
        shapeRenderer = new ShapeRenderer();

        font = FontGuy.getInstance().getFont();
        font.setColor(fontColor1);
        layout = new GlyphLayout();
        layout.setText(font, word);

        state = CardState.IDLE;
    }

    public enum CardState {
        IDLE,FLIPPED,MATCHED
    }

    public void render() {
        switch (state) {
            case IDLE:
                // Draw Background
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                shapeRenderer.setColor(Color.DARK_GRAY);
                shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
                shapeRenderer.end();
                break;
            case FLIPPED:
                // Draw Background
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(Color.DARK_GRAY);
                shapeRenderer.rect(rect.x +2, rect.y +2, rect.width -4, rect.height -4);
                shapeRenderer.end();

                // Draw Border
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                shapeRenderer.setColor(Color.LIGHT_GRAY);
                shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
                shapeRenderer.end();

                // Draw text
                batch.begin();
                font.setColor(fontColor1);
                font.draw(batch, layout, rect.x + layout.width / 3, center.y + layout.height / 3);
                batch.end();
                break;
            case MATCHED:
                // Draw Background
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(0.2f, 0.4f, 0.2f, 1.0f);
                shapeRenderer.rect(rect.x +2, rect.y +2, rect.width -4, rect.height -4);
                shapeRenderer.end();

                // Draw Border
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                shapeRenderer.setColor(Color.GOLD);
                shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
                shapeRenderer.end();

                // Draw text
                batch.begin();
                font.setColor(fontColor2);
                font.draw(batch, layout, rect.x + layout.width / 3, center.y + layout.height / 3);
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
