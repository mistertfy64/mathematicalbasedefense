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
import com.mathematicalbasedefenders.mathematicalbasedefenders.net.Authentication;
import com.mathematicalbasedefenders.mathematicalbasedefenders.net.Networking;

import static com.mathematicalbasedefenders.mathematicalbasedefenders.MathematicalBaseDefenders.core;

public class SettingsScreen implements Screen {


    private final MathematicalBaseDefenders mathematicalBaseDefenders;

    public SpriteBatch settingsScreenSpriteBatch;

    private final Stage settingsScreenStage;
    private final Stage settingsScreenTextStage;
    private final Stage specialSettingsScreenStage;

    String currentSubscreen = "videoSettingsScreen", cachedCurrentSubscreen = "";

    public SettingsScreen(MathematicalBaseDefenders mathematicalBaseDefenders) {
        this.mathematicalBaseDefenders = mathematicalBaseDefenders;

        core.currentScreen = Core.GameScreen.SETTINGS_SCREEN;

        // camera
        MathematicalBaseDefenders.core.orthographicCamera = new OrthographicCamera();
        MathematicalBaseDefenders.core.orthographicCamera.setToOrtho(false, 1920, 1080);

        // spritebatch
        settingsScreenSpriteBatch = new SpriteBatch();


        // stages
        settingsScreenStage = new Stage(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        settingsScreenStage.setViewport(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        settingsScreenStage.getCamera().position.set(1920 / 2, 1080 / 2, 0);
        // text stage
        settingsScreenTextStage = new Stage(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        settingsScreenTextStage.setViewport(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        settingsScreenTextStage.getCamera().position.set(1920 / 2, 1080 / 2, 0);


        // special stage
        specialSettingsScreenStage = new Stage(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        specialSettingsScreenStage.setViewport(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        specialSettingsScreenStage.getCamera().position.set(1920 / 2, 1080 / 2, 0);


        MathematicalBaseDefenders.core.currentStageToDrawOn = settingsScreenStage;
        MathematicalBaseDefenders.core.currentTextStageToDrawOn = settingsScreenTextStage;


        MathematicalBaseDefenders.renderer.addActorToCurrentStage(core.videoSettingsButton, "videoSettingsButton");
        MathematicalBaseDefenders.renderer.addActorToCurrentStage(core.audioSettingsButton, "audioSettingsButton");
        MathematicalBaseDefenders.renderer.addActorToCurrentStage(core.onlineSettingsButton, "onlineSettingsButton");
        MathematicalBaseDefenders.renderer.addActorToCurrentStage(core.settingsBackButton, "backSettingsButton");

        MathematicalBaseDefenders.renderer.addTextToCurrentTextStage("", "settingsNavigationText", 36, Color.BLACK, 72, 32);


    }


    @Override
    public void show() {

        core.currentScreen = Core.GameScreen.SETTINGS_SCREEN;


        core.currentSpriteBatchToDrawOn = settingsScreenSpriteBatch;
        core.currentStageToDrawOn = settingsScreenStage;
        core.currentTextStageToDrawOn = settingsScreenTextStage;

        core.shapeRenderer = new ShapeRenderer();

        Gdx.input.setInputProcessor(settingsScreenStage);


        core.videoSettingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentSubscreen = "videoSettingsScreen";
            }
        });

        core.audioSettingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentSubscreen = "audioSettingsScreen";
            }
        });

        core.onlineSettingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentSubscreen = "onlineSettingsScreen";
            }
        });

        core.settingsBackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                core.videoSettingsButton.addAction(Actions.moveTo(-492, 1000, 0.2f));
                core.audioSettingsButton.addAction(Actions.moveTo(-492, 920, 0.2f));
                core.onlineSettingsButton.addAction(Actions.moveTo(-492, 840, 0.2f));
                core.settingsBackButton.addAction(Actions.moveTo(-492, 120, 0.2f));

                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        mathematicalBaseDefenders.setScreen(new MainMenuScreen(mathematicalBaseDefenders));
                        MathematicalBaseDefenders.core.currentScreen = Core.GameScreen.MAIN_MENU_SCREEN;
                    }
                }, 0.3f);

            }
        });


        core.loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                Networking.loggedIn = Authentication.loginWithCredentials(core.usernameTextField.getText(), core.passwordTextField.getText());

            }
        });


        core.videoSettingsButton.addAction(Actions.moveTo(-492, 1000, 0f));
        core.audioSettingsButton.addAction(Actions.moveTo(-492, 920, 0f));
        core.onlineSettingsButton.addAction(Actions.moveTo(-492, 840, 0f));
        core.settingsBackButton.addAction(Actions.moveTo(-492, 120, 0f));

        core.videoSettingsButton.addAction(Actions.moveTo(-192, 1000, 0.2f));
        core.audioSettingsButton.addAction(Actions.moveTo(-192, 920, 0.2f));
        core.onlineSettingsButton.addAction(Actions.moveTo(-192, 840, 0.2f));
        core.settingsBackButton.addAction(Actions.moveTo(-192, 120, 0.2f));

    }

    @Override
    public void render(float delta) {

        // clear

        Gdx.gl.glClearColor(14f / 15f, 14f / 15f, 14f / 15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));


        // act+begin
        settingsScreenSpriteBatch.begin();
        settingsScreenStage.act();
        settingsScreenTextStage.act();

        core.globalStage.act();

        core.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // change stuff
        core.shapeRenderer.setAutoShapeType(true);

        // process global logic


        MathematicalBaseDefenders.core.processGlobalLogic();


        // process logic

        if (!cachedCurrentSubscreen.equals(currentSubscreen)) {
            cachedCurrentSubscreen = currentSubscreen;

            // make this a loop later
            if (MathematicalBaseDefenders.renderer.findTextOnCurrentTextStage("usernameIndicatorText") != null) {
                MathematicalBaseDefenders.renderer.removeTextFromCurrentTextStage("usernameIndicatorText");
            }

            if (MathematicalBaseDefenders.renderer.findTextOnCurrentTextStage("passwordIndicatorText") != null) {
                MathematicalBaseDefenders.renderer.removeTextFromCurrentTextStage("passwordIndicatorText");
            }


            if (MathematicalBaseDefenders.renderer.findTextOnCurrentTextStage("usernameTextField") != null) {
                MathematicalBaseDefenders.renderer.removeActorFromCurrentStage("usernameTextField");
            }
            if (MathematicalBaseDefenders.renderer.findTextOnCurrentTextStage("passwordTextField") != null) {
                MathematicalBaseDefenders.renderer.removeActorFromCurrentStage("passwordTextField");
            }

            if (MathematicalBaseDefenders.renderer.findActorOnCurrentStage("loginButton") != null) {
                MathematicalBaseDefenders.renderer.removeActorFromCurrentStage("loginButton");
            }

            switch (currentSubscreen) {
                case "videoSettingsScreen": {
                    break;
                }
                case "audioSettingsScreen": {
                    break;
                }
                case "onlineSettingsScreen": {

                    MathematicalBaseDefenders.renderer.addTextToCurrentTextStage("Username: ", "usernameIndicatorText", 36, Color.BLACK, 316, 1000);
                    MathematicalBaseDefenders.renderer.addTextToCurrentTextStage("Password: ", "passwordIndicatorText", 36, Color.BLACK, 316, 920);


                    MathematicalBaseDefenders.renderer.addActorToCurrentStage(core.usernameTextField, "usernameTextField");
                    MathematicalBaseDefenders.renderer.addActorToCurrentStage(core.passwordTextField, "passwordTextField");

                    MathematicalBaseDefenders.renderer.addActorToCurrentStage(core.loginButton, "loginButton");

                    break;
                }
            }
        }


        // draw


        settingsScreenStage.draw();
        settingsScreenTextStage.draw();

        core.globalStage.draw();

        MathematicalBaseDefenders.core.shapeRenderer.setColor(Color.BLACK);
        MathematicalBaseDefenders.core.shapeRenderer.line(new Vector2(-500, 64), new Vector2(2420, 64));

        // end
        settingsScreenSpriteBatch.end();
        core.shapeRenderer.end();

    }

    @Override
    public void resize(int width, int height) {
        settingsScreenStage.getViewport().update(width, height, true);
        settingsScreenTextStage.getViewport().update(width, height, true);
        specialSettingsScreenStage.getViewport().update(width, height, true);

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
        settingsScreenSpriteBatch.dispose();
        settingsScreenStage.dispose();
        settingsScreenTextStage.dispose();
        specialSettingsScreenStage.dispose();

    }
}
