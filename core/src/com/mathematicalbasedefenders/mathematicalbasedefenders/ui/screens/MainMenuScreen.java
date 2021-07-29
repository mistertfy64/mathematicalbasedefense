package com.mathematicalbasedefenders.mathematicalbasedefenders.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mathematicalbasedefenders.mathematicalbasedefenders.MathematicalBaseDefenders;
import com.mathematicalbasedefenders.mathematicalbasedefenders.core.Core;

public class MainMenuScreen implements Screen {

    private final MathematicalBaseDefenders mathematicalBaseDefenders;
    public SpriteBatch mainMenuScreenSpriteBatch;

    private final Stage mainMenuScreenStage;
    private final Stage mainMenuScreenTextStage;


    public MainMenuScreen(MathematicalBaseDefenders mathematicalBaseDefenders) {
        this.mathematicalBaseDefenders = mathematicalBaseDefenders;

        // variables
        MathematicalBaseDefenders.core.currentScreen = Core.GameScreen.MAIN_MENU_SCREEN;


        // camera
        MathematicalBaseDefenders.core.orthographicCamera = new OrthographicCamera();
        MathematicalBaseDefenders.core.orthographicCamera.setToOrtho(false, 1920, 1080);

        // spritebatch
        mainMenuScreenSpriteBatch = new SpriteBatch();

        // stages
        // stage
        mainMenuScreenStage = new Stage(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        mainMenuScreenStage.setViewport(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        mainMenuScreenStage.getCamera().position.set(1920 / 2, 1080 / 2, 0);
        // text stage
        mainMenuScreenTextStage = new Stage(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        mainMenuScreenTextStage.setViewport(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        mainMenuScreenTextStage.getCamera().position.set(1920 / 2, 1080 / 2, 0);
        // global stage
        MathematicalBaseDefenders.core.globalStage = new Stage(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        MathematicalBaseDefenders.core.globalStage.setViewport(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        MathematicalBaseDefenders.core.globalStage.getCamera().position.set(1920 / 2, 1080 / 2, 0);

        MathematicalBaseDefenders.core.currentStageToDrawOn = mainMenuScreenStage;
        MathematicalBaseDefenders.core.currentTextStageToDrawOn = mainMenuScreenTextStage;


        // add stuff
        MathematicalBaseDefenders.renderer.addTextToCurrentTextStage("", "mainMenuNavigationText", 36, Color.BLACK, 8, 32);


        MathematicalBaseDefenders.renderer.addActorToCurrentStage(MathematicalBaseDefenders.core.singleplayerButton, "singleplayerButton");
        MathematicalBaseDefenders.renderer.addActorToCurrentStage(MathematicalBaseDefenders.core.multiplayerButton, "multiplayerButton");
        MathematicalBaseDefenders.renderer.addActorToCurrentStage(MathematicalBaseDefenders.core.statsButton, "statsButton");
        MathematicalBaseDefenders.renderer.addActorToCurrentStage(MathematicalBaseDefenders.core.settingsButton, "settingsButton");
        MathematicalBaseDefenders.renderer.addActorToCurrentStage(MathematicalBaseDefenders.core.quitButton, "quitButton");

    }


    @Override
    public void show() {

        Gdx.input.setInputProcessor(mainMenuScreenStage);

        // variables
        MathematicalBaseDefenders.core.currentScreen = Core.GameScreen.MAIN_MENU_SCREEN;


        MathematicalBaseDefenders.core.shapeRenderer = new ShapeRenderer();

        MathematicalBaseDefenders.core.singleplayerButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {


                MathematicalBaseDefenders.core.singleplayerButton.addAction(Actions.fadeOut(0.2f));
                MathematicalBaseDefenders.core.multiplayerButton.addAction(Actions.fadeOut(0.2f));
                MathematicalBaseDefenders.core.statsButton.addAction(Actions.fadeOut(0.2f));
                MathematicalBaseDefenders.core.settingsButton.addAction(Actions.fadeOut(0.2f));
                MathematicalBaseDefenders.core.quitButton.addAction(Actions.fadeOut(0.2f));

                MathematicalBaseDefenders.core.singleplayerButton.addAction(Actions.moveTo(2060, 400, 0.4f));
                MathematicalBaseDefenders.core.multiplayerButton.addAction(Actions.moveTo(2477, 400, 0.4f));
                MathematicalBaseDefenders.core.statsButton.addAction(Actions.moveTo(2060, 304, 0.4f));
                MathematicalBaseDefenders.core.settingsButton.addAction(Actions.moveTo(2367, 304, 0.4f));
                MathematicalBaseDefenders.core.quitButton.addAction(Actions.moveTo(2675, 304, 0.4f));


                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        mathematicalBaseDefenders.setScreen(new SingleplayerGameScreen(mathematicalBaseDefenders));
                        MathematicalBaseDefenders.core.currentScreen = Core.GameScreen.SINGLEPLAYER_SCREEN;
                        MathematicalBaseDefenders.game.startSingleplayerGame();
                    }
                }, 0.3f);


            }
        });

        MathematicalBaseDefenders.core.settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {


                MathematicalBaseDefenders.core.singleplayerButton.addAction(Actions.fadeOut(0.2f));
                MathematicalBaseDefenders.core.multiplayerButton.addAction(Actions.fadeOut(0.2f));
                MathematicalBaseDefenders.core.statsButton.addAction(Actions.fadeOut(0.2f));
                MathematicalBaseDefenders.core.settingsButton.addAction(Actions.fadeOut(0.2f));
                MathematicalBaseDefenders.core.quitButton.addAction(Actions.fadeOut(0.2f));

                MathematicalBaseDefenders.core.singleplayerButton.addAction(Actions.moveTo(2060, 400, 0.4f));
                MathematicalBaseDefenders.core.multiplayerButton.addAction(Actions.moveTo(2477, 400, 0.4f));
                MathematicalBaseDefenders.core.statsButton.addAction(Actions.moveTo(2060, 304, 0.4f));
                MathematicalBaseDefenders.core.settingsButton.addAction(Actions.moveTo(2367, 304, 0.4f));
                MathematicalBaseDefenders.core.quitButton.addAction(Actions.moveTo(2675, 304, 0.4f));

                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        mathematicalBaseDefenders.setScreen(new SettingsScreen(mathematicalBaseDefenders));
                        MathematicalBaseDefenders.core.currentScreen = Core.GameScreen.SETTINGS_SCREEN;
                    }
                }, 0.3f);


            }
        });

        MathematicalBaseDefenders.core.singleplayerButton.addAction(Actions.moveTo(2060, 400, 0f));
        MathematicalBaseDefenders.core.multiplayerButton.addAction(Actions.moveTo(2477, 400, 0f));
        MathematicalBaseDefenders.core.statsButton.addAction(Actions.moveTo(2060, 304, 0f));
        MathematicalBaseDefenders.core.settingsButton.addAction(Actions.moveTo(2367, 304, 0f));
        MathematicalBaseDefenders.core.quitButton.addAction(Actions.moveTo(2675, 304, 0f));

        MathematicalBaseDefenders.core.singleplayerButton.addAction(Actions.fadeOut(0f));
        MathematicalBaseDefenders.core.multiplayerButton.addAction(Actions.fadeOut(0f));
        MathematicalBaseDefenders.core.statsButton.addAction(Actions.fadeOut(0f));
        MathematicalBaseDefenders.core.settingsButton.addAction(Actions.fadeOut(0f));
        MathematicalBaseDefenders.core.quitButton.addAction(Actions.fadeOut(0f));

        MathematicalBaseDefenders.core.singleplayerButton.addAction(Actions.fadeIn(0.4f));
        MathematicalBaseDefenders.core.multiplayerButton.addAction(Actions.fadeIn(0.4f));
        MathematicalBaseDefenders.core.statsButton.addAction(Actions.fadeIn(0.4f));
        MathematicalBaseDefenders.core.settingsButton.addAction(Actions.fadeIn(0.4f));
        MathematicalBaseDefenders.core.quitButton.addAction(Actions.fadeIn(0.4f));

        MathematicalBaseDefenders.core.singleplayerButton.addAction(Actions.moveTo(560, 400, 0.4f));
        MathematicalBaseDefenders.core.multiplayerButton.addAction(Actions.moveTo(977, 400, 0.4f));
        MathematicalBaseDefenders.core.statsButton.addAction(Actions.moveTo(560, 304, 0.4f));
        MathematicalBaseDefenders.core.settingsButton.addAction(Actions.moveTo(867, 304, 0.4f));
        MathematicalBaseDefenders.core.quitButton.addAction(Actions.moveTo(1175, 304, 0.4f));

    }

    @Override
    public void render(float delta) {


        // clear stuff first

        Gdx.gl.glClearColor(14f / 15f, 14f / 15f, 14f / 15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));


        // process global logic


        MathematicalBaseDefenders.core.processGlobalLogic();


        // process logic


        // make stuff act


        mainMenuScreenStage.act();
        mainMenuScreenTextStage.act();
        MathematicalBaseDefenders.core.globalStage.act();

        MathematicalBaseDefenders.core.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        mainMenuScreenSpriteBatch.begin();


        // draw stuff
        mainMenuScreenStage.draw();
        mainMenuScreenTextStage.draw();

        MathematicalBaseDefenders.core.globalStage.draw();
        MathematicalBaseDefenders.core.shapeRenderer.setColor(Color.BLACK);
        MathematicalBaseDefenders.core.shapeRenderer.line(new Vector2(-500, 64), new Vector2(2420, 64));

        // make stuff end
        mainMenuScreenSpriteBatch.end();
        MathematicalBaseDefenders.core.shapeRenderer.end();


    }

    @Override
    public void resize(int width, int height) {
        mainMenuScreenStage.getViewport().update(width, height);
        mainMenuScreenTextStage.getViewport().update(width, height);
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
        mainMenuScreenStage.dispose();
        mainMenuScreenTextStage.dispose();
        mainMenuScreenSpriteBatch.dispose();
    }
}
