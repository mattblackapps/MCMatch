package com.mygdx.mcmatch.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.mcmatch.com.mygdx.mcmatch.play.Card;
import com.mygdx.mcmatch.com.mygdx.mcmatch.play.CardFrame;
import com.mygdx.mcmatch.handlers.GameStateManager;
import com.mygdx.mcmatch.handlers.TouchProcessor;

import java.util.List;
import java.util.Map;

/**
 * Created by MC on 7/20/16.
 */
public class Play extends GameState {

	private SpriteBatch batch;
	private TouchProcessor tp;

	private int numCards = 10;
	private CardFrame cardFrame;
	private List<Card> cards;
	private Card cardA;
	private Card cardB;

	private int score = 0;

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
		for (Card card : cards) {
			tp.register(card);
		}
	}

	@Override
	public void handleInput() {
		for(Card card : cards) {
			if(card.getState() == Card.CardState.FLIPPED) {
				if(cardA == null) {
					cardA = card;
				} else if (cardB == null) {
					cardB = card;
				}
				if(cardA != null && cardB != null) {
					if (cardA.getWord().contains(cardB.getWord())) {
						cardA.setMatched();
						cardB.setMatched();
						cardA = null;
						cardB = null;
					}

				}
			}
		}
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
}
