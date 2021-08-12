package com.mathematicalbasedefenders.mathematicalbasedefenders.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mathematicalbasedefenders.mathematicalbasedefenders.MathematicalBaseDefenders;
import com.mathematicalbasedefenders.mathematicalbasedefenders.core.Core;
import com.mathematicalbasedefenders.mathematicalbasedefenders.core.Log;
import com.mathematicalbasedefenders.mathematicalbasedefenders.game.Game;
import com.mathematicalbasedefenders.mathematicalbasedefenders.game.Tile;
import org.json.JSONException;

import static com.mathematicalbasedefenders.mathematicalbasedefenders.MathematicalBaseDefenders.*;

public class SingleplayerGameScreen implements Screen {

    private final MathematicalBaseDefenders mathematicalBaseDefenders;

    public SpriteBatch singleplayerScreenSpriteBatch;

    private final Stage singleplayerScreenStage;
    private final Stage singleplayerScreenTextStage;
    private final Stage singleplayerScreenSpecialComputerModernFontStage;
    private final Stage singleplayerScreenProblemSpecialComputerModernFontStage;


    public SingleplayerGameScreen(MathematicalBaseDefenders mathematicalBaseDefenders) {

        //game
        this.mathematicalBaseDefenders = mathematicalBaseDefenders;

        core.currentScreen = Core.GameScreen.SINGLEPLAYER_SCREEN;


        // camera
        MathematicalBaseDefenders.core.orthographicCamera = new OrthographicCamera();
        MathematicalBaseDefenders.core.orthographicCamera.setToOrtho(false, 1920, 1080);

        // spritebatch
        singleplayerScreenSpriteBatch = new SpriteBatch();

        // stages
        // stage
        singleplayerScreenStage = new Stage(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        singleplayerScreenStage.setViewport(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        singleplayerScreenStage.getCamera().position.set(960, 540, 0);
        // text stage
        singleplayerScreenTextStage = new Stage(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        singleplayerScreenTextStage.setViewport(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        singleplayerScreenTextStage.getCamera().position.set(960, 540, 0);
        // SpecialComputerModernFont stages
        singleplayerScreenSpecialComputerModernFontStage = new Stage(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        singleplayerScreenSpecialComputerModernFontStage.setViewport(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        singleplayerScreenSpecialComputerModernFontStage.getCamera().position.set(960, 540, 0);

        singleplayerScreenProblemSpecialComputerModernFontStage = new Stage(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        singleplayerScreenProblemSpecialComputerModernFontStage.setViewport(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        singleplayerScreenProblemSpecialComputerModernFontStage.getCamera().position.set(960, 540, 0);
        // global stage
        core.globalStage = new Stage(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        core.globalStage.setViewport(new ExtendViewport(1920, 1080, (MathematicalBaseDefenders.core.orthographicCamera)));
        core.globalStage.getCamera().position.set(960, 540, 0);


        // set stages
        MathematicalBaseDefenders.core.currentStageToDrawOn = singleplayerScreenStage;
        MathematicalBaseDefenders.core.currentTextStageToDrawOn = singleplayerScreenTextStage;
        MathematicalBaseDefenders.core.currentSpecialComputerModernFontStageToDrawOn = singleplayerScreenSpecialComputerModernFontStage;
        MathematicalBaseDefenders.core.currentProblemSpecialComputerModernFontStageToDrawOn = singleplayerScreenProblemSpecialComputerModernFontStage;

        // modify properties
        core.sendButton.setPosition(733, 100);


        // add things to stage

        for (int r = 0; r < 7; r++) {
            for (int c = 0; c < 7; c++) {
                Game.tiles[c][r] = new Tile(c, r, MathematicalBaseDefenders.generator.generateTileTerm(), false);
            }
        }


        MathematicalBaseDefenders.renderer.renderSpecialComputerModernFont("a", 1190, 340, 0.3f, false);
        MathematicalBaseDefenders.renderer.renderSpecialComputerModernFont("b", 1190, 310, 0.3f, false);
        MathematicalBaseDefenders.renderer.renderSpecialComputerModernFont("c", 1190, 280, 0.3f, false);
        MathematicalBaseDefenders.renderer.renderSpecialComputerModernFont("d", 1190, 250, 0.3f, false);
        MathematicalBaseDefenders.renderer.renderSpecialComputerModernFont("n", 1190, 220, 0.3f, false);
        MathematicalBaseDefenders.renderer.renderSpecialComputerModernFont("x", 1190, 190, 0.3f, false);
        MathematicalBaseDefenders.renderer.renderSpecialComputerModernFont("y", 1190, 160, 0.3f, false, -3);
        MathematicalBaseDefenders.renderer.renderSpecialComputerModernFont("z", 1190, 130, 0.3f, false);


        MathematicalBaseDefenders.renderer.addActorToCurrentStage(core.sendButton, "sendButton");
        MathematicalBaseDefenders.renderer.addActorToCurrentStage(core.base, "base");

        // stats

        // right side of grid
        MathematicalBaseDefenders.renderer.addTextToCurrentTextStage("Score", "scoreIndicatorText", 36, Color.BLACK, 1192, 585);
        MathematicalBaseDefenders.renderer.addTextToCurrentTextStage("0", "currentScoreText", 64, Color.BLACK, 1190, 530);
        MathematicalBaseDefenders.renderer.addTextToCurrentTextStage("Time: 0ms", "timeText", 32, Color.BLACK, 1190, 450);
        MathematicalBaseDefenders.renderer.addTextToCurrentTextStage("Health: 100%", "healthText", 32, Color.BLACK, 1190, 410);
        MathematicalBaseDefenders.renderer.addTextToCurrentTextStage("Enemies: 0", "enemiesText", 32, Color.BLACK, 1190, 370);

        // left side of grid
        MathematicalBaseDefenders.renderer.addTextToCurrentTextStage("", "comboText", 36, Color.BLACK, 723-MathematicalBaseDefenders.renderer.getWidthOfText("", 36),585);
        MathematicalBaseDefenders.renderer.addTextToCurrentTextStage("Actions Per Minute", "actionsPerMinuteIndicatorText", 20, Color.BLACK, 723-MathematicalBaseDefenders.renderer.getWidthOfText("Actions Per Minute", 20),545);
        MathematicalBaseDefenders.renderer.addTextToCurrentTextStage("0", "actionsPerMinuteText", 36, Color.BLACK, 723-MathematicalBaseDefenders.renderer.getWidthOfText("0", 32),505);

        // variables
        MathematicalBaseDefenders.renderer.addTextToCurrentTextStage(" = ?", "valueOfVariableAText", 24, Color.BLACK, 1205, 335);
        MathematicalBaseDefenders.renderer.addTextToCurrentTextStage(" = ?", "valueOfVariableBText", 24, Color.BLACK, 1205, 308);
        MathematicalBaseDefenders.renderer.addTextToCurrentTextStage(" = ?", "valueOfVariableCText", 24, Color.BLACK, 1205, 275);
        MathematicalBaseDefenders.renderer.addTextToCurrentTextStage(" = ?", "valueOfVariableDText", 24, Color.BLACK, 1205, 245);
        MathematicalBaseDefenders.renderer.addTextToCurrentTextStage(" = ?", "valueOfVariableNText", 24, Color.BLACK, 1205, 215);
        MathematicalBaseDefenders.renderer.addTextToCurrentTextStage(" = ?", "valueOfVariableXText", 24, Color.BLACK, 1205, 185);
        MathematicalBaseDefenders.renderer.addTextToCurrentTextStage(" = ?", "valueOfVariableYText", 24, Color.BLACK, 1205, 155);
        MathematicalBaseDefenders.renderer.addTextToCurrentTextStage(" = ?", "valueOfVariableZText", 24, Color.BLACK, 1205, 125);

    }

    @Override
    public void show() {

        core.currentScreen = Core.GameScreen.SINGLEPLAYER_SCREEN;


        core.currentSpriteBatchToDrawOn = singleplayerScreenSpriteBatch;
        core.currentStageToDrawOn = singleplayerScreenStage;
        core.currentTextStageToDrawOn = singleplayerScreenTextStage;
        core.currentSpecialComputerModernFontStageToDrawOn = singleplayerScreenProblemSpecialComputerModernFontStage;
        core.shapeRenderer = new ShapeRenderer();

        Gdx.input.setInputProcessor(singleplayerScreenStage);

        MathematicalBaseDefenders.game.initialBaseHealth = 10;
        MathematicalBaseDefenders.game.currentBaseHealth = MathematicalBaseDefenders.game.initialBaseHealth;

    }

    @Override
    public void render(float delta) {



        // clear stuff first

        Gdx.gl.glClearColor(14f / 15f, 14f / 15f, 14f / 15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));


        // make stuff act
        singleplayerScreenStage.act();
        singleplayerScreenTextStage.act();
        singleplayerScreenSpecialComputerModernFontStage.act();
        singleplayerScreenProblemSpecialComputerModernFontStage.act();

        core.globalStage.act();

        singleplayerScreenSpriteBatch.begin();
        core.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // change stuff

        // process global logic

        MathematicalBaseDefenders.core.processGlobalLogic();

        // process logic
        for (int i = 0; i < 23; i++) {
            int keyToCheck = 10000000;
            try {
                keyToCheck = core.configurationAsJSONObject.getJSONObject("inputs").getInt(core.keybindKeys[i]);
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
            if (i > 14){
                if (Gdx.input.isKeyJustPressed(keyToCheck))
                {
                    MathematicalBaseDefenders.game.selectTerm(Tile.termNames[i]);
                }
            } else {
                if (Gdx.input.isKeyJustPressed(keyToCheck) || Gdx.input.isKeyJustPressed(core.secondaryKeybinds[i]))
                {
                    MathematicalBaseDefenders.game.selectTerm(Tile.termNames[i]);
                }
            }

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            MathematicalBaseDefenders.game.sendProblem();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE) || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_DOT)){
            MathematicalBaseDefenders.game.removeLatestSelectedPosition();
        }


        core.shapeRenderer.setAutoShapeType(true);
        // update text here

        // positions
        MathematicalBaseDefenders.renderer.changeTextPositionOnCurrentTextStage(723-MathematicalBaseDefenders.renderer.getWidthOfText(game.currentCombo>0?game.currentCombo + " Combo":"", 36), 585, "comboText");
        MathematicalBaseDefenders.renderer.changeTextPositionOnCurrentTextStage(723- renderer.getWidthOfText(String.format("%.2f",Game.actionsPerMinute), 36), 505,"actionsPerMinuteText");






        // stats
        MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage(Float.toString(MathematicalBaseDefenders.game.score), "currentScoreText");
        MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("Time: " + utilities.convertMillisecondsToColonifiedTime((TimeUtils.millis() - game.gameStartTimeInMilliseconds)), "timeText");
        MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("Health: " + (MathematicalBaseDefenders.game.currentBaseHealth * 100 / MathematicalBaseDefenders.game.initialBaseHealth) + "%", "healthText");
        MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("Enemies: " + MathematicalBaseDefenders.game.enemiesKilled + "/" + MathematicalBaseDefenders.game.enemiesSpawned, "enemiesText");

        MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage(game.currentCombo>0?game.currentCombo + " Combo":"", "comboText");

        MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage(String.format("%.2f",Game.actionsPerMinute), "actionsPerMinuteText");





        // variables
        MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage(MathematicalBaseDefenders.game.valueOfVariableA == null ? " = ?" : " = " + MathematicalBaseDefenders.game.valueOfVariableA.getArgumentValue(), "valueOfVariableAText");
        MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage(MathematicalBaseDefenders.game.valueOfVariableB == null ? " = ?" : " = " + MathematicalBaseDefenders.game.valueOfVariableB.getArgumentValue(), "valueOfVariableBText");
        MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage(MathematicalBaseDefenders.game.valueOfVariableC == null ? " = ?" : " = " + MathematicalBaseDefenders.game.valueOfVariableC.getArgumentValue(), "valueOfVariableCText");
        MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage(MathematicalBaseDefenders.game.valueOfVariableD == null ? " = ?" : " = " + MathematicalBaseDefenders.game.valueOfVariableD.getArgumentValue(), "valueOfVariableDText");
        MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage(MathematicalBaseDefenders.game.valueOfVariableN == null ? " = ?" : " = " + MathematicalBaseDefenders.game.valueOfVariableN.getArgumentValue(), "valueOfVariableNText");
        MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage(MathematicalBaseDefenders.game.valueOfVariableX == null ? " = ?" : " = " + MathematicalBaseDefenders.game.valueOfVariableX.getArgumentValue(), "valueOfVariableXText");
        MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage(MathematicalBaseDefenders.game.valueOfVariableY == null ? " = ?" : " = " + MathematicalBaseDefenders.game.valueOfVariableY.getArgumentValue(), "valueOfVariableYText");
        MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage(MathematicalBaseDefenders.game.valueOfVariableZ == null ? " = ?" : " = " + MathematicalBaseDefenders.game.valueOfVariableZ.getArgumentValue(), "valueOfVariableZText");



        if (MathematicalBaseDefenders.game.singleplayerGameIsOver) {
            MathematicalBaseDefenders.game.singleplayerGameIsOver = false;
            mathematicalBaseDefenders.setScreen(new GameOverScreen(mathematicalBaseDefenders));
        }


        // make stuff draw
        singleplayerScreenStage.draw();
        singleplayerScreenTextStage.draw();
        singleplayerScreenSpecialComputerModernFontStage.draw();
        singleplayerScreenProblemSpecialComputerModernFontStage.draw();

        core.globalStage.draw();


        // enemies

        Long[] enemyNumbersOfEnemiesToRenderAsLongArray = MathematicalBaseDefenders.core.getEnemyNumbersToRender(Game.activeEnemies.keySet());
        for (Long aLong : enemyNumbersOfEnemiesToRenderAsLongArray) {
            try {
                Game.activeEnemies.get(aLong).render(aLong);
            } catch (Exception exception) {
                Log.logWarningMessage("Enemy #" + aLong + " produced an exception.");
            }
        }


        singleplayerScreenSpriteBatch.end();
        core.shapeRenderer.end();




    }

    @Override
    public void resize(int width, int height) {
        singleplayerScreenStage.getViewport().update(width, height, true);
        singleplayerScreenTextStage.getViewport().update(width, height, true);
        singleplayerScreenSpecialComputerModernFontStage.getViewport().update(width, height, true);
        singleplayerScreenProblemSpecialComputerModernFontStage.getViewport().update(width, height, true);

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
        // make stuff end
        singleplayerScreenSpriteBatch.dispose();
        singleplayerScreenStage.dispose();
        singleplayerScreenTextStage.dispose();
        singleplayerScreenSpecialComputerModernFontStage.dispose();
        singleplayerScreenProblemSpecialComputerModernFontStage.dispose();
    }
}
