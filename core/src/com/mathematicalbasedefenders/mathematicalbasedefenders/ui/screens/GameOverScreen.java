package com.mathematicalbasedefenders.mathematicalbasedefenders.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mathematicalbasedefenders.mathematicalbasedefenders.MathematicalBaseDefenders;
import com.mathematicalbasedefenders.mathematicalbasedefenders.core.Core;

public class GameOverScreen implements Screen {


    private final MathematicalBaseDefenders mathematicalBaseDefenders;
    public SpriteBatch gameOverScreenSpriteBatch;

    private final Stage gameOverScreenStage;
    private final Stage gameOverScreenTextStage;


    public GameOverScreen(MathematicalBaseDefenders mathematicalBaseDefendersInstance) {
        this.mathematicalBaseDefenders = mathematicalBaseDefendersInstance;

        MathematicalBaseDefenders.core.currentScreen = Core.GameScreen.GAME_OVER_SCREEN;

        gameOverScreenSpriteBatch = new SpriteBatch();

        // stages
        // stage
        gameOverScreenStage = new Stage(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        gameOverScreenStage.setViewport(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        gameOverScreenStage.getCamera().position.set(960, 540, 0);
        // text stage
        gameOverScreenTextStage = new Stage(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        gameOverScreenTextStage.setViewport(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        gameOverScreenTextStage.getCamera().position.set(960, 540, 0);
        // global stage
        MathematicalBaseDefenders.core.globalStage = new Stage(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        MathematicalBaseDefenders.core.globalStage.setViewport(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        MathematicalBaseDefenders.core.globalStage.getCamera().position.set(960, 540, 0);

        MathematicalBaseDefenders.core.currentStageToDrawOn = gameOverScreenStage;
        MathematicalBaseDefenders.core.currentTextStageToDrawOn = gameOverScreenTextStage;




        MathematicalBaseDefenders.renderer.addTextToCurrentTextStage(Double.toString(MathematicalBaseDefenders.game.score), "finalScoreText", 72, Color.BLACK, 1536 - MathematicalBaseDefenders.renderer.getWidthOfText(Double.toString((MathematicalBaseDefenders.game.score)), 72), 900);
        MathematicalBaseDefenders.renderer.addTextToCurrentTextStage("Score", "scoreText", 36, Color.BLACK, 384, 900);
        MathematicalBaseDefenders.renderer.addTextToCurrentTextStage("Time: " + MathematicalBaseDefenders.utilities.convertMillisecondsToColonifiedTime(MathematicalBaseDefenders.game.finalTimeElapsedInMilliseconds), "timeText", 36, Color.BLACK, 384, 850);


        MathematicalBaseDefenders.renderer.addActorToCurrentStage(MathematicalBaseDefenders.core.gameOverPlayAgainButton, "gameOverPlayAgainButton");
        MathematicalBaseDefenders.renderer.addActorToCurrentStage(MathematicalBaseDefenders.core.gameOverBackButton, "gameOverBackButton");


    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(gameOverScreenStage);

        MathematicalBaseDefenders.core.currentScreen = Core.GameScreen.GAME_OVER_SCREEN;




    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(14f / 15f, 14f / 15f, 14f / 15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));

        gameOverScreenStage.act();
        gameOverScreenTextStage.act();
        MathematicalBaseDefenders.core.globalStage.act();

        MathematicalBaseDefenders.core.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        gameOverScreenSpriteBatch.begin();


        // process global logic

        MathematicalBaseDefenders.core.processGlobalLogic();

        // process logic
        MathematicalBaseDefenders.core.globalStage.act();

        // draw stuff
        gameOverScreenStage.draw();
        gameOverScreenTextStage.draw();
        MathematicalBaseDefenders.core.globalStage.draw();

        // make stuff end
        gameOverScreenSpriteBatch.end();
        MathematicalBaseDefenders.core.shapeRenderer.end();

    }

    @Override
    public void resize(int width, int height) {
        gameOverScreenStage.getViewport().update(width, height);
        gameOverScreenTextStage.getViewport().update(width, height);
        MathematicalBaseDefenders.core.globalStage.getViewport().update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        gameOverScreenSpriteBatch.dispose();
        gameOverScreenStage.dispose();
        gameOverScreenTextStage.dispose();
    }
}
