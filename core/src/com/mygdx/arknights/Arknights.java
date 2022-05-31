package com.mygdx.arknights;

import Level.LevelManager;
import Level.StartLevel;
import Level.TestLevel;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.esotericsoftware.spine.utils.TwoColorPolygonBatch;

public class Arknights extends ApplicationAdapter
{

	@Override
	public void create () {

		/*Test Level*/
//		LevelManager.getInstance().push(new TestLevel());
		LevelManager.getInstance().push(new StartLevel());
		/*Test Level*/

	}

	/*Game Loop*/
	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		LevelManager.getInstance().update(Gdx.graphics.getDeltaTime());
		LevelManager.getInstance().render();
	}
	/*Game Loop*/

	@Override
	public void dispose () {

	}

	@Override
	public void resize(int width, int height)
	{
		super.resize(width, height);
	}
}
