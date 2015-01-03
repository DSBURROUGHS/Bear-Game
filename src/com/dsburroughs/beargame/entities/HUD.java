package com.dsburroughs.beargame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dsburroughs.beargame.main.Game;

public class HUD {

	private Player player;

	private TextureRegion crystal;
	private TextureRegion[] font;

	public HUD(Player player) {

		this.player = player;

		Texture tex = Game.res.getTexture("hud");

		crystal = new TextureRegion(tex, 80, 0, 16, 16);

		font = new TextureRegion[11];
		for (int i = 0; i < 6; i++) {
			font[i] = new TextureRegion(tex, 32 + i * 9, 16, 9, 9);
		}
		for (int i = 0; i < 5; i++) {
			font[i + 6] = new TextureRegion(tex, 32 + i * 9, 25, 9, 9);
		}

	}

	public void render(SpriteBatch sb) {

		sb.begin();

		// draw crystal
		sb.draw(crystal, 100, 208);

		// draw crystal amount
		drawString(sb, player.getNumCrystals() + " / " + player.getTotalCrystals(), 132, 211);

		sb.end();

	}

	private void drawString(SpriteBatch sb, String s, float x, float y) {
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '/')
				c = 10;
			else if (c >= '0' && c <= '9')
				c -= '0';
			else
				continue;
			sb.draw(font[c], x + i * 9, y);
		}
	}

}
