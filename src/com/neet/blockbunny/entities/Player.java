package com.neet.blockbunny.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.neet.blockbunny.main.Game;

public class Player extends B2DSprite {

	private int numCrystals;
	private int totalCrystals;

	private boolean running;

	public Player(Body body) {

		super(body);

		running = false;

		run();

	}

	public void jump() {

		if (running) {
			getBody().setLinearVelocity(getBody().getLinearVelocity().x, 0);
			getBody().applyForceToCenter(0, 200, true);
			Game.res.getSound("jump").play();

			Texture tex = Game.res.getTexture("bearjump");
			TextureRegion[] sprites = new TextureRegion[3];
			for (int i = 0; i < sprites.length; i++) {
				sprites[i] = new TextureRegion(tex, i * 64, 0, 64, 32);
			}

			animation.setFrames(sprites, 1 / 4f);

			width = sprites[0].getRegionWidth();
			height = sprites[0].getRegionHeight();
			running = false;
		}

	}

	public void run() {

		if (!running) {
			System.out.println("I'M RUNNING");
			Texture tex = Game.res.getTexture("bearwalk");
			TextureRegion[] sprites = new TextureRegion[4];
			for (int i = 0; i < sprites.length; i++) {
				sprites[i] = new TextureRegion(tex, i * 64, 0, 64, 32);
			}

			animation.setFrames(sprites, 1 / 9f);

			width = sprites[0].getRegionWidth();
			height = sprites[0].getRegionHeight();
			running = true;
		}
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

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

}
