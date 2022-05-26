package com.mygdx.arknights;

import Level.LevelManager;
import Level.TestLevel;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.esotericsoftware.spine.SkeletonMeshRenderer;
import com.esotericsoftware.spine.SkeletonRenderer;

public class Arknights extends ApplicationAdapter
{
	public SpriteBatch spritebatch;
	public PolygonSpriteBatch polygonSpriteBatch;
	public SkeletonMeshRenderer renderer;
	public LevelManager levelManager;
	@Override
	public void create () {
		spritebatch = new SpriteBatch();
		polygonSpriteBatch = new PolygonSpriteBatch();
		renderer = new SkeletonMeshRenderer();
		levelManager = new LevelManager();
		/*Test Level*/
		levelManager.push(new TestLevel(levelManager));
		/*Test Level*/
	}

	/*Game Loop*/
	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		levelManager.update(Gdx.graphics.getDeltaTime());
		levelManager.render(spritebatch, polygonSpriteBatch, renderer);
	}
	/*Game Loop*/

	@Override
	public void dispose () {
		spritebatch.dispose();
		polygonSpriteBatch.dispose();
	}
}
