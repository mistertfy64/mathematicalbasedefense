package com.mathematicalbasedefenders.mathematicalbasedefenders.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.mathematicalbasedefenders.mathematicalbasedefenders.MathematicalBaseDefenders;
import com.mathematicalbasedefenders.mathematicalbasedefenders.core.Core;
import com.mathematicalbasedefenders.mathematicalbasedefenders.core.Evaluator;
import com.mathematicalbasedefenders.mathematicalbasedefenders.net.Networking;
import org.mariuszgromada.math.mxparser.Argument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Game {


    public static Tile[][] tiles = new Tile[7][7];

    public static ArrayList<String> currentProblemAsArrayList = new ArrayList<>();
    public static ArrayList<Tile> currentTilesInProblem = new ArrayList<>();

    public static Map<Long, Enemy> activeEnemies = new HashMap<>();
    public static Map<Long, Texture> activeEnemiesAsTextures = new HashMap<>();

    public long enemiesSpawned, enemiesKilled;
    public float score, timeOfLastEnemyKill;
    public double gameStartTimeInMilliseconds, timeElapsedInMilliseconds, finalTimeElapsedInMilliseconds;
    public Argument valueOfVariableA, valueOfVariableB, valueOfVariableC, valueOfVariableD, valueOfVariableN, valueOfVariableX, valueOfVariableY, valueOfVariableZ;
    public int currentBaseHealth, initialBaseHealth, currentCombo;
    public int[] lastForceSelectedPosition = new int[2];
    public static double actionsPerMinute = 0;
    public static int actionsMadeInGame = 0;


    public boolean singleplayerGameRunning = false, singleplayerGameIsOver = false;

    public void startSingleplayerGame() {

        score = 0;

        enemiesSpawned = 0;
        enemiesKilled = 0;

        currentBaseHealth = 10;
        initialBaseHealth = 10;

        currentCombo = -1;

        timeOfLastEnemyKill = TimeUtils.millis() - 20000L; // find a better way to do this lmao

        valueOfVariableA = null;
        valueOfVariableB = null;
        valueOfVariableC = null;
        valueOfVariableD = null;
        valueOfVariableN = null;
        valueOfVariableX = null;
        valueOfVariableY = null;
        valueOfVariableZ = null;

        activeEnemies.clear();
        activeEnemiesAsTextures.clear();
        currentProblemAsArrayList.clear();
        currentTilesInProblem.clear();


        gameStartTimeInMilliseconds = TimeUtils.millis();


        actionsMadeInGame = 0;
        actionsPerMinute = 0;


        singleplayerGameRunning = true;
    }

    public void stopSingleplayerGame() {
        singleplayerGameRunning = false;
    }

    public void endSingleplayerGame() {

        activeEnemies.clear();
        activeEnemiesAsTextures.clear();
        currentProblemAsArrayList.clear();
        currentTilesInProblem.clear();

        finalTimeElapsedInMilliseconds = ((TimeUtils.millis() - gameStartTimeInMilliseconds));


        singleplayerGameRunning = false;
        singleplayerGameIsOver = true;
        // send data to server

        if (!Networking.socketID.equals("") && !Networking.currentLoggedInUser.equals("")) {
            MathematicalBaseDefenders.networking.socket.emit("scoreSubmission", Double.toString(score), Networking.currentLoggedInUser, Networking.userIDOfCurrentlyLoggedInUser, Networking.socketID);
        }


    }


    /**
     * Converts an array full of terms to something to show to the player.
     *
     * @param problemArray The problem array.
     * @return A string.
     */
    public String convertProblemArrayToString(ArrayList<String> problemArray, boolean returnBeautifulStrings) {
        String toReturn = "";
        for (String string : problemArray) {
            for (int j = 0; j < Tile.termsAsStrings.length; j++) {
                if (Tile.termsAsStrings[j].equals(string)) {
                    if (returnBeautifulStrings) {
                        toReturn += Tile.termsAsBetterStrings[j];
                    } else {
                        toReturn += Tile.termsAsStrings[j];
                    }

                }
            }
        }
        return toReturn;
    }

    /**
     * Sends the current problem.
     */
    public void sendProblem() {
        String problem = convertProblemArrayToString(Game.currentProblemAsArrayList, false);
        if (Evaluator.evaluateProblem(problem)[0]) {
            // problem killed enemy or variable assignment is complete
            for (int i = 0; i < Game.currentTilesInProblem.size(); i++) {
                int c = Game.currentTilesInProblem.get(i).getColumn(), r = Game.currentTilesInProblem.get(i).getRow();
                Game.tiles[c][r] = null;
                Game.tiles[c][r] = new Tile(c, r, MathematicalBaseDefenders.generator.generateTileTerm(), false);
            }
            Game.currentTilesInProblem.clear();
            Game.currentProblemAsArrayList.clear();
        }
    }

    /**
     * Selects one tile with the specified term.
     *
     * @param term
     */
    public void selectTerm(Core.Term term) {
        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < 7; x++) {
                if (Game.tiles[x][y].getTerm() == term && !Game.tiles[x][y].selected) {
                    Tile.forceSelect(x, y, true);
                    lastForceSelectedPosition[0] = x;
                    lastForceSelectedPosition[1] = y;
                    return;
                }
            }
        }
    }

    public void removeLatestSelectedPosition() {
        if (Game.currentTilesInProblem.size() > 0) {
            int size = Game.currentTilesInProblem.size();
            Tile.forceSelect(Game.currentTilesInProblem.get(size - 1).getColumn(), Game.currentTilesInProblem.get(size - 1).getRow(), true);
        }

    }

}
