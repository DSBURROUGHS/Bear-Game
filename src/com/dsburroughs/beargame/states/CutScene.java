package com.dsburroughs.beargame.states;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dsburroughs.beargame.handlers.Background;
import com.dsburroughs.beargame.handlers.GameButton;
import com.dsburroughs.beargame.handlers.GameStateManager;
import com.dsburroughs.beargame.main.Game;

public class CutScene extends GameState {

	private Background bg;
	private GameButton continueButton;

	private BitmapFont font;

	public static int level;

	public CutScene(GameStateManager gsm) {
		super(gsm);

		font = new BitmapFont();
		// font.setColor(Color.);

		TextureRegion continueTexture = new TextureRegion(Game.res.getTexture("hud"), 0, 34, 58, 27);
		continueButton = new GameButton(continueTexture, 160, 75, cam);

		Texture tex = Game.res.getTexture("menu");
		bg = new Background(new TextureRegion(tex), cam, 1f);
		bg.setVector(-20, 0);

		cam.setToOrtho(false, Game.V_WIDTH, Game.V_HEIGHT);
	}

	@Override
	public void handleInput() {

		// TODO: Wait for continue to be pressed
		if (continueButton.isClicked()) {
			Game.res.getSound("crystal").play();
			gsm.setState(GameStateManager.LEVEL_SELECT);
		}

	}

	@Override
	public void update(float dt) {

		handleInput();

		continueButton.update(dt);

	}

	@Override
	public void render() {

		String cutSceneText = determineCutScene();

		sb.setProjectionMatrix(cam.combined);

		// draw background
		bg.render(sb);

		// draw button
		continueButton.render(sb);

		// draw bunny
		sb.begin();
		font.drawWrapped(sb, cutSceneText, 40, 200, 250);
		sb.end();

	}

	@Override
	public void dispose() {

	}

	private String determineCutScene() {

		String text = null;

		try {
			text = FileUtils.readFileToString(new File("res/story/level" + level + ".txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return text;
	}
}
