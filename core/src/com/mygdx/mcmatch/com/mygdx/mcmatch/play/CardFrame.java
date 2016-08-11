package com.mygdx.mcmatch.com.mygdx.mcmatch.play;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by matthew on 8/2/16.
 */
public class CardFrame {

    private List<Card> cards;
    private int x, y, w, h;
    private int rows;
    private int columns;

    public CardFrame (int x, int y, int width, int height, int numCards, SpriteBatch batch) {
        if(numCards % 2 != 0) {
            System.out.println("CardFrame: numCards expected even number, received: " + numCards);
            return;
        }

        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;

        cards = new ArrayList<Card>();

        if(w > h) { // Landscape
            rows = getLowFactor(numCards);
            columns = getHighFactor(numCards);
        } else { // Portrait
            rows = getHighFactor(numCards);
            columns = getLowFactor(numCards);
        }

        int xSpace = (w / columns);
        int ySpace = (h / rows);
        int pad = MathUtils.ceil((xSpace + ySpace) * 0.01f); // Calculate padding

        int i = 0;
        for(int c = 0; c < columns && i < numCards; c++) { // For each column c
            for(int r = 0; r < rows && i < numCards; r++) { // and each row r

                // Determine card position
                int cx = x + xSpace * c + pad;
                int cy = y + ySpace * r + pad;
                int cw = xSpace - pad*2;
                int ch = ySpace - pad*2;

                // Determine card text
                String text = Dealer.getInstance().getNextFor(numCards);

                // Add new card to the list
                cards.add(new Card(cx,cy,cw,ch,text,batch));
                i++;
            }
        }

    }

    public List<Card> getCards() {
        return cards;
    }

    private int getHighFactor(int n) {
        return (int) Math.round(Math.sqrt(n+Math.sqrt(n)));
    }
    private int getLowFactor(int n) {
        return (int) Math.round(Math.sqrt(n));
    }
}
