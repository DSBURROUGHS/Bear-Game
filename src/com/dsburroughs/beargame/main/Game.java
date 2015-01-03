package com.dsburroughs.beargame.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dsburroughs.beargame.handlers.BBInput;
import com.dsburroughs.beargame.handlers.BBInputProcessor;
import com.dsburroughs.beargame.handlers.BoundedCamera;
import com.dsburroughs.beargame.handlers.Content;
import com.dsburroughs.beargame.handlers.GameStateManager;

public class Game implements ApplicationListener {
	
	public static final String TITLE = "Bear Game";
	public static final int V_WIDTH = 320;
	public static final int V_HEIGHT = 240;
	public static final int SCALE = 2;
	public static final float STEP = 1 / 60f;
	
	private SpriteBatch sb;
	private BoundedCamera cam;
	private OrthographicCamera hudCam;
	
	private GameStateManager gsm;
	
	public static Content res;
	
	public void create() {
		
		Texture.setEnforcePotImages(false);
		Gdx.input.setInputProcessor(new BBInputProcessor());
		
		res = new Content();
		res.loadTexture("res/images/menu.png");
		res.loadTexture("res/images/bgs.png");
		res.loadTexture("res/images/hud.png");
		res.loadTexture("res/images/bearwalk.png");
		res.loadTexture("res/images/bearjump.png");
		res.loadTexture("res/images/crystal.png");
		res.loadTexture("res/images/spikes.png");
		
		res.loadSound("res/sfx/jump.wav");
		res.loadSound("res/sfx/crystal.wav");
		res.loadSound("res/sfx/levelselect.wav");
		res.loadSound("res/sfx/hit.wav");
		res.loadSound("res/sfx/changeblock.wav");
		
		res.loadMusic("res/music/title.mp3");
		res.getMusic("title").setLooping(true);
		res.getMusic("title").setVolume(0.5f);
		res.getMusic("title").play();
		
		cam = new BoundedCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		
		sb = new SpriteBatch();
		
		gsm = new GameStateManager(this);
		
	}
	
	public void render() {
		
		Gdx.graphics.setTitle(TITLE + " -- FPS: " + Gdx.graphics.getFramesPerSecond());
		
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render();
		BBInput.update();
		
	}
	
	public void dispose() {
		res.removeAll();
	}
	
	public void resize(int w, int h) {}
	
	public void pause() {}
	
	public void resume() {}
	
	public SpriteBatch getSpriteBatch() { return sb; }
	public BoundedCamera getCamera() { return cam; }
	public OrthographicCamera getHUDCamera() { return hudCam; }
	
}
