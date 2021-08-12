package com.mathematicalbasedefenders.mathematicalbasedefenders.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mathematicalbasedefenders.mathematicalbasedefenders.MathematicalBaseDefenders;
import com.mathematicalbasedefenders.mathematicalbasedefenders.core.Core;
import com.mathematicalbasedefenders.mathematicalbasedefenders.game.Tile;
import org.json.JSONException;
import org.json.JSONObject;

import static com.mathematicalbasedefenders.mathematicalbasedefenders.MathematicalBaseDefenders.core;

public class SettingsScreen implements Screen {


    private final MathematicalBaseDefenders mathematicalBaseDefenders;

    public SpriteBatch settingsScreenSpriteBatch;

    private final Stage settingsScreenStage;
    private final Stage settingsScreenTextStage;
    private final Stage specialSettingsScreenStage;

    public static String currentSettingsSubscreen = "videoSettingsScreen", cachedCurrentSubscreen = "";


    public SettingsScreen(MathematicalBaseDefenders mathematicalBaseDefendersInstance) {



        this.mathematicalBaseDefenders = mathematicalBaseDefendersInstance;

        core.currentScreen = Core.GameScreen.SETTINGS_SCREEN;

        // camera
        MathematicalBaseDefenders.core.orthographicCamera = new OrthographicCamera();
        MathematicalBaseDefenders.core.orthographicCamera.setToOrtho(false, 1920, 1080);

        // spritebatch
        settingsScreenSpriteBatch = new SpriteBatch();


        // stages
        settingsScreenStage = new Stage(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        settingsScreenStage.setViewport(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        settingsScreenStage.getCamera().position.set(960, 540, 0);
        // text stage
        settingsScreenTextStage = new Stage(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        settingsScreenTextStage.setViewport(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        settingsScreenTextStage.getCamera().position.set(960, 540, 0);


        // special stage
        specialSettingsScreenStage = new Stage(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        specialSettingsScreenStage.setViewport(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        specialSettingsScreenStage.getCamera().position.set(960, 540, 0);


        MathematicalBaseDefenders.core.currentStageToDrawOn = settingsScreenStage;
        MathematicalBaseDefenders.core.currentTextStageToDrawOn = settingsScreenTextStage;



        core.videoSettingsButton.addAction(Actions.moveTo(-492, 1000, 0f));
        core.audioSettingsButton.addAction(Actions.moveTo(-492, 920, 0f));
        core.inputSettingsButton.addAction(Actions.moveTo(-492, 840, 0f));
        core.onlineSettingsButton.addAction(Actions.moveTo(-492, 760, 0f));
        core.settingsBackButton.addAction(Actions.moveTo(-492, 120, 0f));

        core.videoSettingsButton.addAction(Actions.moveTo(-192, 1000, 0.2f));
        core.audioSettingsButton.addAction(Actions.moveTo(-192, 920, 0.2f));
        core.inputSettingsButton.addAction(Actions.moveTo(-192, 840, 0.2f));
        core.onlineSettingsButton.addAction(Actions.moveTo(-192, 760, 0.2f));
        core.settingsBackButton.addAction(Actions.moveTo(-192, 120, 0.2f));


        MathematicalBaseDefenders.renderer.addActorToCurrentStage(core.videoSettingsButton, "videoSettingsButton");
        MathematicalBaseDefenders.renderer.addActorToCurrentStage(core.audioSettingsButton, "audioSettingsButton");
        MathematicalBaseDefenders.renderer.addActorToCurrentStage(core.inputSettingsButton, "inputSettingsButton");
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




    }

    @Override
    public void render(float delta) {


        // System.out.println(core.settingsBackButton.getListeners().size);

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

        if (!cachedCurrentSubscreen.equals(currentSettingsSubscreen)) {
            cachedCurrentSubscreen = currentSettingsSubscreen;

            String[] subscreenTextActorsToRemove = {
                    "usernameIndicatorText",
                    "passwordIndicatorText",
                    "usernameTextField",
                    "passwordTextField",

            };

            String[] subscreenActorsToRemove = {
                    "loginButton",
            };

            for (String string : subscreenTextActorsToRemove){
                if (MathematicalBaseDefenders.renderer.findTextOnCurrentTextStage(string) != null) {
                    MathematicalBaseDefenders.renderer.removeTextFromCurrentTextStage(string);
                }
            }

            for (String string: subscreenActorsToRemove){
                if (MathematicalBaseDefenders.renderer.findActorOnCurrentStage(string) != null){
                    MathematicalBaseDefenders.renderer.removeActorFromCurrentStage(string);
                }
            }

            for (int i = 0; i < 23; i++){
                if (MathematicalBaseDefenders.renderer.findTextOnCurrentTextStage("inputIndicatorText"+i) != null){
                    MathematicalBaseDefenders.renderer.removeTextFromCurrentTextStage("inputIndicatorText"+i);
                    MathematicalBaseDefenders.renderer.removeActorFromCurrentStage("inputTextField"+i);
                }
            }


            switch (currentSettingsSubscreen) {
                case "videoSettingsScreen": {
                    break;
                }
                case "audioSettingsScreen": {
                    break;
                }
                case "inputSettingsScreen": {

                    String[] inputKeys = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "additionSign", "subtractionSign", "multiplicationSign", "divisionSign", "equalsSign", "a", "b", "c", "d", "n", "x", "y", "z"};

                    JSONObject inputKeysAsJSON = null;
                    try {
                        inputKeysAsJSON = MathematicalBaseDefenders.core.configurationAsJSONObject.getJSONObject("inputs");
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    }


                    for (int i = 0; i < 23; i++){
                        int xPos = 0, yPos = 0;
                        if (i > 18){
                            xPos = 1650;
                            yPos = 1000-(80*(i-19));
                        } else if (i > 12){
                            xPos = 1250;
                            yPos =  1000-(80*(i-13));
                        } else if (i > 6){
                            xPos = 850;
                            yPos = 1000-(80*(i-7));
                        } else {
                            xPos = 450;
                            yPos = 1000-(80*i);
                        }
                        MathematicalBaseDefenders.renderer.addTextToCurrentTextStage(Tile.termsAsBetterStrings[i],"inputIndicatorText"+i, 36,Color.BLACK, xPos, yPos);
                    }


                    for (int i = 0; i < 23; i++){
                        try {
                            core.inputTextFields[i].setText(Input.Keys.toString(core.configurationAsJSONObject.getJSONObject("inputs").getInt(inputKeys[i])));
                        } catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                        }
                        core.inputTextFields[i].setMaxLength(1);
                        MathematicalBaseDefenders.renderer.addActorToCurrentStage(core.inputTextFields[i], "inputTextField"+i);
                    }
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
