package com.dsburroughs.beargame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.dsburroughs.beargame.main.Game;

public class Player extends B2DSprite {

	private int numCrystals;
	private int totalCrystals;

	public Player(Body body) {

		super(body);

		run();

	}

	public void jump() {

		getBody().setLinearVelocity(getBody().getLinearVelocity().x, 0);
		getBody().applyForceToCenter(0, 200, true);
		Game.res.getSound("jump").play();

		// TODO: fix issue with character not resetting texture upon jump completion

		// Texture tex = Game.res.getTexture("bearjump");
		// TextureRegion[] sprites = new TextureRegion[3];
		// for (int i = 0; i < sprites.length; i++) {
		// sprites[i] = new TextureRegion(tex, i * 64, 0, 64, 32);
		// }
		//
		// animation.setFrames(sprites, 1 / 4f);
		//
		// width = sprites[0].getRegionWidth();
		// height = sprites[0].getRegionHeight();

	}

	public void run() {

		Texture tex = Game.res.getTexture("bearwalk");
		TextureRegion[] sprites = new TextureRegion[4];
		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = new TextureRegion(tex, i * 64, 0, 64, 32);
		}

		animation.setFrames(sprites, 1 / 9f);

		width = sprites[0].getRegionWidth();
		height = sprites[0].getRegionHeight();

	}

	public void collectCrystal() {
		numCrystals++;
	}

	public int getNumCrystals() {
		return numCrystals;
	}

	public void setTotalCrystals(int i) {
		totalCrystals = i;
	}

	public int getTotalCrystals() {
		return totalCrystals;
	}

}
