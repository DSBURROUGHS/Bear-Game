package com.dsburroughs.beargame.states;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dsburroughs.beargame.handlers.GameButton;
import com.dsburroughs.beargame.handlers.GameStateManager;
import com.dsburroughs.beargame.main.Game;

public class LevelSelect extends GameState {

	private TextureRegion reg;
	private GameButton[][] buttons;

	private int currentMaxLevel;

	private static final int MAX_COLUMN_SIZE = 4;

	private static final int MAXIMUM_LEVEL = 4;

	public LevelSelect(GameStateManager gsm) {

		super(gsm);

		currentMaxLevel = 0;

		reg = new TextureRegion(Game.res.getTexture("bgs"), 0, 0, 320, 240);

		TextureRegion buttonReg = new TextureRegion(Game.res.getTexture("hud"), 0, 0, 32, 32);
		buttons = new GameButton[1][MAX_COLUMN_SIZE];
		for (int row = 0; row < buttons.length; row++) {
			for (int col = 0; col < buttons[0].length; col++) {

				GameButton button = new GameButton(buttonReg, 100 + col * 40, 200 - row * 40, cam);
				button.setText(row * buttons[0].length + col + 1 + "");
				button.setVisible(false);

				buttons[row][col] = button;

			}
		}

		cam.setToOrtho(false, Game.V_WIDTH, Game.V_HEIGHT);

	}

	public void handleInput() {
	}

	public void update(float dt) {

		handleInput();

		for (int row = 0; row < buttons.length; row++) {
			for (int col = 0; col < buttons[0].length; col++) {
				buttons[row][col].update(dt);
				if (buttons[row][col].isClicked()) {
					Play.level = row * buttons[0].length + col + 1;
					Game.res.getSound("levelselect").play();
					gsm.setState(GameStateManager.PLAY);
				}
			}
		}

	}

	public void render() {

		sb.setProjectionMatrix(cam.combined);

		sb.begin();
		sb.draw(reg, 0, 0);
		sb.end();

		for (int row = 0; row < buttons.length; row++) {
			for (int col = 0; col < buttons[0].length; col++) {
				buttons[row][col].render(sb);
			}
		}

	}

	public void dispose() {
		// everything is in the resource manager com.neet.blockbunny.handlers.Content
	}

	/**
	 * Used to increment the max level of the level select.
	 */
	public void increaseMaximumLevel() {

		if (currentMaxLevel < MAXIMUM_LEVEL) {

			int row = currentMaxLevel / MAX_COLUMN_SIZE;
			int col = currentMaxLevel % MAX_COLUMN_SIZE;

			buttons[row][col].setVisible(true);

			currentMaxLevel++;
		}

	}

	public void setMaximumLevel(int maximumCurrentLevel) {

		for (int i = 0; i < maximumCurrentLevel; i++) {
			increaseMaximumLevel();
		}
	}
}
