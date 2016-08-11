package com.mygdx.mcmatch.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.mcmatch.com.mygdx.mcmatch.play.Card;
import com.mygdx.mcmatch.com.mygdx.mcmatch.play.CardFrame;
import com.mygdx.mcmatch.handlers.GameStateManager;
import com.mygdx.mcmatch.handlers.Touch;
import com.mygdx.mcmatch.handlers.TouchProcessor;
import com.mygdx.mcmatch.handlers.Touchable;

import java.util.List;
import java.util.Map;

/**
 * Created by MC on 7/20/16.
 */
public class Play extends GameState implements Touchable {

	public enum PlayState {
		IDLE, ONEFLIP, TWOFLIP, MATCH
	}

	private SpriteBatch batch;
	private TouchProcessor tp;

	private int numCards = 12;
	private CardFrame cardFrame;
	private List<Card> cards;
	private Card cardA;
	private Card cardB;

	private int score = 0;

	private PlayState state = PlayState.IDLE;

	public Play(GameStateManager gsm) {
		super(gsm);
		batch = gsm.game().getSpriteBatch();

		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		height = MathUtils.ceil( height * .85f);

		cardFrame = new CardFrame(0,0,width,height,numCards,batch);
		cards = cardFrame.getCards();

		tp = new TouchProcessor();
		Gdx.input.setInputProcessor(tp);
		tp.register(this);
	}

	@Override
	public void handleInput() {

	}

	@Override
	public boolean touched(Touch touch) {
		if(touch.state == Touch.TouchState.ENDED) {
			Card card = getTouchedCard(touch);
			if (card != null) {
				switch (state) {

					case IDLE:
						handleFlip(card);
						break;

					case ONEFLIP:
						handleMatch(card);
						break;
					case TWOFLIP:
						cardA.toggleFlipped();
						cardB.toggleFlipped();
						cardA = null;
						cardB = null;
						handleFlip(card);
				}
			}
		}
		return true;
	}

	@Override
	public void update(float dt) {
		handleInput();

//		world.step(dt, 6, 2);

	}

	@Override
	public void render() {
		for(Card card: cards) {
			card.render();
		}
	}

	@Override
	public void dispose() {

	}

	private Card getTouchedCard(Touch touch) {
		for(Card card : cards) {
			if(card.touched(touch)) {
				return card;
			}
		}
		return null;
	}

	private void handleFlip(Card card) { // called when play state = idle
		if(card.getState() == Card.CardState.IDLE) { // card not already matched or flipped
			card.toggleFlipped();
			cardA = card;
			state = PlayState.ONEFLIP;
		}
	}

	private void handleMatch(Card card) {
		if(card.getState() == Card.CardState.IDLE && cardA != null) { // cardA exists
			cardB = card;
			if(cardA.getWord().equals(cardB.getWord())) { // Match
				cardA.setMatched();
				cardB.setMatched();
				state = PlayState.IDLE;
				System.out.println("Cards match: " + Integer.toString(cards.indexOf(cardA)) + " & " + Integer.toString(cards.indexOf(cardB)));
			} else { // No match
				cardB.toggleFlipped();
				state = PlayState.TWOFLIP;
				System.out.println("Cards dont match: " + Integer.toString(cards.indexOf(cardA)) + " & " + Integer.toString(cards.indexOf(cardB)));
			}
		} else {
			System.out.println("Else?");
		}
	}
}
