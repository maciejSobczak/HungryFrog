package com.mygdx.game.desktop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class MyGdxGame extends ApplicationAdapter {
//	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture backgroundImg;
	private Texture frogImg;
	private Texture bugImg;
	private Rectangle frog;
	private Array<Rectangle> bugs;
	private long lastBugTime;

	public void spawnBugs () {
		Rectangle bug = new Rectangle();
		bug.x = MathUtils.random(0, Gdx.graphics.getWidth());
		bug.y = Gdx.graphics.getHeight();
		bug.width = 72;
		bug.height = 74;
		lastBugTime = TimeUtils.nanoTime();
	}

	@Override
	public void create () {
//		camera = new OrthographicCamera();
//		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		backgroundImg = new Texture(Gdx.files.internal("forest.jpg"));
		frogImg = new Texture(Gdx.files.internal("frog.png"));
		frog = new Rectangle();
		frog.x = Gdx.graphics.getWidth()/2 - 91/2;
		frog.y = 20;
		frog.width = 91;
		frog.height = 60;
		bugImg = new Texture(Gdx.files.internal("butterfly.png"));
	}

	public void updateScene() {
//		if(Gdx.input.justTouched()) {
//			System.out.println(Gdx.input.getX() + "," + Gdx.input.getY());
//		}
		if(Gdx.input.isTouched()) {
//			Vector3 touchPos = new Vector3();
//			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//			camera.unproject(touchPos);
			frog.x = Gdx.input.getX() - frog.width/2;
			frog.y = Gdx.graphics.getHeight() - Gdx.input.getY() - frog.height/2;

			System.out.println("żabka: " + frog.x + "," + frog.y + " | kursor: " + Gdx.input.getX() + "," + Gdx.input.getY());
		}

		if(TimeUtils.nanoTime() - lastBugTime > 1000000000) spawnBugs();

		Iterator<Rectangle> iter = bugs.iterator();
		while(iter.hasNext()) {
			Rectangle bug = iter.next();
			bug.y -= 200 * Gdx.graphics.getDeltaTime();
			if(bug.y + bug.height < 0) iter.remove();
		}
	}

	public void renderScene() {
		Gdx.gl.glClearColor(1, 0, 0, 1);	// atrybuty? R,G,B,Y 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.setProjectionsMatrix(camera.combined); //why not defined for type SpriteBatch?
		batch.begin();
		batch.draw(backgroundImg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());	//atrybuty?
		batch.draw(frogImg, frog.x, frog.y);
		for(Rectangle bug: bugs) {
            batch.draw(bugImg, bug.x, bug.y);
        }
		batch.end();
	}

	@Override
	public void render () {
        bugs = new Array<Rectangle>();
        spawnBugs();
        updateScene();
		renderScene();
	}

	@Override
	public void dispose () {
		batch.dispose();
		backgroundImg.dispose();
		frogImg.dispose();
	}
}
