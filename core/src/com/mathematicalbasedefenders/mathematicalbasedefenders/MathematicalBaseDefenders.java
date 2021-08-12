package com.mathematicalbasedefenders.mathematicalbasedefenders;

import com.badlogic.gdx.Game;
import com.mathematicalbasedefenders.mathematicalbasedefenders.core.*;
import com.mathematicalbasedefenders.mathematicalbasedefenders.net.Networking;
import com.mathematicalbasedefenders.mathematicalbasedefenders.ui.screens.MainMenuScreen;


public class MathematicalBaseDefenders extends Game {

    public static Core core;
    public static com.mathematicalbasedefenders.mathematicalbasedefenders.game.Game game;

    public static Renderer renderer;
    public static Utilities utilities;
    public static Generator generator;
    public static Networking networking;


    public UpdateLoop updateLoop;
    public TickLoop tickLoop;

    public MainMenuScreen mainMenuScreen;


    public void create() {

        renderer = new Renderer();
        utilities = new Utilities();
        generator = new Generator();
        networking = new Networking();

        core = new Core();
        core.initializeGame(this);

        game = new com.mathematicalbasedefenders.mathematicalbasedefenders.game.Game();


        //update thread
        updateLoop = new UpdateLoop();
        Thread updateLoopThread = new Thread(updateLoop);
        updateLoopThread.start();

        // loop thread
        tickLoop = new TickLoop();
        Thread tickLoopThread = new Thread(tickLoop);
        tickLoopThread.start();


        this.setScreen(new MainMenuScreen(this));

    }

    public void render() {
        super.render();
    }

    public void dispose() {
        if (core.currentTextStageToDrawOn != null) {
            core.currentTextStageToDrawOn.dispose();
        }

        if (core.currentStageToDrawOn != null) {
            core.currentStageToDrawOn.dispose();
        }


        if (core.currentSpriteBatchToDrawOn != null) {
            core.currentSpriteBatchToDrawOn.dispose();
        }

        if (core.currentProblemSpecialComputerModernFontStageToDrawOn != null) {
            core.currentProblemSpecialComputerModernFontStageToDrawOn.dispose();
        }

        if (core.currentSpecialComputerModernFontStageToDrawOn != null) {
            core.currentSpecialComputerModernFontStageToDrawOn.dispose();
        }

        if (core.globalStage != null) {
            core.globalStage.dispose();
        }

        System.exit(0);


    }


}
