package com.mathematicalbasedefenders.mathematicalbasedefenders.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mathematicalbasedefenders.mathematicalbasedefenders.MathematicalBaseDefenders;
import com.mathematicalbasedefenders.mathematicalbasedefenders.game.Enemy;
import com.mathematicalbasedefenders.mathematicalbasedefenders.game.Game;
import com.mathematicalbasedefenders.mathematicalbasedefenders.ui.ToastNotification;
import io.socket.client.IO;

import java.net.URI;
import java.util.*;

public class Core {


    public enum GameScreen {
        MAIN_MENU_SCREEN, SINGLEPLAYER_SCREEN, SETTINGS_SCREEN, GAME_OVER_SCREEN
    }

    public enum Term {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, ADDITION_SIGN, SUBTRACTION_SIGN, MULTIPLICATION_SIGN, DIVISION_SIGN, EQUALS_SIGN, A, B, C, D, N, X, Y, Z
    }

    // fonts
    public FreeTypeFontGenerator freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("assets/fonts/computermodernunicode.ttf"));

    public BitmapFont size12ComputerModernUnicodeFont, size16ComputerModernUnicodeFont, size20ComputerModernUnicodeFont, size24ComputerModernUnicodeFont, size32ComputerModernUnicodeFont, size36ComputerModernUnicodeFont, size48ComputerModernUnicodeFont, size64ComputerModernUnicodeFont, size72ComputerModernUnicodeFont, size144ComputerModernUnicodeFont;


    // text
    // text
    // textbuttons

    // images
    // images
    // imagebuttons


    // main menu screen

    public ImageButton singleplayerButton;
    public ImageButton multiplayerButton;
    public ImageButton statsButton;
    public ImageButton settingsButton;
    public ImageButton quitButton;


    // singleplayer screen
    public ImageButton sendButton = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("assets/images/sendbutton.png"), true)));

    public Image base = new Image(new Texture(Gdx.files.internal("assets/images/base.png")));


    // settings screen
    public ImageButton audioSettingsButton;
    public ImageButton videoSettingsButton;
    public ImageButton onlineSettingsButton;
    public ImageButton settingsBackButton;

    public ImageButton loginButton;


    public TextField usernameTextField, passwordTextField;


    // gameover screen

    public ImageButton gameOverPlayAgainButton;
    public ImageButton gameOverBackButton;


    // images that don't need to have their filter set.
    public Drawable toastNotificationBackground = new TextureRegionDrawable(new Texture(Gdx.files.internal("assets/images/toastnotificationbackground.png")));


    // global

    public ArrayList<String> toastNotificationQueue = new ArrayList<>();


    public OrthographicCamera orthographicCamera = new OrthographicCamera();
    public ShapeRenderer shapeRenderer;

    // Global variables

    public static long updatesComputedSinceStart = 0L, ticksComputedSinceStart = 0L;


    // terms, SpecialComputerModernFont and tiles


    public TextureAtlas specialComputerModernFontTextTextureAtlas = new TextureAtlas(Gdx.files.internal("assets/images/specialcomputermodernfont.atlas"));
    public AtlasRegion[] specialComputerModernFontTextTextureAtlasRegions = new AtlasRegion[24];
    public Pixmap[] specialComputerModernFontTextTextureAtlasRegionsAsPixmaps = new Pixmap[24];
    public String[] specialComputerModernFontTextTextureAtlasRegionNames = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "additionsign", "subtractionsign", "multiplicationsign", "divisionsign", "equalssign", "a", "b", "c", "d", "n", "x", "y", "z", "dot"};

    public TextureAtlas tilesTextureAtlas = new TextureAtlas(Gdx.files.internal("assets/images/tiles.atlas"));
    public AtlasRegion[][] tilesTextureAtlasRegions = new AtlasRegion[2][24];
    public Pixmap[][] tilesTextureAtlasRegionsAsPixmaps = new Pixmap[2][24];
    public String[][] tilesTextureAtlasRegionNames = {
            {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "additionsign", "subtractionsign", "multiplicationsign", "divisionsign", "equalssign", "a", "b", "c", "d", "n", "x", "y", "z", "dot"},
            {"0selected", "1selected", "2selected", "3selected", "4selected", "5selected", "6selected", "7selected", "8selected", "9selected", "additionsignselected", "subtractionsignselected", "multiplicationsignselected", "divisionsignselected", "equalssignselected", "aselected", "bselected", "cselected", "dselected", "nselected", "xselected", "yselected", "zselected", "dotselected"},
    };


    // SpecialComputerModernFont rendering
    public static final String CHARACTERS_ALLOWED_IN_PROBLEM = "0123456789+-*/=abcdnxyz×÷";

    // instances
    public Game game;
    public SpriteBatch currentSpriteBatchToDrawOn;
    public Stage currentStageToDrawOn, currentTextStageToDrawOn, currentSpecialComputerModernFontStageToDrawOn, currentProblemSpecialComputerModernFontStageToDrawOn;
    public Stage globalStage = new Stage(new ExtendViewport(1920, 1080, orthographicCamera));


    // variables
    public boolean gameAllowedToRender = true;


    // other important stuff

    public Drawable transparentBackground = new TextureRegionDrawable(new Texture(Gdx.files.internal("assets/images/transparentbackground.png")));
    public Drawable transparentPixelBackground = new TextureRegionDrawable(new Texture(Gdx.files.internal("assets/images/transparentpixelbackground.png"), true));
    public Drawable transparent1500By36Pixmap = new TextureRegionDrawable(new Texture(new Pixmap(1500, 36, Pixmap.Format.RGBA8888)));


    // other pointless stuff


    public GameScreen currentScreen = GameScreen.MAIN_MENU_SCREEN;


    /**
     * Initializes the game.
     */
    public void initialize() {

        double startTime = TimeUtils.millis();
        Log.logInfoMessage("Initializing game...");


        // font

        // fonts

        FreeTypeFontGenerator.FreeTypeFontParameter size12FreeTypeFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        size12FreeTypeFontParameter.size = 12;
        size12ComputerModernUnicodeFont = freeTypeFontGenerator.generateFont(size12FreeTypeFontParameter);
        size12ComputerModernUnicodeFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        FreeTypeFontGenerator.FreeTypeFontParameter size16FreeTypeFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        size16FreeTypeFontParameter.size = 16;
        size16ComputerModernUnicodeFont = freeTypeFontGenerator.generateFont(size16FreeTypeFontParameter);
        size16ComputerModernUnicodeFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        FreeTypeFontGenerator.FreeTypeFontParameter size20FreeTypeFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        size20FreeTypeFontParameter.size = 20;
        size20ComputerModernUnicodeFont = freeTypeFontGenerator.generateFont(size20FreeTypeFontParameter);
        size20ComputerModernUnicodeFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        FreeTypeFontGenerator.FreeTypeFontParameter size24FreeTypeFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        size24FreeTypeFontParameter.size = 24;
        size24ComputerModernUnicodeFont = freeTypeFontGenerator.generateFont(size24FreeTypeFontParameter);
        size24ComputerModernUnicodeFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        FreeTypeFontGenerator.FreeTypeFontParameter size32FreeTypeFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        size32FreeTypeFontParameter.size = 32;
        size32ComputerModernUnicodeFont = freeTypeFontGenerator.generateFont(size32FreeTypeFontParameter);
        size32ComputerModernUnicodeFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        FreeTypeFontGenerator.FreeTypeFontParameter size36FreeTypeFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        size36FreeTypeFontParameter.size = 36;
        size36ComputerModernUnicodeFont = freeTypeFontGenerator.generateFont(size36FreeTypeFontParameter);
        size36ComputerModernUnicodeFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        FreeTypeFontGenerator.FreeTypeFontParameter size48FreeTypeFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        size48FreeTypeFontParameter.size = 48;
        size48ComputerModernUnicodeFont = freeTypeFontGenerator.generateFont(size48FreeTypeFontParameter);
        size48ComputerModernUnicodeFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        FreeTypeFontGenerator.FreeTypeFontParameter size64FreeTypeFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        size64FreeTypeFontParameter.size = 64;
        size64ComputerModernUnicodeFont = freeTypeFontGenerator.generateFont(size64FreeTypeFontParameter);
        size64ComputerModernUnicodeFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        FreeTypeFontGenerator.FreeTypeFontParameter size72FreeTypeFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        size72FreeTypeFontParameter.size = 72;
        size72ComputerModernUnicodeFont = freeTypeFontGenerator.generateFont(size72FreeTypeFontParameter);
        size72ComputerModernUnicodeFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


        FreeTypeFontGenerator.FreeTypeFontParameter size144FreeTypeFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        size144FreeTypeFontParameter.size = 144;
        size144ComputerModernUnicodeFont = freeTypeFontGenerator.generateFont(size144FreeTypeFontParameter);
        size144ComputerModernUnicodeFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        // mainmenu screen
        // text
        // text
        // text buttons

        // images
        // images
        // buttons

        Texture singleplayerButtonTexture = new Texture(Gdx.files.internal("assets/images/singleplayerbutton.png"), true);
        singleplayerButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        singleplayerButton = new ImageButton(new TextureRegionDrawable(singleplayerButtonTexture));
        singleplayerButton.setPosition(560, 400);
        singleplayerButton.addListener(new ClickListener() {


            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                singleplayerButton.setTransform(true);
                singleplayerButton.setOrigin(Align.center);
                singleplayerButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
                MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("Test yourself and maybe get a spot on the leaderboards.", "mainMenuNavigationText");
            }


            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                singleplayerButton.setTransform(true);
                singleplayerButton.setOrigin(Align.center);
                singleplayerButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
                MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("", "mainMenuNavigationText");
            }

        });


        Texture multiplayerButtonTexture = new Texture(Gdx.files.internal("assets/images/multiplayerbutton.png"), true);
        multiplayerButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        multiplayerButton = new ImageButton(new TextureRegionDrawable(multiplayerButtonTexture));
        multiplayerButton.setPosition(977, 400);
        multiplayerButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {

            }


            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                multiplayerButton.setTransform(true);
                multiplayerButton.setOrigin(Align.center);
                multiplayerButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
                MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("Play against others and get bragging rights.", "mainMenuNavigationText");
            }


            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                multiplayerButton.setTransform(true);
                multiplayerButton.setOrigin(Align.center);
                multiplayerButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
                MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("", "mainMenuNavigationText");
            }
        });


        Texture statsButtonTexture = new Texture(Gdx.files.internal("assets/images/statsbutton.png"), true);
        statsButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        statsButton = new ImageButton(new TextureRegionDrawable(statsButtonTexture));
        statsButton.setPosition(560, 304);
        statsButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {

            }


            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                statsButton.setTransform(true);
                statsButton.setOrigin(Align.center);
                statsButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
                MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("View your stats, or someone else's if you feel like it.", "mainMenuNavigationText");
            }


            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                statsButton.setTransform(true);
                statsButton.setOrigin(Align.center);
                statsButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
                MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("", "mainMenuNavigationText");
            }
        });

        Texture settingsButtonTexture = new Texture(Gdx.files.internal("assets/images/settingsbutton.png"), true);
        settingsButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        settingsButton = new ImageButton(new TextureRegionDrawable(settingsButtonTexture));
        settingsButton.setPosition(867, 304);
        settingsButton.addListener(new ClickListener() {


            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                settingsButton.setTransform(true);
                settingsButton.setOrigin(Align.center);
                settingsButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
                MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("Change how the game behaves, looks, and feels.", "mainMenuNavigationText");
            }


            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                settingsButton.setTransform(true);
                settingsButton.setOrigin(Align.center);
                settingsButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
                MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("", "mainMenuNavigationText");

            }
        });


        Texture quitButtonTexture = new Texture(Gdx.files.internal("assets/images/quitbutton.png"), true);
        quitButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        quitButton = new ImageButton(new TextureRegionDrawable(quitButtonTexture));
        quitButton.setPosition(1175, 304);
        quitButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {

            }


            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                quitButton.setTransform(true);
                quitButton.setOrigin(Align.center);
                quitButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
                MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("Exit the game. Maybe that's enough math for today.", "mainMenuNavigationText");
            }


            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                quitButton.setTransform(true);
                quitButton.setOrigin(Align.center);
                quitButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
                MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("", "mainMenuNavigationText");

            }
        });


        // singleplayer screen
        // text
        // text
        // textbuttons

        // images
        // images
        base.setPosition(100, 800);
        // imagebuttons
        sendButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent inputEvent, float x, float y) {
                MathematicalBaseDefenders.game.sendProblem();
            }

        });


        // settings screen
        // text
        // text
        // textbuttons

        // images
        // images

        // imagebuttons
        Texture videoSettingsButtonTexture = new Texture(Gdx.files.internal("assets/images/videosettingsbutton.png"), true);
        videoSettingsButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        videoSettingsButton = new ImageButton(new TextureRegionDrawable(videoSettingsButtonTexture));
        videoSettingsButton.setPosition(-192, 1000);
        videoSettingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }


            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                videoSettingsButton.setTransform(true);
                videoSettingsButton.setOrigin(Align.center);
                videoSettingsButton.addAction(Actions.moveTo(-128, 1000, 0.1f));
                MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("Change how the game looks like.", "settingsNavigationText");
            }


            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                videoSettingsButton.setTransform(true);
                videoSettingsButton.setOrigin(Align.center);
                videoSettingsButton.addAction(Actions.moveTo(-192, 1000, 0.1f));
                MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("", "settingsNavigationText");

            }
        });

        Texture audioSettingsButtonTexture = new Texture(Gdx.files.internal("assets/images/audiosettingsbutton.png"), true);
        audioSettingsButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        audioSettingsButton = new ImageButton(new TextureRegionDrawable(audioSettingsButtonTexture));
        audioSettingsButton.setPosition(-192, 920);
        audioSettingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }


            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                audioSettingsButton.setTransform(true);
                audioSettingsButton.setOrigin(Align.center);
                audioSettingsButton.addAction(Actions.moveTo(-128, 920, 0.1f));
                MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("Change how the game sounds like.", "settingsNavigationText");
            }


            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                audioSettingsButton.setTransform(true);
                audioSettingsButton.setOrigin(Align.center);
                audioSettingsButton.addAction(Actions.moveTo(-192, 920, 0.1f));
                MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("", "settingsNavigationText");

            }
        });


        Texture onlineButtonTexture = new Texture(Gdx.files.internal("assets/images/onlinesettingsbutton.png"), true);
        onlineButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        onlineSettingsButton = new ImageButton(new TextureRegionDrawable(onlineButtonTexture));
        onlineSettingsButton.setPosition(-192, 840);
        onlineSettingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }


            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                onlineSettingsButton.setTransform(true);
                onlineSettingsButton.setOrigin(Align.center);
                onlineSettingsButton.addAction(Actions.moveTo(-128, 840, 0.1f));
                MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("Change your online presence in the game.", "settingsNavigationText");
            }


            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                onlineSettingsButton.setTransform(true);
                onlineSettingsButton.setOrigin(Align.center);
                onlineSettingsButton.addAction(Actions.moveTo(-192, 840, 0.1f));
                MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("", "settingsNavigationText");

            }
        });


        Texture settingsBackButtonTexture = new Texture(Gdx.files.internal("assets/images/backbutton.png"), true);
        settingsBackButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        settingsBackButton = new ImageButton(new TextureRegionDrawable(settingsBackButtonTexture));
        settingsBackButton.setPosition(-192, 200);
        settingsBackButton.addListener(new ClickListener() {


            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                settingsBackButton.setTransform(true);
                settingsBackButton.setOrigin(Align.center);
                settingsBackButton.addAction(Actions.moveTo(-128, 120, 0.1f));
                MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("Go back to the main menu.", "settingsNavigationText");
            }


            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                settingsBackButton.setTransform(true);
                settingsBackButton.setOrigin(Align.center);
                settingsBackButton.addAction(Actions.moveTo(-192, 120, 0.1f));
                MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("", "settingsNavigationText");

            }
        });


        // textfields


        usernameTextField = new TextField("", new TextField.TextFieldStyle(size36ComputerModernUnicodeFont, Color.BLACK, transparent1500By36Pixmap, transparent1500By36Pixmap, transparent1500By36Pixmap));
        usernameTextField.setPosition(500, 1000);
        usernameTextField.setWidth(1300);


        passwordTextField = new TextField("", new TextField.TextFieldStyle(size36ComputerModernUnicodeFont, Color.BLACK, transparent1500By36Pixmap, transparent1500By36Pixmap, transparent1500By36Pixmap));
        passwordTextField.setPosition(500, 920);
        passwordTextField.setWidth(1300);
        passwordTextField.setPasswordCharacter('*');
        passwordTextField.setPasswordMode(true);


        Texture loginButtonTexture = new Texture(Gdx.files.internal("assets/images/loginbutton.png"), true);
        loginButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        loginButton = new ImageButton(new TextureRegionDrawable(loginButtonTexture));
        loginButton.setPosition(316, 840);


        // game over screen
        Texture gameOverPlayAgainButtonTexture = new Texture(Gdx.files.internal("assets/images/gameoverplayagainbutton.png"), true);
        gameOverPlayAgainButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        gameOverPlayAgainButton = new ImageButton(new TextureRegionDrawable(gameOverPlayAgainButtonTexture));
        gameOverPlayAgainButton.setPosition(564, 400);
        gameOverPlayAgainButton.addListener(new ClickListener() {

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                gameOverPlayAgainButton.setTransform(true);
                gameOverPlayAgainButton.setOrigin(Align.center);
                gameOverPlayAgainButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }


            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                gameOverPlayAgainButton.setTransform(true);
                gameOverPlayAgainButton.setOrigin(Align.center);
                gameOverPlayAgainButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));

            }
        });

        Texture gameOverBackButtonTexture = new Texture(Gdx.files.internal("assets/images/gameoverbackbutton.png"), true);
        gameOverBackButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        gameOverBackButton = new ImageButton(new TextureRegionDrawable(gameOverBackButtonTexture));
        gameOverBackButton.setPosition(1010, 400);
        gameOverBackButton.addListener(new ClickListener() {

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                gameOverBackButton.setTransform(true);
                gameOverBackButton.setOrigin(Align.center);
                gameOverBackButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }


            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                gameOverBackButton.setTransform(true);
                gameOverBackButton.setOrigin(Align.center);
                gameOverBackButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));

            }
        });


        // variables
        gameAllowedToRender = true;


        // images

        // atlas regions
        for (int i = 0; i < 24; i++) {
            AtlasRegion atlasRegion = specialComputerModernFontTextTextureAtlas.findRegion(specialComputerModernFontTextTextureAtlasRegionNames[i]);
            specialComputerModernFontTextTextureAtlasRegions[i] = atlasRegion;
            specialComputerModernFontTextTextureAtlasRegionsAsPixmaps[i] = Utilities.convertTextureAtlasRegionToPixmap(atlasRegion);
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 24; j++) {
                AtlasRegion atlasRegion = tilesTextureAtlas.findRegion(tilesTextureAtlasRegionNames[i][j]);
                tilesTextureAtlasRegions[i][j] = atlasRegion;
                tilesTextureAtlasRegionsAsPixmaps[i][j] = Utilities.convertTextureAtlasRegionToPixmap(atlasRegion);
            }
        }


        // networking
        URI uri = URI.create("http://localhost:8080");
        IO.Options options = IO.Options.builder().build();

        MathematicalBaseDefenders.networking.socket = IO.socket(uri, options);
        MathematicalBaseDefenders.networking.socket.connect();


        MathematicalBaseDefenders.networking.configureSocketEvents();


        Gdx.app.log(Utilities.getRealLifeTime() + " INFO", "Initialization has finished and took " + Utilities.convertMillisecondsToStringifiedTime((TimeUtils.millis() - startTime)) + "!");
    }


    // Methods


    // INPUT


    /**
     * Gets the mouse position.
     *
     * @return The mouse position expressed as (x, y)
     */

    public static float getMouseXPosition() {
        return MathematicalBaseDefenders.core.currentStageToDrawOn.getViewport().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).x;
    }

    public static float getMouseYPosition() {
        return MathematicalBaseDefenders.core.currentStageToDrawOn.getViewport().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).y;
    }


    // LOGIC


    public void computeUpdate() {
        // Gdx.graphics.setTitle("Mathematical Base Defense | (" +  Core.getMouseXPosition() + ", " + Core.getMouseYPosition() + ") | " + Gdx.graphics.getFramesPerSecond() + " FPS | " + updatesPerSecondToLog + " UPS " + TickLoop.ticksPerSecondToLog + " TPS (" + TickLoop.timeTookForTickToCompute  + "ms) | " + Gdx.graphics.getWidth() + "x" + Gdx.graphics.getHeight());


        // Global

        // Listen for a click
        if (Gdx.input.justTouched()) {
            Log.logInfoMessage("Mouse clicked at (" + Core.getMouseXPosition() + ", " + Core.getMouseYPosition() + ")");
        }

        // Listen for a key press
        // F12 -> Turn on/off stats panel visibility

        if (Gdx.input.isKeyPressed(Input.Keys.F12)) {
            Log.logInfoMessage("F12 key pressed. Toggling stats panel visibility...");
            MathematicalBaseDefenders.utilities.toggleStatsVisibility();

        }


        // Singleplayer Screen

        if (MathematicalBaseDefenders.core.currentScreen == GameScreen.SINGLEPLAYER_SCREEN) {

            // Esc -> End game

            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                MathematicalBaseDefenders.game.currentBaseHealth = 0;
            }


        }


        // Process other logic

        for (int i = 0; i < ToastNotification.getActiveToastNotifications().size(); i++) {
            ToastNotification.getActiveToastNotifications().get(i).setAge(ToastNotification.getActiveToastNotifications().get(i).getAge() + 1);
            if (ToastNotification.getActiveToastNotifications().get(i).getAge() >= 600) {
                ToastNotification.getActiveToastNotifications().get(i).getBody().addAction(Actions.fadeOut(0.2f));
                ToastNotification.getActiveToastNotifications().remove(i);
            }
        }


    }


    public void computeTick() {

        // Increment counter
        ticksComputedSinceStart++;


        // Singleplayer Screen
        if (currentScreen == GameScreen.SINGLEPLAYER_SCREEN && MathematicalBaseDefenders.game.singleplayerGameRunning) {
            if (Math.random() > 0.99) {
                new Enemy();
            }


            Iterator<Map.Entry<Long, Enemy>> iterator = MathematicalBaseDefenders.game.activeEnemies.entrySet().iterator();

            List<Long> toRemove = new ArrayList<>();

            // Move all enemies
            while (iterator.hasNext()) {

                Map.Entry<Long, Enemy> entry = iterator.next();
                entry.getValue().move();
                if (entry.getValue().getXPosition() < 100) {
                    toRemove.add(entry.getKey());
                    MathematicalBaseDefenders.game.currentBaseHealth -= entry.getValue().getAttack();
                }


            }


            if (MathematicalBaseDefenders.game.currentBaseHealth <= 0) {
                MathematicalBaseDefenders.game.endSingleplayerGame();
            }

            for (int i = 0; i < toRemove.size(); i++) {
                MathematicalBaseDefenders.game.activeEnemies.remove(toRemove.get(i));
                MathematicalBaseDefenders.game.activeEnemiesAsTextures.remove(toRemove.get(i));
            }

            MathematicalBaseDefenders.game.timeElapsedInMilliseconds = ((TimeUtils.millis() - MathematicalBaseDefenders.game.gameStartTimeInMilliseconds) / 1e3d);
        }
    }

    public void processGlobalLogic() {
        if (toastNotificationQueue.size() > 0) {
            ToastNotification toastNotification = new ToastNotification(toastNotificationQueue.get(0));
            toastNotificationQueue.remove(0);
        }
    }

    public Long[] getEnemyNumbersToRender(Set<Long> keySet) {
        try {
            return keySet.toArray(new Long[0]);
        } catch (Exception exception) {
            Log.logWarningMessage("getEnemyNumbersToRender() produced an exception.");
            return getEnemyNumbersToRender(keySet);
        }

    }

    public BitmapFont createNewComputerModernFont(int size) {
        FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        freeTypeFontParameter.size = size;
        BitmapFont bitmapFont = freeTypeFontGenerator.generateFont(freeTypeFontParameter);
        bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        return bitmapFont;
    }


}
