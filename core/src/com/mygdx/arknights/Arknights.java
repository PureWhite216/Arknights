package com.mygdx.arknights;

import Level.LevelManager;
import Level.StartLevel;
import Level.StoryLevels.StoryLevel_00;
import Level.StoryLevels.StoryLevel_02;
import Level.StoryLevels.StoryLevel_03;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Arknights extends ApplicationAdapter
{

	@Override
	public void create () {

		/*Test Level*/
//		LevelManager.getInstance().push(new TestLevel());
		LevelManager.getInstance().push(new StartLevel());
//		LevelManager.getInstance().push(new StoryLevelBase());
//		LevelManager.getInstance().push(new StoryLevel_00());
//		LevelManager.getInstance().push(new BattleLevel_09());
	}

	/*Game Loop*/
	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
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
