package com.mathematicalbasedefenders.mathematicalbasedefenders.core;

import com.badlogic.gdx.Gdx;

public class Log {

    public static void logDebugMessage(String message) {
        Gdx.app.log(Utilities.getRealLifeTime() + " DEBUG", message);
    }

    public static void logInfoMessage(String message) {
        Gdx.app.log(Utilities.getRealLifeTime() + " INFO", message);
    }

    public static void logWarningMessage(String message) {
        Gdx.app.log(Utilities.getRealLifeTime() + " WARNING", message);
    }

    public static void logErrorMessage(String message) {
        Gdx.app.log(Utilities.getRealLifeTime() + " ERROR", message);
    }


}
