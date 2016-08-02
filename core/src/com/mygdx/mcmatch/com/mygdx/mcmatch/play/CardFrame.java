package com.mygdx.mcmatch.com.mygdx.mcmatch.play;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Map;

/**
 * Created by matthew on 8/2/16.
 */
public class CardFrame {

    private Map<Integer,Card> cards;
    private int x, y, w, h;
    private int numCards;
    private int cardHeight;
    private int cardWidth;
    private int rows;
    private int columns;

    private static final String TAG = "CardFrame";
    public CardFrame (int x, int y, int width, int height, int numCards, SpriteBatch batch) {
        if(numCards % 2 != 0) {
//            android.util.Log.e(TAG, "CardFrame: numCards expected even number, received: " + numCards);
        }
        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;

        if(w > h) { // Landscape
            if((numCards & (numCards - 1)) == 0) { // isPowerOfTwo
                rows = numCards / 2;
                columns = rows;
                int xSpace = (w / columns);
                int ySpace = (h / rows);
                int pad = 10;

                int i = 0;
                for(int c = 0; c < columns; c++) {
                    for(int r = 0; r < rows; r++) {
                        int cx = xSpace * c + pad;
                        int cy = ySpace * r + pad;
                        int cw = xSpace - pad;
                        int ch = ySpace - pad;
                        cards.put(i, new Card(cy,cy,cw,ch,"TEST",batch));
                    }
                }

            }
        } else { // Portrait

        }

    }

    public Map<Integer, Card> getCards() {
        return cards;
    }

}
