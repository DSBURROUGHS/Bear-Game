package com.dsburroughs.beargame.handlers;

import java.util.Stack;

import com.dsburroughs.beargame.main.Game;
import com.dsburroughs.beargame.states.CutScene;
import com.dsburroughs.beargame.states.GameState;
import com.dsburroughs.beargame.states.LevelSelect;
import com.dsburroughs.beargame.states.Menu;
import com.dsburroughs.beargame.states.Play;

public class GameStateManager {

	private Game game;

	private Stack<GameState> gameStates;

	public static final int MENU = 83774392;
	public static final int PLAY = 388031654;
	public static final int LEVEL_SELECT = -9238732;
	public static final int CUT_SCENE = 8675309;

	private int maximumCurrentLevel;

	public GameStateManager(Game game) {
		this.game = game;

		maximumCurrentLevel = 1;

		gameStates = new Stack<GameState>();
		pushState(MENU);
	}

	public void update(float dt) {
		gameStates.peek().update(dt);
	}

	public void render() {
		gameStates.peek().render();
	}

	public Game game() {
		return game;
	}

	private GameState getState(int state) {
		if (state == MENU)
			return new Menu(this);
		if (state == PLAY)
			return new Play(this);
		if (state == LEVEL_SELECT) {
			LevelSelect temp = new LevelSelect(this);
			temp.setMaximumLevel(maximumCurrentLevel);
			return temp;
		}
		if (state == CUT_SCENE) {
			CutScene temp = new CutScene(this);
			return temp;
		}

		return null;
	}

	public void setState(int state) {
		popState();
		pushState(state);
	}

	public void pushState(int state) {
		gameStates.push(getState(state));
	}

	public void popState() {
		GameState g = gameStates.pop();
		g.dispose();
	}

	public void incrementLevel() {
		maximumCurrentLevel++;
	}

	public int getMaximumLevel() {
		return maximumCurrentLevel;
	}

}
