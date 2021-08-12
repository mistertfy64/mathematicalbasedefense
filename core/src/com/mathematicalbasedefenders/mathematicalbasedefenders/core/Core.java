package com.mathematicalbasedefenders.mathematicalbasedefenders.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
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
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mathematicalbasedefenders.mathematicalbasedefenders.MathematicalBaseDefenders;
import com.mathematicalbasedefenders.mathematicalbasedefenders.game.Enemy;
import com.mathematicalbasedefenders.mathematicalbasedefenders.game.Game;
import com.mathematicalbasedefenders.mathematicalbasedefenders.net.Authentication;
import com.mathematicalbasedefenders.mathematicalbasedefenders.ui.ToastNotification;
import com.mathematicalbasedefenders.mathematicalbasedefenders.ui.screens.MainMenuScreen;
import com.mathematicalbasedefenders.mathematicalbasedefenders.ui.screens.SettingsScreen;
import com.mathematicalbasedefenders.mathematicalbasedefenders.ui.screens.SingleplayerGameScreen;
import io.socket.client.IO;
import org.apache.commons.lang3.SystemUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.URI;
import java.util.*;

import static com.mathematicalbasedefenders.mathematicalbasedefenders.MathematicalBaseDefenders.core;


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

    public Map<Integer, BitmapFont> nonDefaultSizeBitmapFonts = new HashMap<>();


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
    public ImageButton inputSettingsButton;
    public ImageButton settingsBackButton;

    public ImageButton loginButton;

    public TextField[] inputTextFields = new TextField[23];

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

    public enum OperatingSystem {
        WINDOWS, MACOS, LINUX
    }

    public OperatingSystem operatingSystem = null;
    public String workingDirectory = null;

    public String configurationAsJSONString = "";
    public JSONObject configurationAsJSONObject;


    public Drawable transparent150By36Pixmap = new TextureRegionDrawable(new Texture(new Pixmap(150, 36, Pixmap.Format.RGBA8888)));
    public Drawable transparent1500By36Pixmap = new TextureRegionDrawable(new Texture(new Pixmap(1500, 36, Pixmap.Format.RGBA8888)));

    public static FileHandle workingDirectoryFileHandle;

    public static FileHandle configurationFileFileHandle;


    // other pointless stuff
    public String[] keybindKeys = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "additionSign", "subtractionSign", "multiplicationSign", "divisionSign", "equalsSign", "a", "b", "c", "d", "n", "x", "y", "z"};
    public int[] secondaryKeybinds = {144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 157, 156, 155, 154, 160};

    public MathematicalBaseDefenders mathematicalBaseDefenders;


    public GameScreen currentScreen = GameScreen.MAIN_MENU_SCREEN;


    /**
     * Initializes the game.
     */
    public void initializeGame(MathematicalBaseDefenders mathematicalBaseDefendersInstance) {
        mathematicalBaseDefenders = mathematicalBaseDefendersInstance;

        double startTime = TimeUtils.millis();
        Log.logInfoMessage("Initializing game...");

        // get information about operating system first
        if (SystemUtils.IS_OS_WINDOWS) {
            operatingSystem = OperatingSystem.WINDOWS;
            workingDirectory = System.getenv("LOCALAPPDATA");
            workingDirectory += "\\MathematicalBaseDefenders";
        } else if (SystemUtils.IS_OS_MAC) {
            operatingSystem = OperatingSystem.MACOS;
            workingDirectory = System.getProperty("user.home");
            workingDirectory += "\\Library\\Application Support";
            workingDirectory += "\\MathematicalBaseDefenders";
        } else if (SystemUtils.IS_OS_LINUX) {
            operatingSystem = OperatingSystem.LINUX;
            workingDirectory = System.getProperty("user.home");
            workingDirectory += "\\.MathematicalBaseDefenders";

        }

        if (operatingSystem == null) {
            Log.logInfoMessage("Unsupported Operating System. Be careful when playing.");
        }

        // file io

        workingDirectoryFileHandle = Gdx.files.absolute(workingDirectory);

        configurationFileFileHandle = Gdx.files.absolute(workingDirectory + "\\configuration.txt");

        File configurationFile = new File("workingDirectory" + "\\configuration.txt");
        if (!configurationFile.exists()) {
            configurationFileFileHandle.writeString("{\n\t\"warnings\": {\n\t\t\"warning1\": \"DO NOT SHARE THIS FILE. IT CONTAINS YOUR ACCOUNT'S PASSWORD\",\n\t},\t\n\t\"video\": {\n\t},\n\t\"audio\": {\n\t\t\"soundOn\": false,\n\t},\n\t\"inputs\":{\n\t\t\"zero\": 41,\n\t\t\"one\": 38,\n\t\t\"two\": 39,\n\t\t\"three\": 40,\n\t\t\"four\": 49,\n\t\t\"five\": 37,\n\t\t\"six\": 43,\n\t\t\"seven\": 14,\n\t\t\"eight\": 15,\n\t\t\"nine\": 16,\n\t\t\"additionSign\": 76,\n\t\t\"subtractionSign\": 74,\n\t\t\"multiplicationSign\": 44,\n\t\t\"divisionSign\": 7,\n\t\t\"equalsSign\": 36,\n\t\t\"a\": 10,\n\t\t\"b\": 11,\n\t\t\"c\": 33,\n\t\t\"d\": 46,\n\t\t\"n\": 32,\n\t\t\"x\": 34,\n\t\t\"y\": 31,\n\t\t\"z\": 50,\n\t\t\"submitProblem\": 62,\n\t\t\"removeCharacter\": 4,\n\t},\n\t\"online\": {\n\t\t\"username\": \"\",\n\t\t\"password\": \"\",\n\t}\n}", false);
        }

        configurationAsJSONString = new FileHandle(core.workingDirectory + "\\configuration.txt").readString();


        try {
            core.configurationAsJSONObject = new JSONObject(core.configurationAsJSONString);
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }


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
        multiplayerButton = new

                ImageButton(new TextureRegionDrawable(multiplayerButtonTexture));
        multiplayerButton.setPosition(977, 400);
        multiplayerButton.addListener(new

                                              ClickListener() {

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
        statsButton = new

                ImageButton(new TextureRegionDrawable(statsButtonTexture));
        statsButton.setPosition(560, 304);
        statsButton.addListener(new

                                        ClickListener() {

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
        settingsButton = new

                ImageButton(new TextureRegionDrawable(settingsButtonTexture));
        settingsButton.setPosition(867, 304);
        settingsButton.addListener(new

                                           ClickListener() {

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
        quitButton = new

                ImageButton(new TextureRegionDrawable(quitButtonTexture));
        quitButton.setPosition(1175, 304);
        quitButton.addListener(new

                                       ClickListener() {

                                           @Override
                                           public void clicked(InputEvent event, float x, float y) {
                                               System.exit(0);
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
        sendButton.addListener(new

                                       ClickListener() {

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
        videoSettingsButton = new

                ImageButton(new TextureRegionDrawable(videoSettingsButtonTexture));
        videoSettingsButton.setPosition(-192, 1000);
        videoSettingsButton.addListener(new

                                                ClickListener() {
                                                    @Override
                                                    public void clicked(InputEvent event, float x, float y) {
                                                        SettingsScreen.currentSettingsSubscreen = "videoSettingsScreen";
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
        audioSettingsButton = new

                ImageButton(new TextureRegionDrawable(audioSettingsButtonTexture));
        audioSettingsButton.setPosition(-192, 920);
        audioSettingsButton.addListener(new

                                                ClickListener() {
                                                    @Override
                                                    public void clicked(InputEvent event, float x, float y) {
                                                        SettingsScreen.currentSettingsSubscreen = "audioSettingsScreen";
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

        Texture inputButtonTexture = new Texture(Gdx.files.internal("assets/images/inputsettingsbutton.png"), true);
        inputButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        inputSettingsButton = new

                ImageButton(new TextureRegionDrawable(inputButtonTexture));
        inputSettingsButton.setPosition(-192, 840);
        inputSettingsButton.addListener(new

                                                ClickListener() {
                                                    @Override
                                                    public void clicked(InputEvent event, float x, float y) {
                                                        SettingsScreen.currentSettingsSubscreen = "inputSettingsScreen";
                                                    }


                                                    @Override
                                                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                                                        inputSettingsButton.setTransform(true);
                                                        inputSettingsButton.setOrigin(Align.center);
                                                        inputSettingsButton.addAction(Actions.moveTo(-128, 840, 0.1f));
                                                        MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("Change how you control the game.", "settingsNavigationText");
                                                    }


                                                    @Override
                                                    public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                                                        inputSettingsButton.setTransform(true);
                                                        inputSettingsButton.setOrigin(Align.center);
                                                        inputSettingsButton.addAction(Actions.moveTo(-192, 840, 0.1f));
                                                        MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("", "settingsNavigationText");

                                                    }
                                                });

        Texture onlineButtonTexture = new Texture(Gdx.files.internal("assets/images/onlinesettingsbutton.png"), true);
        onlineButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        onlineSettingsButton = new

                ImageButton(new TextureRegionDrawable(onlineButtonTexture));
        onlineSettingsButton.setPosition(-192, 760);
        onlineSettingsButton.addListener(new

                                                 ClickListener() {
                                                     @Override
                                                     public void clicked(InputEvent event, float x, float y) {
                                                         SettingsScreen.currentSettingsSubscreen = "onlineSettingsScreen";
                                                     }


                                                     @Override
                                                     public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                                                         onlineSettingsButton.setTransform(true);
                                                         onlineSettingsButton.setOrigin(Align.center);
                                                         onlineSettingsButton.addAction(Actions.moveTo(-128, 760, 0.1f));
                                                         MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("Change your online presence in the game.", "settingsNavigationText");
                                                     }


                                                     @Override
                                                     public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                                                         onlineSettingsButton.setTransform(true);
                                                         onlineSettingsButton.setOrigin(Align.center);
                                                         onlineSettingsButton.addAction(Actions.moveTo(-192, 760, 0.1f));
                                                         MathematicalBaseDefenders.renderer.changeTextOnCurrentTextStage("", "settingsNavigationText");

                                                     }
                                                 });


        Texture settingsBackButtonTexture = new Texture(Gdx.files.internal("assets/images/backbutton.png"), true);
        settingsBackButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        settingsBackButton = new

                ImageButton(new TextureRegionDrawable(settingsBackButtonTexture));
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

            @Override
            public void clicked(InputEvent event, float x, float y) {

                // System.out.println("Clicked!");

                core.videoSettingsButton.addAction(Actions.moveTo(-492, 1000, 0.2f));
                core.audioSettingsButton.addAction(Actions.moveTo(-492, 920, 0.2f));
                core.inputSettingsButton.addAction(Actions.moveTo(-492, 840, 0.2f));
                core.onlineSettingsButton.addAction(Actions.moveTo(-492, 760, 0.2f));
                core.settingsBackButton.addAction(Actions.moveTo(-492, 120, 0.2f));

                try {
                    core.configurationAsJSONObject = new JSONObject(core.configurationAsJSONString);
                    Core.configurationFileFileHandle.writeString(core.configurationAsJSONString, false);
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }

                core.toastNotificationQueue.add("Successfully saved settings!");

                com.badlogic.gdx.utils.Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        mathematicalBaseDefenders.setScreen(new MainMenuScreen(mathematicalBaseDefenders));
                        MathematicalBaseDefenders.core.currentScreen = Core.GameScreen.MAIN_MENU_SCREEN;
                    }
                }, 0.3f);

            }
        });


        // textfields


        usernameTextField = new

                TextField("", new TextField.TextFieldStyle(size36ComputerModernUnicodeFont, Color.BLACK, transparent1500By36Pixmap, transparent1500By36Pixmap, transparent1500By36Pixmap));
        usernameTextField.setPosition(500, 1000);
        usernameTextField.setWidth(1300);


        passwordTextField = new

                TextField("", new TextField.TextFieldStyle(size36ComputerModernUnicodeFont, Color.BLACK, transparent1500By36Pixmap, transparent1500By36Pixmap, transparent1500By36Pixmap));
        passwordTextField.setPosition(500, 920);
        passwordTextField.setWidth(1300);
        passwordTextField.setPasswordCharacter('*');
        passwordTextField.setPasswordMode(true);


        for (
                int i = 0;
                i < 23; i++) {
            inputTextFields[i] = new TextField(Integer.toString(i), new TextField.TextFieldStyle(size36ComputerModernUnicodeFont, Color.BLACK, transparent150By36Pixmap, transparent150By36Pixmap, transparent150By36Pixmap));
            inputTextFields[i].setWidth(150);
            if (i > 18) {
                inputTextFields[i].setPosition(1700, 1000 - (80 * (i - 19)));
            } else if (i > 12) {
                inputTextFields[i].setPosition(1300, 1000 - (80 * (i - 13)));
            } else if (i > 6) {
                inputTextFields[i].setPosition(900, 1000 - (80 * (i - 7)));
            } else {
                inputTextFields[i].setPosition(500, 1000 - (80 * i));
            }

        }


        Texture loginButtonTexture = new Texture(Gdx.files.internal("assets/images/loginbutton.png"), true);
        loginButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        loginButton = new ImageButton(new TextureRegionDrawable(loginButtonTexture));
        loginButton.setPosition(316, 840);
        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Authentication.loginWithCredentials(usernameTextField.getText(), passwordTextField.getText());
            }
        });

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

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mathematicalBaseDefenders.setScreen(new SingleplayerGameScreen(mathematicalBaseDefenders));
                MathematicalBaseDefenders.core.currentScreen = Core.GameScreen.SINGLEPLAYER_SCREEN;
                MathematicalBaseDefenders.game.startSingleplayerGame();
            }

        });

        Texture gameOverBackButtonTexture = new Texture(Gdx.files.internal("assets/images/gameoverbackbutton.png"), true);
        gameOverBackButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        gameOverBackButton = new

                ImageButton(new TextureRegionDrawable(gameOverBackButtonTexture));
        gameOverBackButton.setPosition(1010, 400);
        gameOverBackButton.addListener(new

                                               ClickListener() {

                                                   @Override
                                                   public void enter(InputEvent event, float x, float y,
                                                                     int pointer, Actor fromActor) {
                                                       gameOverBackButton.setTransform(true);
                                                       gameOverBackButton.setOrigin(Align.center);
                                                       gameOverBackButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
                                                   }

                                                   @Override
                                                   public void exit(InputEvent event, float x, float y,
                                                                    int pointer, Actor fromActor) {
                                                       gameOverBackButton.setTransform(true);
                                                       gameOverBackButton.setOrigin(Align.center);
                                                       gameOverBackButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
                                                   }

                                                   @Override
                                                   public void clicked(InputEvent event, float x, float y) {
                                                       mathematicalBaseDefenders.setScreen(new MainMenuScreen(mathematicalBaseDefenders));
                                                       MathematicalBaseDefenders.core.currentScreen = Core.GameScreen.MAIN_MENU_SCREEN;
                                                   }
                                               });


        // variables
        gameAllowedToRender = true;


        // images

        // atlas regions
        for (
                int i = 0;
                i < 24; i++) {
            AtlasRegion atlasRegion = specialComputerModernFontTextTextureAtlas.findRegion(specialComputerModernFontTextTextureAtlasRegionNames[i]);
            specialComputerModernFontTextTextureAtlasRegions[i] = atlasRegion;
            specialComputerModernFontTextTextureAtlasRegionsAsPixmaps[i] = Utilities.convertTextureAtlasRegionToPixmap(atlasRegion);
        }

        for (
                int i = 0;
                i < 2; i++) {
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
        updatesComputedSinceStart++;


        // Listen for a click
        if (Gdx.input.justTouched()) {
            // Log.logInfoMessage("Mouse clicked at (" + Core.getMouseXPosition() + ", " + Core.getMouseYPosition() + ")");
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

        // Global

        // Increment counter
        ticksComputedSinceStart++;

        Game.actionsPerMinute = (Game.actionsMadeInGame / (MathematicalBaseDefenders.game.timeElapsedInMilliseconds / 1000) * 60);


        // Singleplayer Screen
        if (currentScreen == GameScreen.SINGLEPLAYER_SCREEN && MathematicalBaseDefenders.game.singleplayerGameRunning) {

            // combo check

            if (TimeUtils.millis() - MathematicalBaseDefenders.game.timeOfLastEnemyKill > 5000L && MathematicalBaseDefenders.game.currentCombo != -1) {
                MathematicalBaseDefenders.game.currentCombo = -1;
            }

            // enemy spawn

            if (Game.activeEnemies.size() == 0 || Math.random() >= 0.995) {
                new Enemy();
            }


            Iterator<Map.Entry<Long, Enemy>> iterator = Game.activeEnemies.entrySet().iterator();

            List<Long> toRemove = new ArrayList<>();

            // Move all enemies
            while (iterator.hasNext()) {

                Map.Entry<Long, Enemy> entry = iterator.next();
                entry.getValue().move(Math.max((float) MathematicalBaseDefenders.game.timeElapsedInMilliseconds, 120000f) / 120000f);
                if (entry.getValue().getXPosition() < 100) {
                    toRemove.add(entry.getKey());
                    MathematicalBaseDefenders.game.currentBaseHealth -= entry.getValue().getAttack();
                }


            }


            if (MathematicalBaseDefenders.game.currentBaseHealth <= 0) {
                MathematicalBaseDefenders.game.endSingleplayerGame();
            }

            for (Long aLong : toRemove) {
                Game.activeEnemies.remove(aLong);
                Game.activeEnemiesAsTextures.remove(aLong);
            }

            MathematicalBaseDefenders.game.timeElapsedInMilliseconds = ((TimeUtils.millis() - MathematicalBaseDefenders.game.gameStartTimeInMilliseconds));
        }
    }

    public void processGlobalLogic() {
        if (toastNotificationQueue.size() > 0) {
            new ToastNotification(toastNotificationQueue.get(0));
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

    public BitmapFont createOrGetNewComputerModernFont(int size) {
        if (nonDefaultSizeBitmapFonts.containsKey(size)) {
            return nonDefaultSizeBitmapFonts.get(size);
        }
        FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        freeTypeFontParameter.size = size;
        BitmapFont bitmapFont = freeTypeFontGenerator.generateFont(freeTypeFontParameter);
        bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        nonDefaultSizeBitmapFonts.put(size, bitmapFont);
        return bitmapFont;
    }


}
